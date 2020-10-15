

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Constants {
    
    public static final String ISBN_REGEX = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";
    
    public static final String ALL_FIELDS_REQUIRED_ERROR = "All fields are required!";
    
    public static final String INVALID_ISBN_FORMAT = "ISBN has an invalid format!";
    
    public static final String INVALID_YEAR_FORMAT = "Invalid year!";
    
    public static final int SUBMIT_ACTION = 1000;
    
    public static final int GET_MESSAGE_FROM_SERVER = 1001;
    
    public static final int RETRIEVE_ALL_ACTION = 1002;
    
    public static final int GET_MAP_FROM_SERVER = 1003;
    
    public static final int GET_SPECIFIC_ENTRIES = 1004;
    
    public static final int GET_SPECIFIC_ENTRIES_RESULT = 1005;
    
    public static final int REMOVE_SPECIFIC_ENTRIES = 1006;
    
    public static final int REMOVE_SPECIFIC_ENTRIES_RESPONSE = 1007;
    
    public static final int UPDATE_ENTRY = 1008;
    
    public static final String SUBMIT_MESSAGE_SUCCESSFUL = "Submit was successful!";
    
    public static final String SUBMIT_MESSAGE_UNSSUCCESFUL = "Entry already exists with this ISBN!";
    
    public static final String SERVER_ENTRIES_EMPTY = "No information to show for now!";
    
    public static final String NUMBER_OF_ENTRIES_REMOVED = "Number of entries removed is: ";
     
    public static final String UPDATE_EXECUTED_SUCCESSFULY = "Update was successfuly executed!";
     
    public static final String UPDATE_EXECUTED_UNSUCCESSFULY = "No entry was updated!"; 
    
    public static final String ERROR_READING_MESSAGE = "Error reading message";
    
    public static final String ERROR_SENDING_MESSAGE = "Error sending message";
    
    public static final String CONNECTION_CLOSED = "Connection was closed!";
    
    public static final String CLIENT_DISCONNECTED = "Client was disconnected!";
    
    public static final String INVALID_PORT_NUMBER = "Port Number is invalid!";
}
