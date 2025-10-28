/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.awt.Event;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.controller.table.BillLogTable;
import lodgeman.lalitman.controller.table.ReceiptLogTable;
//import lodgeman.lalitman.model.pojo.Bills1;
//import lodgeman.lalitman.model.pojo.Bills2;
//import lodgeman.lalitman.model.pojo.Creditbill1trans;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
//import lodgeman.lalitman.model.pojo.Creditbills1;
//import lodgeman.lalitman.model.pojo.Creditbills2;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
//import lodgeman.lalitman.model.pojo.Receipts2;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.LodgeMan;
import lodgeman.lalitman.controller.table.CreditbillViewTable;
import lodgeman.lalitman.controller.table.ExpenseDailyDetailsTable;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.SessionCalculation;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Daysession;
import lodgeman.lalitman.model.pojo.Payments;
//import lodgeman.lalitman.model.pojo.Payments2;
import lodgeman.lalitman.model.pojo.Receives;
//import lodgeman.lalitman.model.pojo.Receives2;
import lodgeman.lalitman.view.AppInfo;

/**
 * FXML Controller class
 *
 * @author RADHAKRISHNA
 */
public class DayDetailsController implements Initializable 
{
    private LodgeMan application; 
    
    /**************************************************************************/
    @FXML
    private TableView<ReceiptLogTable> advancetable;
    
    @FXML
    private TableColumn<ReceiptLogTable, String> roomno;
    
    @FXML
    private TableColumn<ReceiptLogTable, String> guestname;
    
    @FXML
    private TableColumn<ReceiptLogTable, Date> arrdate;
    
    @FXML
    private TableColumn<ReceiptLogTable, Integer> advpaid;
    
    @FXML
    private TableColumn<ReceiptLogTable, Boolean> ColumnAction;
    
    ObservableList<ReceiptLogTable> finalList = FXCollections.observableArrayList();
    /**************************************************************************/
    
    
    /**************************************************************************/
    @FXML
    private TableView<BillLogTable> allBillTable;
    
    @FXML
    private TableView<BillLogTable> allBillTable2;
    
    @FXML
    private TableColumn<BillLogTable, Integer> billno;
    
    @FXML
    private TableColumn<BillLogTable, Integer> nBillno;
    
    @FXML
    private TableColumn<BillLogTable, Integer> mRoomno;

    @FXML
    private TableColumn<BillLogTable, Integer> nRoomno;
    
    @FXML
    private TableColumn<BillLogTable, String> mGuestname;
    
    @FXML
    private TableColumn<BillLogTable, String> nGuestname;
    
    @FXML
    private TableColumn<BillLogTable, Date> checkindate;
    
    @FXML
    private TableColumn<BillLogTable, Date> nCheckindate;
    
    @FXML
    private TableColumn<BillLogTable, Date> checkoutdate;
    
    @FXML
    private TableColumn<BillLogTable, Date> nCheckoutdate;
    
    @FXML
    private TableColumn<BillLogTable, Integer> receive;
    
    @FXML
    private TableColumn<BillLogTable, Integer> refund;
    
    ObservableList<BillLogTable> OBReceiveList = FXCollections.observableArrayList();
    ObservableList<BillLogTable> OBRefundList = FXCollections.observableArrayList();
    /**************************************************************************/
    
    
    /**************************************************************************/
    @FXML
    private TableView<CreditbillViewTable> CBallCredittable;
    
    @FXML
    private TableColumn<CreditbillViewTable, String> CBcreditowner;
    
    @FXML
    private TableColumn<CreditbillViewTable, String> CBguestname;
    
    @FXML
    private TableColumn<CreditbillViewTable, Date> CBentrydate;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> CBbillno ;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> CBtotalAmount;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> CBamountDue;
    
    @FXML
    private TableColumn<CreditbillViewTable, Date> CBcleardate;
    
    @FXML
    private TableColumn<CreditbillViewTable, Boolean> CBtActionCol;
    
    ObservableList<CreditbillViewTable> OBCBlist = FXCollections.observableArrayList();
    /**************************************************************************/
    
    @FXML
    private TextField totalreceiveAmount;
    
    @FXML
    private TextField totalrefundAmount;
    
    @FXML
    private ComboBox<Creditowners> allCreditownerslist;
    
