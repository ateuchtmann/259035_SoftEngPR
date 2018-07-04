package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.CurrentSession;
import models.Lists;
import resources.LoadDependences;
import views.AlertBox;
import views.ConfirmBox;
import views.PopupWindowFirst;
import views.PopupWindowSecond;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeleteTaskGroupControl implements Initializable {
    //Set Variables
    int currentProjectID = CurrentSession.getInstance().getSessionProjectID();
    int currentTaskGroupID = CurrentSession.getInstance().getSessionTaskGroupID();
    int currentTaskGroupIDListIndex = Lists.getInstance().getTaskGroupListIndex(currentTaskGroupID);
    String taskGroupName = "";

    //SceneControls
    @FXML private TextField textFieldTaskGroupName;
    @FXML private RadioButton radioButtonDeleteTaskGroup;
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
            //Set Defaults
        setDefaultTaskGroupSettings();

        buttonCancel.setOnAction(event -> {
            close();
        });

        buttonSubmit.setOnAction(event -> {
            if (!(radioButtonDeleteTaskGroup.isSelected())) {
                if (editTaskGroup()) {
                    AlertBox.display("Notification", "TaskGroup successfully changed!");
                    close();
                } else {
                    AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                }
            } else {
                if (deleteTaskGroup()) {
                    AlertBox.display("Notification", "TaskGroup successfully deleted!");
                    close();
                } else {
                    AlertBox.display("Notification", "No Action exectuted!");
                    setDefaultTaskGroupSettings();
                }
            }
        });
    }

    @FXML
    public boolean editTaskGroup() {

        if (textFieldTaskGroupName.getText().isEmpty()) {
            return false;
        } else {
            //Get New TaskGroupValues
            taskGroupName = textFieldTaskGroupName.getText();

            //Save Changes to the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();

            databaseUpdate.updateLoadObject(Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex), currentTaskGroupID);

            Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex).setTaskGroupName(taskGroupName);

            databaseUpdate.updateSaveObject(Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex), currentTaskGroupID);
            Connection.closeConnection();

            System.out.println("TaskGroup [ID]: " + currentTaskGroupID + " changed");

            return true;
        }
    }

    @FXML
    public boolean deleteTaskGroup() {

        if (Lists.getInstance().getTaskGroupTaskQuantity(currentTaskGroupID) > 0) {
            AlertBox.display("Errormessage", "It's not allowed to delete a Taskgroup with Tasks inside!");
            return false;

        } else {
            if (ConfirmBox.display("Confirm Action", "Delete Taskgroup: " + Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex).getTaskGroupName() + " ?")) {

                //Delete from Database
                Connection.openConnection();
                DatabaseUpdate databaseUpdate = new DatabaseUpdate();
                databaseUpdate.deleteObject(Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex), currentTaskGroupID);
                Connection.closeConnection();

                //Delete from the TaskGroupList
                Lists.getInstance().getTaskGroupList().remove(currentTaskGroupIDListIndex);

                System.out.println("TaskGroup [ID]: " + currentTaskGroupID + " deleted");
                return true;
            }
            else {
                return false;
            }
        }
    }

    @FXML
    public void setDefaultTaskGroupSettings() {
        textFieldTaskGroupName.setText(Lists.getInstance().getTaskGroupList().get(currentTaskGroupIDListIndex).getTaskGroupName());
        radioButtonDeleteTaskGroup.setSelected(false);
    }

    @FXML
    public void clearScene() {
        //clear
        textFieldTaskGroupName.clear();
        radioButtonDeleteTaskGroup.setSelected(false);
    }

    @FXML
    public void close() {
        clearScene();
        CurrentSession.getInstance().setMemoryNumber(2); //2 --> opens Tab TaskGroupSetting --> EditDeleteProjectScene
        PopupWindowSecond.getPopupWindow().close();
        PopupWindowFirst.getPopupWindow().setScene(LoadDependences.getInstance().openFirstPopupScene("editDeleteProject"));
    }
}