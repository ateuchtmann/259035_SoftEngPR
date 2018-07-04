package views;

import javafx.application.Application;
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

import static javafx.scene.text.Font.font;

public class AlertBox{

    private static Stage alertBoxWindow;

    //Constructor
    public AlertBox() {
    }

    //Methods
    public static void display(String pageTitle, String message) {
        alertBoxWindow=new Stage();

        //Set Window - settings
        alertBoxWindow.initModality(Modality.APPLICATION_MODAL);
        alertBoxWindow.setTitle(pageTitle);
        alertBoxWindow.setWidth(450);
        alertBoxWindow.setHeight(170);

        Label labelMessage = new Label();
        labelMessage.setTextFill(Color.web("#383838"));
        labelMessage.setFont(Font.font("System",FontWeight.BOLD,14));
        labelMessage.setWrapText(true);
        labelMessage.setAlignment(Pos.TOP_CENTER);
        labelMessage.setTextAlignment(TextAlignment.CENTER);
        labelMessage.setText(message);

        Button buttonOK = new Button("OK");
        buttonOK.setPrefWidth(120);
        buttonOK.setPrefHeight(30);
        buttonOK.setOnAction(event -> alertBoxWindow.close());

        VBox alertBoxLayout = new VBox(10);
        alertBoxLayout.setPadding(new Insets(5,10,5,10));
        alertBoxLayout.getChildren().addAll(labelMessage,buttonOK);
        alertBoxLayout.setAlignment(Pos.CENTER);

        Scene alertBoxScene = new Scene(alertBoxLayout);

        alertBoxWindow.setScene(alertBoxScene);
        alertBoxWindow.setResizable(false);
        alertBoxWindow.showAndWait();
    }

    //Getter&Setter
    public static Stage getAlertBoxWindow() {
        return alertBoxWindow;
    }

    public static void setAlertBoxWindow(Stage alertBoxWindow) {
        AlertBox.alertBoxWindow = alertBoxWindow;
    }
}
