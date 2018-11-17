package teaState.utility;

import java.time.LocalDate;

public class month {

    private LocalDate startDate;
    private LocalDate endDate;
    private String year;
    String month;

    private static month instance;

    public month(String year, String month) {
        this.year = year;
        this.month = month;
        getDays();
    }

    public static month createInstance(String year, String month){
        return new month(year,month);
    }

    private void getDays(){
        switch (month){
            case "January":
                startDate = LocalDate.parse(year+"-01-01");
                break;
            case "February":
                startDate = LocalDate.parse(year+"-02-01");
                break;
            case "March":
                startDate = LocalDate.parse(year+"-03-01");
                break;
            case "April":
                startDate = LocalDate.parse(year+"-04-01");
                break;
            case "May":
                startDate = LocalDate.parse(year+"-05-01");
                break;
            case "June":
                startDate = LocalDate.parse(year+"-06-01");
                break;
            case "July":
                startDate = LocalDate.parse(year+"-07-01");
                break;
            case "August":
                startDate = LocalDate.parse(year+"-08-01");
                break;
            case "September":
                startDate = LocalDate.parse(year+"-09-01");
                break;
            case "October":
                startDate = LocalDate.parse(year+"-10-01");
                break;
            case "November":
                startDate = LocalDate.parse(year+"-11-01");
                break;
            case "December":
                startDate = LocalDate.parse(year+"-12-01");
                break;
        }
        endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        System.out.println(endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
