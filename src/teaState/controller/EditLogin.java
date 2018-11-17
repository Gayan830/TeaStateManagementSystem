package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;

import java.io.IOException;
import java.sql.SQLException;

public class EditLogin {

    @FXML private AnchorPane editLogin;
    @FXML private JFXButton btnChange;
    @FXML private JFXTextField userName;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField newPassword;
    @FXML private JFXPasswordField confirmPassword;
    @FXML private JFXTextField newUserName;
    @FXML private JFXButton btnClose;

    /*==============================================================================
                change() method eka run wenne change kiyana button eka click karama.
                change() eken wenne login wechcha kenage username ekai password ekai wenas karano
        ================================================================================*/
    @FXML
    public void change() throws SQLException {
        String user = DataSource.getInstance().getIndividualUser().getString(2);
        String pw = DataSource.getInstance().getIndividualUser().getString(3);
        if(userName.getText().equals(user) && password.getText().equals(pw)){
            if(newPassword.getText().equals(confirmPassword.getText()) &&
              !newUserName.getText().trim().isEmpty()){
                if(DataSource.getInstance().updateLogin(newUserName.getText(),newPassword.getText())){
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION,"Successfully Updated Username and Password",ButtonType.OK);
                    dialog.setTitle("Successfully Updated.");
                    dialog.show();
                    return;
                }
            }
        }
        Alert dialog = new Alert(Alert.AlertType.WARNING,"Cannot update Login details",ButtonType.OK);
        dialog.setTitle("Can't Update");
        dialog.show();
    }
    /*==============================================================================
                    back() method eka run wenne back kiyana button eka click karama.
                    back() eken wenne Edit details kiyana eka display wena eka
            ================================================================================*/
    @FXML
    public void back() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditDetails.fxml"));
        editLogin.getChildren().setAll(pane);
    }

}

