
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ServerThread extends Thread{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    
    public ServerThread(Socket socket) throws  Exception{
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this. in = new ObjectInputStream(socket.getInputStream());
    }
    
    public void run() {
        try {
            while (true) {
                Integer action = (Integer) in.readObject();
                switch(action){
                    case Constants.SUBMIT_ACTION:
                        BibliographyBean bb = (BibliographyBean) in.readObject();
                        out.writeObject(Constants.GET_MESSAGE_FROM_SERVER);
                        if(Server.bibliographyMap.get(bb.getISBN()) == null){
                            Server.bibliographyMap.put(bb.getISBN(), bb);
                            out.writeObject(Constants.SUBMIT_MESSAGE_SUCCESSFUL);
                        }else{
                            out.writeObject(Constants.SUBMIT_MESSAGE_UNSSUCCESFUL);
                        }
                    break;
                    case Constants.RETRIEVE_ALL_ACTION:
                        out.writeObject(Constants.GET_MAP_FROM_SERVER);
                        out.writeObject(Server.bibliographyMap.size());
                        Set<String> keySet = Server.bibliographyMap.keySet();
                        keySet.forEach((key) -> {
                            try {
                                out.writeObject(Server.bibliographyMap.get(key));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                    break;
                    case Constants.GET_SPECIFIC_ENTRIES:
                        BibliographyBean queryBB = (BibliographyBean) in.readObject();
                       
                        Map<String, BibliographyBean> queryResultMap = getMapOfMatchedEntries(queryBB);
                        
                        out.writeObject(Constants.GET_SPECIFIC_ENTRIES_RESULT);
                        out.writeObject(queryResultMap);
                    break;
                    case Constants.REMOVE_SPECIFIC_ENTRIES:
                        BibliographyBean removeBB = (BibliographyBean) in.readObject();
                        
                        Map<String, BibliographyBean> removeMap = getMapOfMatchedEntries(removeBB);
                        Integer entriesRemoved = 0;
                        for(String key: removeMap.keySet()){
                            BibliographyBean item = Server.bibliographyMap.get(key);
                            if(item != null){
                                Server.bibliographyMap.remove(key);
                                entriesRemoved++;
                            }
                        }
                        
                        out.writeObject(Constants.REMOVE_SPECIFIC_ENTRIES_RESPONSE);
                        out.writeObject(entriesRemoved);
                    break;
                    case Constants.UPDATE_ENTRY:
                        BibliographyBean updateBB = (BibliographyBean) in.readObject();
                        Boolean updateExecuted = false;
                        for(String key : Server.bibliographyMap.keySet()){
                            BibliographyBean item = Server.bibliographyMap.get(key);
                            if(item.getISBN().equals(updateBB.getISBN())){
                                if(updateBB.getAuthor().isEmpty()){
                                    updateBB.setAuthor(item.getAuthor());
                                }
                                
                                if(updateBB.getTitle().isEmpty()){
                                    updateBB.setTitle(item.getTitle());
                                }
                                
                                if(updateBB.getPublisher().isEmpty()){
                                    updateBB.setPublisher(item.getPublisher());
                                }
                                
                                if(updateBB.getYear() == null){
                                    updateBB.setYear(item.getYear());
                                }
                                
                                Server.bibliographyMap.replace(key, updateBB);
                                updateExecuted = true;
                            }
                        }
                        
                        out.writeObject(Constants.GET_MESSAGE_FROM_SERVER);
                        if(updateExecuted){
                            out.writeObject(Constants.UPDATE_EXECUTED_SUCCESSFULY);
                        }else{
                            out.writeObject(Constants.UPDATE_EXECUTED_UNSUCCESSFULY);
                        }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(Constants.CLIENT_DISCONNECTED);
        }
    }
    
    private Map<String, BibliographyBean> getMapOfMatchedEntries(BibliographyBean bean){
        Map<String, BibliographyBean> resultMap = new HashMap<>();
        Server.bibliographyMap.keySet().forEach((key)->{
            BibliographyBean item = Server.bibliographyMap.get(key);
            Boolean itemMatched = true;
                            
            if(!bean.getISBN().isEmpty()){
                if(!item.getISBN().equals(bean.getISBN())){
                    itemMatched = false;
                }
            }
                            
            if(!bean.getTitle().isEmpty()){
                if(!item.getTitle().equals(bean.getTitle())){
                    itemMatched = false;
                }
            }
                            
            if(!bean.getAuthor().isEmpty()){
                if(!item.getAuthor().equals(bean.getAuthor())){
                    itemMatched = false;
                }
            }
                            
            if(!bean.getPublisher().isEmpty()){
                if(!item.getPublisher().equals(bean.getPublisher())){
                    itemMatched = false;
                }
            }
                            
            if(bean.getYear() != null){
                if(item.getYear().intValue() != bean.getYear().intValue()){
                    itemMatched = false;
                }
            }
                            
            if(itemMatched){
                resultMap.put(item.getISBN(), item);
            }
        });
        
        return resultMap;
    }
}
