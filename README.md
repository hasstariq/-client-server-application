# Client-Server-application

# Contents
The project is composed of 2 modules called server and client, each module having its own main class (Server and Client).
There are also 2 common classes used by these two modules in order to ensure
communication between the server and the client (Constants and BibliographyBean).

# Input data

When the server is started, the first thing it does is to read the port number as a command line argument.
The server ensures that it receives valid input data. It will check if the port number is passed as a command line argument and if it has a valid format.
Depending on the case, it will display in the console error messages if those requirements are not met.


# Opening socket connection

Once the port number is received and it is valid, the server will instantiate an object of type ServerSocket using the port number.
This object will be responsible for accepting requests that will come over the network on the respective port number.


# Handling request

For each client that connects to the server, a special thread is started in order to handle the communication between the server and that respective client. The class used for this job is called ServerThread.
The ServerThread class uses the classes ObjectInputStream and
ObjectOutputStream in order to read and write objects on the stream received on the respective port from the respective client.
Depending on the actions done by the client, the server receives a constant number which is associated with a respective action on both client and server side. Based on these actions the server will know what actions to perform and the client will know what responses to expect from the server.


# Client module architecture

The main class of the client module is called Client.
The client is built as a graphical user interface using java swing and awt packages.
It is composed by a group of 6 frames (ConnectionFrame.java, MainFrame.java, SubmitFrame.java, GetFrame.java, UpdateFrame.java, RemoveFrame.java) all working in order to ensure correctness in the exchanging of data between the client and server and also to ensure validation of the data that is sent to the server.


# Connecting to the server

The input data for connecting to the server is acquired using the
ConnectionFrame class. This frame is composed by two input fields, an error label and a button. The two input fields are used in order to read the address and the
port of the server to which the respective user wants to establish the connection. The error label is used to display any error messages that might occur during the establishment of the connection. The button is used to send the connection request to the server.
The connection to the server is done using the class ServerConnectionController which establishes the connection to the server using the Socket class which receives as input parameters the server address and port number.
The ServerConnectionController class also instantiates the ObjectInputStream and ObjectOutputStream in order to ensure reading and writing of the data to/from the server.


# Actions 

The client can perform the following actions: submit book entry to the server, update book entry, remove book entry, get book entry (or get all entries). All these actions are done using their respective frames (SubmitFrame, GetFrame, UpdateFrame and RemoveFrame).
Each of these frames contains input text fields and buttons in order to simplify the receiving of input data from the user. Validation is performed on the necessary fields, and validation error messages are displayed to the user if data is invalid.
The object that is sent to the server when a submit, get, update or remove action is performed is the BibliographyBean class. This class contains the following fields: ISBN, title, author, publisher and year together with their respective setter and getter methods.


# Running the project 
The order in which the project must be started is the following:
- Start the server first using a port number of your choice (make sure that the port number is not used by another application).
- Start the client and enter the address on which the server is running and the port number which you used to start the server.

In order to run the project from the command line you need to follow these instructions:

Server
For Server
Let path be the variable to where your project is located on the disk
Go to path/server/src/main/java using the following command: cd path/server/src/main/java c. Build the main class using the following command: javac Server.java
Run the main class using the following command: java Server <port_number>
, where <port_number> must be replaced with the desired port number.

Client
For Client
Let path be the variable to where your project is located on the disk b. Go to path/client/src/main/java using the following command: cd path/client/src/main/java c. Build the main class using the following command: javac Client.java d. Run the main class using the following command: java Client




