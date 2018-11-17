package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;
import teaState.utility.ReportUtility;
import teaState.utility.Tableview;
import teaState.utility.month;

import java.io.IOException;
import java.time.LocalDate;

public class Payment {

    @FXML private AnchorPane Payment;
    @FXML private TableView<Tableview> tblPayment;
    @FXML private TableColumn<Tableview, LocalDate> date;
    @FXML private TableColumn<Tableview, String> details;
    @FXML private TableColumn<Tableview, Double> payrate;
    @FXML private TableColumn<Tableview, Integer> hoursWorked;
    @FXML private TableColumn<Tableview, Double> payment;
    @FXML private TableColumn<Tableview, String> status;
    @FXML private JFXComboBox<String> cmbYear;
    @FXML private JFXComboBox<String> cmbMonth;
    @FXML private JFXComboBox<String> selectWorker;
    @FXML private JFXComboBox<String> cmbStatus;
    @FXML private JFXButton generatePayment;
    @FXML private JFXButton btnPopulate;
    ObservableList<Tableview> listPayment;
    int workerID;
    AnchorPane pane = null;
    month m ;

    public void initialize(){
        selectWorker.setItems(DataSource.getInstance().populateWorker());
        cmbStatus.getItems().addAll("paid","not paid");
        listPayment =DataSource.getInstance().populatePaymentTable();
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        details.setCellValueFactory(new PropertyValueFactory<>("type"));
        payrate.setCellValueFactory(new PropertyValueFactory<>("payRate"));
        hoursWorked.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblPayment.setItems(listPayment);
        btnPopulate.setDisable(true);
        generatePayment.setDisable(true);
    }

    @FXML
    public void cancel() throws IOException {
        if(DataSource.getInstance().getLoggedUser().equals("admin")) {
            pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        }
        if(DataSource.getInstance().getLoggedUser().equals("staff")){
            pane = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
        }
        Payment.getChildren().setAll(pane);
    }

    @FXML
    public void generatePayment() {
        populate();
        if(listPayment.get(0).getStatus().equals("not paid")) {
            DataSource.getInstance().doPayment(listPayment);
            ReportUtility report = new ReportUtility();
            report.paymentDetails(m, selectWorker.getValue(), listPayment);
            Alert dialog = new Alert(Alert.AlertType.INFORMATION,"Payment Report generate successfully",ButtonType.OK);
            dialog.setTitle("Successfully Generated.");
            dialog.show();
            return;
        }
        Alert dialog = new Alert(Alert.AlertType.WARNING,"Couldn't generate the report",ButtonType.CLOSE);
        dialog.setTitle("Couldn't Generate");
        dialog.show();
    }

    @FXML
    public void populate() {
        workerID = DataSource.getInstance().getWorkerID(selectWorker.getValue());
        m = month.createInstance(cmbYear.getValue(),cmbMonth.getValue());
        listPayment = DataSource.getInstance().populatePaymentTable(m, cmbStatus.getValue(),workerID);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        details.setCellValueFactory(new PropertyValueFactory<>("type"));
        payrate.setCellValueFactory(new PropertyValueFactory<>("payRate"));
        hoursWorked.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblPayment.setItems(listPayment);
    }

    @FXML
    public void getSelectionModel(){
        if(!(selectWorker.getSelectionModel().isEmpty()||
             cmbYear.getSelectionModel().isEmpty() ||
             cmbMonth.getSelectionModel().isEmpty()||
             cmbStatus.getSelectionModel().isEmpty())){
             btnPopulate.setDisable(false);
             generatePayment.setDisable(false);
        }
    }
}
