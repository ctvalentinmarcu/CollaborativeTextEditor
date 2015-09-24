/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

/**
 *
 * @author vali
 */
public class Test extends Thread {
    
    Communication comm;
    
    public Test(Communication comm){
        this.comm = comm;
    }
    
    public void run(){
        try{
            int id = 0;//comm.ID;
            int PEERS = comm.PEERS;
            int steps = 40;
            Thread.sleep(1000);
            for(int i = 1; i<= steps; i++){
                comm.submitCommandFromGUI("insert", 'a', 0);
                Thread.sleep(123*id+1);
                comm.submitCommandFromGUI("insert", 'b', 1);
                Thread.sleep(133*id+2);
                comm.submitCommandFromGUI("insert", 'c', 2);
                Thread.sleep(134*id+3);
                comm.submitCommandFromGUI("insert", ' ', 3);
                Thread.sleep(123*id+1);
                comm.submitCommandFromGUI("insert", 'e', 4);
                Thread.sleep(133*id+2);
                comm.submitCommandFromGUI("insert", '\n', 5);
                Thread.sleep(143*id+3);
                comm.submitCommandFromGUI("delete", ' ', 1);
                Thread.sleep(132*id+PEERS);
                comm.submitCommandFromGUI("delete", ' ', 4);
                Thread.sleep(123*id+1);
                comm.submitCommandFromGUI("insert", 'b', 1);
                Thread.sleep(113*id+2);
                comm.submitCommandFromGUI("insert", 'c', 0);
                Thread.sleep(123*id+3);
                comm.submitCommandFromGUI("delete", ' ', 0);
                Thread.sleep(13*id+PEERS);
            }
            
        }
        catch(Exception e){}
    }
}
