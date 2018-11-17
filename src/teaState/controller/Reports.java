package teaState.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import teaState.utility.DataSource;
import java.io.IOException;

public class Reports{
    @FXML AnchorPane reports;

    AnchorPane pane;
    @FXML
    public void cancel() throws IOException {
        if(DataSource.getInstance().getLoggedUser().equals("admin")) {
            pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        }
        if(DataSource.getInstance().getLoggedUser().equals("staff")){
            pane = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
        }
        reports.getChildren().setAll(pane);
    }

    @FXML
    public void genExpenseReport() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ReportExpense.fxml"));
        reports.getChildren().setAll(pane);
    }

    @FXML
    public void genIncomeReport() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("ReportIncome.fxml"));
        reports.getChildren().setAll(pane);
    }

    @FXML
    public void generatePayment() throws IOException{
        pane = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        reports.getChildren().setAll(pane);
    }

}
