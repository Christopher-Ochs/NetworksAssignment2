/*
    Name: Phillip Tallo and Christopher Ochs
    Date: September 18, 2018
    Desc: Assignment 2 Computer Networks.  Client Server Protocol.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MessagingClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);

        InputStream is = socket.getInputStream();

        int size = is.read();
        byte[] message = new byte[size];
        is.read(message, 0, size);

        System.out.println(new String(message));

        socket.close();
    }
}
