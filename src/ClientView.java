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

public class ClientView {

    private GridPane root;

    private TextField messageField;
    private Button sendMessageButton;
    private ScrollPane scrollPane;
    private VBox items;

    public ClientView(GridPane root) {
        this.root = root;
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
            }
        });
        sendMessageButton.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        items.getChildren().add(new Text("MESSAGE"));
    }
}
