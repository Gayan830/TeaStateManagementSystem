package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import teaState.employee.Staff;
import teaState.employee.Worker;
import teaState.utility.DataSource;
import java.io.IOException;
import java.time.LocalDate;

public class Registration {

        @FXML private AnchorPane registration;
        @FXML private JFXDatePicker dateJoined;
        @FXML private JFXButton btnRegister;
        @FXML private JFXTextField firstName;
        @FXML private JFXTextField lastName;
        @FXML private JFXTextField address;
        @FXML private JFXTextField telephone;
        @FXML private JFXComboBox<String> accountType;
        @FXML private JFXTextField userName;
        @FXML private JFXButton btnReset;
        @FXML private JFXButton btnCancel;
        @FXML private JFXPasswordField password;

        AnchorPane pane = null;
        boolean disableRegister;
        @FXML
        public void initialize(){
            System.out.println(accountType.getSelectionModel().isEmpty());
            System.out.println(accountType.getValue());
            userName.setDisable(true);
            password.setDisable(true);
            btnRegister.setDisable(true);
            accountType.getItems().addAll("Staff","Worker","Admin");
        }


        @FXML
        public void cancel() throws IOException {
            if(DataSource.getInstance().getLoggedUser().equals("admin")) {
                 pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            }
            if(DataSource.getInstance().getLoggedUser().equals("staff")){
                 pane = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
            }
            registration.getChildren().setAll(pane);
        }
        @FXML
        public void register(){
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to enter the employee? ",
                    ButtonType.YES, ButtonType.NO);
            dialog.setTitle("Confirm Registration");
            dialog.showAndWait();


            LocalDate date = dateJoined.getValue();
            if (dialog.getResult() == ButtonType.YES) {
                if (accountType.getValue() == "Worker") {
                    boolean isTrue = DataSource.getInstance().registerWorker(
                                     new Worker(firstName.getText(),
                                                lastName.getText(),
                                                address.getText(),
                                                telephone.getText(),
                                                date));
                    if (isTrue == false) {
                        dialog.setAlertType(Alert.AlertType.WARNING);
                        dialog.setTitle("cannot insert");
                        dialog.showAndWait();
                        dialog.setContentText("Couldn't Insert the worker table.");
                    }else {
                        dialog = new Alert(Alert.AlertType.INFORMATION,"Successfully Inserted to the Worker table.",ButtonType.OK);
                        dialog.setTitle("Successfully Inserted.");
                        dialog.show();
                    }
                }
                if (accountType.getValue() != "Worker") {
                    boolean isTrue = DataSource.getInstance().registerStaff(new Staff(firstName.getText(),
                            lastName.getText(),
                            address.getText(),
                            telephone.getText(),
                            date, userName.getText(),
                            password.getText(),
                            accountType.getValue()));
                    if (isTrue == false) {
                        dialog.setAlertType(Alert.AlertType.WARNING);
                        dialog.setTitle("cannot insert");
                        dialog.show();
                        dialog.setContentText("Couldn't Insert the Staff table.");
                    }else {
                        dialog = new Alert(Alert.AlertType.INFORMATION,"Successfully Inserted to the Staff table.",ButtonType.OK);
                        dialog.setTitle("Successfully Inserted.");
                        dialog.show();
                    }
                }
                firstName.clear();
                lastName.clear();
                telephone.clear();
                address.clear();
                dateJoined.setValue(null);
                accountType.setValue(null);
            }
        }

        @FXML
        public void reset() {
            firstName.clear();
            lastName.clear();
            password.clear();
            userName.clear();
            telephone.clear();
            address.clear();
            dateJoined.setValue(null);
            accountType.setValue(null);
        }
    @FXML
    public void getSelection(){
        if(accountType.getValue() == null){
            return;
        }
        if(accountType.getValue() != "Worker"){
            userName.setDisable(false);
            password.setDisable(false);
            disableRegister = false;
            handleKey();
            return;
        }
        userName.setDisable(true);
        password.setDisable(true);
        handleKey();
    }

    @FXML
        public void handleKey(){
            disableRegister = firstName.getText().trim().isEmpty()||
                                      lastName.getText().trim().isEmpty() ||
                                      telephone.getText().trim().isEmpty()||
                                      address.getText().trim().isEmpty();
            if(accountType.getSelectionModel().isEmpty() ){
                disableRegister =true;
            }else if( !accountType.getValue().equals("Worker")){
                disableRegister =true;
                disableRegister = userName.getText().trim().isEmpty() ||
                                  password.getText().trim().isEmpty() ;
            }
            btnRegister.setDisable(disableRegister);
        }



    }


