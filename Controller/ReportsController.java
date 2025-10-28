/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lodgeman.lalitman.controller.reports.CreditBillRecv;
import lodgeman.lalitman.controller.reports.CreditOwnersReport;
import lodgeman.lalitman.controller.reports.Customerdetails;
import lodgeman.lalitman.controller.reports.Expense;
import lodgeman.lalitman.controller.reports.PaymentTransactionReport;
import lodgeman.lalitman.controller.reports.RoomConversion;
import lodgeman.lalitman.controller.reports.RoomwiserevenueReport;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.Ledgers;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ReportsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField Conversionfromdate;
    
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Conversiontodate;
     
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Customerdetailsfromdate;
    
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Customerdetailstodate; 
     
      @FXML
    private jfxtras.labs.scene.control.CalendarTextField Expensefromdate;
    
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Expensetodate; 
     
      @FXML
    private jfxtras.labs.scene.control.CalendarTextField Creditrecvfromdate;
    
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Creditrecvtodate; 
     
     @FXML
     private TextField RoomwiseRoomno;
     
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Roomwisefromdate;
    
     @FXML
    private jfxtras.labs.scene.control.CalendarTextField Roomwisetodate; 
     
     @FXML
     private ComboBox<Ledgers> PaymentLedgers;
     
     @FXML
     private jfxtras.labs.scene.control.CalendarTextField PaymentLedgersfromdate;
     
     @FXML
     private jfxtras.labs.scene.control.CalendarTextField PaymentLedgerstodate;
      @FXML
     private ComboBox<Creditowners> CreaditOwners;
     
      @FXML
     private jfxtras.labs.scene.control.CalendarTextField CreditOwnersfromdate;
      
      @FXML
     private jfxtras.labs.scene.control.CalendarTextField CreditOwnerstodate;
      
     // @FXML
    //private TextField Cfromdate;
     ObservableList<Ledgers> finalList = FXCollections.observableArrayList();
     ObservableList<Creditowners> creditownerlist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        List<Ledgers> allList = qm.findAll("Ledgers.findAll");
        for(int i = 0; i < allList.size(); i++)
        {
            finalList.add(allList.get(i));
        }
        PaymentLedgers.setItems(finalList);
        
        List<Creditowners> allcreditownersList = qm.findAll("Creditowners.findAll");
        for(int i = 0; i < allcreditownersList.size(); i++)
        {
            creditownerlist.add(allcreditownersList.get(i));
        }
        CreaditOwners.setItems(creditownerlist);
    }
    
    public void processHome() 
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }
    
    public void processConversionReport() throws JRException, IOException
    {
        Calendar tempFromdate = Conversionfromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = Conversiontodate.getValue();
        Date toDate = tempToDate.getTime();
        
         /******************************************************************/
         Timestamp t1 = new Timestamp(fromDate.getTime());
         System.out.println("ts1 =" + t1);
         Timestamp t2 = new Timestamp(toDate.getTime());
         System.out.println("ts2 =" + t2);
          /******************************************************************/
            
            String path1 = givemepath();
            Integer p = (int)(Math.random() * 9999);
            
            String filename_roomconversionreport = path1 + "/" + "Conversion_REPORT_" + p + ".pdf";
            RoomConversion.printroomconversion(t1, t2, filename_roomconversionreport);
    }
    
    public void processCustomerdetailsReport() throws JRException, IOException
    {
        Calendar tempFromdate = Customerdetailsfromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = Customerdetailstodate.getValue();
        Date toDate = tempToDate.getTime();
        
         /******************************************************************/
         Timestamp t1 = new Timestamp(fromDate.getTime());
         System.out.println("ts1 =" + t1);
         Timestamp t2 = new Timestamp(toDate.getTime());
         System.out.println("ts2 =" + t2);
          /******************************************************************/
            
            String path1 = givemepath();
            Integer p = (int)(Math.random() * 9999);
            
            String filename_customerdetails = path1 + "/" + "Customerdetails_REPORT_" + p + ".pdf";
            Customerdetails.printcustomerdetails(t1, t2, filename_customerdetails);
    }
    
      public void processExpenseReport() throws JRException, IOException
    {
        Calendar tempFromdate = Expensefromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = Expensetodate.getValue();
        Date toDate = tempToDate.getTime();
        
         /******************************************************************/
         Timestamp t1 = new Timestamp(fromDate.getTime());
         System.out.println("ts1 =" + t1);
         Timestamp t2 = new Timestamp(toDate.getTime());
         System.out.println("ts2 =" + t2);
          /******************************************************************/
            
            String path1 = givemepath();
            Integer p = (int)(Math.random() * 9999);
            
            String filename_expense = path1 + "/" + "Expense_REPORT_" + p + ".pdf";
            Expense.printexpense(t1, t2, filename_expense);
    }
      
        public void processCreditBillRecv() throws JRException, IOException
    {
        Calendar tempFromdate = Creditrecvfromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = Creditrecvtodate.getValue();
        Date toDate = tempToDate.getTime();
        
         /******************************************************************/
         Timestamp t1 = new Timestamp(fromDate.getTime());
         System.out.println("ts1 =" + t1);
         Timestamp t2 = new Timestamp(toDate.getTime());
         System.out.println("ts2 =" + t2);
          /******************************************************************/
            
            String path1 = givemepath();
            Integer p = (int)(Math.random() * 9999);
            
            String filename_creditbillrecv = path1 + "/" + "credit_recv" + p + ".pdf";
            CreditBillRecv.printcreditbillrecvreport(t1, t2, filename_creditbillrecv);
    }
      
      public void processRoomwiseRevenue () throws JRException, IOException
      {
         Integer  roomno = Integer.parseInt(RoomwiseRoomno.getText());
          
         Calendar tempFromdate = Roomwisefromdate.getValue();
         Date fromDate =  tempFromdate.getTime();
        
         Calendar tempToDate = Roomwisetodate.getValue();
         Date toDate = tempToDate.getTime();
        
         /******************************************************************/
         Timestamp t1 = new Timestamp(fromDate.getTime());
         System.out.println("ts1 =" + t1);
         Timestamp t2 = new Timestamp(toDate.getTime());
         System.out.println("ts2 =" + t2);
         /******************************************************************/
            
         String path1 = givemepath();
         Integer p = (int)(Math.random() * 9999);
            
         String filename_roomwise_booking_revenue = path1 + "/" + "Roomwise_Booking_REPORT_" + p + ".pdf";
         RoomwiserevenueReport.printroomwiserevenue(roomno,t1, t2, filename_roomwise_booking_revenue);
      }
      
     public void processPaymentTransaction() throws JRException, IOException
     {
        Ledgers thisledger = PaymentLedgers.getValue();
        thisledger.getOid();
         
        Calendar tempFromdate = PaymentLedgersfromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = PaymentLedgerstodate.getValue();
        Date toDate = tempToDate.getTime();
        
        /******************************************************************/
        Timestamp t1 = new Timestamp(fromDate.getTime());
        System.out.println("ts1 =" + t1);
        Timestamp t2 = new Timestamp(toDate.getTime());
        System.out.println("ts2 =" + t2);
        /******************************************************************/
            
        String path1 = givemepath();
        Integer p = (int)(Math.random() * 9999);
            
        String filename_paymenttransaction = path1 + "/" + "paymenttransaction_REPORT_" + p + ".pdf";
        PaymentTransactionReport.printpaymenttransaction(thisledger.getOid(),t1, t2, filename_paymenttransaction);
     }
 
      public void processCreditOwnersReport() throws JRException, IOException
     {
        Creditowners thisowners = CreaditOwners.getValue();
        System.out.println("Oid: " + thisowners.getOid());;
         
        Calendar tempFromdate = CreditOwnersfromdate.getValue();
        Date fromDate =  tempFromdate.getTime();
        
        Calendar tempToDate = CreditOwnerstodate.getValue();
        Date toDate = tempToDate.getTime();
        
        /******************************************************************/
        Timestamp t1 = new Timestamp(fromDate.getTime());
        System.out.println("ts1 =" + t1);
        Timestamp t2 = new Timestamp(toDate.getTime());
        System.out.println("ts2 =" + t2);
        /******************************************************************/
            
        String path1 = givemepath();
        Integer p = (int)(Math.random() * 9999);
            
        String filename_creditoweners = path1 + "/" + "credit_owner" + p + ".pdf";
        CreditOwnersReport.printcreditownersreport(thisowners.getOid(),t1, t2, filename_creditoweners);
     }
         
    public String givemepath()
    {
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        System.out.println("Before" + path);
        String path1 = path.replaceFirst("c", "C");
        return path1;
    }
}
