package ru.kpfu.itis.group400.amirova;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChatApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chat Bot");
        stage.setOnCloseRequest(event -> System.exit(0));

        chatView = new ChatView();

        root = new BorderPane();

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();


    }
}
