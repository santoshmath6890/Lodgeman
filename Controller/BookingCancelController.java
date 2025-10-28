/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.TimersForLodgeManMgr;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class BookingCancelController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;    
    
    @FXML
    private TextField mUserRoom;    
    
    @FXML
    private TextField dGuestName;    
    
    @FXML
    private TextField dCheckinDate;    
    
    private Room roomG;
    private Bookings bookingsG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        dGuestName.setEditable(false);
        dCheckinDate.setEditable(false);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }
    
    public boolean  validate ()
    {
    if(mUserRoom.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter valid Roomno");
            return false;
        }
    
    return true;
    }
    
    public void processSearch()
    {
        if(validate())
        {
            Integer UserRoomNo;
            QueryManager qm = QueryManager.getInstance();
            /******************************************************************/
            try
            {
                UserRoomNo = Integer.parseInt(mUserRoom.getText());
            }
            catch (Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter valid Roomno");
                mUserRoom.clear();
                return ;
            }
            /******************************************************************/
            
            
            /******************************************************************/
            Room room = Room.getRoomByOid(UserRoomNo);
            if (room == null)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter valid Roomno");
                mUserRoom.clear();
                mUserRoom.requestFocus();
                return ;
            }
            /******************************************************************/
            
            this.roomG = room;  
            Bookings currentBooking = qm.getBookingByBookedRoom(room);
            this.bookingsG = currentBooking;
            
            /******************************************************************/
            //Booking Cancel time condition.
             Timersforlodgeman t = qm.getTimervalues();
             Integer flexibleAmount = t.getCancelbooking();
             System.out.println("flexi: " + flexibleAmount);
             
             if(TimersForLodgeManMgr.validateTimer(flexibleAmount, currentBooking.getFromdate()) == true)
             {
                 Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Cancel not possible. Time up.");
                 return;
             }
            /******************************************************************/
            
            Guests currentGuests = currentBooking.getCustomerid();
            dGuestName.setText("" +currentGuests.getName() + "  " + currentGuests.getLastname() + "." );
            dCheckinDate.setText("" + currentBooking.getFromdate().toString());
        }
    }
    
    public void processCancelBooking()
    {
        QueryManager qm = QueryManager.getInstance();
        Date todayDate = new Date();
        
        int result = qm.updateBookingCancel(bookingsG.getOid(), todayDate);
        int result1 = qm.deleteReceiptsByBookingId(bookingsG);
        
        /**********************************************************************/
        Roomtype OriRoomtype = roomG.getRoomtypeid();
        Integer tempRoomTypeId = OriRoomtype.getOid();
        int res = qm.updateTempRoomtypeId(tempRoomTypeId, roomG.getOid());
        /**********************************************************************/
        
        application.gotoViewEntity();
    }
}
