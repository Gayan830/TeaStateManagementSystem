package teaState.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StaffPanel {

        @FXML private AnchorPane staffPanel;
        AnchorPane pane;

        @FXML
        public void UpdateEmployee() throws IOException{
            pane = FXMLLoader.load(getClass().getResource("EditDetails.fxml"));
            staffPanel.getChildren().setAll(pane);
        }

        @FXML
        public void addTaskDetails() throws IOException {
            pane = FXMLLoader.load(getClass().getResource("TaskDetails.fxml"));
            staffPanel.getChildren().setAll(pane);
        }

        @FXML
        public void genReports() throws IOException{
            pane = FXMLLoader.load(getClass().getResource("Reports.fxml"));
            staffPanel.getChildren().setAll(pane);
        }

        @FXML
        public void logout() throws IOException{
            pane = FXMLLoader.load(getClass().getResource("LoginAn.fxml"));
            staffPanel.getChildren().setAll(pane);
        }

    }


