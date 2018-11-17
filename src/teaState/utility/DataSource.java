package teaState.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import teaState.employee.*;

import java.sql.*;
import java.time.LocalDate;

public class DataSource {
    public static final String DB_NAME = "teastatemanagement";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "6305";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    public static final String INSERT_EMPLOYEE = "insert into employee (FirstName,LastName,Telephone,Address) values(?,?,?,?)";
    public static final String INSERT_STAFF = "insert into staff(StaffID,UserName,Password,Type) values(?,?,?,?)";
    public static final String INSERT_WORKER = "insert into worker(WorkerID) values(?)";
    public static final String QUERY_LOGIN_CHECK = "select * from staff where UserName = ?  and Password = ? ";
    public static final String INSERT_SUNDRY = "insert into sundry( Date,TaskType,PayRate,HoursWorked,WorkerID,Payment,Status )" +
                                                "values(?,?,?,?,?,?,?)";
    public static final String INSERT_TEA_PLUCKING = "insert into teaplucking( Date,Weight,PayRate,Payment,WorkerID,Status)" +
                                                     "values(?,?,?,?,?,?) ";
    public static final String QUERY_EMPLOYEE_NAMES = "select * from employee";
    public static final String QUERY_TASKDETAILS_SUNDRY = "select * from sundry";
    public static final String QUERY_SORT_TASKDETAILS_SUNDRY = "select * from sundry where Date >=  ? and Date <=? and Status = ? and WorkerID= ?";
    public static final String QUERY_TASKDETAILS_TEAPLUCKING = "select * from teaplucking";
    public static final String QUERY_SORT_TASKDETAILS_TEAPLUCKING = "select * from teaplucking where Date >= ? and Date <= ? and  Status =? and WorkerID= ?";
    public static final String QUERY_TEA_PLUCKING = "select Date,Payment from teaplucking where Date >= ? and Date <= ?";
    public static final String QUERY_SORT_SUNDRY_DETAILS =  "select Date,TaskType,Payment from sundry where Date >= ? and Date <= ? and TaskType = ?";
    public static final String QUERY_GET_TEAPLUCK_INFO = "select Date,Weight from teaplucking where Date >= ? and Date <= ?";
    public static final String QUERY_UPDATE_CONTACT = "update employee set Telephone = ? ,Address = ? where EmployeeID = ?";
    public static final String QUERY_UPDATE_USERNAME_PASSWORD = "update staff set UserName = ?,Password = ? where StaffID = ?";
    public static final String QUERY_UPDATE_PAYMENT_SUNDRY = "update sundry set Status = 'paid' where WorkerID = ?";
    public static final String QUERY_UPDATE_PAYMENT_TEAPLUCKING = "update sundry set Status = 'paid' where WorkerID = ?";
    private PreparedStatement insertEmployee;
    private PreparedStatement insertStaff;
    private PreparedStatement insertWorker;
    private PreparedStatement queryWorker;
    private PreparedStatement queryLoginCheck;
    private PreparedStatement queryEmployeeNames;
    private PreparedStatement insertSundry;
    private PreparedStatement insertTeaPlucking;
    private PreparedStatement queryTaskDetailSundry;
    private PreparedStatement queryTaskDetailTeaPlucking;
    private PreparedStatement querySortTaskDetailSundry;
    private PreparedStatement queryUpdatePaymentSundry;
    private PreparedStatement queryUpdatePaymentTeaPlucking;
    private PreparedStatement queryTaskSortDetailTeaPlucking;
    private PreparedStatement querySortSundryDetails;
    private PreparedStatement queryTeaPlucking;
    private PreparedStatement queryGetTeaPluckingInfo;
    private PreparedStatement queryUpdateContact;
    private PreparedStatement updateUsernamePassword;


    private static Statement statement;
    private static ResultSet individualUser;
    private static String user;
    private Connection conn;

