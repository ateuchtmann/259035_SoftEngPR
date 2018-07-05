package views;

import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.LoadDependences;

public class PopupWindowSecond {

    private static Stage popupWindow;

    public static void display(String sceneName,String pageTitle)  {
        popupWindow = new Stage();

        //Set Window - settings
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(pageTitle);
        //popupWindow.setWidth(450);
        //popupWindow.setHeight(250);

        popupWindow.setScene(LoadDependences.getInstance().loadScene(sceneName));
        popupWindow.sizeToScene();
        popupWindow.setResizable(false);
        popupWindow.showAndWait();
    }

    //Methods

    //Getter&Setter

    public static Stage getPopupWindow() {
        return popupWindow;
    }

    public static void setPopupWindow(Stage popupWindow) {
        PopupWindowSecond.popupWindow = popupWindow;
    }
}