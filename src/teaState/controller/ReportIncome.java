package teaState.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;
import teaState.utility.ReportUtility;
import teaState.utility.Tableview;
import teaState.utility.month;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReportIncome {

    @FXML private AnchorPane reportIncome;
    @FXML private TableView<Tableview> tblPluckingDetail;
    @FXML private TableColumn<Tableview, LocalDate> colDate;
    @FXML private TableColumn<Tableview, Integer> colWeight;
    @FXML private JFXComboBox<String> cmbYear;
    @FXML private JFXComboBox<String> cmbMonth;
    @FXML private JFXTextField txtPayRate;
    @FXML private Label lblIncome;

    double payrate;
    int weight;
    double income;
    ObservableList<Tableview> list = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        list = DataSource.getInstance().sortIncomeTbl();
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblPluckingDetail.setItems(list);
    }

    @FXML
    public void back() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        reportIncome.getChildren().setAll(pane);
    }

    @FXML
    public void genReportIncome() {
        sort();
        ReportUtility report = new ReportUtility();
        report.generateIncome(list,cmbYear.getValue(),cmbMonth.getValue(),weight,payrate,income);
    }

    @FXML
    public void sort() {
       if(cmbYear.getValue() == null || cmbMonth.getValue() == null || txtPayRate == null){
           Alert dialog = new Alert(Alert.AlertType.WARNING, "Please fill the fields inorder to get Income Details ",
                                    ButtonType.CLOSE);
           dialog.setTitle("Fill the fields");
           dialog.showAndWait();
           return;
       }
       month m = month.createInstance(cmbYear.getValue(),cmbMonth.getValue());
       list = DataSource.getInstance().sortIncomeTbl(m);
       colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
       tblPluckingDetail.setItems(list);
       payrate = Double.parseDouble(txtPayRate.getText());
       weight = 0;
       for(Tableview element : list){
           weight += element.getWeight();
       }
       income = payrate * weight;
       lblIncome.setText("Total Plucked Tea Weight :" +weight + "\nIncome :" + income +"\n ");
    }

}
