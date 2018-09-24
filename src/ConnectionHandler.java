import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private Socket socket;
    private OutputStream os;
    private InputStream is;

    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
    }

    public void sendMessage(ChatMessage message) throws IOException {
        byte[] outMessage = message.getByteArray();
        os.write(outMessage.length);
        os.write(outMessage);
        os.flush();
    }

    private void readMessage() throws IOException {
        int size = is.read();
        byte[] message = new byte[size];
        is.read(message, 0, size);
        String stringMessage = new String(message);
        MessagingServer.messages.add(stringMessage);
        MessagingServer.messagesSent.get(this).add(stringMessage);
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                readMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
