import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketHandler {

    static ChatMessage read(InputStream is) throws IOException {
        int sizeOfSize = is.read();
        byte[] size = new byte[sizeOfSize];
        is.read(size, 0, sizeOfSize);

        int type = is.read();

        String sSize = new String(size);
        Integer iSize = Integer.parseInt(sSize);
        byte[] message = new byte[iSize];
        is.read(message, 0, iSize);

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
