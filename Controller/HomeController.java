/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lodgeman.lalitman.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import lodgeman.lalitman.controller.reports.CreditBillRecv;
import lodgeman.lalitman.controller.reports.CreditBillReport;
import lodgeman.lalitman.controller.reports.DayReport;
import lodgeman.lalitman.controller.reports.Sessionreport;
import lodgeman.lalitman.controller.reports.advancereport;
import lodgeman.lalitman.controller.reports.billreport;
import lodgeman.lalitman.controller.reports.expensereport;
import lodgeman.lalitman.controller.table.ReservationRemainders;
import lodgeman.lalitman.model.business.Booking.CheckRoom;
import lodgeman.lalitman.model.business.Booking.RoomStatus;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Daysession;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Reservationwadvance;
import lodgeman.lalitman.model.pojo.Reservationwoadvance;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Sessiondetails;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/*
 * FXML Controller class
 *
 * @author vijay
 */
public class HomeController implements Initializable 
{
    /*
     * Initializes the controller class.
     */
    private LodgeMan application;
    private Users currentUserG;
    
    @FXML
    private TableView<ReservationRemainders> tablereservationremainders;
    
    @FXML
    private TableColumn<ReservationRemainders, Roomtype> columnRoomtype;
    
    @FXML
    private TableColumn<ReservationRemainders, Integer> columnNo;
    
    @FXML
    private TableColumn<ReservationRemainders, String> columnName;
    
    @FXML
    private TableColumn<ReservationRemainders, Date> columnArrivalDate;
    
    @FXML
    private TableColumn<ReservationRemainders, Integer> columnAdvance;
    
    @FXML
    private TableColumn<ReservationRemainders, BigInteger> columnContactno;
      
    ObservableList<ReservationRemainders> finalList = FXCollections.observableArrayList();
    
    @FXML
    private Label currentUser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application) 
    {
        this.application = application;            
        TableMapping();
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
        Users currentUser1 = qm.findLoggedInUser();
        this.currentUserG = currentUser1;
        String currentUserName = currentUser1.getEmployid().getName();
        //currentUser.setText("Welcome, Mr." + currentUserName + "!");
        
        Date FromDate = new Date();
        FromDate.setHours(FromDate.getHours() - 12);
        
        Date ToDate = new Date();
        ToDate.setHours(ToDate.getHours() + 12);
        System.out.println("From: " + FromDate);
        System.out.println("To: " + ToDate);
        /**********************************************************************/
        
        /**********************************************************************/
        List<Reservationwoadvance> allReservationWOadvance = qm.findReservationwithoutadvanceBetween(FromDate, ToDate);
        for(int i = 0; i < allReservationWOadvance.size(); i++)
        {
           Reservationwoadvance temp = allReservationWOadvance.get(i);
           ReservationRemainders obj =  new ReservationRemainders(temp.getRoomtypeid(), temp.getNoofrooms(), temp.getBookedby(), temp.getArrivaldatetime(), 0, temp.getContactno());
           
           finalList.add(obj);
        }
        /**********************************************************************/
        
        /**********************************************************************/
        List<Reservationwadvance> allReservationWadvance = qm.findReservationwithadvanceBetween(FromDate, ToDate);
        for(int i =0; i < allReservationWadvance.size(); i++)
        {
            Reservationwadvance tempR = allReservationWadvance.get(i);
            BigInteger contactno = BigInteger.valueOf(tempR.getContactno());
            ReservationRemainders obj =  new ReservationRemainders(tempR.getRoomtypeid(), tempR.getNoofrooms(), tempR.getBookedby(), tempR.getArrivaldatetime(), tempR.getAdvancepaid(), contactno);
           
            finalList.add(obj);
        }
        /**********************************************************************/
        
        tablereservationremainders.setItems(finalList);
    }
    
    public void TableMapping()
    {
       /**********************************************************************/
        columnRoomtype.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, Roomtype>("roomtype"));
        columnNo.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, Integer>("no"));
        columnName.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, String>("name"));
        columnArrivalDate.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, Date>("arrdate"));
        columnAdvance.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, Integer>("advance"));
        columnContactno.setCellValueFactory(new PropertyValueFactory<ReservationRemainders, BigInteger>("contactno"));
        /**********************************************************************/ 
    }            
            
    
    public void processConfig()
    {
        application.gotoConfig();
    }
    
    public void processCheckin()
    {
        application.gotoCheckin();
    }
    
    public void processViewBooking()
    {
        application.gotoSearchCurrentGuests();
    }
    
    public void processCheckout()
    {
        application.gotoCheckout();
    }
    
    public void processAddBookingAddon()
    {
        application.gotoAddBookingAddon();
    }
    
    public void processBookingRoomChange()
    {
        application.gotoBookingRoomChange();
    }
    
    public void processAdvanceAdd()
    {
        application.gotoAdvanceAdd();
    }
    
    public void processViewEntity()
    {
        application.gotoViewEntity();
    }
    
    public void processViewCurrentCustomers()
    {
        application.gotoViewCurrentCustomers();
    }
    
    public void processCurrentBookingEdit()
    {
        application.gotoCurrentBookingEdit();
    }
    
    public void processLaundry()
    {
        application.gotoLaundry();
    }
    
    public void processAccounts()
    {
        application.gotoAccounts();
    }
    
    public void processDailyAcitivities()
    {
        application.gotoDailyActitivites();
    }
    
    public void processDayDetails()
    {
        application.gotoDaydetails();
    }
    
    public void processSessionDetails()
    {
        application.gotoSessionDetails();
    }
     public void processDuplicateCheckoutBills()
    {
        application.gotoDuplicateBills();
    }
    public void processDuplicateCheckinreceipts()
    {
        application.gotoDuplicateReceipts();
    }
    public void processCharts()
    {
        application.gotoCharts();
    }
    
    public void takeBackup() throws Exception
    {
        try
        {
            Generic.databaseBackup();
            Dialogs.showConfirmDialog(AppInfo.getInstance().getmPrimaryStage() , "Successfully taken");
        }
        catch(Exception ex)
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Backup can not be taken.");
        }
    }
}
