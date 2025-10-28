
package lodgeman.lalitman.view;

import java.io.File;
import lodgeman.lalitman.controller.NewReservationWithoutAdvanceController;
import lodgeman.lalitman.controller.UsersController;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.NoResultException;
import lodgeman.lalitman.controller.AccountsController;
import lodgeman.lalitman.controller.AddBookingAddonController;
import lodgeman.lalitman.controller.AdjustReservationPaymentsController;
import lodgeman.lalitman.controller.AdvanceBookingsController;
import lodgeman.lalitman.controller.AdvanceLogViewController;
import lodgeman.lalitman.controller.AdvanceNewController;
import lodgeman.lalitman.controller.AmenitiesAddController;
import lodgeman.lalitman.controller.AmenitiesController;
import lodgeman.lalitman.controller.AmenityFixController;
import lodgeman.lalitman.controller.AmenityLifeCycleController;
import lodgeman.lalitman.controller.AmenityTrackController;
import lodgeman.lalitman.controller.AskMoreAdvanceController;
import lodgeman.lalitman.controller.BookingCancelController;
import lodgeman.lalitman.controller.BookingController;
import lodgeman.lalitman.controller.BookingEditPlusController;
import lodgeman.lalitman.controller.BookingLogViewController;
import lodgeman.lalitman.controller.BookingRoomChangeController;
import lodgeman.lalitman.controller.BookingViewController;
import lodgeman.lalitman.controller.ChangeOpeningBalanceController;
import lodgeman.lalitman.controller.ChartsController;
import lodgeman.lalitman.controller.CheckoutController;
import lodgeman.lalitman.controller.ConfigController;
import lodgeman.lalitman.controller.CreditBillRecvController;
import lodgeman.lalitman.controller.CreditbillViewController;
import lodgeman.lalitman.controller.CreditownersNewController;
import lodgeman.lalitman.controller.CurrentBookingEditController;
import lodgeman.lalitman.controller.CurrentCustomerDetailsController;
import lodgeman.lalitman.controller.CurrentExtraBedsController;
import lodgeman.lalitman.controller.CustomerMenuViewController;
import lodgeman.lalitman.controller.DailyActivitiesController;
import lodgeman.lalitman.controller.DayCollectionController;
import lodgeman.lalitman.controller.DayDetailsController;
import lodgeman.lalitman.controller.DuplicateCheckinReceiptsController;
import lodgeman.lalitman.controller.DuplicateCheckoutBillsController;
import lodgeman.lalitman.controller.FXMLController;
import lodgeman.lalitman.controller.FileController;
import lodgeman.lalitman.controller.GuestAddController;
import lodgeman.lalitman.controller.HistoryController;
import lodgeman.lalitman.controller.HomeController;
import lodgeman.lalitman.controller.LaundryController;
import lodgeman.lalitman.controller.LaundryLogController;
import lodgeman.lalitman.controller.LaundryNewItemController;
import lodgeman.lalitman.controller.LaundryTransactionController;
import lodgeman.lalitman.controller.LedgersNewController;
import lodgeman.lalitman.controller.LedgersViewController;
import lodgeman.lalitman.controller.LoginController;
import lodgeman.lalitman.controller.MyAccountController;
import lodgeman.lalitman.controller.PaymentsNewController;
import lodgeman.lalitman.controller.ReceiveNewController;
import lodgeman.lalitman.controller.ReportsController;
import lodgeman.lalitman.controller.RoomAddController;
import lodgeman.lalitman.controller.RoomAmenityAddController;
import lodgeman.lalitman.controller.RoomController;
import lodgeman.lalitman.controller.RoomHistoryController;
import lodgeman.lalitman.controller.RoomReadyController;
import lodgeman.lalitman.controller.RoomRecvController;
import lodgeman.lalitman.controller.RoomUpdateController;
import lodgeman.lalitman.controller.RoomViewController;
import lodgeman.lalitman.controller.RoomtypeAddController;
import lodgeman.lalitman.controller.RoomtypeController;
import lodgeman.lalitman.controller.RoomtypeUpdateController;
import lodgeman.lalitman.controller.SessionDetailsController;
import lodgeman.lalitman.controller.SettingsController;
//import lodgeman.lalitman.controller.SingleBillDetailController;
import lodgeman.lalitman.controller.UserAddController;
import lodgeman.lalitman.controller.ViewAdvanceBookingController;
import lodgeman.lalitman.controller.ViewCurrentCustomerSFloorAController;
import lodgeman.lalitman.controller.ViewCurrentCustomerSFloorBController;
import lodgeman.lalitman.controller.ViewCurrentCustomersController;
import lodgeman.lalitman.controller.ViewCurrentCustomersFFloorController;
import lodgeman.lalitman.controller.ViewCurrentGuestsController;
import lodgeman.lalitman.controller.ViewEntityController;
import lodgeman.lalitman.controller.ViewReservationWOAdvanceController;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Users;


