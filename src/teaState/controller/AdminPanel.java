package teaState.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;

public class AdminPanel {
    @FXML private AnchorPane adminPanel;
    @FXML private JFXButton btnAddTask;
    @FXML private JFXButton btnUpdateEmp;
    @FXML private JFXButton btnAddEmp;
    @FXML private JFXButton btnGenerateReports;
    @FXML private JFXButton btnLogout;

    AnchorPane pane;
    public void initialize() throws IOException{

    }

    @FXML
    public void addEmp() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        adminPanel.getChildren().setAll(pane);
    }

    public void addTaskDetails() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("TaskDetails.fxml"));
        adminPanel.getChildren().setAll(pane);
    }

    @FXML
    public void updateEmp() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("EditDetails.fxml"));
        adminPanel.getChildren().setAll(pane);
    }

    @FXML
    public void genReport() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        adminPanel.getChildren().setAll(pane);
    }

    @FXML
    public void logout() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("LoginAn.fxml"));
        adminPanel.getChildren().setAll(pane);
    }

}

