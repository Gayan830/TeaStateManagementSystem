package teaState.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;

import java.io.IOException;

public class EditDetails {

    @FXML private AnchorPane editDetails;
    @FXML private JFXButton btnEditLogin;
    @FXML private JFXButton btnEditContact;
    @FXML private JFXButton btnCancel12;
    AnchorPane pane;
    @FXML
    void close() throws IOException {
        if(DataSource.getInstance().getLoggedUser().equals("admin")) {
            pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        }
        if(DataSource.getInstance().getLoggedUser().equals("staff")){
            pane = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
        }
        editDetails.getChildren().setAll(pane);
    }

    @FXML
    void editContact() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("EditContact.fxml"));
        editDetails.getChildren().setAll(pane);
    }

    @FXML
    void editLogin() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("EditLogin.fxml"));
        editDetails.getChildren().setAll(pane);
    }

}
