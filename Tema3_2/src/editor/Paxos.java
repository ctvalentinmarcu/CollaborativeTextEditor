/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vali
 */
public class Paxos implements CommunicationProtocol{
    
    int PEERS;
    int ID;
    GUI gui;
    String text; // the text that is in the GUI's Textbox
    
    
    ArrayList<Message> temp_q;
    //ArrayList<Message> propose_q;
    ConcurrentLinkedQueue<Message> propose_q;
    Communication comm;
    
    int clock;
    int priority;
    int responses;
    int ok_responses;
    int max_ts;
    int tag;
    
    int wait = 0;
    int max_wait = 100;
    
    boolean phase1 = false;
    boolean phase2 = false;
    Message currentMessage;
    Message max_accepted;
    int leaderID = 0;
    
    boolean busy=false;
    
    //ArrayList<ObjectOutputStream> outputstreams;
    //ArrayList<ObjectInputStream> inputstreams;
    
    public Paxos(Communication comm, int id, int peers, GUI gui){
        this.ID = id;
        this.PEERS = peers;
        this.gui = gui;       
        
        this.temp_q = new ArrayList<Message>(1000);
        //this.propose_q = new ArrayList<Message>();
        this.propose_q = new ConcurrentLinkedQueue<Message>();
        this.clock = 0;
        this.wait = 0;
        this.priority = 0;
        this.tag = 0; //this.ID * 1000;
        
        this.comm = comm;
        this.text = "";
        this.busy = false;
        this.currentMessage = new Message();
        this.max_accepted = new Message();
        this.max_accepted.tag = 0;
    }
    
    public synchronized void submitCommandFromGUI(String command, char c, int position){
        Message message = new Message("propose", this.tag, this.ID, command, c, position);
        //if(this.busy) return;
        this.propose_q.add(message);
    }
    
    
    //
    public synchronized void processMessage(Message message){
        System.out.println("Paxos: received command "+message.command+" from GUI (via processData), trying to propose it....");
        //this.gui.updateStatus(command);
        
        //Message message = new Message("propose", this.tag,  this.ID, command, c, position);
        //if there is only one site, just execute te message
        if(this.PEERS == 1) { this.executeRequest(message); return; }
              
        this.tag++;
       
        //set responses and max_ts to 0, send propose and wait revise messages from all peers
        this.max_ts = 0; 
        this.responses = 0;
        this.ok_responses = 0;
        
        // if I am leader, start phase 1 (prepare)
        if(this.isLeader()){
            this.sendPrepare(message);
            return;
        }
        //else{ // else, forward the message to leader
        message.source = this.ID;
        message.destination = this.leaderID;
        message.type = "propose";
        
        this.comm.submitMessageFromProtocol(message);
        //}
       
        //this.gui.updateStatus("sent propose to all peers, ts="+this.clock);
       // System.out.println("Paxos: processsing message with tag = "+message.tag+" and ts = "+message.ts);
        //this.updateBusy(true);

    }
    public synchronized void submitCommandFromPeer(Message message){
        System.out.println("received command "+message.type+" from Peer; position="+message.position);
                
        
        if(message.type.endsWith("propose")){
            if(!isLeader()) return; // only the leader receives propose messages from other sites
            //this.sendPrepare(message);
            this.propose_q.add(message);
            return;
        }
        
        // ==== PHASE 1 ====
        
        if(message.type.endsWith("prepare")){
            // send promise anyway, eventually with a negative answer
            this.sendPromise(message);
            return;
        }
        
                
        if(message.type.endsWith("promise")){
            this.responses++;
            if(message.tag >= 0){
                this.ok_responses++;
                if(this.max_ts < message.tag){
                    this.max_ts = message.tag;
                    this.currentMessage.c = message.c;
                    this.currentMessage.command = message.command;
                    this.currentMessage.position = message.position;
                    this.currentMessage.tag = message.tag;
                }               
            }
            
            // when I receive all responses, check if I can move forward
            if(this.responses == this.PEERS-1){
                // if not a majority of ok, abort this phase and try again from the beginning
                if(this.ok_responses < this.PEERS/2){
                    this.phase1 = false;
                    this.phase2 = false;
                    this.updateBusy(false); 
                    //Message m = this.propose_q.peek();
                    //this.processMessage(m);
                }
                else{
                    this.phase1 = false;
                    this.phase2 = true;
                    this.ok_responses = 0;
                    this.responses = 0;
                    this.max_ts = 0;
                    this.currentMessage.type = "acceptRequest";
                    this.sendAcceptRequest(this.currentMessage);
                }
            }
            return;
        }
        
        // ==== PHASE 2 ====
        
        if(message.type.endsWith("acceptRequest")){
            // send accept anyway, eventually with a negative answer
            this.sendAccept(message);
            return;
        }
        
        if(message.type.endsWith("accept")){
            System.out.println("accept_received: command="+message.command+" and position="+message.position);
            this.responses++;
            if(message.tag >= 0){
                this.ok_responses++;                            
            }
            // when I receive all responses, check if I can move forward
            if(this.responses == this.PEERS-1){
                // if not a majority of ok, abort this phase and try again from the beginning
                if(this.ok_responses < this.PEERS/2){
                    this.phase1 = false;
                    this.phase2 = false;
                    this.updateBusy(false);
                    //Message m = this.propose_q.peek();
                    //this.processMessage(m);
                }
                else{
                    this.phase1 = false;
                    this.phase2 = false;
                    this.ok_responses = 0;
                    this.responses = 0;
                    System.out.println("accept_received: command="+message.command+" and position="+message.position);
                    this.executeRequest(message);
                    this.propose_q.poll();
                    this.updateBusy(false);
                }
            }
            return;
        }

    }
    
