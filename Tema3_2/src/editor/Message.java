/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.Serializable;


/**
 *
 * @author vali
 */
public class Message implements Serializable, Comparable {
    String type; // proposer2leader, prepare, prepareOK, accept, acceptOK
    
    String command;  // insert or delete
    char c;
    int position;
    
    int source;
    int destination;
    
    int tag;    
    int ts;  // timestamp
    boolean deliverable = false;
    
    public Message() {}
    
    public Message(String type, int tag, int source, String command, char c, int position) {
        this.type = type;
        this.source = source;
        this.command = command;
        this.c = c;
        this.position = position;
        this.tag = tag;
        //this.ts = ts;
        this.destination = -1;        
    }

    @Override
    public int compareTo(Object o) {
        //throw new UnsupportedOperationException("Not supported yet.");
        Message m = (Message) o;
        if(this.ts < m.ts) return -1;
        if(this.ts > m.ts) return 1;
        return 0;
    }
}
class Command {
    
    boolean insert;
    char c=' ';
    int position;
    
    public Command(){
        this.insert = true;
        this.c = ' ';
        this.position = 0;
    }
    
    public Command(boolean insert, char c, int position){
        this.insert = insert;
        this.c = c;
        this.position = position;
    }
    
    public Command(String command, char c, int position){
        if(command=="insert") this.insert = true;
        else this.insert = false;
        this.c = c;
        this.position = position;
    }
    
    public Command(String command, int position){
        if(command=="delete") this.insert = false;
        else this.insert = true;
        this.c = ' ';
        this.position = position;
    }
}
