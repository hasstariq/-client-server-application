

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Server {
    
    public static Map<String, BibliographyBean> bibliographyMap = Collections.synchronizedMap(new HashMap<String, BibliographyBean>());
    
    public static void main(String[] args){
        Integer portNumber = null;
        try{
            portNumber = readPortFromArgs(args);
            if(portNumber != null){
                try{
                    ServerSocket ss = new ServerSocket(portNumber);
                    while(true){
                        Socket socket = ss.accept();
                        ServerThread serverThread = new ServerThread(socket);
                        serverThread.start();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }catch(Exception ex){
            System.out.println(Constants.INVALID_PORT_NUMBER);
        }
    }
    
    private static Integer readPortFromArgs(String[] args){
        Integer portNumber = null;
        try{
            portNumber = Integer.parseInt(args[0]);
        }catch(NumberFormatException ex){}
        
        return portNumber;
    }
}