public class LodgeMan extends Application 
{
    private Stage stage;    
    private final double MINIMUM_WINDOW_WIDTH = 1068;
    private final double MINIMUM_WINDOW_HEIGHT = 564;
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        try 
        {
            stage = primaryStage;
            //stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            //stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.sizeToScene();
            
            /******************************************************************/
            String path = "e:\\radhe\\lalitmahal\\dist\\res\\icon.png";
            File thisfile = new File(path);
            Image image = new Image(thisfile.toURI().toString());
            stage.getIcons().add(image);
            stage.setTitle("LodgeMan V1.0 Powered by: SachiSoft Inc");
            /******************************************************************/
            
            AppInfo.getInstance().setmApplication(this);
            AppInfo.getInstance().setmPrimaryStage(primaryStage);
            
            gotoLogin();
            
            //stage.setFullScreen(true);
            primaryStage.show();
            
            System.out.println("I don't come here!");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void gotoLogin() throws Exception 
    {
        System.out.println("LodgeMan");
        QueryManager qm = QueryManager.getInstance();
        
        /**** Existing user *****/
        try
        {
            Users currentUser = qm.findLoggedInUser();
            HomeController home1 = (HomeController) replaceSceneContent("Home.fxml");
            home1.setApp(this);
        }
        /*************************/
        
        
        /*******************************New user*******************************/
        catch(NoResultException ex)
        {
            try
            {
                LoginController login = (LoginController) replaceSceneContent("Login.fxml");
                login.setApp(this);
            } 
            catch (Exception ex1) 
            {
                Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /**********************************************************************/
    }
    
    public void gotoLogin1(Integer closingbalance)
    {
        try
            {
                LoginController login = (LoginController) replaceSceneContent("Login.fxml");
                login.setApp1(this, closingbalance);
            } 
            catch (Exception ex1) 
            {
                Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex1);
            }
    }
    
    public  void gotoResevation() throws Exception
    {
         try
            {
                //Login
                ReservationRemainderController res = (ReservationRemainderController) replaceSceneContent("ReservationRemainder.fxml");
                res.setApp(this);
            } 
            catch (Exception ex1) 
            {
                Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            
    }
    
    public void gotoViewEntity()
    {
        try 
        {
            ViewEntityController viewentitycontroller = (ViewEntityController) replaceSceneContent("ViewEntity.fxml");
            viewentitycontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoHome()
    {
        try 
        {
            
            HomeController home = (HomeController) replaceSceneContent("Home.fxml");
            home.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Clean  up the rsource...");
        System.gc();
    }
    
    public void gotoUsers()
    {
        try 
        {            
            UsersController users = (UsersController) replaceSceneContent("Users.fxml");
            users.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoNewUser()
    {
        try 
        {            
            UserAddController userAdd = (UserAddController) replaceSceneContent("UserAdd.fxml");
            userAdd.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoConfig()
    {
        try 
        {            
            ConfigController config = (ConfigController) replaceSceneContent("Config.fxml");
            config.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoGuestAdd(List bookedRoomList, Date checkingDate, Date checkoutDate, Double total, Double taxApplicable, Integer nod)
    {
        try 
        {            
            GuestAddController guestAdd = (GuestAddController) replaceSceneContent("GuestAdd.fxml");
            guestAdd.setApp(this,bookedRoomList, checkingDate,checkoutDate, total, taxApplicable,nod);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoRoom()
    {
        try 
        {            
            RoomController room = (RoomController) replaceSceneContent("Room.fxml");
            room.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoAddRoom(Room thisroom)
    {
        try 
        {            
            RoomAddController roomAdd = (RoomAddController) replaceSceneContent("RoomAdd.fxml");
            roomAdd.setApp(this, thisroom);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoRoomType()
    {
        try 
        {            
            RoomtypeController roomType = (RoomtypeController) replaceSceneContent("Roomtype.fxml");
            roomType.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoRoomTypeAdd()
    {
        try 
        {            
            RoomtypeAddController roomTypeAdd = (RoomtypeAddController) replaceSceneContent("RoomtypeAdd.fxml");
            roomTypeAdd.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoAmenities()
    {
        try 
        {            
            AmenitiesController amenities = (AmenitiesController) replaceSceneContent("Amenities.fxml");
            amenities.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoAmenitiesAdd()
    {
        try 
        {            
            AmenitiesAddController amenitiesAdd = (AmenitiesAddController) replaceSceneContent("AmenitiesAdd.fxml");
            amenitiesAdd.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void gotoCheckin()
    {
        try 
        {            
            BookingController checkin = (BookingController) replaceSceneContent("booking.fxml");
            checkin.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoViewBooking()
    {
        try 
        {            
            BookingViewController bookingview = (BookingViewController) replaceSceneContent("BookingView.fxml");
            bookingview.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoCheckout()
    {
        try 
        {            
            CheckoutController checkout = (CheckoutController) replaceSceneContent("Checkout.fxml");
            checkout.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoAddBookingAddon()
    {
        try 
        {            
            AddBookingAddonController addbookingaddon = (AddBookingAddonController) replaceSceneContent("AddBookingAddon.fxml");
            addbookingaddon.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoBookingRoomChange()
    {
        try 
        {            
            BookingRoomChangeController bookingroomchange = (BookingRoomChangeController) replaceSceneContent("BookingRoomChange.fxml");
            bookingroomchange.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoViewCurrentCustomers()
    {
        try 
        {            
            ViewCurrentCustomersController viewcurrentcustomerscontroller = (ViewCurrentCustomersController) replaceSceneContent("ViewCurrentCustomers.fxml");
            viewcurrentcustomerscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoViewCurrentCustomerFFloor()
    {
        try 
        {            
            ViewCurrentCustomersFFloorController viewcurrentcustomersffloorcontroller = (ViewCurrentCustomersFFloorController) replaceSceneContent("ViewCurrentCustomersFFloor.fxml");
            viewcurrentcustomersffloorcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoSearchCurrentGuests()
    {
        try 
        {            
            FXMLController fxmlcontroller = (FXMLController) replaceSceneContent("FXML.fxml");
            fxmlcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAdvanceAdd()
    {
        try 
        {            
            AdvanceNewController advancenewcontroller = (AdvanceNewController) replaceSceneContent("AdvanceNew.fxml");
            advancenewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoAccounts()
    {
        try 
        {            
            AccountsController accountscontroller = (AccountsController) replaceSceneContent("accounts.fxml");
            accountscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoLedgersNew()
    {
        try 
        {            
            LedgersNewController ledgersnewcontroller = (LedgersNewController) replaceSceneContent("LedgersNew.fxml");
            ledgersnewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLedgersView()
    {
       try 
        {            
            LedgersViewController ledgersviewcontroller = (LedgersViewController) replaceSceneContent("LedgersView.fxml");
            ledgersviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoPaymentNew()
    {
        try 
        {            
            PaymentsNewController paymentsnewcontroller = (PaymentsNewController) replaceSceneContent("PaymentsNew.fxml");
            paymentsnewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoBookingMenuView()
    {
        try 
        {            
            BookingViewController bookingviewcontroller = (BookingViewController) replaceSceneContent("PaymentsNew.fxml");
            bookingviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoCustomerMenuView()
    {
        try 
        {            
            CustomerMenuViewController customermenuviewvontroller = (CustomerMenuViewController) replaceSceneContent("CustomerMenuView.fxml");
            customermenuviewvontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoAskMoreAdvance()
    {
        try 
        {            
            AskMoreAdvanceController askmoreadvanceController = (AskMoreAdvanceController) replaceSceneContent("AskMoreAdvance.fxml");
            askmoreadvanceController.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoViewCurrentGuests()
    {
        try 
        {            
            ViewCurrentGuestsController viewcurrentguestscontroller = (ViewCurrentGuestsController) replaceSceneContent("ViewCurrentGuests.fxml");
            viewcurrentguestscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoBookingCancel()
    {
        try 
        {            
            BookingCancelController bookingcancelcontroller = (BookingCancelController) replaceSceneContent("BookingCancel.fxml");
            bookingcancelcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoCreditownersNew()
    {
        try 
        {            
            CreditownersNewController creditownersnewcontroller = (CreditownersNewController) replaceSceneContent("CreditownersNew.fxml");
            creditownersnewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoCreditbillView()
    {
        try 
        {            
            CreditbillViewController creditbillviewcontroller = (CreditbillViewController) replaceSceneContent("CreditbillView.fxml");
            creditbillviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoBookingLogView()
    {
        try 
        {            
            BookingLogViewController bookinglogviewcontroller = (BookingLogViewController) replaceSceneContent("BookingLogView.fxml");
            bookinglogviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoAdvanceLogView()
    {
        try 
        {            
            AdvanceLogViewController advancelogviewcontroller = (AdvanceLogViewController) replaceSceneContent("AdvanceLogView.fxml");
            advancelogviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void gotoAditionalAdvance()
    {
        try 
        {            
            AddAditionalAdvanceController addAditionalAdvanceController = (AddAditionalAdvanceController) replaceSceneContent("AddAditionalAdvance.fxml");
            addAditionalAdvanceController.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gotoCurrentBookingEdit()
    {
        try 
        {            
            CurrentBookingEditController currentbookingeditcontroller = (CurrentBookingEditController) replaceSceneContent("CurrentBookingEdit.fxml");
            currentbookingeditcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoAmenityTrack()
    {
        try 
        {            
            AmenityTrackController amenitytrackcontroller = (AmenityTrackController) replaceSceneContent("AmenityTrack.fxml");
            amenitytrackcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLaundry()
    {
        try 
        {            
            LaundryController laundrycontroller = (LaundryController) replaceSceneContent("Laundry.fxml");
            laundrycontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLaundryNewItem()
    {
        try 
        {            
            LaundryNewItemController laundrynewitemController = (LaundryNewItemController) replaceSceneContent("LaundryNewItem.fxml");
            laundrynewitemController.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLaundryTransaction()
    {
        try 
        {            
            LaundryTransactionController laundrytransactionController = (LaundryTransactionController) replaceSceneContent("LaundryTransaction.fxml");
            laundrytransactionController.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLaundryLog()
    {
        try 
        {            
            LaundryLogController laundrylogcontroller = (LaundryLogController) replaceSceneContent("LaundryLog.fxml");
            laundrylogcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoRoomupdate()
    {
      try 
        {            
            RoomUpdateController roomupdate = (RoomUpdateController) replaceSceneContent("RoomUpdate.fxml");
            roomupdate.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void gotoAddRoomAmenity()
    {
        try 
        {            
            RoomAmenityAddController roomamenityaddcontroller = (RoomAmenityAddController) replaceSceneContent("RoomAmenityAdd.fxml");
            roomamenityaddcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void gotoRoomView()
    {
        try 
        {            
            RoomViewController roomviewcontroller = (RoomViewController) replaceSceneContent("RoomView.fxml");
            roomviewcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
    public void gotoAmenityFix()
    {
        try 
        {            
            AmenityFixController amenityfixcontroller = (AmenityFixController) replaceSceneContent("AmenityFix.fxml");
            amenityfixcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public void gotoAmenityCycle()
    {
        try 
        {            
            AmenityLifeCycleController amenitylifecyclecontroller = (AmenityLifeCycleController) replaceSceneContent("AmenityLifeCycle.fxml");
            amenitylifecyclecontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoRoomReady()
    {
        try 
        {            
            RoomReadyController roomreadycontroller = (RoomReadyController) replaceSceneContent("RoomReady.fxml");
            roomreadycontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoRoomRecv()
    {
        try 
        {            
            RoomRecvController roomrecvcontroller = (RoomRecvController) replaceSceneContent("RoomRecv.fxml");
            roomrecvcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoViewCurrentCustomersSFloorA()
    {
        try 
        {            
            ViewCurrentCustomerSFloorAController viewcurrentcustomersflooracontroller = (ViewCurrentCustomerSFloorAController) replaceSceneContent("ViewCurrentCustomerSFloorA.fxml");
            viewcurrentcustomersflooracontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoViewCurrentCustomersSFloorB()
    {
        try 
        {            
            ViewCurrentCustomerSFloorBController viewcurrentcustomersfloorbcontroller = (ViewCurrentCustomerSFloorBController) replaceSceneContent("ViewCurrentCustomerSFloorB.fxml");
            viewcurrentcustomersfloorbcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoMyAccount()
    {
       try 
        {            
            MyAccountController myaccountcontroller = (MyAccountController) replaceSceneContent("MyAccount.fxml");
            myaccountcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void gotoCurrentCustomerDetail(Integer Roomno)
    {
        try 
        {            
            CurrentCustomerDetailsController currentcustomerdetailscontroller = (CurrentCustomerDetailsController) replaceSceneContent("CurrentCustomerDetails.fxml");
            currentcustomerdetailscontroller.setApp(this, Roomno);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void gotoReservationWithoutadvance()
    {
        try 
        {            
            NewReservationWithoutAdvanceController newreservationwithoutadvancecontroller = (NewReservationWithoutAdvanceController) replaceSceneContent("newReservationWithoutAdvance.fxml");
            newreservationwithoutadvancecontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoViewReservationWOAdvance()
    {
        try
        {
            ViewReservationWOAdvanceController viewreservationwoadvancecontroller = (ViewReservationWOAdvanceController) replaceSceneContent("ViewReservationWOAdvance.fxml");
            viewreservationwoadvancecontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoAdvanceBookings()
    {
        try
        {
            AdvanceBookingsController advancebookingscontroller = (AdvanceBookingsController) replaceSceneContent("AdvanceBookings.fxml");
            advancebookingscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoAdjustReservationPayments()
    {
        try
        {
            AdjustReservationPaymentsController adjustreservationpaymentscontroller = (AdjustReservationPaymentsController) replaceSceneContent("AdjustReservationPayments.fxml");
            adjustreservationpaymentscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoViewAdvanceBookings()
    {
        try
        {
            ViewAdvanceBookingController viewadvancebookingcontroller = (ViewAdvanceBookingController) replaceSceneContent("ViewAdvanceBooking.fxml");
            viewadvancebookingcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoSettings()
    {
        try
        {
            SettingsController settingscontroller = (SettingsController) replaceSceneContent("Settings.fxml");
            settingscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoChangeOpeningBalance()
    {
        try
        {
            ChangeOpeningBalanceController changeopeningcontroller = (ChangeOpeningBalanceController) replaceSceneContent("ChangeOpeningBalance.fxml");
            changeopeningcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoRoomtypeUpdate(Roomtype thisroomtype)
    {
        try
        {
            RoomtypeUpdateController roomtypeupdatecontroller = (RoomtypeUpdateController) replaceSceneContent("RoomtypeUpdate.fxml");
            roomtypeupdatecontroller.setApp(this, thisroomtype);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoCreditBillRecv(Creditbills thiscreditbills)
    {
        try
        {
            CreditBillRecvController creditbillrecvcontroller = (CreditBillRecvController) replaceSceneContent("CreditBillRecv.fxml");
            creditbillrecvcontroller.setApp(this, thiscreditbills);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoHistory()
    {
        try
        {
            HistoryController historycontroller = (HistoryController) replaceSceneContent("History.fxml");
            historycontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoRoomHistory()
    {
        try
        {
            RoomHistoryController roomhistorycontroller = (RoomHistoryController) replaceSceneContent("RoomHistory.fxml");
            roomhistorycontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }        
            
   public void gotoReports()
   {
        try
        {
            ReportsController reportscontroller = (ReportsController) replaceSceneContent("Reports.fxml");
            reportscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoCurrentExtraBeds()
   {
       try
        {
            CurrentExtraBedsController currentextrabedscontroller = (CurrentExtraBedsController) replaceSceneContent("CurrentExtraBeds.fxml");
            currentextrabedscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoDayCollection()
   {
        try
        {
            DayCollectionController daycollectioncontroller = (DayCollectionController) replaceSceneContent("DayCollection.fxml");
            daycollectioncontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
  
   public void gotoAccountantDailyReport()
   {
        try
        {
            AccountantDailyReportController accountantDailyReportController = (AccountantDailyReportController) replaceSceneContent("AccountantDailyReport.fxml");
            accountantDailyReportController.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoAccountantMontlyReport()
   {
       try
        {
            AccMonthlyReportController accmonthlyreportcontroller = (AccMonthlyReportController) replaceSceneContent("AccMonthlyReport.fxml");
            accmonthlyreportcontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void gotoSingleBillDetail(Bills thisBill)
   {
       try
        {
//            SingleBillDetailController singlebilldetailcontroller = (SingleBillDetailController) replaceSceneContent("SingleBillDetail.fxml");
   //         singlebilldetailcontroller.setApp(this, thisBill);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoReceiveNew()
           
   {
       try
        {
            ReceiveNewController receivenewcontroller = (ReceiveNewController) replaceSceneContent("ReceiveNew.fxml");
            receivenewcontroller.setApp(this);
   
   
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoDailyActitivites()
   {
       try
        {
            DailyActivitiesController dailyactivitiescontroller = (DailyActivitiesController) replaceSceneContent("DailyActivities.fxml");
            dailyactivitiescontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   public void gotoDaydetails()
   {
        try
        {    
            DayDetailsController daydetails = (DayDetailsController) replaceSceneContent("DayDetails.fxml");
            daydetails.setApp(this);
        } 
        catch (Exception ex1) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
   
    public void gotoSessionDetails()
    {
        try
        {
            SessionDetailsController sessiondetailscontroller = (SessionDetailsController) replaceSceneContent("SessionDetails.fxml");
            sessiondetailscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gotoCharts()
    {
        try
        {
            ChartsController chartscontroller = (ChartsController) replaceSceneContent("Charts.fxml");
            chartscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoDuplicateBills()
    {
        try
        {
            DuplicateCheckoutBillsController duplicatecheckoutbillscontroller = (DuplicateCheckoutBillsController) replaceSceneContent("DuplicateCheckoutBills.fxml");
            duplicatecheckoutbillscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoDuplicateReceipts()
    {
        try
        {
            DuplicateCheckinReceiptsController duplicatecheckinreceiptscontroller = (DuplicateCheckinReceiptsController) replaceSceneContent("DuplicateCheckinReceipts.fxml");
            duplicatecheckinreceiptscontroller.setApp(this);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(LodgeMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) 
    {
        Application.launch(LodgeMan.class, (java.lang.String[])null);
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception 
    {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = LodgeMan.class.getResourceAsStream(fxml);
        
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(LodgeMan.class.getResource(fxml));
        
        AnchorPane page;
        try 
        {
            page = (AnchorPane) loader.load(in);
        } 
        finally 
        {
            in.close();
        }
        
        // store the stage height in case the user has resized the window
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) 
        {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }
        
        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) 
        {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }
//        Scene scene = stage.getScene();
//        if (scene == null) {
        
        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) 
        {
            page.setPrefWidth(stageWidth);
        }
        
        if (!Double.isNaN(stageHeight)) 
        {
            page.setPrefHeight(stageHeight);
        }
        
        stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
}