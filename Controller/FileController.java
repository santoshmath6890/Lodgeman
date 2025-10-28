/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class FileController implements Initializable 
{
    private LodgeMan application;
    
    @FXML
    private MenuItem oBookingView;
    
    @FXML
    private MenuItem oCustomerView;
    
    @FXML
    private MenuItem oLedgerView;
    
    @FXML
    private MenuItem mihome;
    
    @FXML
    private MenuItem micheckin;
    
    @FXML
    private MenuItem micheckout;
    
    @FXML
    private MenuItem miaddextrabed;
    
    @FXML
    private MenuItem midepositadvance;
    
    @FXML
    private MenuItem mieditbooking;
    
    @FXML
    private MenuItem miroomconversion;
    
    @FXML
    private MenuItem miexit;
    
    @FXML
    private MenuItem micustomersearch;
    
    @FXML
    private MenuItem mifirstfloorcustomerview;
    
    @FXML
    private MenuItem misecondfloorcustomerview;
    
    @FXML
    private MenuItem mithirdflooracustomerview;
    
    @FXML
    private MenuItem mithirdfloorbcustomerview;
    
    @FXML
    private MenuItem micustomersearchroomwise;
    
    @FXML
    private MenuItem mipaymententry;
    
    @FXML
    private MenuItem miviewtodayspayments;
    
    @FXML
    private MenuItem micreatenewpayment;
    
    @FXML
    private MenuItem milaction1;
    
    @FXML
    private MenuItem midaction1;
    
    @FXML
    private MenuItem mireportaction1;
    
    
    /*
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application) 
    {
        this.application = application;
    }
    
    public void processBookingMenuView()
    {
       
        System.out.println("Display BookingView");
    }
    
    public void processCustomerMenuView()
    {
        System.out.println("Display Customer View");
        this.application.gotoCustomerMenuView();
    }
    
    public void processLedgerMenuView()
    {
        System.out.println("Display ");
    }
    public void processLedgerMenuCheckin()
    {
         AppInfo.getInstance().getmApplication().gotoCheckin();    
         
    }
    public void processBookingMenuHome()
    {
        AppInfo.getInstance().getmApplication().gotoHome();              
    }
    
    public void processBookingMenuCheckout()
    {
        AppInfo.getInstance().getmApplication().gotoCheckout();
    }
    
    public void processBookingMenuAddExtraBed()
    {
        AppInfo.getInstance().getmApplication().gotoAddBookingAddon();
    }
    
    public void processBookingMenuDepositAdvance()
    {
        AppInfo.getInstance().getmApplication().gotoAdvanceAdd();
    }
    
    public void processBookingMenuEditBookings()
    {
        AppInfo.getInstance().getmApplication().gotoCurrentBookingEdit();
    }
    
    public void processBookingMenuRoomConversion()
    {
        AppInfo.getInstance().getmApplication().gotoBookingRoomChange();
    }
    
    public void processBookingMenuExit()
    {
        
    }
    
    
    
    
    public void processBookingMenuCustomerSearch()
    {
        AppInfo.getInstance().getmApplication().gotoSearchCurrentGuests();
    }
    
    public void processBookingMenuGroundFloorCustomerView()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuFirstFloorCustomerView()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuSecondFloorCustomerView()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuThirdFloorACustomerView()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuThirdFloorBCustomerView()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuCustomerSearchRoomWise()
    {
        AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
   
    public void processCustomerMenuViewPaymentEntry()
    {
       AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();
    }
    
    public void processBookingMenuViewVewTodaysPayment()
    {
       AppInfo.getInstance().getmApplication().gotoViewCurrentCustomers();

    }
    
    public void processLedgerMenuViewCreateNewPayment()
    {
        AppInfo.getInstance().getmApplication().gotoLedgersView();

    }
    
    public void processLaundryMenuLaundryAction1()
    {
    AppInfo.getInstance().getmApplication().gotoLaundry();
  
    }
   
    public void processLaundryMenuDayLogAction1()
    {
           AppInfo.getInstance().getmApplication().gotoLaundryLog();

    }
   
    public void processReportsMenuAction1()
    {
         AppInfo.getInstance().getmApplication().gotoAskMoreAdvance();
      
    }
   
    
}
