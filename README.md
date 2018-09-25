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

The protocol will look like the following

    [int x][int y][int z]
    [message bytes....]
    
    x = the size of our size
    y = the size of our message
    z = 0 if the content is text or 1 if the content is an image
    message bytes = the message converted to bytes of length y
    
    This is followed by the whole message of length y converted to bytes

- When a `byte array` is sent to the server it will have encoded in it a `byte` for the size and `byte` another for type (image or text) of the message.  
- The class `chatMessage` will handle the byte arrays and display the proper outputs to the client.  
- The server will update each client with the proper messages every two seconds.
