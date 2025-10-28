/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lodgeman.lalitman.controller.backupandrestore.BackupAndRestore;
import lodgeman.lalitman.controller.backupandrestore.FileHide;
import lodgeman.lalitman.controller.reports.CreditBillRecv;
import lodgeman.lalitman.controller.reports.CreditBillReport;
import lodgeman.lalitman.controller.reports.DayReport;
import lodgeman.lalitman.controller.reports.Sessionreport;
import lodgeman.lalitman.controller.reports.advancereport;
import lodgeman.lalitman.controller.reports.billreport;
import lodgeman.lalitman.controller.reports.expensereport;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
import lodgeman.lalitman.model.pojo.Daysession;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Receives;
import lodgeman.lalitman.model.pojo.Sessiondetails;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class MyAccountController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    private TextField Topeningbalance;
    
    @FXML
    private TextField Tadvances;
    
    @FXML
    private TextField Treceived;
    
    @FXML
    private TextField Tcreditbillrecvd;
    
    @FXML
    private TextField Trefund;
    
    @FXML
    private TextField Texpenses;
    
    @FXML
    private TextField Tcreditbills;
    
    @FXML
    private TextField Tclosingbalance;
    
    @FXML
    private TextField Tcashincounter;
    
    @FXML
    private TextField Textextraaddition;
    
    @FXML
    private Label LwarningCashCounter;
    
    private Users currentUserG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        QueryManager qm = QueryManager.getInstance();
        Date currentDate = new Date();
        
        Users currentUser1 = qm.findLoggedInUser();
        Userlog userlog = qm.getLastUserlog();
        
        qm.updateEndDateTime(userlog.getOid(), currentDate);
        
        /**********************************************************************/
        Integer advance_total = 0;
        List<Receipts> allList = qm.findReceiptsBeBetween(userlog.getStartdatetime(), currentDate);
        for(int i = 0; i < allList.size(); i++)
        {
            //System.out.println("" + allList.get(i));
            advance_total = advance_total + allList.get(i).getAdvancepaid();
        }
        Tadvances.setText("" + advance_total);
        /**********************************************************************/
        
        /**********************************************************************/
        Integer received_total = 0;
        BigDecimal refund_total = BigDecimal.ZERO;
        List<Bills> allBillList = qm.findBillsBetween(userlog.getStartdatetime(), currentDate);
        for(int i = 0 ; i < allBillList.size(); i++)
        {
            received_total = received_total + allBillList.get(i).getCashreceived();
            refund_total = refund_total.add( allBillList.get(i).getCashrefund());
        }
        Treceived.setText("" + received_total);
        Trefund.setText("" + refund_total);
        /**********************************************************************/
        
        /**********************************************************************/
        Integer creditbillamount = 0;
        List<Creditbills> allCreditbillList = qm.findCreditbillsBetween(userlog.getStartdatetime(), currentDate, false);
        for(int i = 0; i < allCreditbillList.size(); i++)
        {
            creditbillamount = creditbillamount + allCreditbillList.get(i).getBillno().getCashreceived();
        }
        Tcreditbills.setText("" + creditbillamount);
        /**********************************************************************/
        
        /**********************************************************************/
        System.out.println("----------------------------------");
        System.out.println("Credit bill trans");
        List<Creditbilltrans> allCreditbilltrans = qm.findCreditbilltransBetween(userlog.getStartdatetime(), currentDate);
        Integer credittransint = 0;
        for(int i = 0; i < allCreditbilltrans.size(); i++)
        {
            System.out.println("" + allCreditbilltrans.get(i));
            credittransint = credittransint + allCreditbilltrans.get(i).getAmountrecvd();
        }
        System.out.println("----------------------------------");
        Tcreditbillrecvd.setText("" + credittransint);
        /**********************************************************************/
        
        /**********************************************************************/
        Integer expense_total = 0;
        List<Payments> allPaymentsList = qm.findPaymentsBetween(userlog.getStartdatetime(), currentDate);
        for(int i = 0; i < allPaymentsList.size(); i++)
        {
            expense_total = expense_total + allPaymentsList.get(i).getAmount();
        }
        Texpenses.setText("" + expense_total);
        /**********************************************************************/
        
        /**********************************************************************/
        Integer receives_total = 0;
        List<Receives> allReveivesList = qm.findReceiveBetween(userlog.getStartdatetime(), currentDate);
        for(int i = 0 ; i < allReveivesList.size(); i++)
        {
            receives_total = receives_total + allReveivesList.get(i).getAmount();
        }
        Textextraaddition.setText("" + receives_total);
        /**********************************************************************/
        
        Integer openingbalance = userlog.getOpeningbalance();
        Integer add_closing_balance = openingbalance + advance_total + received_total + credittransint + receives_total ;
        Double Minus_closing_balance = refund_total.doubleValue() + expense_total + creditbillamount;
        Double closing_balance = add_closing_balance - Minus_closing_balance;
        
        Topeningbalance.setText("" + openingbalance);
        Tclosingbalance.setText("" + closing_balance);
        
        Topeningbalance.setEditable(false);
        Tadvances.setEditable(false);
        Treceived.setEditable(false);
        Tcreditbillrecvd.setEditable(false);
        Trefund.setEditable(false);
        Texpenses.setEditable(false);
        Tcreditbills.setEditable(false);
        Tclosingbalance.setEditable(false);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }
    
    public void processLogout() throws JRException, IOException, Exception
    {
        /**********************************************************************/ 
        Integer cashincounter = 0;
        try
        {
            cashincounter = Integer.parseInt(Tcashincounter.getText());
        }
        catch(Exception ex)
        {
            LwarningCashCounter.setText("Cash in Counter field is missing!");
            
        }
        LwarningCashCounter.requestFocus();
        /**********************************************************************/
        
        if (cashincounter > 0) 
        {
            Date currentDate = new Date();
            QueryManager qm = QueryManager.getInstance();
            
            Users currentUser1 = qm.findLoggedInUser();
            this.currentUserG = currentUser1;
            qm.updateLoggedOut(currentUserG.getOid());
            
            Userlog userlog = qm.getLastUserlog();
            qm.updateEndDateTime(userlog.getOid(), currentDate);

            /******************************************************************/
            Timestamp t1 = new Timestamp(userlog.getStartdatetime().getTime());
            System.out.println("ts1 =" + t1);
            Timestamp t2 = new Timestamp(currentDate.getTime());
            System.out.println("ts2 =" + t2);
            /******************************************************************/
            
            /**** File Names ****/
            String path1 = givemepath();
            String filename_advancereport = path1 + "/" + "ADVANCE_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_billreport = path1 + "/" + "BILL_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_expensereport = path1 + "/" + "EXPENSE_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_sessionreport = path1 + "/" + "SESSION_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_creditbillreport = path1 + "/" + "CREDITBILLREPORT" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_creditbillrecvreport = path1 + "/" + "CREDITBILLRECVREPORT" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            /*********************/
            
            /******************************************************************/
            Integer advance_total = 0;
            List<Receipts> allList = qm.findReceiptsBeBetween(userlog.getStartdatetime(), currentDate);
            for (int i = 0; i < allList.size(); i++) 
            {
                advance_total = advance_total + allList.get(i).getAdvancepaid();
            }
            /******************************************************************/
            
            /******************************************************************/
            Integer received_total = 0;
            BigDecimal refund_total = BigDecimal.ZERO;
            List<Bills> allBillList = qm.findBillsBetween(userlog.getStartdatetime(), currentDate);
            for (int i = 0; i < allBillList.size(); i++) 
            {
                received_total = received_total + allBillList.get(i).getCashreceived();
                refund_total = refund_total.add(allBillList.get(i).getCashrefund());
            }
            /******************************************************************/
            
            /******************************************************************/
            Integer creditbillamount = 0;
            List<Creditbills> allCreditbillList = qm.findCreditbillsBetween(userlog.getStartdatetime(), currentDate, false);
            for (int i = 0; i < allCreditbillList.size(); i++) 
            {
                creditbillamount = creditbillamount + allCreditbillList.get(i).getBillno().getCashreceived();
            }
            /******************************************************************/
                      
            /******************************************************************/
            System.out.println("----------------------------------");
            System.out.println("Credit bill trans");
            List<Creditbilltrans> allCreditbilltrans = qm.findCreditbilltransBetween(userlog.getStartdatetime(), currentDate);
            Integer creditbilltransint = 0;
            for(int i = 0; i < allCreditbilltrans.size(); i++)
            {
                creditbilltransint = creditbilltransint + allCreditbilltrans.get(i).getAmountrecvd();
            }
            System.out.println("----------------------------------");
            /*******************************************************************
            
            /******************************************************************/
            Integer expense_total = 0;
            List<Payments> allPaymentsList = qm.findPaymentsBetween(userlog.getStartdatetime(), currentDate);
            for (int i = 0; i < allPaymentsList.size(); i++) {
                expense_total = expense_total + allPaymentsList.get(i).getAmount();
            }
            /******************************************************************/
            
            /**********************************************************************/
            Integer receives_total = 0;
            List<Receives> allReveivesList = qm.findReceiveBetween(userlog.getStartdatetime(), currentDate);
            for(int i = 0 ; i < allReveivesList.size(); i++)
            {
                receives_total = receives_total + allReveivesList.get(i).getAmount();
            }
            Textextraaddition.setText("" + receives_total);
            /**********************************************************************/
            
            /**** Test ****/
            System.out.println("Total expense: " + expense_total);
            System.out.println("Received Total: " + received_total);
            System.out.println("Refund Total: " + refund_total);
            System.out.println("Advance total: " + advance_total);
            Integer openingbalance = userlog.getOpeningbalance();
            System.out.println("Opening bal: " + openingbalance);
            System.out.println("Credit bill: " + creditbillamount);
            System.out.println("Credit bill recv: " + creditbilltransint);

            Integer add_closing_balance = openingbalance + advance_total + received_total + creditbilltransint + receives_total;
            Double Minus_closing_balance = refund_total.doubleValue() + expense_total + creditbillamount;
            Double closing_balance = add_closing_balance - Minus_closing_balance;
            /***************/

            System.out.println("Closing Balance:" + closing_balance);
            Double difference = closing_balance - cashincounter;
            //int res = qm.updateClosingbalance(userlog.getOid(), closing_balance);
            int res1 = qm.updateClosingbalanceCashInCounterDifference(userlog.getOid(), closing_balance.intValue(), cashincounter, difference.intValue());
            
            
            /**** New Session ****/
            LodgeConfig.newSessiondetails(openingbalance, refund_total.intValue(), advance_total.intValue(), received_total.intValue(), expense_total.intValue(), closing_balance.intValue(), currentDate, currentUserG, creditbillamount, creditbilltransint, cashincounter, difference.intValue(), receives_total);
            Sessiondetails lastsession = qm.getLastSessiondetails();
            Integer sessionid = lastsession.getOid();
            /*********************/
            
            /**** Reports ****/
            advancereport.printadvancereport(userlog.getUserid().getOid(), t1, t2, filename_advancereport);
            billreport.printbillreport(t1, t2, filename_billreport);
            expensereport.printexpencereport(t1, t2, filename_expensereport);
            Sessionreport.printsessionreport(sessionid, filename_sessionreport);
            CreditBillReport.printcreditbillreport(t1, t2, filename_creditbillreport);
            CreditBillRecv.printcreditbillrecvreport(t1, t2, filename_creditbillrecvreport);
            /******************/
            
            application.gotoLogin1(closing_balance.intValue());
            //Platform.exit();
        } 
        else 
        {
            LwarningCashCounter.setText("Cash in Counter field is missing!");
        }
    }
    
    public void processDaySessionClose() throws JRException, IOException, Exception
    {
        /**********************************************************************/ 
        Integer cashincounter = 0;
        try
        {
            cashincounter = Integer.parseInt(Tcashincounter.getText());
        }
        catch(Exception ex)
        {
            LwarningCashCounter.setText("Cash in Counter field is missing!");
        }
        /**********************************************************************/
        if (cashincounter > 0) 
        {
            Date currentDate = new Date();
            QueryManager qm = QueryManager.getInstance();
            Users currentUser1 = qm.findLoggedInUser();
            this.currentUserG = currentUser1;
            qm.updateLoggedOut(currentUserG.getOid());
            Userlog userlog = qm.getLastUserlog();
            qm.updateEndDateTime(userlog.getOid(), currentDate);
        
            /**********************************************************************/
            Timestamp t1 = new Timestamp(userlog.getStartdatetime().getTime());
            System.out.println("ts1 =" + t1);
            Timestamp t2 = new Timestamp(currentDate.getTime());
            System.out.println("ts2 =" + t2);
            /**********************************************************************/
        
            /**********************************************************************/
            String path1 = givemepath();
            String filename_advancereport = path1 + "/" + "ADVANCE_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_billreport = path1 + "/" + "BILL_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_expensereport = path1 + "/" + "EXPENSE_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_sessionreport = path1 + "/" + "SESSION_REPORT_" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_creditbillreport = path1 + "/" + "CREDITBILLREPORT" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            String filename_creditbillrecvreport = path1 + "/" + "CREDITBILLRECVREPORT" + userlog.getOid() + "" + currentUserG.getEmployid().getName() + ".pdf";
            /**********************************************************************/
        
            /**********************************************************************/
            Integer advance_total = 0;
            List<Receipts> allList = qm.findReceiptsBeBetween(userlog.getStartdatetime(), currentDate);
            for(int i = 0; i < allList.size(); i++)
            {
                advance_total = advance_total + allList.get(i).getAdvancepaid();
            }
            /**********************************************************************/
        
        
            /**********************************************************************/
            Integer received_total = 0;
            BigDecimal refund_total = BigDecimal.ZERO;
            List<Bills> allBillList = qm.findBillsBetween(userlog.getStartdatetime(), currentDate);
            for(int i = 0 ; i < allBillList.size(); i++)
            {
                received_total = received_total + allBillList.get(i).getCashreceived();
                refund_total = refund_total.add( allBillList.get(i).getCashrefund());
            }
            /**********************************************************************/
        
        
            /**********************************************************************/
            Integer creditbillamount = 0;
            List<Creditbills> allCreditbillList = qm.findCreditbillsBetween(userlog.getStartdatetime(), currentDate, false);
            for(int i = 0; i < allCreditbillList.size(); i++)
            {
                creditbillamount = creditbillamount + allCreditbillList.get(i).getBillno().getCashreceived();
            }
            /**********************************************************************/
        
        
            /**********************************************************************/
            /*
            Integer creditbillrecvamount = 0;
            List<Creditbills> allCreditbillrecvList = qm.findCreditbillsReceivedBetween(userlog.getStartdatetime(), currentDate, true);
            for(int i = 0; i < allCreditbillrecvList.size(); i++)
            {
                creditbillrecvamount = creditbillrecvamount + allCreditbillrecvList.get(i).getBillno().getCashreceived();
            }
            */ 
            /**********************************************************************/
        
            /**********************************************************************/
            System.out.println("----------------------------------");
            System.out.println("Credit bill trans");
            List<Creditbilltrans> allCreditbilltrans = qm.findCreditbilltransBetween(userlog.getStartdatetime(), currentDate);
            Integer creditbilltransint = 0;
            for(int i = 0; i < allCreditbilltrans.size(); i++)
            {
                creditbilltransint = creditbilltransint + allCreditbilltrans.get(i).getAmountrecvd();
            }
            System.out.println("----------------------------------");
            /**********************************************************************/
        
            /**********************************************************************/
            Integer expense_total = 0;
            List<Payments> allPaymentsList = qm.findPaymentsBetween(userlog.getStartdatetime(), currentDate);
            for(int i = 0; i < allPaymentsList.size(); i++)
            {
                expense_total = expense_total + allPaymentsList.get(i).getAmount();
            }
            /**********************************************************************/
     
            /**********************************************************************/
            Integer receives_total = 0;
            List<Receives> allReveivesList = qm.findReceiveBetween(userlog.getStartdatetime(), currentDate);
            for(int i = 0 ; i < allReveivesList.size(); i++)
            {
                receives_total = receives_total + allReveivesList.get(i).getAmount();
            }
            Textextraaddition.setText("" + receives_total);
            /**********************************************************************/
        
            /**********************************************************************/
            System.out.println("Total expense: " + expense_total);
            System.out.println("Received Total: " + received_total);
            System.out.println("Refund Total: " + refund_total);
            System.out.println("Advance total: " + advance_total);
            Integer openingbalance = userlog.getOpeningbalance();
            System.out.println("Opening bal: " + openingbalance);
            System.out.println("Credit bill: " + creditbillamount);
            System.out.println("Credit bill recv: " + creditbilltransint);

            Integer add_closing_balance = openingbalance + advance_total + received_total + creditbilltransint + receives_total;
            Double Minus_closing_balance = refund_total.doubleValue() + expense_total + creditbillamount;
            Double closing_balance = add_closing_balance - Minus_closing_balance;

            System.out.println("Closing Balance:" + closing_balance);
            //int res = qm.updateClosingbalance(userlog.getOid(), closing_balance);
            Double difference = closing_balance - cashincounter;
            //int res = qm.updateClosingbalance(userlog.getOid(), closing_balance);
            int res1 = qm.updateClosingbalanceCashInCounterDifference(userlog.getOid(), closing_balance.intValue(), cashincounter, difference.intValue());
            /**********************************************************************/

        
            /**********************************************************************/
            LodgeConfig.newSessiondetails(openingbalance, refund_total.intValue(), advance_total, received_total.intValue(), expense_total.intValue(), closing_balance.intValue(), currentDate, currentUserG, creditbillamount, creditbilltransint, cashincounter, difference.intValue(), receives_total);
            Sessiondetails lastsession = qm.getLastSessiondetails();
            Integer sessionid = lastsession.getOid();
            /**********************************************************************/

        
            /**********************************************************************/
            advancereport.printadvancereport(userlog.getUserid().getOid(),t1,t2,filename_advancereport);
            billreport.printbillreport(t1, t2, filename_billreport);
            expensereport.printexpencereport(t1, t2, filename_expensereport);
            Sessionreport.printsessionreport(sessionid, filename_sessionreport);
            CreditBillReport.printcreditbillreport(t1, t2, filename_creditbillreport);
            CreditBillRecv.printcreditbillrecvreport(t1, t2, filename_creditbillrecvreport);
            /**********************************************************************/
        
            /**********************************************************************/
            Daysession thissession = qm.findSessiondaystarted();
            Date todayDate = new Date();
            qm.updateDaySessionEndDateandStatus(thissession.getOid(), todayDate, false);
        
            List<Userlog> thislist = qm.findUserlogByDaysession(thissession);
            for(int i = 0; i < thislist.size(); i++)
            {
                String f = thislist.get(i).getUserid().getLoginname();
                System.out.println("" + f);
            }
            /**********************************************************************/
        
        
            /********************************Print report**************************/
            Daysession lastsession1 = qm.getLastDaysession();
            String fileN = path1 + "/" + "DayReport" + lastsession.getOid() + ".pdf";
            DayReport.printdayreport(lastsession1.getOid(), fileN);
            /**********************************************************************/

            Platform.exit();
        } 
        else 
        {
            LwarningCashCounter.setText("Cash in Counter field is missing!");
        }
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
