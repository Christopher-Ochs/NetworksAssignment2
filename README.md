NetworksAssignment2
-
- Created by Phillip Tallo and Christopher Ochs

**Description**: 
-
This program will create a client server connection.  The clients will interact similar to a chat room.

**To run**: 
-
1. Run the `main` method in the _MessagingServer.java_ file.  This will start the server and will listen for clients to connect.
2. Run the `main` method in the _MessagingClient.java_ file.  This will create two clients messaging GUIs.
3. A text message or image can be sent from one client to another.

**Protocol:**
-
When a `byte array` is sent to the server it will have encoded in it a `byte` for the size and `byte` another for type (image or text) of the message.  The class `chatMessage` will handle the byte arrays and display the proper outputs to the client.  The server will update each client with the proper messages every two seconds.
