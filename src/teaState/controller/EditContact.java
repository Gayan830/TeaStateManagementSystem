package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import teaState.employee.Employee;
import teaState.utility.DataSource;

import java.io.IOException;

public class EditContact {
    @FXML private AnchorPane editContact;
    @FXML private JFXTextField newTelephone;
    @FXML private JFXTextField newAddress;
    @FXML private JFXComboBox<String> selectEmployee;
    @FXML private JFXButton btnCancel;
    @FXML private JFXButton btnChange;
    ObservableList<String> employees;
    Employee emp;
    public void initialize(){
        employees = DataSource.getInstance().populateEmployees();
        selectEmployee.getItems().setAll(employees);
    }

    @FXML
    public void back() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditDetails.fxml"));
        editContact.getChildren().setAll(pane);
    }

    @FXML
    public void change() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Update contacts ",
                ButtonType.YES, ButtonType.NO);
        dialog.setTitle("Update Contact");
        dialog.showAndWait();
        emp.setTelephone(newTelephone.getText());
        emp.setAddress(newAddress.getText());
        System.out.println(newTelephone.getText());
        System.out.println(newAddress.getText());
        if(DataSource.getInstance().updateContacts(emp)){
            dialog= new Alert(Alert.AlertType.INFORMATION,"Successfully Updated the contact Details",ButtonType.OK);
            dialog.setTitle("Successfully Updated");
            dialog.show();
            return;
        }
    }

    @FXML
    public void getSelectionModel() {
        emp = DataSource.getInstance().getEmployee(selectEmployee.getValue());
        newTelephone.setText(emp.getTelephone());
        newAddress.setText(emp.getAddress());

    }

}

