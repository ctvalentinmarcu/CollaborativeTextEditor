/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

/**
 *
 * @author vali
 */
public interface CommunicationProtocol {
    
    public void submitCommandFromGUI(String command, char c, int position);
    
    public void submitCommandFromPeer(Message messsage);
    
    public void processData();
    
    public void updateGUI(String s, int position);
}
