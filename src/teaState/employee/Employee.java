package teaState.employee;

import java.time.LocalDate;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private LocalDate dateJoined;

    public Employee(String firstName, String lastName, String address, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }
    public Employee(String firstName, String lastName, String address, String telephone, LocalDate dateJoined) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.dateJoined = dateJoined;
    }
    public Employee(int employeeID,String firstName, String lastName, String telephone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.employeeID = employeeID;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
