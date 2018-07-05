package resources;


public class References {

    //Instance of this Class, to work with the Lists
    private static References instance;

    //Set StandardScene
    private String standardSceneName = "login";

    //LabelContents
    private String windowPageTitle ="TimeTrackerApp";
    private String labelLeftStatus="TimeTrackerApp \u00a9 SE_PR Team 3";
    private String labelRightStatus="Version 1.0.0 RC1";

    //Hibernate
    private String hibernateConfigPath = "resources/hibernate.cfg.xml";

    //FXML File Paths
    private String fxmlPath = "../resources/fxml/";

    //Constructor

    public References() {
    }

    //Getter

    //Create an Instance of this Class
    public static References getInstance(){
        if (instance == null){
            instance = new References();
        }
        return instance;
    }

    //Constants
    public String getStandardSceneName() {
        return standardSceneName;
    }
    public String getWindowPageTitle() {
        return windowPageTitle;
    }
    public String getLabelLeftStatus() {
        return labelLeftStatus;
    }
    public String getLabelRightStatus() {
        return labelRightStatus;
    }
    public String getHibernateConfigPath() {
        return hibernateConfigPath;
    }
    public String getFxmlPath() {
        return fxmlPath;
    }
}