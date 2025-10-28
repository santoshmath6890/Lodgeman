/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.SessionCalculation;
import lodgeman.lalitman.view.CustomAccRptFactory;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class DayCollectionController implements Initializable 
{
    private LodgeMan application;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputDate;
    
    @FXML
    private Button btnPrint;
    @FXML
    private TextField txtAdvanceTotal;
    
    @FXML
    private TextField txtReceiveTotal;
    
    @FXML
    private TextField txtRefundTotal;
    
    @FXML
    private TextField txtExpenseTotal;
    
    
    @FXML
    private TextField txtCreditBillTotal;
    
    @FXML
    private TextField txtCreditBillReceivedTotal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    
    public void setApp(LodgeMan aThis) 
    {
        txtAdvanceTotal.setEditable(false);
        txtReceiveTotal.setEditable(false);
        txtRefundTotal.setEditable(false);
        txtExpenseTotal.setEditable(false);
        txtCreditBillTotal.setEditable(false);
        txtCreditBillReceivedTotal.setEditable(false);
        this.application = aThis;
        Date sDate = new Date();
        Date eDate = new Date();
        
        SessionCalculation thissession = new SessionCalculation(sDate, eDate);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHistory();
    }
    
    public void processGetDetails() throws JRException
    {
        /**********************************************************************/
        Calendar userInput = InputDate.getValue();
        Calendar userInput1 = InputDate.getValue();
        userInput1.add(Calendar.HOUR_OF_DAY, 00);
        userInput1.add(Calendar.MINUTE, 00);
        userInput1.add(Calendar.SECOND, 01);
        Date fromDate = userInput1.getTime();
        System.out.println("Date: " + fromDate);
        
        
        userInput.add(Calendar.HOUR_OF_DAY, 23);
        userInput.add(Calendar.MINUTE, 59);
        userInput.add(Calendar.SECOND, 59);
        Date toDate = userInput.getTime();
        System.out.println("Date: " + toDate);
        /**********************************************************************/
        
        /**********************************************************************/
        SessionCalculation thisSession = new SessionCalculation(fromDate, toDate);
        
        System.out.println("_______________________________________");
        Integer receiptTotal = thisSession.calulateReceiptsTotal();
        System.out.println("Advance total: " + receiptTotal);
        txtAdvanceTotal.setText("" + receiptTotal);
        Integer receiveTotal = thisSession.calulateReceiveTotal();
        System.out.println("Receive total: " + receiveTotal);
        txtReceiveTotal.setText("" + receiveTotal);
        Integer expenseTotal =thisSession.calulateExpense();
        System.out.println("Expense total: " + expenseTotal);
        txtExpenseTotal.setText("" + expenseTotal);
        Integer refundTotal = thisSession.calulateRefundTotal().intValue();
        System.out.println("refund Total: " + refundTotal);
        txtRefundTotal.setText("" + refundTotal);
        Integer creditBillTotal = thisSession.calulateCreditBill();
        System.out.println("Credit Bill total total: " + creditBillTotal);
        txtCreditBillTotal.setText("" + creditBillTotal);
        Integer creditBillRecvTotal = thisSession.calculateCreditBillTrans();
        System.out.println("Credit Bill Recvd total: " + creditBillRecvTotal);
        txtCreditBillReceivedTotal.setText("" + creditBillRecvTotal);
        System.out.println("_______________________________________");
        
        Integer grandTotal =  (receiptTotal + receiveTotal +creditBillRecvTotal)  - (expenseTotal + refundTotal + creditBillTotal);
        //DayCollectionBean(Double crdBill, Double refundtot, Double crdBillReceived, Double ExpenseTot, Double advTotal, Double receiveTotal, Double GrandToal, Date stdate) {
        DayCollectionBean d = new DayCollectionBean(creditBillTotal,refundTotal ,creditBillRecvTotal, expenseTotal, receiptTotal, receiveTotal, grandTotal, fromDate);
        DayCollectionBeanFactory.insert(d);
        
        

		
        /**********************************************************************/
    }
    
    public void btnPrint_click(ActionEvent ae) throws JRException
    {
        
    String test = JasperFillManager.fillReportToFile("e:\\radhe\\lalitmahal\\dist\\res\\Daycollection.jasper",null, new JRBeanArrayDataSource(DayCollectionBeanFactory.getBeanArray(),false));
                JasperExportManager.exportReportToPdfFile("e:\\radhe\\lalitmahal\\dist\\res\\Daycollection.jrprint");
       
    
    }
}
