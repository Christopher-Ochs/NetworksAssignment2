import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        Socket socket = server.accept();

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String message = "Hello Client!!!!";

        byte[] byte_message = message.getBytes();

        os.write(byte_message.length);
        os.write(byte_message);
        os.flush();

        int size = is.read();
        byte[] messageIn = new byte[size];
        is.read(messageIn, 0, size);

        System.out.println(new String(messageIn));

        socket.close();
    }
}
