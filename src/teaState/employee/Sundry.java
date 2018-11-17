package teaState.employee;

import java.time.LocalDate;

public class Sundry{
    private LocalDate date;
    private String description;
    private double hourlyPayRate;
    private int hoursWorked;
    private double payment;
    private String status;
    private int WorkerID;

    public Sundry(LocalDate date, String description, double hourlyPayRate, int hoursWorked,double payment,String status,int workerID) {
        this.date = date;
        this.description = description;
        this.hourlyPayRate = hourlyPayRate;
        this.hoursWorked = hoursWorked;
        this.payment = payment;
        this.status = status;
        this.WorkerID = workerID;
    }
    public Sundry(LocalDate date, String description, double hourlyPayRate, int hoursWorked,int workerID) {
        this.date = date;
        this.description = description;
        this.hourlyPayRate = hourlyPayRate;
        this.hoursWorked = hoursWorked;
        this.payment = hourlyPayRate * hoursWorked;
        this.status = "not paid";
        this.WorkerID = workerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }

    public int getWorkerID() {
        return WorkerID;
    }
}
