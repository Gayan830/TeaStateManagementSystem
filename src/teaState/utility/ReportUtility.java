package teaState.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class ReportUtility {
    Document document = new Document();
    Paragraph paragraph = new Paragraph();
    PdfPTable table;
    String fileName;
    PdfPCell cell;

    public void  paymentDetails (month m ,String name, ObservableList<Tableview> payment){
        fileName = "C:\\Users\\Windhelm\\IdeaProjects\\TeaStateManagementSystem\\Reports"
                +name+"-"+m.getStartDate()+"-"+m.getEndDate()+" Payment.pdf";
        Rectangle pageSize = new Rectangle(500, 842);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        try {
            PdfWriter.getInstance(document,new FileOutputStream(fileName));
            document.open();
            document.setPageSize(PageSize.A4);
            document.setMargins(30,30,30,30);
            table = new PdfPTable(5);
            cell = new PdfPCell(new Phrase("Name: "+name+"\nWorkerID:"+payment.get(0).getWorkerID()+
                    "\nStart Date: "+m.getStartDate()+"   End Date: "+m.getEndDate()));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setIndent(80);
            cell.setPadding(20);
            cell.setRowspan(3);
            cell.setColspan(5);
            table.addCell(cell);
            table.addCell("Date");
            table.addCell("Type");
            table.addCell("Hours Worked/Weight");
            table.addCell("Payrate");
            table.addCell("Payment");
            double overallPayment = 0.0;
            for (Tableview element: payment) {
                table.addCell(element.getDate().toString());
                table.addCell(element.getType());
                table.addCell(Integer.toString(element.getHoursWorked()));
                table.addCell(Double.toString(element.getPayRate()));
                table.addCell(Double.toString(element.getPayment()));
                overallPayment  += element.getPayment();
            }
            paragraph.setSpacingBefore(20);
            document.add(table);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add("Payment : "+ overallPayment);
            document.close();
        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void generateIncome(ObservableList<Tableview> list, String year,String month,int weight,double payrate,double income){
        fileName = "C:\\Users\\Windhelm\\IdeaProjects\\TeaStateManagementSystem\\Reports" +year+" "+month+"IncomeReport.pdf";
        Rectangle pageSize = new Rectangle(500, 842);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        document = new Document(pageSize);
        try {
            PdfWriter.getInstance(document,new FileOutputStream(fileName));
            document.open();
            paragraph.setSpacingAfter(20);
            paragraph.add("Year : " + year + "    Month : " + month);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.setMargins(20,20,30,30);
            document.add(paragraph);
            document.setPageSize(PageSize.A4);
            table = new PdfPTable(2);
            table.setWidths(new float[]{5,2});
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Date");
            table.addCell("Plucked Tea Weight");
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            for(Tableview item:list){
                table.addCell(item.getDate().toString());
                table.addCell(Integer.toString(item.getWeight()));
            }
            paragraph.clear();
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setSpacingBefore(20);
            paragraph.add("\t\tOverall Plucked Tea Weight: "+weight+"\n\t\tPayRate: "+payrate+"\n \t\tIncome: "+ income);
            document.add(table);
            document.add(paragraph);
            document.close();
        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateExpense(ObservableList<Tableview> expense, LocalDate startDate,LocalDate endDate,
                                String type, double totalExpenses){
        fileName = "C:\\Users\\Windhelm\\IdeaProjects\\TeaStateManagementSystem\\Reports" +startDate+" "+endDate+"ExpenseReport.pdf";
        Rectangle pageSize = new Rectangle(500, 842);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        document = new Document(pageSize);
        try{
           PdfWriter.getInstance(document,new FileOutputStream(fileName));
           document.open();
           document.setPageSize(PageSize.A4);
           document.setMargins(30,30,30,30);
           paragraph.add("Expense Report");
           paragraph.add("\nStart Date: "+startDate+"End Date: "+endDate +"\n"+type);
           document.add(paragraph);
           paragraph.setSpacingAfter(20);
           table = new PdfPTable(3);
           table.setSpacingBefore(20);
           table.setWidths(new int[]{2,5,2});
           table.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell("Date");
           table.addCell("Description");
           table.addCell("Payment");
            for(Tableview item:expense){
                table.addCell(item.getDate().toString());
                table.addCell(item.getType());
                table.addCell(Double.toString(item.getPayment()));
            }
            document.add(table);
            paragraph.setSpacingAfter(20);
            paragraph.setSpacingBefore(20);
            Paragraph para2 = new Paragraph();
            para2.setSpacingBefore(20);
            para2.add("Total expenses: "+totalExpenses);
            document.add(para2);
            document.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
