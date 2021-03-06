/*
    Name: Phillip Tallo and Christopher Ochs
    Date: September 18, 2018
    Desc: Assignment 2 Computer Networks.  Client Server Protocol.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MessagingClient extends Application {
    /*
        This will start up a new client GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Socket Client");

        GridPane root = new GridPane();
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTGRAY);
        primaryStage.setScene(scene);

        ClientView clientView = new ClientView(root, primaryStage);

        primaryStage.show();
    }
}
