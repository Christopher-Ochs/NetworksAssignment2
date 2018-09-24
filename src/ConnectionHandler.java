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
        ProtocolHandler.write(os, message);
    }

    private void readMessage() throws IOException {
        ChatMessage chatMessage = ProtocolHandler.read(is);
        MessagingServer.messages.add(chatMessage);
        MessagingServer.messagesSent.get(this).add(chatMessage);
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
