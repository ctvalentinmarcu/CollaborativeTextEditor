/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.SwingUtilities;
/**
 *
 * @author vali
 */
public class Communication extends Thread {
    
    int PEERS = 2;
    String type;
    GUI gui;
    int ID;
    int port;    
    String status;
    boolean finish=false;
    
    ArrayList<ObjectOutputStream> outputstreams;
    ArrayList<ObjectInputStream> inputstreams;
    
    CommunicationProtocol protocol;
    ConcurrentLinkedQueue<Message> messagesToSend;
    
    public Communication(GUI gui, String type, int ID, int peers){
        this.gui = gui;
        this.PEERS = peers;
        this.ID = ID;
        this.type = type;
        this.port = 5000 + ID;

        inputstreams = new ArrayList<ObjectInputStream>(PEERS);
        outputstreams = new ArrayList<ObjectOutputStream>(PEERS);
        messagesToSend = new ConcurrentLinkedQueue<Message>();
        
        this.finish = false;
    }
    
    public void submitCommandFromGUI(String command, char c, int position){
        //System.out.println("received command "+command+" from GUI");
        this.protocol.submitCommandFromGUI(command, c, position);
    }
    public synchronized void submitCommandFromPeer(Message messsage){
        //System.out.println("received command "+messsage+" from Peer");
        this.protocol.submitCommandFromPeer(messsage);
    }
    
    public void submitMessageFromProtocol(Message m){
        try {this.messagesToSend.add(m);} catch(Exception e) {System.out.println("COMM: exception messagesToSend");}
        //System.out.println("COMM: received message "+m.type+" from Paxos, ready to send it to peers");
    }
    
    @Override
    public void run(){
        this.gui.updateStatus("Welcome"); 
        try{ Thread.sleep(2000); } catch (Exception ex) {}
        
        // connect to all the other peers
        initializeConnections(type, ID);
        this.gui.updateStatus("Connection established with everyone. You may start writing.");
        
        // activate the appropiate protocol
        switch(this.type){
            case("3phase"):
                protocol = new Paxos(this, this.ID, this.PEERS, this.gui);
                break;
            default:
                this.gui.updateStatus("No communication protocol selected.");
                break;
        }
        
        // start the handler threads for incoming messages
        //if(this.type == "Paxos" || this.ID==0) // dOPT or Jupiter server
        for(ObjectInputStream ois : inputstreams){
            InputHandler handler = new InputHandler(this, ois);
            handler.start();
        }
        /*else{ //Jupiter client
            InputHandler handler = new InputHandler(this, inputstreams.get(0));
            handler.start();
        }*/
        
        
        while(this.finish == false){
            Message message;        
        
            // send to destination all the messages in the queue 
            if(PEERS > 1) while (!(this.messagesToSend.isEmpty())){
                message = this.messagesToSend.poll();
                //System.out.println("COMM_RUN: received command to "+message.destination+" from GUI");
                int id=0;
                id = message.destination;
                //if(this.type=="dOPT") id = message.destination;
                //else if(this.ID==0) id = -1;
                //this.gui.updateStatus("sending message "+message.command+message.position);
                if(id > this.ID) id--;

                try{
                    if(id >= 0) {
                        this.outputstreams.get(id).writeObject(message);
                        this.outputstreams.get(id).flush();
                        
                        this.gui.updateSentBytes(32);
                    }
                    else for(ObjectOutputStream oos : this.outputstreams){
                        
                        //System.out.println("sending message "+message.command+message.position);
                        oos.writeObject(message);
                        oos.flush();
                        this.gui.updateSentBytes(32);
                        //this.gui.updateStatus("sent message "+message.command+message.position);
                        //System.out.println("sent message "+message.command+message.position);
                    }
                    
                }
                catch (Exception e) {System.out.println("Error while sending a message to peer "+id);}  
            }   
            //System.out.println("COMM_RUN: processData:");
            //if(this.type == "Paxos") 
                this.protocol.processData();
        }
    }
    