    public DataSource() {}
    private static DataSource instance = new DataSource();
    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            insertEmployee = conn.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            insertStaff = conn.prepareStatement(INSERT_STAFF, Statement.RETURN_GENERATED_KEYS);
            insertWorker = conn.prepareStatement(INSERT_WORKER, Statement.RETURN_GENERATED_KEYS);
            queryLoginCheck = conn.prepareStatement(QUERY_LOGIN_CHECK, Statement.RETURN_GENERATED_KEYS);
            queryEmployeeNames = conn.prepareStatement(QUERY_EMPLOYEE_NAMES, Statement.RETURN_GENERATED_KEYS);
            insertSundry = conn.prepareStatement(INSERT_SUNDRY, Statement.RETURN_GENERATED_KEYS);
            insertTeaPlucking = conn.prepareStatement(INSERT_TEA_PLUCKING, Statement.RETURN_GENERATED_KEYS);
            queryTaskDetailSundry = conn.prepareStatement(QUERY_TASKDETAILS_SUNDRY);
            queryTaskDetailTeaPlucking = conn.prepareStatement(QUERY_TASKDETAILS_TEAPLUCKING);
            querySortTaskDetailSundry = conn.prepareStatement(QUERY_SORT_TASKDETAILS_SUNDRY,statement.RETURN_GENERATED_KEYS);
            queryTaskSortDetailTeaPlucking = conn.prepareStatement(QUERY_SORT_TASKDETAILS_TEAPLUCKING,Statement.RETURN_GENERATED_KEYS);
            querySortSundryDetails = conn.prepareStatement(QUERY_SORT_SUNDRY_DETAILS,Statement.RETURN_GENERATED_KEYS);
            queryTeaPlucking = conn.prepareStatement(QUERY_TEA_PLUCKING,Statement.RETURN_GENERATED_KEYS);
            queryGetTeaPluckingInfo = conn.prepareStatement(QUERY_GET_TEAPLUCK_INFO,Statement.RETURN_GENERATED_KEYS);
            queryUpdateContact = conn.prepareStatement(QUERY_UPDATE_CONTACT,Statement.RETURN_GENERATED_KEYS);
            queryUpdatePaymentSundry = conn.prepareStatement(QUERY_UPDATE_PAYMENT_SUNDRY,Statement.RETURN_GENERATED_KEYS);
            updateUsernamePassword = conn.prepareStatement(QUERY_UPDATE_USERNAME_PASSWORD,Statement.RETURN_GENERATED_KEYS);
            queryUpdatePaymentTeaPlucking = conn.prepareStatement(QUERY_UPDATE_PAYMENT_TEAPLUCKING,Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't connect the database " + ex.getMessage());
            return false;
        }

    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void insertEmployee(Employee employee){
        try {
            System.out.println(employee.getFirstName());
            insertEmployee.setString(1, employee.getFirstName());
            insertEmployee.setString(2, employee.getLastName());
            insertEmployee.setString(3, employee.getTelephone());
            insertEmployee.setString(4, employee.getAddress());
            int affectedRows = insertEmployee.executeUpdate();
            if(affectedRows != 1)
                System.out.println("couldn't insert data to employee table.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean registerWorker(Worker worker) {
        try {
            insertEmployee(new Employee(worker.getFirstName(),worker.getLastName(),
                                worker.getTelephone(),worker.getAddress()));
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from employee");
            rs.last();
            int id = rs.getInt("EmployeeID");
            System.out.println(id);
            insertWorker.setInt(1, id);
            int affectedRows = insertWorker.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Couldn't insert the data to Worker table.");
                return false;
            }
            return true;
        } catch (SQLException ex) {
            ex.getMessage();
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean registerStaff(Staff staff) {
        try {
            insertEmployee(new Employee(staff.getFirstName(),staff.getLastName(),
                                staff.getTelephone(),staff.getAddress()));
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from employee");
            rs.last();

            int id = rs.getInt("EmployeeID");
            insertStaff.setInt(1, id);
            insertStaff.setString(2, staff.getUserName());
            insertStaff.setString(3, staff.getPassword());
            insertStaff.setString(4,staff.getType().toLowerCase());
            int affectedRows = insertStaff.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Couldn't insert the data to staff table.");
                return false;
            }
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private void loggedUser(ResultSet rs) throws SQLException {
        user = rs.getString(4).toLowerCase();
        individualUser = rs;
    }
    public String getLoggedUser(){
        return user;
    }
    public ResultSet getIndividualUser(){ return individualUser;}

    public boolean checkLogin(String userName, String password) {
        try {
            queryLoginCheck.setString(1, userName);
            queryLoginCheck.setString(2, password);
            ResultSet rs = queryLoginCheck.executeQuery();
            if (rs.next() == false) {
                System.out.println("userName or Password Incorrect!");
                return false;
            }
            loggedUser(rs);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public ObservableList<String> populateWorker() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from employee INNER join worker ON employee.employeeID = worker.WorkerID");
            ObservableList<String> arr = FXCollections.observableArrayList();
            String name;
            while (rs.next() != false) {
                name = rs.getString(2) + " " + rs.getString(3);
                arr.add(name);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ObservableList<String> populateEmployees() {
        try {
            ResultSet rs = queryEmployeeNames.executeQuery();
            ObservableList<String> arr = FXCollections.observableArrayList();
            String name;
            while (rs.next() != false) {
                name = rs.getString(2) + " " + rs.getString(3);
                arr.add(name);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int getWorkerID(String fullName) {
        try {
            ResultSet rs = queryEmployeeNames.executeQuery();
            while (rs.next() != false) {
                if (fullName.equals(rs.getString(2) + " " + rs.getString(3))) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }


    public Employee getEmployee(String fullName){
        try {
            ResultSet rs = queryEmployeeNames.executeQuery();
            while (rs.next() != false) {
                if (fullName.equals(rs.getString(2) + " " + rs.getString(3))) {
                    return new Employee(rs.getInt(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5));
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean updateContacts(Employee emp){
        try{
            System.out.println(emp.getTelephone());
            queryUpdateContact.setString(1,emp.getTelephone());
            queryUpdateContact.setString(2,emp.getAddress());
            queryUpdateContact.setInt(3,emp.getEmployeeID());
            int affectedRows = queryUpdateContact.executeUpdate();
            if(affectedRows != 1){
                System.out.println("Couldn't update the record.");
                return false;
            }
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateLogin(String userName,String password){
        try {
            int staffID = individualUser.getInt(1);
            updateUsernamePassword.setString(1,userName);
            updateUsernamePassword.setString(2,password);
            updateUsernamePassword.setInt(3,staffID);
            int affectedRows = updateUsernamePassword.executeUpdate();
            if(affectedRows != 1) {
                System.out.println("Cannot update the record");
                return false;
            }
            return true;
        }catch (SQLException ex){
            ex.getMessage();
            return false;
        }
    }

    public boolean addTeaPluckingDetails(TeaPlucking teaPlucking) {
        try {
            insertTeaPlucking.setDate(1, Date.valueOf(teaPlucking.getDate()));
            insertTeaPlucking.setInt(2, teaPlucking.getWeight());
            insertTeaPlucking.setDouble(3, teaPlucking.getPayRate());
            insertTeaPlucking.setDouble(4, teaPlucking.getPayment());
            insertTeaPlucking.setInt(5, teaPlucking.getWorkerID());
            insertTeaPlucking.setString(6, teaPlucking.getStatus());
            int affectedRows = insertTeaPlucking.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean addSundryDetails(Sundry sundry) {
        try {
            insertSundry.setDate(1, Date.valueOf(sundry.getDate()));
            insertSundry.setString(2, sundry.getDescription());
            insertSundry.setDouble(3, sundry.getHourlyPayRate());
            insertSundry.setInt(4, sundry.getHoursWorked());
            insertSundry.setInt(5, sundry.getWorkerID());
            insertSundry.setDouble(6, sundry.getPayment());
            insertSundry.setString(7, sundry.getStatus());
            int affectedRows = insertSundry.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ObservableList<Tableview> populatePaymentTable(){
        try {
            ResultSet rs = queryTaskDetailSundry.executeQuery();
            ObservableList<Tableview> arr = FXCollections.observableArrayList();
            while (rs.next() != false) {
                arr.add(new Tableview(rs.getDate(1).toLocalDate(), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getInt(7)));
            }
            rs= queryTaskDetailTeaPlucking.executeQuery();
            while (rs.next() != false) {
                arr.add(new Tableview(rs.getDate(1).toLocalDate(), "Tea Plucking", rs.getDouble(3),
                        rs.getInt(2), rs.getDouble(4), rs.getString(6),
                        rs.getInt(5)));
            }
            return arr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

   public ObservableList<Tableview> populatePaymentTable(month m,String status,int workerID){
        try {
            querySortTaskDetailSundry.setDate(1,Date.valueOf(m.getStartDate()));
            querySortTaskDetailSundry.setDate(2,Date.valueOf(m.getEndDate()));
            querySortTaskDetailSundry.setString(3,status);
            querySortTaskDetailSundry.setInt(4,workerID);
            ResultSet rs = querySortTaskDetailSundry.executeQuery();

            ObservableList<Tableview> arr = FXCollections.observableArrayList();
            while (rs.next() != false) {
                arr.add(new Tableview(rs.getDate(1).toLocalDate(), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getInt(7)));
            }
            queryTaskSortDetailTeaPlucking.setDate(1,Date.valueOf(m.getStartDate()));
            queryTaskSortDetailTeaPlucking.setDate(2,Date.valueOf(m.getEndDate()));
            queryTaskSortDetailTeaPlucking.setString(3,status);
            queryTaskSortDetailTeaPlucking.setInt(4,workerID);
            rs= queryTaskDetailTeaPlucking.executeQuery();
            while (rs.next() != false) {
                arr.add(new Tableview(rs.getDate(1).toLocalDate(), "Tea Plucking", rs.getDouble(3),
                        rs.getInt(2), rs.getDouble(4), rs.getString(6),
                        rs.getInt(5)));
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void doPayment(ObservableList<Tableview> payments){
        try {
            int affectedRows;
            System.out.println(payments.get(0).getWorkerID());
            for(Tableview payment:payments){
                if(payment.getType().equals("Tea Plucking")) {
                    queryUpdatePaymentTeaPlucking.setInt(1,payment.getWorkerID());
                    affectedRows = queryUpdatePaymentTeaPlucking.executeUpdate();
                    if(affectedRows != 1){
                        System.out.println("couldn't update record.");
                    }
                }else{
                    queryUpdatePaymentSundry.setInt(1,payment.getWorkerID());
                    affectedRows = queryUpdatePaymentSundry.executeUpdate();
                    if(affectedRows != 1){
                        System.out.println("couldn't update record.");
                    }
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private ObservableList<Tableview> getSortedSundryList(LocalDate startDate, LocalDate endDate, String type){
        try{
            ResultSet rs;
            ObservableList<Tableview> arr = FXCollections.observableArrayList();
            if(!type.equals("all")) {
                querySortSundryDetails.setDate(1, Date.valueOf(startDate));
                querySortSundryDetails.setDate(2, Date.valueOf(endDate));
                querySortSundryDetails.setString(3, type);
                rs = querySortSundryDetails.executeQuery();

                while (rs.next() != false) {
                    arr.add(new Tableview(rs.getDate(1).toLocalDate(),
                            rs.getString(2),
                            rs.getDouble(3)));
                }
            }
            else{
                rs = statement.executeQuery("select Date,TaskType,Payment from sundry where Date <= "+
                        startDate + "Date >= "+endDate);
                while (rs.next() != false) {
                    arr.add(new Tableview(rs.getDate(1).toLocalDate(),
                            rs.getString(2),
                            rs.getDouble(3)));
                }
            }
            return arr;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private ObservableList<Tableview> getTeaPluckingList(LocalDate startDate, LocalDate endDate) {
        try {
            queryTeaPlucking.setDate(1, Date.valueOf(startDate));
            queryTeaPlucking.setDate(2, Date.valueOf(endDate));
            ResultSet rs = queryTeaPlucking.executeQuery();
            ObservableList<Tableview> arr = FXCollections.observableArrayList();
            while (rs.next() != false) {
                arr.add(new Tableview(rs.getDate(1).toLocalDate(),
                        "TeaPlucking",
                        rs.getDouble(2)));
            }
            return arr;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
    public ObservableList<Tableview> expenseInstances (LocalDate startDate, LocalDate endDate, String type) {
        ObservableList<Tableview> list =  FXCollections.observableArrayList();
        if(type.equals("all")){
             list.addAll(getTeaPluckingList(startDate,endDate));
             list.addAll(getSortedSundryList(startDate,endDate,type));
             return list;
        }
        else if(type.equals("tea Plucking")){
            list.addAll(getTeaPluckingList(startDate,endDate));
            return list;
        }
        else {
            list.addAll(getSortedSundryList(startDate,endDate,type));
            return list;
        }
    }

    public ObservableList<Tableview> populateExpenseTable() throws SQLException{
        Statement  statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("select Date,TaskType,Payment from sundry");
        ObservableList<Tableview> arr = FXCollections.observableArrayList();
        while (rs.next() != false) {
            arr.add(new Tableview(rs.getDate(1).toLocalDate(),
                                  rs.getString(2),
                                  rs.getDouble(3)));
        }
        rs = statement.executeQuery("select Date,Payment from teaPlucking");
        while (rs.next() != false){
            arr.add(new Tableview(rs.getDate(1).toLocalDate(),
                            "tea Plucking",
                                 rs.getDouble(2)));
        }
        return arr;
    }
    public ObservableList<Tableview> sortIncomeTbl() throws SQLException{
        ObservableList<Tableview> arr = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select Date,Weight from teaPlucking ");
        while(rs.next() != false){
            arr.add(new Tableview(rs.getDate(1).toLocalDate(),rs.getInt(2)));
        }
        return arr;
    }
    public ObservableList<Tableview> sortIncomeTbl(month m){
        try {
            queryGetTeaPluckingInfo.setDate(1,Date.valueOf(m.getStartDate()));
            queryGetTeaPluckingInfo.setDate(2,Date.valueOf(m.getEndDate()));
            ResultSet rs = queryGetTeaPluckingInfo.executeQuery();
            ObservableList<Tableview> arr = FXCollections.observableArrayList();
            while(rs.next() != false){
                arr.add(new Tableview(rs.getDate(1).toLocalDate(),rs.getInt(2)));
            }
            return arr;
        }catch (SQLException ex){
            ex.getMessage();
            return null;
        }
    }
}