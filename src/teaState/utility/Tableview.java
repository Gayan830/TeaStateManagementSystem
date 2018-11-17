package teaState.utility;

import java.time.LocalDate;

public class Tableview {

     private LocalDate date;
     private String type;
     private double payment;
     private int weight;
     private double payRate;
     private int hoursWorked;
     private String status;
     private int workerID;


    public Tableview(LocalDate date, String type, double payment) {
        this.date = date;
        this.type = type;
        this.payment = payment;
    }
    public Tableview(LocalDate date,int weight){
        this.date = date;
        this.weight = weight;
    }

    public Tableview(LocalDate date, String type, double payRate,int hoursWorked, double payment, String status,int workerID) {
        this.date = date;
        this.type = type;
        this.payment = payment;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
        this.status = status;
        this.workerID = workerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getPayment() {
        return payment;
    }

    public int getWeight() {
        return weight;
    }

    public double getPayRate() {
        return payRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public String getStatus() {
        return status;
    }

    public int getWorkerID() {
        return workerID;
    }
}