    public void initializeConnections(String type, int ID){
        int i=0;
        // we establish connections as client with 0...ID-1
        if(ID > 0)
            for (i=0; i<ID; i++) {
                this.gui.updateStatus("Trying to establish connection as client to peer"+i);                
                try {
                    String ip = "localhost";
                    int port = 5000 + i*100 + ID;
                    //this.gui.updateStatus("Trying to establish connection as client to "+i);
                    connectToServer(ip, port, i);
                    this.gui.updateStatus("Succesfully connected as client to peer "+i);
                    Thread.sleep(2000);
                } 
            catch (Exception e) {e.printStackTrace(); System.out.println(ID+": eroare la conexiunea client cu "+i);}
            }
        // we establish connections as server with ID+1 ... PEERS-1
        if(ID < PEERS-1)
            for (i=ID+1; i<PEERS; i++) {
                this.gui.updateStatus("Trying to establish connection as server to peer "+i);
            try{
                int port = 5000 + ID*100 + i;
                ServerSocket ss =  new ServerSocket(port); 
                //this.gui.updateStatus("Trying to establish connection as server to "+i);
                connectToClient(ss, i);
                this.gui.updateStatus("Succesfully connected as server to peer "+i);
                Thread.sleep(2000);
            }
            catch (Exception e) {e.printStackTrace(); System.out.println(ID+": eroare la conexiunea server cu "+i);}
            }         
    }
    
    
    public void connectToClient(ServerSocket ss, int idClient) {
        Socket client = null;
    try{
        //accepts incoming client (blocking function)
        client = ss.accept();    

        //get the output stream
        OutputStream output = client.getOutputStream();
        
        //get the input stream
        InputStream input = client.getInputStream();

        //wrap the output stream in an object output stream to easily write objects
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.flush();

        //add the output stream to the list fo client output streams
        this.outputstreams.add(objectOutput);
        
        //wrap the input stream in an object input stream to easily write objects
        ObjectInputStream objectInput = new ObjectInputStream(input);

        //add the input stream to the list of client input streams
        this.inputstreams.add(objectInput);            
        }
        catch (Exception e) {
            //e.printStackTrace(); 
            this.gui.updateStatus(ID+": eroare la conexiunea server cu "+idClient);
        }
    } // end of connectToClient
    
    public void connectToServer(String ip, int port, int idServer){
        boolean connected = false;
        int max_tries = 300;
        while(connected==false){
        //for(int i=0; i<max_tries && connected==false; i++){
            try{
                //Thread.currentThread().sleep(2000);
                //Thread.sleep(2000);
                Socket s = new Socket(ip, port);
                //connected = true;
                //get the output stream
                OutputStream output = s.getOutputStream();

                //get the input stream
                InputStream input = s.getInputStream();

                //wrap the output stream in an object output stream to easily write objects
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
                objectOutput.flush();

                //add the output stream to the list fo client output streams
                this.outputstreams.add(objectOutput);

                //wrap the input stream in an object input stream to easily write objects
                ObjectInputStream objectInput = new ObjectInputStream(input);

                //add the input stream to the list of client input streams
                this.inputstreams.add(objectInput); 
                
                connected = true;
                //break;
            }
            catch (Exception e) {
                //e.printStackTrace(); 
                this.gui.updateStatus(ID+": Error connecting as client with "+idServer+" (remote server down); still trying...");     
                System.out.println(ID+": eroare la conexiunea client cu "+idServer+"; still trying...");
                try{ Thread.sleep(2000); } catch (Exception ex) {}
            }
        } // end of while
    } // end of connectToServer()
    
     
    
} // end of Communication class


class InputHandler extends Thread {
    
    Communication comm;
    ObjectInputStream ois;
    
    public InputHandler(Communication comm, ObjectInputStream ois){
        this.comm = comm;
        this.ois = ois;
    }
    
    public void run(){
        while(true){
        Message message;
        try{
            message = (Message) ois.readObject();
            if(message.command == "finish") this.comm.finish = true;
            int size = 32;
            this.comm.gui.updateReceivedBytes(size);
            //this.comm.gui.updateStatus("received message "+message.command+message.position+message.c);
            //System.out.println("HANDLER_RUN: received message "+message.command+message.position+message.c);
            
            this.comm.submitCommandFromPeer(message);                  
         }
         catch (Exception e) {}//System.out.println("HANDLER_RUN: error");}   
        }
    }
}