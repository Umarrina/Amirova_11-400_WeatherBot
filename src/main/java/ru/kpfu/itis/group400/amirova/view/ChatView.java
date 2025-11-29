package ru.kpfu.itis.group400.amirova.view;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.group400.amirova.server.ChatBot;


public class ChatView {

    private AnchorPane root;
    private TextArea conversation;
    private TextArea input;
    private ChatBot chatBot;

    public ChatView() {
        chatBot = new ChatBot();
        createView();
    }

    public Parent getView() {
        if (root == null) {
            createView();
        }
        return root;
    }

    public void append(String message) {
        if (message != null) {
            conversation.appendText(message + System.lineSeparator());
        }
    }

    private void createView() {
        root = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);
        AnchorPane.setTopAnchor(conversation, 10.0);
        AnchorPane.setBottomAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);
        input.setPromptText("Enter your message");

        AnchorPane.setBottomAnchor(input, 10.0);
        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);

        input.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if  (event.getCode() == KeyCode.ENTER) {
                String message = input.getText().trim();

                if (!message.isEmpty()) {
                    append(message);

                    String response = chatBot.processCommand(message);

                    if ("Чат очищен".equals(response)) {
                        conversation.clear();
                        append("Чат очищен. Введите 'list' для списка команд");
                    } else {
                        append(response);
                    }

                    input.clear();
                    event.consume();
                }



            }
        });

        root.getChildren().addAll(conversation, input);
    }
}
