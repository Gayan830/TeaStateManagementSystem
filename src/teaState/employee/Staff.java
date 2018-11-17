package teaState.employee;

import java.time.LocalDate;

public class Staff extends Employee {

    private String userName;
    private String password;
    private String Type;

    public Staff(String firstName, String lastName, String address, String telephone, LocalDate dateJoined, String userName, String password,String type) {
        super(firstName, lastName, address, telephone, dateJoined);
        this.userName = userName;
        this.password = password;
        this.Type =type;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return Type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
