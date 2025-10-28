/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AdjustReservationPaymentsController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField InputRoomno;
    
    @FXML
    private TextField InputCheckinDate;
    
    @FXML
    private TextField InputName;
    
    @FXML
    private TextField InputContactno;
                
    @FXML
    private TextField InputReceiptNo;
    
    @FXML
    private TextField InputPaidBy;
                        
    @FXML
    private TextField InputAmountPaid;
                            
    @FXML
    private TextField InputPaymentByDate;
    
    @FXML
    private Label InputRemark;
    
    private Room roomG;
    private Bookings bookingG;
    private Receipts receiptG;
            
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
         this.application = aThis;  
         InputCheckinDate.setEditable(false);
         InputName.setEditable(false);
         InputContactno.setEditable(false);
         InputPaidBy.setEditable(false);
         InputAmountPaid.setEditable(false);
         InputPaymentByDate.setEditable(false);
         
         
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewEntity();
    }
     public boolean  validate()
    {
    
      if(InputRoomno.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
      return false;
      }
      return true;
    }
    public void processSearch()
    {
         if(validate())
         {
      QueryManager qm = QueryManager.getInstance();
      
        /**********************************************************************/ 
        String userRoom = InputRoomno.getText();
        Integer userRoomInt;
         if (InputRoomno == null) 
        {
          Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          InputRoomno.clear();
            return;
         }
        try
        {
         userRoomInt = Integer.parseInt(userRoom);
        }
        catch(Exception e)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          InputRoomno.clear();
                 return;
        }
        Room thisroom = new Room();
        thisroom = thisroom.getRoomByOid(userRoomInt);
        if (thisroom == null)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          InputRoomno.clear();
                 return;
        
        }
        
        this.roomG = thisroom;  
        Bookings thisbooking = qm.getBookingByBookedRoom(thisroom);
        this.bookingG = thisbooking;
        /**********************************************************************/
        
        
        /**********************************************************************/
        
        InputCheckinDate.setText("" + thisbooking.getFromdate());
        
        InputName.setText("" + thisbooking.getCustomerid().getName() + "  " + thisbooking.getCustomerid().getLastname());
        InputContactno.setText("" + thisbooking.getCustomerid().getContactno());
        InputRemark.setText("Please insert receipt no from the Reservation - Receipt.");
        /**********************************************************************/
    }
    }
    public void prcessFindReceipt()
    {
        Integer userreceipt = Integer.parseInt(InputReceiptNo.getText());
        QueryManager qm = QueryManager.getInstance();
        Receipts thisreceipts = qm.findReceiptByOid(userreceipt);
        this.receiptG = thisreceipts;
        InputPaidBy.setText("" + thisreceipts.getGuestname());
        InputAmountPaid.setText("" + thisreceipts.getAdvancepaid());
        InputPaymentByDate.setText("" + thisreceipts.getArrdate());
    }
    
    public void processAddPayments()
    {
        QueryManager qm = QueryManager.getInstance();
        int result  = qm.updateReceiptsByBookingid(bookingG, receiptG.getOid());
        application.gotoViewEntity();
    }
}
