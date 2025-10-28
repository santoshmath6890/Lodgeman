/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lodgeman.lalitman.controller.reports.finalbill;
import lodgeman.lalitman.controller.reports.newbillreport;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.business.CalcRoomRent;
import lodgeman.lalitman.model.business.DayTariff;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.GstMgr;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.TotalDaysTariff;
import lodgeman.lalitman.model.pojo.Addon;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.Custgstdetails;
import lodgeman.lalitman.model.pojo.EntityEnum;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;
import org.joda.time.Days;

/**
 * FXML Controller class
 *
 * @author vijay
 */

public class CheckoutController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField mUserRoomNo;
    
    @FXML
    private TableView<ReceiptsTable> receiptT;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> oid;
    
    @FXML
    private TableColumn<ReceiptsTable, String> pariculars;
    
    @FXML
    private TableColumn<ReceiptsTable, Date> mDate;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> amt;
    
    @FXML
    private TextField guestname;
    
    @FXML
    private TextField GuestContactNo;
    
    @FXML
    private TextField bookingCheckin;
    
    @FXML
    private TextField bookingExpectedCheckout;
    
    @FXML
    private TextField totaladvancepaid;
    
    @FXML
    private TextField noofDays;
    
    @FXML
    private TextField roomrent;
    
    @FXML
    private TextField luxurytax;
    
    @FXML
    private TextField receive;
    
    @FXML
    private TextField refund;
    
    @FXML
    private TextField grandtotal;
    
    @FXML
    private TextField roomtotal;
    
    @FXML
    private TextField othertotal;
    
    @FXML
    private TextField actualCheckout;
    
    @FXML
    private Button checkoutButton;
    
    @FXML
    private TextField bookingRegisterNo;
    
    @FXML
    private Label LabelGuest;
    
    @FXML
    private Label LabelContactno;
    
    @FXML
    private Label LabelRegno;
    
    @FXML
    private Label LabelCheckin;
    
    @FXML
    private Label LabelCheckout;
    
    @FXML
    private Label LabelRent;
    
    @FXML
    private Label LabelDays;
    
    @FXML
    private Label LabelRoomTotal;
    
    @FXML
    private Label LabelOthers;
    
    @FXML
    private Label LabelTax;
    
    @FXML
    private Label LabelTotal;
    
    @FXML
    private Label LabelGrandTotal;
    
    @FXML
    private Label LabelTotalAdvancePaid;
    
    @FXML
    private Label LabelReceive;
    
    @FXML
    private Label LabelRefund;
    
    @FXML
    private Label LabelMultiply;
    
    @FXML
    private Label LabelEquals;
    
    @FXML
    private ComboBox<Creditowners> creditownerlist;
    private ObservableList<Creditowners> creditownerslistmain = FXCollections.observableArrayList();
    
    @FXML
    private CheckBox forcredit;
    
    private LodgeMan application;
    
    private ObservableList<ReceiptsTable> recptlist;
    
    @FXML
    private TextField taxapplicableG;
    
    @FXML
    private TextField grandtotalwithtax;
    
    @FXML
    private TextField gstno;
    @FXML
    private TextField gstName;
    @FXML
    private TextField gstcmpname;
    
    boolean gstDetailsFound =false;
     Custgstdetails m_gstdetailsObj= null;
    Bookings bookingG = null;
    Guests guestsG = null;
    
    private double cashrecvG;
    private double cashrefundG;
    private double grandtotalG;
    private double luxurytaxG;
    private Integer noofdaysG;
    private double totaladvanceG;
    private List<Bookingaddon> thisbookingaddonListG;
    
    String receiptlistAllG;
    Double totalbillG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        gstDetailsFound=false;
        m_gstdetailsObj = null;
       checkoutButton.setDisable(false);
       checkoutButton.setVisible(true);
       guestname.setEditable(false);
       actualCheckout.setEditable(false);
       GuestContactNo.setEditable(false);
       bookingCheckin.setEditable(false);
       totaladvancepaid.setEditable(false);
       noofDays.setEditable(false);
       roomrent.setEditable(false);
       taxapplicableG.setEditable(false);
       roomtotal.setEditable(false);
       receive.setEditable(false);
       refund.setEditable(false);
       grandtotalwithtax.setEditable(false);
       othertotal.setEditable(false);
       grandtotal.setEditable(false);
       bookingRegisterNo.setEditable(false);
       //(gstDetailsFound);
       
       setvisibility(false);
       
       this.application = aThis;
       
       oid.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("No"));
       pariculars.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, String>("Particulars"));
       mDate.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Date>("AdvDate"));
       amt.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("Amount"));              
    }
    
    public void setvisibility(Boolean status)
    {
        gstName.setVisible(status);
        gstcmpname.setVisible(status);
        gstno.setVisible(status);
       LabelGuest.setVisible(status);
       LabelContactno.setVisible(status);
       LabelRegno.setVisible(status);
       LabelCheckin.setVisible(status);
       LabelCheckout.setVisible(status);
       receiptT.setVisible(status);
       LabelRent.setVisible(status);
       LabelDays.setVisible(status);
       LabelRoomTotal.setVisible(status);
       LabelMultiply.setVisible(status);
       LabelEquals.setVisible(status);
       LabelOthers.setVisible(status);
       LabelTotal.setVisible(status);
       LabelTax.setVisible(status);
       LabelGrandTotal.setVisible(status);
       LabelTotalAdvancePaid.setVisible(status);
       LabelReceive.setVisible(status);
       LabelRefund.setVisible(status);
       
       checkoutButton.setVisible(status);
       guestname.setVisible(status);
       GuestContactNo.setVisible(status);
       receiptT.setVisible(status);
       bookingRegisterNo.setVisible(status);
       bookingCheckin.setVisible(status);
       actualCheckout.setVisible(status);
       roomrent.setVisible(status);
       noofDays.setVisible(status);
       roomtotal.setVisible(status);
       othertotal.setVisible(status);
       grandtotal.setVisible(status);
       taxapplicableG.setVisible(status);
       grandtotalwithtax.setVisible(status);
       totaladvancepaid.setVisible(status);
       receive.setVisible(status);
       refund.setVisible(status);
       //ButtonDiscount.setVisible(status);
       //ButtonReset.setVisible(status);
    }
    
    public boolean  validate()
    {
      if(mUserRoomNo.getText().isEmpty() == true)
      {
        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
        return false;
      }
      return true;
    }
    
    public boolean isRoomCheckedout(Room r)
    {
        QueryManager qm = QueryManager.getInstance();
        Bookings b = qm.getRoomcheckoutByRoom(r);
        if(b == null)
             return false;
        else
            return true;    
    }
   public void processGstCheckout() throws JRException, IOException
   {
   processCheckout();
       
   }
   public void saveCustGstDetails(int id)
   {
   
     if(!gstDetailsFound)  
     {
         if(gstno.getText().length() > 1)
        {
        Custgstdetails   obj= new Custgstdetails(id,gstno.getText(),gstName.getText(),gstcmpname.getText(),0);
       
        QueryManager.getInstance().Save(EntityEnum.CUSTGSTDETAILS, obj);
        }
     }
     else 
     {
     // update the details with billid 
         //
         QueryManager.getInstance().updateBookingIdInGstDetails(m_gstdetailsObj.getOid(),id);
         
     }
   
   
   }
    public void processCheckRoom()
    {
        if(validate())
        {
            QueryManager qm = QueryManager.getInstance();
            
            /******************************************************************/
            String userRoom = mUserRoomNo.getText();
            Integer userRoomInt;
            try
            {
                userRoomInt = Integer.parseInt(userRoom);
            }
            catch(NumberFormatException e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Enter Valid Room NO.");
                mUserRoomNo.clear();
                mUserRoomNo.requestFocus();
                return;
            }
            Room room ;//= new Room();
            room = Room.getRoomByOid(userRoomInt);
            if (room == null) 
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
                mUserRoomNo.clear();
                mUserRoomNo.requestFocus();
                return;
            }
        
            Bookings bookings = qm.getBookingByBookedRoom(room);
            if(bookings == null)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room is not booked please use booked room number");
                mUserRoomNo.clear();
                mUserRoomNo.requestFocus();
                return;
            }
            this.bookingG = bookings;
            
            setvisibility(true);
            
            bookingRegisterNo.setText("" + bookingG.getRegno());
        
            Guests guestDetails = bookings.getCustomerid();
            this.guestsG = guestDetails;
            /******************************************************************/
        
            /******************************************************************/
            List<Receipts> receipts = qm.getReceiptsByBooking(bookings);
            Integer totalAdvance = 0;
            recptlist = FXCollections.observableArrayList();
            String receiptlistAll = "";
            for (int i = 0; i < receipts.size(); i++) 
            {
                totalAdvance = totalAdvance + receipts.get(i).getAdvancepaid();
                recptlist.add(new ReceiptsTable(receipts.get(i).getOid(), "Advance", receipts.get(i).getArrdate(), (double)receipts.get(i).getAdvancepaid()));
                receiptlistAll = receiptlistAll + " " + receipts.get(i).getOid();
            }
            this.receiptlistAllG = receiptlistAll;
            this.totaladvanceG = totalAdvance;
            /******************************************************************/
        
            /******************************************************************/
            List<Bookingaddon> bookingaddon = qm.findBookingAddonByBookingid(bookings);
            this.thisbookingaddonListG = bookingaddon;
        
            Double totalOther = 0.0;
            Date todayDate = new Date();
            for(int i = 0 ; i < bookingaddon.size(); i++)
            {
                Double amount = 0.0;
                if(bookingaddon.get(i).getTodate() == null)
                {
                    Integer noofDay = Generic.noOfDaysBetween(bookingaddon.get(i).getFromdate(), todayDate);
                    System.out.println("Used from :" + bookingaddon.get(i).getFromdate() + " to: " + todayDate);
                    amount = bookingaddon.get(i).getAddontype().getRate().doubleValue() * noofDay;
                    System.out.println("They used me for: " + noofDay);
                    System.out.println("I was never stopped: " + amount);
                }
                else
                {
                    Integer noofDay = Generic.noOfDaysBetween(bookingaddon.get(i).getFromdate(), bookingaddon.get(i).getTodate());
                    amount = bookingaddon.get(i).getAddontype().getRate().doubleValue() * noofDay;
                    System.out.println("They used me for: " + noofDay);
                    System.out.println("I was stopped: " + amount);
                }
                recptlist.add(new ReceiptsTable((bookingaddon.get(i).getOid()),bookingaddon.get(i).getAddontype().getAddon(), bookingaddon.get(i).getFromdate(), amount));
                totalOther = totalOther + amount;
            }
            othertotal.setText("" + Round(totalOther));
            /******************************************************************/
        
            receiptT.setItems(recptlist);
        
            /******************************************************************/
            guestname.setText("" + guestDetails.getName() + " " + guestDetails.getLastname());
            GuestContactNo.setText("" + guestDetails.getContactno());
            bookingCheckin.setText("" + bookings.getFromdate());
            totaladvancepaid.setText("" + totalAdvance);
            Date currentDate = new Date();
            actualCheckout.setText("" + currentDate.toString());
            Date CurrentDate = new Date();
        
            Integer noofdaysint = Generic.noOfDaysBetween(bookings.getFromdate(), currentDate);
            noofDays.setText("" + noofdaysint);
            this.noofdaysG = noofdaysint;
            /************************* Get GST Details if any*****************************************/
           Custgstdetails custgst=  qm.findCustgstdetailsBygid(guestDetails.getOid());
           
            if (custgst != null )
            {
                gstno.setText(custgst.getGstno());
                gstName.setText(custgst.getGstaddr());
                gstcmpname.setText(custgst.getGstcmpname());
                gstDetailsFound = true;
                 m_gstdetailsObj = custgst;
            }
            
            
            
            /**********************Room Rate***********************************/
            Roomtype roomtype = null;
            Integer rtno;
            try
            {
                rtno = qm.findTempRoomType(userRoomInt);
                roomtype = qm.findRoomtypeByOid(rtno);
            }
            catch(Exception x)
            {
                System.out.println("Wrong RoomType");
            }
        
            double rate = roomtype.getRate().doubleValue();
            roomrent.setText("" + rate);
            Integer ltax = 0;
        
            Double rent = rate * noofdaysint;
            roomtotal.setText("" + rent);
            /******************************************************************/
        
            /******************************************************************/
            if(userRoomInt == 1111 || userRoomInt == 2222)
            {
                Integer noOfBed = bookingG.getNoofpersons();
                rate = noOfBed * 360;
                roomrent.setText("" + rate);
                rent = rate * noofdaysint;
            }
            /******************************************************************/
        
            /*****************************Total rent***************************/
            roomtotal.setText("" + rent);
            /******************************************************************/
              Double taxApplicable_Double = 0.0;
        taxApplicable_Double = GstMgr.getInstance().CalculateTax(rate);
            
             Integer taxApplicable_Integer = taxApplicable_Double.intValue()*noofdaysint;
            /******************************************************************/
            CalcRoomRent r ;
            Date veryDate = new Date();
            r = new CalcRoomRent(bookings.getFromdate(), veryDate, new BigDecimal(rate),  new BigDecimal( qm.findAddonRate()),bookingaddon);
            r.calculate();
            printResult(r);
            /******************************************************************/
        
            
            /**********************************Receive or Refund***************/
            Double finaltotal3 = totalbillG;
        
            double diff = totalAdvance - Math.round(finaltotal3);
            if (diff > 0) 
            {
                refund.setText("" + diff);
                receive.setText("0");
                this.cashrefundG = diff;
                this.cashrecvG = 0;
            } 
            else if (diff < 0) 
            {
                diff = (diff) * (-1);
                receive.setText("" + Round(diff));
                refund.setText("0");
                this.cashrefundG = 0;
                this.cashrecvG = diff;
            } 
            else if (diff == 0) 
            {   
                refund.setText("0");
                receive.setText("0");
                this.cashrefundG = 0;
                this.cashrecvG = 0;
            }
            /******************************************************************/
        }
    }  
    
    private void processDayTariffDB(CalcRoomRent r, Bills thisbill) 
    {
        /**********************************************************************/
        //System.out.println("=============================================================================");
        List<DayTariff> li = r.getmTotalTarrif();
        DayTariff  d = null;
        TotalDaysTariff tot = r.getMtotDaysTariff();
        for (int i = 0; i < li.size(); i++) 
        {
            d = li.get(i);
            System.out.println("* Stat Date: " + d.getStartDate() + "End Date:  " + d.getEndDate() + " Room Rent:  " + d.getRoomrent() + " Extra bed: " + d.getExtraBedRent() + " Tax Amt:  " + d.getTaxtotal() + "Day Total: " + d.getDayTotal() + " *** ");
            int intTaxTotal = d.getTaxtotal().intValue();
            int intDayTotal = d.getDayTotal().intValue();
            LodgeConfig.newDayTariff(d.getStartDate(), d.getEndDate(), d.getRoomrent(), d.getExtraBedRent().intValue(), new BigDecimal(intTaxTotal), new BigDecimal(intDayTotal), thisbill);
                      //newDayTariff(Date startdatetime, Date enddatetime, BigDecimal roomrent, Integer extrabed, BigDecimal taxapplicable, BigDecimal daytotal, Bills thisbill)
        }
        //System.out.println("grand RoomTotal : " + tot.getGrandTotal() + "Grand extra total : " + tot.getGrandExtraBedTax() + "Grand Tax toal : " + tot.getGrandTotalTax() + "Total : " + tot.getTotalBill());
        //System.out.println("=============================================================================");
        /***********************************************************************/
    }
    
    private void printResult(CalcRoomRent r) 
    {
        /**********************************************************************/
        //System.out.println("=============================================================================");
        BigDecimal roomrent=BigDecimal.ONE;
        List<DayTariff> li = r.getmTotalTarrif();
        DayTariff  d = null;
        TotalDaysTariff tot = r.getMtotDaysTariff();
        for (int i = 0; i < li.size(); i++) 
        {
            d = li.get(i);
            roomrent = d.getRoomrent();
            //System.out.println("* Stat Date: " + d.getStartDate() + "End Date:  " + d.getEndDate() + " Room Rent:  " + d.getRoomrent() + " Extra bed: " + d.getExtraBedRent() + " Tax Amt:  " + d.getTaxtotal() + "Day Total: " + d.getDayTotal() + " *** ");
        }
        //System.out.println("grand RoomTotal : " + tot.getGrandTotal() + "Grand extra total : " + tot.getGrandExtraBedTax() + "Grand Tax toal : " + tot.getGrandTotalTax() + "Total : " + tot.getTotalBill());
        //System.out.println("=============================================================================");
        /***********************************************************************/
        
        
        /***********************************************************************/
        int othertotaltemp = tot.getGrandExtraBedTax().intValue();
        othertotal.setText("" + othertotaltemp);
        
     
        
        Double grandtotaltemp1 = Double.parseDouble(roomtotal.getText());
        Double grandtotaltemp2 = Double.parseDouble(othertotal.getText());
        Double grandtotaltemp = grandtotaltemp1 + grandtotaltemp2;
        grandtotal.setText("" + Round(grandtotaltemp));
        /***********************************************************************/
        
        //New tax on extrabed 
        //get tax slab 
         int taxslab = GstMgr.getInstance().getTax(roomrent.doubleValue());
        //   int taxtapplicabletemp = tot.getGrandTotalTax().intValue();
         double taxtapplicabletemp = (taxslab *grandtotaltemp) /100;
         
        taxapplicableG.setText("" + Round(taxtapplicabletemp));
        
       // int grandtotalwithtaxtemp = tot.getTotalBill().intValue();
        double grandtotalwithtaxtemp = taxtapplicabletemp + grandtotaltemp;
        grandtotalwithtax.setText("" + Round(grandtotalwithtaxtemp));
        this.totalbillG = Round(grandtotalwithtaxtemp);
    }
    
    public void processCheckout() throws JRException, IOException
    {
        checkoutButton.setDisable(true);
        
        /**********************************************************************/
        String roomnoS = mUserRoomNo.getText();
        Integer roomno = Integer.parseInt(roomnoS);
        Room roomToUpdate = new Room();
        /**********************************************************************/
        
        roomToUpdate = roomToUpdate.getRoomByOid(roomno);
        Integer rentperday = roomToUpdate.getTemproomtypeid();
        
        /**********************************************************************/
        Integer regno1 = Integer.parseInt(bookingRegisterNo.getText());
         BigDecimal rentperdayp = new BigDecimal(roomrent.getText());
        BigDecimal cash_refund = new BigDecimal(refund.getText());
        BigDecimal cash_recv = new BigDecimal(receive.getText());
        Integer total_advance = Integer.parseInt(totaladvancepaid.getText());
        BigDecimal luxtax = new BigDecimal(Double.parseDouble(taxapplicableG.getText()));
        Integer noofdaystemp = Integer.parseInt("" + noofDays.getText());
        BigDecimal grand_total = new BigDecimal(grandtotal.getText());
        grand_total = grand_total.setScale(2, RoundingMode.HALF_UP);
        
        //Integer grand_total = Integer.parseInt(grandtotalwithtax.getText());
        Integer extraperson = Integer.parseInt(othertotal.getText());
        BigDecimal otherservices = new BigDecimal(grandtotalwithtax.getText());
        //Integer otherservices = 0;
        String guestname1 = guestname.getText();
        Integer extrabed = 0;
        Date checkdate = bookingG.getFromdate();
        Date billDate = new Date();
        Date currentDate = new Date();
        Date checkout = bookingG.getFromto();
        /**********************************************************************/
        
        
        //System.out.println("/*****************************************/");
        /*
        System.out.println("Bill No: oid");
        System.out.println("Register No: " + regno1);
        System.out.println("Room No.: " + roomno);
        System.out.println("Bill Date: " + billDate);
        System.out.println("Guest Name: " + guestname1);
        System.out.println("Checkin Date: " + bookingG.getFromdate());
        System.out.println("Checkout Date: " + currentDate);
        System.out.println("No Of Person: " + bookingG.getNoofpersons());
        System.out.println("No Of Days: " + noofDays.getText());
        System.out.println("Rent per Day: " + rentperdayp);
        System.out.println("Extrabed: " + extraperson);
        System.out.println("Total rent: " + otherservices);
        System.out.println("AdditionalLuxuryTax : " + luxtax);
        System.out.println("Total Bill: " + grand_total);
        System.out.println("Total Advance paid: " + total_advance);
        System.out.println("Total Bill: " + total_advance);
        System.out.println("Cash Received: " + cash_recv);
        System.out.println("Cash Refund: " + cash_refund);
        System.out.println("All receipts: " + receiptlistAllG);
        */ 
        //System.out.println("/*****************************************/");
        
        LodgeConfig.newBills(roomno, billDate, guestname1, bookingG.getFromdate(), currentDate, bookingG.getNoofpersons(), receiptlistAllG , noofdaystemp, rentperdayp, extrabed, extraperson, grand_total, luxtax, total_advance, cash_recv.intValue(), cash_refund, bookingG, otherservices,regno1);
                    //newBills(int roomno, Date billdate, String guestname, Date checkindate, Date checkoutdate, int noofpersons, String advreceiptsno, int noofdays, BigDecimal rentperday, int extrabed, int extraperson, int otherservices, BigDecimal additionalluxurytax, int totaladvance, int cashreceived, BigDecimal cashrefund, Bookings bookings, BigDecimal totalbill, Integer regno)
                    //newBills(int roomno, Date billdate, String guestname, Date checkindate, Date checkoutdate, int noofpersons, String advreceiptsno, int noofdays, BigDecimal rentperday, int extrabed, int extraperson, int otherservices, BigDecimal additionalluxurytax, int totaladvance, int cashreceived,                   BigDecimal cashrefund, Bookings bookings, BigDecimal totalbill, Integer regno)
                    //newBills(Integer roomno, Date billDate, String guestname1, Date fromdate, Date currentDate, int noofpersons, String receiptlistAllG, Integer noofdaystemp, BigDecimal rentperdayp, Integer extrabed, Integer extraperson, BigDecimal grand_total, BigDecimal luxtax, Integer total_advance, Integer cash_recv, BigDecimal cash_refund, Bookings bookingG, Integer otherservices, Integer regno1) {
        QueryManager qm = QueryManager.getInstance();
        int p = qm.updateBookingCheckout(roomToUpdate, billDate);
        Bills bill = qm.findLastBills();
        
        /**********************************************************************/
        Integer billno = bill.getOid();
        Integer billno1 = bill.getOid();
        System.out.println(bill);
        /**********************************************************************/
        
        
        /**** New Bill DB ****/
        CalcRoomRent r ;
        Date veryDate = new Date();
       
        r = new CalcRoomRent(bookingG.getFromdate(), veryDate, rentperdayp, new BigDecimal(qm.findAddonRate()),thisbookingaddonListG);
        r.calculate();
        processDayTariffDB(r, bill);
        /*********************/
        
        
        /**** Reset Room Type ****/
        Roomtype OriRoomtype = roomToUpdate.getRoomtypeid();
        Integer tempRoomTypeId = OriRoomtype.getOid();
        int res = qm.updateTempRoomtypeId(tempRoomTypeId, roomno);
        /*************************/
        
        
        /**** Print Bill ****/
        /*
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        */
        String path1 = Generic.giveMePath();
        String filename = path1 + "/" + "BillReceipt_" + billno + ".pdf"; 
        System.out.println("File name : " + filename);
        
       if(gstno.getText().length() > 1){
           saveCustGstDetails(billno1);
           finalbill.printfinalbill(billno1, filename,true);}
       else
            finalbill.printfinalbill(billno1, filename,false);
        /*******************/
        

        /**** Print New Bill ****/
        String newfilename = path1 + "/" + "NewBillReceipt_" + billno + ".pdf"; 
        newbillreport.printnewfinalbill(billno1, newfilename);
        /************************/
        
      
        
        application.gotoHome();
    }
    
    public void processLaundry(Room thisroom)
    {   
        QueryManager qm = QueryManager.getInstance();
        Amenityroom amenityroom = qm.findAmenityroomByRoom(thisroom);
        List<Items> allItems = qm.findAll("Items.findAll");
        
        Items singleBed = new Items();        
        Items doubleBed = new Items();        
        Items pillow = new Items();        
        Items blanket = new Items();        
        Items towel = new Items(); 
        
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getItemName().contains("Single"))
            {
                singleBed = allItems.get(i);
                System.out.println("SIngle:" + singleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Double"))
            {
                doubleBed = allItems.get(i);
                System.out.println("Double:" + doubleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Pillow"))
            {
                pillow = allItems.get(i);
                System.out.println("pillow:" + pillow.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Blanket"))
            {
                blanket = allItems.get(i);
                System.out.println("Blanket:" + blanket.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Towel"))
            {
                towel = allItems.get(i);
                System.out.println("towel:" + towel.getItemName() );
                //continue;
            }
        }
        
        Integer singleBInt = amenityroom.getSinglebedsheetqtytemp();
        /*
         *  Single Bed from Inuse to Dirty!
         */
        /**********************************************************************/
        Iteminuse singlebedinuse = qm.findIteminuseByItem(singleBed);
        Integer singleinuseqty = singlebedinuse.getQty();
        Integer newsingleqty = singleinuseqty - singleBInt;
        Integer result = qm.updateQtyInIteminUse(singleBed, newsingleqty);
        
        Itemdirty singlebeddirty = qm.findItemdirtyByItem(singleBed);
        Integer singledirtyqty = singlebeddirty.getQty();
        Integer newsinglediryqty = singledirtyqty + singleBInt;
        Integer result1 = qm.updateQtyInItemDirty(singleBed, newsinglediryqty);
        /**********************************************************************/
        
        Integer doubleBInt = amenityroom.getDoublebedsheetqtytemp();
        /*
         * Double Bed from inuse to Dirty!
         */
        /**********************************************************************/
        Iteminuse doublebedinuse = qm.findIteminuseByItem(doubleBed);
        Integer doubleinuseqty = doublebedinuse.getQty();
        Integer newdoubleqty = doubleinuseqty - doubleBInt;
        Integer result2 = qm.updateQtyInIteminUse(doubleBed, newdoubleqty);
        
        Itemdirty doublebeddirty = qm.findItemdirtyByItem(doubleBed);
        Integer doubledirtyqty = doublebeddirty.getQty();
        Integer newdoublediryqty = doubledirtyqty + doubleBInt;
        Integer result3 = qm.updateQtyInItemDirty(doubleBed, newdoublediryqty);
        /**********************************************************************/
        
        
        Integer blanketInt = amenityroom.getBlanketqtytemp();
        
        /*
        if (blanketInt > amenityroom.getBlanketqty())
        {
            Integer diff = blanketInt - amenityroom.getBlanketqty();
            Iteminuse blanketinuse = qm.findIteminuseByItem(blanket);
            Integer blanketqty = blanketinuse.getQty();
            Integer newblanketqty = blanketqty - diff;
            Integer result4 = qm.updateQtyInIteminUse(blanket, newblanketqty);
        
            Itemdirty blanketdirty = qm.findItemdirtyByItem(blanket);
            Integer blanketdirtyqty = doublebeddirty.getQty();
            Integer newblanketdiryqty = blanketdirtyqty + diff;
            Integer result5 = qm.updateQtyInItemDirty(blanket, newblanketdiryqty);
        }
        */ 
        /*
         * Blanket from insue to Dirty!
         */
        /**********************************************************************/
        Iteminuse blanketinuse = qm.findIteminuseByItem(blanket);
        Integer blanketqty = blanketinuse.getQty();
        Integer newblanketqty = blanketqty - blanketInt;
        Integer result4 = qm.updateQtyInIteminUse(blanket, newblanketqty);
        
        Itemdirty blanketdirty = qm.findItemdirtyByItem(blanket);
        Integer blanketdirtyqty = doublebeddirty.getQty();
        Integer newblanketdiryqty = blanketdirtyqty + blanketInt;
        Integer result5 = qm.updateQtyInItemDirty(blanket, newblanketdiryqty);
        /**********************************************************************/
        Integer pillowInt = amenityroom.getPillowqtytemp();
        /*
         * Pillow from inuse to dirty
         */
        /**********************************************************************/
        Iteminuse pilowinuse = qm.findIteminuseByItem(pillow);
        Integer pillowqty = pilowinuse.getQty();
        Integer newpillowqty = pillowqty - pillowInt;
        Integer result6 = qm.updateQtyInIteminUse(pillow, newpillowqty);
        
        Itemdirty pillowdirty = qm.findItemdirtyByItem(pillow);
        Integer pillowdirtyqty = pillowdirty.getQty();
        Integer newpillowdiryqty = pillowdirtyqty + pillowInt;
        Integer result7 = qm.updateQtyInItemDirty(pillow, newpillowdiryqty);
        /**********************************************************************/
        Integer towelInt = amenityroom.getTowelqtytemp();
        /*
         * Towel from inuse to dirty
         */
        /**********************************************************************/
        Iteminuse towelinuse = qm.findIteminuseByItem(towel);
        Integer towelqty = towelinuse.getQty();
        Integer newtowelqty = towelqty - towelInt;
        Integer result8 = qm.updateQtyInIteminUse(towel, newtowelqty);
        
        Itemdirty toweldirty = qm.findItemdirtyByItem(towel);
        Integer toweldirtyqty = toweldirty.getQty();
        Integer newtowediryqty = toweldirtyqty + towelInt;
        Integer result9 = qm.updateQtyInItemDirty(towel, newtowediryqty);
        /**********************************************************************/
        
        qm.updateAmenityroomTempQty(0, 0, 0, 0, 0, amenityroom.getOid());
        System.out.println("Room not ready!");
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public static int julianDay(int year, int month, int day) 
    {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        int jdn = day + (153 * m + 2)/5 + 365*y + y/4 - y/100 + y/400 - 32045;
        return jdn;
    }
  
    public static int diff(int y1, int m1, int d1, int y2, int m2, int d2) 
    {
    return julianDay(y1, m1, d1) - julianDay(y2, m2, d2);
  }
    
    public double Round(double value)
    {
        double rounded = Math.round(value * 100.0) / 100.0;
        return rounded;
    }
    public void calculateTax(List<Bookingaddon> addonlist, Date startDate, Date endDate, Integer rent, Integer noofdays)
    {
       /***********************************************************************/ 
       Date tempStartDate = startDate;
       Calendar tempcal = Calendar.getInstance();
       tempcal.setTime(tempStartDate);
       tempcal.add(Calendar.DATE, 1);
       tempStartDate = tempcal.getTime();
       /***********************************************************************/
       
      for(Integer i = 0; i < noofdays; i++)
      {
          /********************************************************************/
          System.out.println("--------------------------------");
          System.out.println("Today Date: " + startDate);
          System.out.println("" + rent);
          /********************************************************************/
          
          /********************************************************************/
          for(int j = 0; j < addonlist.size(); j++)
          {
              Date temptodate = addonlist.get(j).getTodate();
              if(addonlist.get(j).getTodate() == null)
              {
                  temptodate = new Date();
              }
              
              if(addonlist.get(j).getFromdate().after(startDate) && temptodate.before(tempStartDate))
              {
                  
              }
          }
          System.out.println("--------------------------------");
          /********************************************************************/
          
          /********************************************************************/
          Calendar cal = Calendar.getInstance();
          cal.setTime(startDate);
          cal.add(Calendar.DATE, 1);
          startDate = cal.getTime();
          /********************************************************************/
      }
    }
}