    ObservableList<Creditowners> creditownerlist = FXCollections.observableArrayList();
    
    /**************************************************************************/
    @FXML
    private TableView<ExpenseDailyDetailsTable> EXPTable;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> EXPledger;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> EXPnote;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, Date> EXPdate;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> EXPuser;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, Integer> EXAmount;
    
    ObservableList<ExpenseDailyDetailsTable> OBExpenseList = FXCollections.observableArrayList();
    /**************************************************************************/
    
    /**************************************************************************/
    @FXML
    private TableView<ExpenseDailyDetailsTable> RCVTable;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> RCVledger;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> RCVnote;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, Date> RCVdate;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, String> RCVuser;
    
    @FXML
    private TableColumn<ExpenseDailyDetailsTable, Integer> RCVAmount;
    
    ObservableList<ExpenseDailyDetailsTable> OBRcvList = FXCollections.observableArrayList();
    /**************************************************************************/
    
    
    @FXML
    private TextField totaladvanceamount;
    
    @FXML
    private ComboBox<String> lodgeno;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField userDate;
            
    
    ObservableList<String> lodgenames = FXCollections.observableArrayList();
   
      @FXML
      TitledPane tp1;
      @FXML
      TitledPane tp2;
      @FXML
      TitledPane tp3;
      @FXML
      TitledPane tp4;
      @FXML
      TitledPane tp5;
      @FXML
      TitledPane tp6;
      @FXML
      TitledPane tp7;
      
      
    @FXML
    Button BTNcheckin;
    
    @FXML
    Button BTNcheckoutRecive;
    
    @FXML
    Button BTNcheckRefund;
      
    @FXML
    Button BTNchkCreditRecv;
      
    @FXML
    Button  BTNchkCreditBills;
      
    @FXML
    Button BTNchkExpenses;
      
    @FXML
    Button BTNchkOthersRecv;
    
    @FXML
    private Label LabelAdvance;
    
    @FXML
    private Label LabelRefund;
    
    @FXML
    private Label LabelReceive;
    
    @FXML
    private Label LabelCreditbill;
    
    @FXML
    private Label LabelCreditbillrecvd;
    
    @FXML
    private Label LabelExpense;
    
    @FXML
    private Label LabelReceivables;
    
    @FXML
    private Label LabelOpeningbalance;
    
    @FXML
    private Label LabelClosingbalance;
    
    @FXML
    Button ap;
    
