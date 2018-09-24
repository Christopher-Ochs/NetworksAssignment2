/*
    Name: Phillip Tallo and Christopher Ochs
    Date: September 18, 2018
    Desc: Assignment 2 Computer Networks.  Client Server Protocol.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MessagingClient extends Application {
//    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("127.0.0.1", 8080);
//
//        InputStream is = socket.getInputStream();
//        OutputStream os = socket.getOutputStream();
//
//        int size = is.read();
//        byte[] message = new byte[size];
//        is.read(message, 0, size);
//
//        System.out.println(new String(message));
//
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Enter your Message:  ");
//        String outputMessage = reader.nextLine();
//        reader.close();
//
//        byte[] outMessage = outputMessage.getBytes();
//
//        os.write(outMessage.length);
//        os.write(outMessage);
//        os.flush();
//
//
//        socket.close();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Socket Client");

        GridPane root = new GridPane();
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTGRAY);
        primaryStage.setScene(scene);

        ClientView clientView = new ClientView(root);

        primaryStage.show();
    }
}
