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
public class TO3Phase implements CommunicationProtocol{
    
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
    int max_ts;
    int tag;
    
    boolean busy=false;
    
    //ArrayList<ObjectOutputStream> outputstreams;
    //ArrayList<ObjectInputStream> inputstreams;
    
    public TO3Phase(Communication comm, int id, int peers, GUI gui){
        this.ID = id;
        this.PEERS = peers;
        this.gui = gui;       
        
        this.temp_q = new ArrayList<Message>(1000);
        //this.propose_q = new ArrayList<Message>();
        this.propose_q = new ConcurrentLinkedQueue<Message>();
        this.clock = 0;
        this.priority = 0;
        this.tag = this.ID * 1000;
        
        this.comm = comm;
        this.text = "";
        this.busy = false;
    }
    
    public synchronized void submitCommandFromGUI(String command, char c, int position){
        Message message = new Message("propose", this.tag, this.clock, this.ID, command, c, position);
        //if(this.busy) return;
        this.propose_q.add(message);
    }
    
    
    
    public synchronized void processMessage(String command, char c, int position){
        System.out.println("3Phase: received command "+command+" from processData");
        this.gui.updateStatus(command);
        
        Message message = new Message("propose", this.tag, this.clock, this.ID, command, c, position);
        //if there is only one site, just execute te message
        if(this.PEERS == 1) { this.executeRequest(message); return; }
        
       /* if(this.busy==true) { 
            this.propose_q.add(message); 
            return;
        }
        this.updateBusy(true);
        */
    
        // update local clock and tag
        this.clock++;
        this.tag++;
        if(this.tag >= this.ID * 1000 + 1000) tag -= 1000;    
        
        //set responses and max_ts to 0, send propose and wait revise messages from all peers
        this.max_ts = 0; 
        this.responses = 0;
        /*while (this.responses < this.PEERS-1) try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(TO3Phase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        this.comm.submitMessageFromProtocol(message);
        this.gui.updateStatus("sent propose to all peers, ts="+this.clock);
        System.out.println("3Phase: processsing message with tag = "+message.tag+" and ts = "+message.ts);
        //this.updateBusy(true);
        
        /*if(this.responses == this.PEERS-1){
                    message.type = "final";
                    message.source = this.ID;
                    message.destination = -1; // broadcast the final message
                    message.ts = this.max_ts;
                    this.comm.submitMessageFromProtocol(message);
                    //update local clock
                    this.clock = (this.clock > message.ts) ? this.clock : message.ts;
                    this.clock++;
                    //execute the message
                    this.executeRequest(message);
                }
          */
        //System.out.println("dOPT: command submitted to q: "+m.state[0]+m.state[1]);
    }
    public synchronized void submitCommandFromPeer(Message message){
        System.out.println("received command "+message.type+" from Peer; position="+message.position);
        //switch(message.type){
            //case("propose"):
        if(message.type.endsWith("propose")){
                //this.clock++;
                //int revise_ts = (this.clock > message.ts) ? this.clock : message.ts;
                this.priority++;
                if(this.priority < message.ts) this.priority = message.ts;
                int revise_ts = this.priority;
                //this.clock = revise_ts;
                message.ts = revise_ts;
                message.deliverable = false;
                //add message to temp_q 
                this.temp_q.add(message);
                //return revise message to sender
                message.type = "revise";
                message.destination = message.source;
                message.source = this.ID;
                this.comm.submitMessageFromProtocol(message);
                //break;
                return;
        }
            //case("revise"):
        if(message.type.endsWith("revise")){
                //this.clock++;
                this.max_ts = (this.max_ts > message.ts) ? this.max_ts : message.ts;
                this.responses++;
                if(this.responses == this.PEERS-1){
                    message.type = "final";
                    message.source = this.ID;
                    message.destination = -1; // broadcast the final message
                    message.ts = this.max_ts;
                    this.comm.submitMessageFromProtocol(message);
                    //update local clock
                    //this.clock++;
                    this.clock = (this.clock > this.max_ts) ? this.clock : this.max_ts;                    
                    
                    //execute the message
                    this.executeRequest(message);
                    // permit other proposed messages to be processed from propose_q
                    this.updateBusy(false);
                    //this.processData();
                    
                }//*/
                //break;
                return;
        }
            //case("final"):{
        if(message.type.endsWith("final")){
                //System.out.println("3Phase final: busy = "+this.busy + ", length of propose_q = "+this.propose_q.size());
                for(Message m : this.temp_q) if (m.tag == message.tag){
                    m.deliverable = true;
                    m.ts = message.ts;
                }
                // sort temp_q in ascending order of timestamps
                Collections.sort(temp_q); 
                
                //System.out.println("3Phase final: busy = "+this.busy + ", length of propose_q = "+this.propose_q.size());
                // retrieve and execute the head of temp_q if it has the same tag
                if(this.temp_q.get(0).tag == message.tag){
                    this.executeRequest(this.temp_q.get(0));                    
                    this.clock = (this.clock > this.temp_q.get(0).ts) ? this.clock : this.temp_q.get(0).ts;
                    this.clock++;
                    this.temp_q.remove(0);
                  
                    //also remove from temp_q and execute all the following deliverable messages
                    while(this.temp_q.get(0).deliverable == true){
                        this.executeRequest(this.temp_q.get(0));                        
                        this.clock = (this.clock > this.temp_q.get(0).ts) ? this.clock : this.temp_q.get(0).ts;
                        this.clock++;
                        this.temp_q.remove(0);                                    
                    }
                }
                //System.out.println("3Phase final: busy = "+this.busy + ", length of propose_q = "+this.propose_q.size());
     
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
            Message next = propose_q.poll();                
            processMessage(next.command, next.c, next.position);   
        }
        //this.updateBusy(false);
        //return;
    }
    
     public synchronized void executeRequest(Message m){
        //if( m.position < 0 || m.position > this.text.length() ) return;
         int position = m.position;
         String command = m.command;
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
        
        //System.out.println("executeRequest: text="+this.text+ ", command="+command+" and position="+position);
        this.updateGUI(this.text, position);        
    }   
     
     

}