    private Date startDateG;
    private Integer OpeningBalanceG;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }
    
    public void setApp(LodgeMan aThis)
    {
       this.application =  aThis;
       tableMapping();
       
       Date currentDate = new Date();
       QueryManager qm = QueryManager.getInstance();
       Userlog userlog = qm.getLastUserlog();
       Daysession id = userlog.getDaysessionid();
       
       List<Userlog> userloglist = qm.findUserlogByDaysession(id);
       
       startDateG = userloglist.get(0).getStartdatetime();
       OpeningBalanceG = userloglist.get(0).getOpeningbalance();
       
       processDayDetails(currentDate);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processGo()
    {   
        Date thisDate = new Date();
        processDayDetails(thisDate);
    }
    
    public void processDayDetails(Date enDate)
    {   
        Integer flag = 0;
        
        //Integer Advance_Anugraha = 0;
        //Integer Advance_Aradhana = 0;
        Integer Advance_Total = 0;
        
        Integer Refund_Total = 0;
        
        Integer Receive_Total = 0;
        
        Integer Creditbill_Total = 0;
        
        Integer Creditbillrecv_Total = 0;
        
        Integer Expense_Total = 0;
        
        Integer Receivales_Total = 0;
        
        QueryManager qm = QueryManager.getInstance();
        
        //Userlog userlog = qm.getLastUserlog();
        
        Date stDate = startDateG;
        
        System.out.println("StartDate: " + stDate);
        System.out.println("EndDate: " + enDate);
        
        SessionCalculation thisSession = new SessionCalculation(stDate, enDate);
            
            
        Advance_Total = thisSession.calulateReceiptsTotal();
             
        Receive_Total = thisSession.calulateReceiveTotal();
        
        Refund_Total = thisSession.calulateRefundTotal().intValue();
            
        Creditbillrecv_Total =  thisSession.calculateCreditBillTrans();
            
        Creditbill_Total = thisSession.calulateCreditBill();
            
        Expense_Total = thisSession.calulateExpense();
            
        Receivales_Total = thisSession.calculateReceivables();
        
        /**** table items****/
        Checkin_TableItems(stDate, enDate);
        Checkout_TableItems(stDate, enDate);
        CreditBill_TableItems(stDate, enDate);
        Expense_TableItems(stDate, enDate);
        Receive_TableItems(stDate, enDate);
        /********************/
        
        Integer add_closing_balance = OpeningBalanceG + Advance_Total + Receive_Total + Creditbillrecv_Total + Receivales_Total;
        Integer Minus_closing_balance = Refund_Total + Expense_Total + Creditbill_Total;
        Integer closing_balance = add_closing_balance - Minus_closing_balance;
        
        LabelOpeningbalance.setText(": " + OpeningBalanceG);
        LabelClosingbalance.setText(": " + closing_balance);
        LabelAdvance.setText(": " + Advance_Total);
        LabelRefund.setText(": " + Refund_Total);
        LabelReceive.setText(": " + Receive_Total);
        LabelCreditbill.setText(": " + Creditbill_Total);
        LabelCreditbillrecvd.setText(": " + Creditbillrecv_Total);
        LabelExpense.setText(": " + Expense_Total);
        LabelReceivables.setText(": " + Receivales_Total);
    }
    
    public void tableMapping()
    {
        roomno.setCellValueFactory(new PropertyValueFactory<ReceiptLogTable, String>("roomno"));
        guestname.setCellValueFactory(new PropertyValueFactory<ReceiptLogTable, String>("guestname"));
        arrdate.setCellValueFactory(new PropertyValueFactory<ReceiptLogTable, Date>("arrdate"));
        advpaid.setCellValueFactory(new PropertyValueFactory<ReceiptLogTable, Integer>("advancepaid"));
        
        billno.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("oid"));
        nBillno.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("oid"));
        mRoomno.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("roomno"));
        nRoomno.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("roomno"));
        mGuestname.setCellValueFactory(new PropertyValueFactory<BillLogTable, String>("guestname"));
        nGuestname.setCellValueFactory(new PropertyValueFactory<BillLogTable, String>("guestname"));
        checkindate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkindate"));
        nCheckindate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkindate"));
        checkoutdate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkoutdate"));
        nCheckoutdate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkoutdate"));
        receive.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("cashreceived"));
        refund.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("cashrefund"));
        
        CBcreditowner.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, String>("creditowners"));
        CBguestname.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, String>("guestname"));
        //CBentrydate.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Date>("entrydate"));
        CBbillno.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("billno"));
        CBtotalAmount.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("totalamount"));
        CBamountDue.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("balanceamount"));
        //CBcleardate.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Date>("cleardate"));
    
        EXPledger.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("ledger"));
        EXPnote.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("note")); 
        EXPdate.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, Date>("expensedate")); 
        EXPuser.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("user")); 
        EXAmount.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, Integer>("amount"));  
    
        RCVledger.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("ledger"));
        RCVnote.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("note")); 
        RCVdate.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, Date>("expensedate")); 
        RCVuser.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, String>("user")); 
        RCVAmount.setCellValueFactory(new PropertyValueFactory<ExpenseDailyDetailsTable, Integer>("amount"));  
    }
    
    public void Checkin_TableItems(Date stDate, Date enDate)
    {
        finalList.clear();
        
        QueryManager qm = QueryManager.getInstance();
        List<Receipts> allList1 = qm.findReceiptsBeBetween(stDate, enDate);
        for(int i = 0; i < allList1.size(); i++)
        {
                System.out.println("" + allList1.get(i));
                finalList.add(new ReceiptLogTable(allList1.get(i).getOid(), allList1.get(i).getGuestname(), allList1.get(i).getRoomno(), allList1.get(i).getArrdate(), allList1.get(i).getAdvancepaid()));
        }
    
        advancetable.setItems(finalList);
    }
    
    public void Checkout_TableItems(Date stDate, Date enDate)
    {
        OBReceiveList.clear();
        OBRefundList.clear();
        
        QueryManager qm = QueryManager.getInstance();
            
        List<Bills> allBillList1 = qm.findBillsBetween(stDate, enDate);

        for (int i = 0 ; i < allBillList1.size(); i++)
        {
            System.out.println("oid" + allBillList1.get(i).getOid());
            if(allBillList1.get(i).getCashreceived() > 0)
                OBReceiveList.add(new BillLogTable(allBillList1.get(i).getOid(),allBillList1.get(i).getRoomno(),allBillList1.get(i).getGuestname(),allBillList1.get(i).getCheckindate(),allBillList1.get(i).getCheckoutdate(), allBillList1.get(i).getCashreceived(), allBillList1.get(i).getCashrefund().intValue()));
            else if (allBillList1.get(i).getCashrefund().intValue() > 0)
                OBRefundList.add(new BillLogTable(allBillList1.get(i).getOid(),allBillList1.get(i).getRoomno(),allBillList1.get(i).getGuestname(),allBillList1.get(i).getCheckindate(),allBillList1.get(i).getCheckoutdate(), allBillList1.get(i).getCashreceived(), allBillList1.get(i).getCashrefund().intValue()));
        }
            
        allBillTable.setItems(OBReceiveList);
        allBillTable2.setItems(OBRefundList);
    }
    
    public void CreditBill_TableItems(Date stDate, Date enDate)
    {
        OBCBlist.clear();
        
        QueryManager qm = QueryManager.getInstance();
        
        List<Creditbills> outputlist = qm.findCreditbillsBetween(stDate, enDate, false);
        for(int i = 0; i < outputlist.size();i++)
        {
            System.out.println("Creditbill: " + outputlist.get(i).getOid());
                
            String creditownername = outputlist.get(i).getCreditownerid().getOwnername();
            String billguestname = outputlist.get(i).getBillno().getGuestname();
            Date entrydate = outputlist.get(i).getEntrydate();
            Integer billno = outputlist.get(i).getBillno().getOid();
            Integer receiveamount = outputlist.get(i).getBillno().getCashreceived();
            BigDecimal refundamount = outputlist.get(i).getBillno().getCashrefund();
            Boolean paid = outputlist.get(i).getPaid();
            Date cleardate = outputlist.get(i).getCleardate();
            Integer poid = outputlist.get(i).getOid();
                
            List<Creditbilltrans> thislist = qm.getCreditBillReceivedByCreditBill(outputlist.get(i));
            Integer total = 0;
            for(int ii = 0 ; ii < thislist.size(); ii++)
            {
                total = total + thislist.get(ii).getAmountrecvd();
            }
            Integer amtdue = outputlist.get(i).getBillno().getCashreceived() - total;
            OBCBlist.add(new CreditbillViewTable(poid,creditownername,billguestname,entrydate,billno,receiveamount,amtdue,cleardate));
        
            CBallCredittable.setItems(OBCBlist);
        }
    }
    
    public void Expense_TableItems(Date stDate, Date enDate)
    {
        OBExpenseList.clear();
        
        QueryManager qm = QueryManager.getInstance();
        
        List<Payments> allPaymentsList = qm.findPaymentsBetween(stDate, enDate);
        for(int i = 0; i < allPaymentsList.size(); i++)
        {
            OBExpenseList.add(new ExpenseDailyDetailsTable(allPaymentsList.get(i).getLedgertype().getName(), allPaymentsList.get(i).getNote(), allPaymentsList.get(i).getPaymentdatetime(), allPaymentsList.get(i).getUserid().getLoginname(), allPaymentsList.get(i).getAmount()));
        }
        
        EXPTable.setItems(OBExpenseList);
    }
    
    public void Receive_TableItems(Date stDate, Date enDate)
    {
        OBRcvList.clear();
        
        QueryManager qm = QueryManager.getInstance();
        
        List<Receives> allPaymentsList = qm.findReceiveBetween(stDate, enDate);
        for(int i = 0 ; i < allPaymentsList.size(); i++)
        {
            OBRcvList.add(new ExpenseDailyDetailsTable(allPaymentsList.get(i).getLedgertype().getName(), allPaymentsList.get(i).getNote(), allPaymentsList.get(i).getReceivedatetime(), allPaymentsList.get(i).getUserid().getLoginname(), allPaymentsList.get(i).getAmount()));
        }
        
        RCVTable.setItems(OBRcvList);
    }
}   


