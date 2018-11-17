package teaState.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;
import teaState.utility.ReportUtility;
import teaState.utility.Tableview;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReportExpense {
    @FXML private AnchorPane expenseReport;
    @FXML private JFXButton btnLogout;
    @FXML private JFXDatePicker startDate;
    @FXML private JFXDatePicker endDate;
    @FXML private JFXButton btnSort;
    @FXML private TableView<Tableview> tblExpenses;
    @FXML private TableColumn<Tableview, LocalDate> colDate;
    @FXML private TableColumn<Tableview, String> colTask;
    @FXML private TableColumn<Tableview, Double> colPayment;
    @FXML private Label lblExpenseDetail;
    @FXML private JFXComboBox<String> cmbSelectTask;
    private double totalExpenses;
    private ObservableList<Tableview> expenses;
    private ObservableList<String> tasks;

    public void initialize() throws SQLException {
        cmbSelectTask.getItems().addAll("tea Plucking","tea Cutting","tea planting","all");

        expenses = DataSource.getInstance().populateExpenseTable();
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblExpenses.setItems(expenses);
    }

    @FXML
    public void back() throws IOException {
        AnchorPane report = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        expenseReport.getChildren().setAll(report);
    }

    @FXML
    public void genReportExpense() {
        sort();
        ReportUtility report = new ReportUtility();
        report.generateExpense(expenses,startDate.getValue(),endDate.getValue(),cmbSelectTask.getValue(),totalExpenses);
    }

    @FXML
    public void sort() {
        totalExpenses = 0.0;
        expenses = DataSource.getInstance().expenseInstances(startDate.getValue(),endDate.getValue(),cmbSelectTask.getValue());
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblExpenses.setItems(expenses);
        for(Tableview expense:expenses) {
            totalExpenses += expense.getPayment();
        }
        lblExpenseDetail.setText("Date: " + startDate.getValue() + " \nto " + endDate.getValue() +
                                    " \nTotal Expense: "+totalExpenses);
    }

}
