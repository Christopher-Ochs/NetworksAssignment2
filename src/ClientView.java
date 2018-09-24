import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientView {

    private GridPane root;

    private TextField messageField;
    private Button sendMessageButton;
    private ScrollPane scrollPane;
    private VBox items;

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public ClientView(GridPane root) {
        this.root = root;
        initView(root);

        try {
            socket = new Socket("127.0.0.1", 8080);

            is = socket.getInputStream();
            os = socket.getOutputStream();

            new Thread(() -> {
                while (!socket.isClosed()) {
                    readMessage();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView(GridPane root) {
        initDimensions(root);
        root.setAlignment(Pos.CENTER);

        messageField = new TextField();
        sendMessageButton = new Button("Send");
        scrollPane = new ScrollPane();
        items = new VBox();
        scrollPane.setContent(items);

        initListeners();

        root.add(messageField, 0, 0);
        root.add(sendMessageButton, 1, 0);
        root.add(scrollPane, 0, 1, 2, 1);
    }

    private void initDimensions(GridPane root) {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(90);
        root.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        root.getColumnConstraints().add(column2);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        root.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(90);
        root.getRowConstraints().add(row2);
    }

    private void initListeners() {
        root.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            }
        });
        sendMessageButton.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        try {
            String outputMessage = messageField.getText();
            messageField.setText("");

            byte[] outMessage = outputMessage.getBytes();

            os.write(outMessage.length);
            os.write(outMessage);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() {
        try {
            int size = is.read();
            byte[] message = new byte[size];
            is.read(message, 0, size);
            items.getChildren().add(new Text(new String(message)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
