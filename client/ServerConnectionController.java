


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ServerConnectionController {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public ServerConnectionController(String ip, Integer port) throws Exception{
        socket = new Socket(ip, port);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void sendMessage(Object object) {
        try{
            out.writeObject(object);
        }catch(Exception ex){
            System.out.println(Constants.ERROR_SENDING_MESSAGE);
        }
    }
    
    public Object readMessage() {
        try {
            return in.readObject();
        } catch (Exception e) {
            System.out.println(Constants.ERROR_READING_MESSAGE);
        }
        return null;
    }
    
    public void closeConnection(){
        try{
            this.socket.close();
        }catch(Exception ex){
            System.out.println(Constants.CONNECTION_CLOSED);
        } 
    }
}