    public synchronized void updateBusy(boolean busy){
        this.busy = busy;
    }
    
    public void updateGUI(String s, int position){
        this.gui.TextboxUpdate(s, position);
    }
     
    
    public synchronized void processData(){         
        // extract new message from propose_q only if necessary
        if(!this.propose_q.isEmpty() && !this.busy){
            this.updateBusy(true);
            
            Message next = new Message();
            if(this.isLeader()) next = this.propose_q.peek(); //propose_q.poll(); 
            else next = this.propose_q.poll();
            this.currentMessage = next;
            processMessage(next);   
            System.out.println("processData: command="+next.command+" and position="+next.position);
        }
        //Message m = this.propose_q.peek();
        //this.updateBusy(false);
        //return;
    }
    
     public synchronized void executeRequest(Message m){
        //if( m.position < 0 || m.position > this.text.length() ) return;
         int position = m.position;
         String command = m.command;
         System.out.println("executeRequest: text="+this.text+ ", command="+command+" and position="+position);
        //m.state = this.state;
        // add this message at the beginning of the log (newest executed messages first)
        //this.log.add(0, m);
        //this.gui.TextboxUpdateLocal(m.command, m.c, m.position);
        String aux1="", aux2="", aux3="";
        if(this.text.length() > 0) {
            aux1 = this.text.substring(0, m.position);
            //aux3 += this.text.charAt(m.position);
        }
        if(this.text.length() > m.position+1) {
            aux2 = this.text.substring(m.position+1, this.text.length());
            aux3 += this.text.charAt(m.position);
        }
        if(command.charAt(0) == 'i') {
            aux2 = m.c + aux3 + aux2;
            position++;
            //if(position > this.text.length()) position = this.text.length();
        }
        this.text = aux1 + aux2;
        
        System.out.println("executeRequest: text="+this.text+ ", command="+command+" and position="+position);
        this.updateGUI(this.text, position);        
    }   
     
     public synchronized boolean isLeader(){
         this.wait++;
         if(this.wait >= this.max_wait) this.wait -= this.max_wait;
         if(this.ID == 0 && this.wait > 0) return true;
         return false;
     }
     
     public synchronized void sendPrepare(Message message){
        message.type = "prepare";
        this.responses = 0;
        this.max_ts = 0;
        this.phase1 = true;
        this.phase2 = false;
        message.source = this.ID;
        message.destination = -1;
        this.tag++;
        message.tag = this.tag;
        System.out.println("sendPrepare: command="+message.command+" and position="+message.position);
        this.comm.submitMessageFromProtocol(message);
     } 
     
     public synchronized void sendPromise(Message message){
         int leader = message.source;
         
         if(this.max_accepted.tag > message.tag)
             message.tag = -1;        
         
         if(this.max_accepted.tag < message.tag && this.max_accepted.tag > 0){
             message.tag = this.max_accepted.tag; 
             message.c = this.max_accepted.c;
             message.position = this.max_accepted.position;
             message.command = this.max_accepted.command;
             System.out.println("sendPromise1: command="+message.command+" and position="+message.position);
         }
         
         if(this.max_accepted.tag == 0){
             message.tag = 0; 
             this.max_accepted.command = message.command;
             this.max_accepted.tag = message.tag;
             this.max_accepted.position = message.position;
             this.max_accepted.c = message.c;
             System.out.println("sendPromise2: command="+message.command+" and position="+message.position);
         }
         
        message.source = this.ID;
        message.destination = this.leaderID;
        message.type = "promise";
        System.out.println("sendPromise3: command="+message.command+" and position="+message.position);
        this.comm.submitMessageFromProtocol(message);
     }
     
     public synchronized void sendAcceptRequest(Message m){
         /*//first check if it is the original message
         Message ref = this.propose_q.peek();
         if(ref.c==m.c && ref.command==m.command && ref.position==m.position)
             this.propose_q.poll();
         */
         m.type = "acceptRequest";
         m.destination = -1;
         m.source = this.ID;
         System.out.println("sendAcceptRequest: command="+m.command+" and position="+m.position);
         this.comm.submitMessageFromProtocol(m);
     }
     
     public synchronized void sendAccept(Message m){
         System.out.println("sendAccept1: max_accepted.tag = "+this.max_accepted.tag+" and argument m.tag = "+m.tag);
         
         if(this.max_accepted.tag > m.tag) {
             m.tag = -1;
         }
         else{
             System.out.println("sendAccept2: max_accepted.tag = "+this.max_accepted.tag+" and argument m.tag = "+m.tag);
             this.max_accepted.tag = 0;
             
             this.executeRequest(m);
         }
         m.destination = this.leaderID;
         m.source = this.ID;
         m.type = "accept";
         System.out.println("sendAccept3: command="+m.command+" and position="+m.position);
         this.comm.submitMessageFromProtocol(m);
     }

}
