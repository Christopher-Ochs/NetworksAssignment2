import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientView {

    private Stage primaryStage;
    private GridPane root;

    private TextField messageField;
    private Button sendMessageButton;
    private Button sendImageButton;
    private ScrollPane scrollPane;
    private VBox items;

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public ClientView(GridPane root, Stage primaryStage) {
        this.root = root;
        this.primaryStage = primaryStage;
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

//        Image image = new Image(new File("hellothere.jpeg").toURI().toString());
//        Platform.runLater(() -> {
//            ImageView imageView = new ImageView(image);
//            items.getChildren().add(imageView);
//        });
    }

    private void initView(GridPane root) {
        initDimensions(root);
        root.setAlignment(Pos.CENTER);

        messageField = new TextField();
        sendMessageButton = new Button("Send");
        sendImageButton = new Button("Send Image");
        scrollPane = new ScrollPane();
        items = new VBox();
        scrollPane.setContent(items);

        initListeners();

        root.setAlignment(Pos.CENTER);
        root.add(messageField, 0, 0);
        GridPane.setHalignment(messageField, HPos.CENTER);

        root.add(sendMessageButton, 1, 0);
        GridPane.setHalignment(sendMessageButton, HPos.CENTER);

        root.add(sendImageButton, 2, 0);
        GridPane.setHalignment(sendImageButton, HPos.CENTER);

        root.add(scrollPane, 0, 1, 3, 1);
        GridPane.setHalignment(scrollPane, HPos.CENTER);
    }

    private void initDimensions(GridPane root) {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(85);
        root.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(5);
        root.getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        root.getColumnConstraints().add(column3);

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
        sendImageButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Send Image Explorer");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                ChatMessage message = new ChatMessage(new Image(selectedFile.toURI().toString()));
                System.out.println(selectedFile.toString());
            }
        });
    }

    private void sendMessage() {
        try {
            String outputMessage = messageField.getText();
            messageField.setText("");

            byte[] outMessage = outputMessage.getBytes();

            os.write(outMessage.length);
            os.write(outMessage);
            os.flush();

            Platform.runLater(() -> items.getChildren().add(new Text(new String(outMessage))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() {
        try {
            int size = is.read();
            byte[] message = new byte[size];
            is.read(message, 0, size);
            Platform.runLater(() -> items.getChildren().add(new Text(new String(message))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
