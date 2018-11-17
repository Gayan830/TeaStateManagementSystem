package teaState.employee;

import java.time.LocalDate;

public class TeaPlucking  {
    private LocalDate date;
    private int weight;
    private double payRate;
    private double payment;
    private int workerID;
    private String status;

    public TeaPlucking(LocalDate date, int weight, double payRate, int workerID) {
        this.date = date;
        this.weight = weight;
        this.payRate = payRate;
        this.payment = payRate*weight;
        this.workerID = workerID;
        this.status = "not paid";
    }

    public TeaPlucking(LocalDate date, int weight, double payRate, double payment ,int workerID,String status) {
        this.date = date;
        this.weight = weight;
        this.payRate = payRate;
        this.payment = payment;
        this.workerID = workerID;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getWeight() {
        return weight;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getPayment() {
        return payment;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
