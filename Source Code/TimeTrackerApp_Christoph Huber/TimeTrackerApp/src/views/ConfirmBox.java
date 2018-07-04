package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private static Stage confirmBoxWindow;
    static boolean userChoice=false;

    //Constructor
    public ConfirmBox() {
    }

    //Methods
    public static boolean display(String pageTitle, String message) {
        confirmBoxWindow = new Stage();

        //Set Window - settings
        confirmBoxWindow.initModality(Modality.APPLICATION_MODAL);
        confirmBoxWindow.setTitle(pageTitle);
        confirmBoxWindow.setWidth(450);
        confirmBoxWindow.setHeight(170);

        //Label Settings
        Label labelMessage = new Label();
        labelMessage.setTextFill(Color.web("#383838"));
        labelMessage.setFont(Font.font("System",FontWeight.BOLD,14));
        labelMessage.setWrapText(true);
        labelMessage.setAlignment(Pos.TOP_CENTER);
        labelMessage.setTextAlignment(TextAlignment.CENTER);
        labelMessage.setText(message);

        //Button Settings
            //Button OK
        Button buttonOK = new Button("OK");
        buttonOK.setPrefWidth(120);
        buttonOK.setPrefHeight(30);
        buttonOK.setOnAction(event -> {
            userChoice=true;
            confirmBoxWindow.close();
        });

            //Button Cancel
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setPrefWidth(120);
        buttonCancel.setPrefHeight(30);
        buttonCancel.setOnAction(event -> {
            userChoice=false;
            confirmBoxWindow.close();
        });

        // VBOX Settings
        VBox confirmBoxLayout = new VBox(10);
        confirmBoxLayout.setPadding(new Insets(5,10,5,10));
        confirmBoxLayout.getChildren().addAll(labelMessage,buttonOK,buttonCancel);
        confirmBoxLayout.setAlignment(Pos.CENTER);

        //Scenesettings
        Scene alertBoxScene = new Scene(confirmBoxLayout);
        confirmBoxWindow.setScene(alertBoxScene);
        confirmBoxWindow.setResizable(false);

        confirmBoxWindow.showAndWait();

        return userChoice;
    }

    //Getter&Setter
    public static Stage getConfirmBoxWindow() {
        return confirmBoxWindow;
    }

    public static void setConfirmBoxWindow(Stage confirmBoxWindow) {
        ConfirmBox.confirmBoxWindow = confirmBoxWindow;
    }
}
