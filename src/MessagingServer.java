import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MessagingServer {

    static ArrayList<String> messages = new ArrayList<>();
    static HashMap<ConnectionHandler, ArrayList<String>> messagesSent = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);

        new Thread(() -> {
            while(!server.isClosed()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateAllSockets();
            }
        }).start();

        while (!server.isClosed()) {
            Socket socket = server.accept();
            ConnectionHandler handler = new ConnectionHandler(socket);
            new Thread(handler).start();
        }
    }

    private static void updateAllSockets() {
        // Will be used to update each socket with the messages they are missing
    }
}
