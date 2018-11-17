package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import teaState.utility.DataSource;

import java.io.IOException;

public class Login {
    @FXML private MenuItem btnClose;
    @FXML private MenuBar menubarStart;
    @FXML private VBox loginVbox;
    @FXML private AnchorPane Login;
    @FXML private JFXButton btnLogin;
    @FXML private JFXTextField userName;
    @FXML private JFXButton btnCancel;
    @FXML private JFXPasswordField password;


    @FXML
    public void close() {
        Platform.exit();
    }

    @FXML
    public void login() throws IOException {
        if(!DataSource.getInstance().checkLogin(userName.getText(),password.getText())){
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("Invalid Login");
            dialog.show();
            dialog.setContentText("Username or password incorrect!");
            userName.clear();
            password.clear();
            return;
        }else if(DataSource.getInstance().getLoggedUser().equals("staff")){
            AnchorPane staffPanel = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));

            Login.getChildren().setAll(staffPanel);
        }else if(DataSource.getInstance().getLoggedUser().equals("admin")){
            AnchorPane adminPanel = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            Login.getChildren().setAll(adminPanel);
        }

    }

}

