/*
    Name: Phillip Tallo and Christopher Ochs
    Date: September 18, 2018
    Desc: Assignment 2 Computer Networks.  Client Server Protocol.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
    This will handle our protocol implementation
 */
public class ProtocolHandler {

    static ChatMessage read(InputStream is) throws IOException {
        // Get the size of the message
        int sizeOfSize = is.read();
        // Initialize the size of the byte array to necessary length
        byte[] size = new byte[sizeOfSize];
        // Read in the remaining part of the message
        is.read(size, 0, sizeOfSize);

        // Get the type of message
        int type = is.read();

        String sSize = new String(size);
        Integer iSize = Integer.parseInt(sSize);
        byte[] message = new byte[iSize];
        is.read(message, 0, iSize);

        // create chatMessage object with correct type
        return new ChatMessage(message, type == 0);
    }

    static void write(OutputStream os, ChatMessage message) throws IOException {
        byte[] outMessage = message.getByteArray();
        int outMessageLength = outMessage.length;
        String outMessageLengthS = String.valueOf(outMessageLength);
        os.write(outMessageLengthS.length());
        os.write(outMessageLengthS.getBytes());
        os.write(message.isText() ? 0 : 1);
        os.write(outMessage);
        os.flush();
    }
}
