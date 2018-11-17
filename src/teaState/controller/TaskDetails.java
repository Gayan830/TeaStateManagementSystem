package teaState.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import teaState.employee.Sundry;
import teaState.employee.TeaPlucking;
import teaState.utility.DataSource;

import java.io.IOException;

public class TaskDetails {

    @FXML private AnchorPane taskDetails;
    @FXML private JFXDatePicker date;
    @FXML private JFXTextField hoursWorked;
    @FXML private JFXTextField payRate;
    @FXML private JFXComboBox<String> selectEmp;
    @FXML private JFXComboBox<String> taskType;
    @FXML private JFXTextField leafWeight;
    AnchorPane pane =null;

    public void initialize(){
        selectEmp.setItems(DataSource.getInstance().populateWorker());
        taskType.getItems().addAll("Tea Plucking","Tea Cutting","Tea Planting");
        leafWeight.setDisable(true);
        hoursWorked.setDisable(true);
    }

    public void getSelectionModel(){
        if(taskType.getValue().equals("Tea Plucking")){
            leafWeight.setDisable(false);
            hoursWorked.setDisable(true);
        }else{
            hoursWorked.setDisable(false);
            leafWeight.setDisable(true);
        }
    }

    @FXML
    private void add() {
        Alert dialog;
        int WorkerID = DataSource.getInstance().getWorkerID(selectEmp.getValue());
        System.out.println(WorkerID);
        if(taskType.getValue().equals("Tea Plucking")) {
            boolean isTrue = DataSource.getInstance().addTeaPluckingDetails(new TeaPlucking(date.getValue(),
                                Integer.parseInt(leafWeight.getText()), Double.parseDouble(payRate.getText()), WorkerID));
            if (isTrue == false) {
                dialog = new Alert(Alert.AlertType.WARNING, "Couldn't Insert the Tea plucking table.",ButtonType.CANCEL);
                dialog.setTitle("cannot insert");
                dialog.showAndWait();
            }else {
                dialog = new Alert(Alert.AlertType.INFORMATION, "Successfully Inserted to the Tea plucking table.",ButtonType.OK);
                dialog.setTitle("Successfully Inserted.");
                dialog.showAndWait();

            }
        }else {
            boolean isTrue = DataSource.getInstance().addSundryDetails(new Sundry(date.getValue(),taskType.getValue(),
                            Double.parseDouble(payRate.getText()),
                    Integer.parseInt(hoursWorked.getText())
                    ,WorkerID));
            if (isTrue == false) {
                dialog = new Alert(Alert.AlertType.WARNING, "Couldn't Insert the Sundry table.",ButtonType.CANCEL);
                dialog.setTitle("cannot insert");
                dialog.showAndWait();
            }else {
                dialog = new Alert(Alert.AlertType.INFORMATION, "Successfully Inserted to the Sundry table.",ButtonType.OK);
                dialog.setTitle("Successfully Inserted.");
                dialog.showAndWait();
            }
        }
    }

    @FXML
    public void cancel() throws IOException {
        if(DataSource.getInstance().getLoggedUser().equals("admin")) {
            pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        }
        if(DataSource.getInstance().getLoggedUser().equals("staff")){
            pane = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
        }
        taskDetails.getChildren().setAll(pane);
    }

    @FXML
    public void reset() {
        hoursWorked.clear();
        payRate.clear();
        leafWeight.clear();
        date.setValue(null);
        selectEmp.setValue(null);

    }


}
