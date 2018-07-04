package views;

import javafx.scene.Scene;
import resources.LoadDependences;
import resources.References;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) {

        //Start routine
        LoadDependences.getInstance().startRoutine();
        //Set Window - settings
        window = primaryStage;
        window.setTitle(References.getInstance().getWindowPageTitle());
        setDefaultResoltuion();

        //Set Different Stylesheet
        //Main.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);

        //Load StandardScene
        LoadDependences.getInstance().openMainScene(References.getInstance().getStandardSceneName());

        //Show Window
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setDefaultResoltuion() {
        window.setMaxWidth(1400);
        window.setMaxHeight(933);
        window.setMinWidth(800);
        window.setMinHeight(533);
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        Main.window = window;
    }
}