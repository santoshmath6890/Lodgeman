/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import org.apache.commons.collections.CollectionUtils;
/*
 * FXML Controller class
 *
 * @author vijay
 */

public class BookingRoomChangeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField mUserRoom;
    @FXML
    private ComboBox<Integer> mRoomList;
    @FXML
    private TextField mGuest;
    @FXML
    private TextField mRoomNo;
    @FXML
    private ImageView mGuestImage;
    private LodgeMan application;
    
    @FXML
    private Label roombookedas;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }

    public void setApp(LodgeMan aThis)
    {
        this.application = aThis;
    }

    public void processHome() 
    {
        this.application.gotoHome();
    }

    public void processBack() 
    {
        this.application.gotoHome();
    }

    public boolean  validate()
    {
    
      if(mUserRoom.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
      return false;
      }
      return true;
    }
    public void processSearch() 
    {
        
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
        String userRoomno = mUserRoom.getText();
        Integer userRoomnoInt = null;
        try
        {
         userRoomnoInt = Integer.parseInt(userRoomno);
        }
        catch(Exception e)
        {
        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
        mUserRoom.clear();
        mUserRoom.requestFocus();
         return ;
        }
        
        Room userRoom = Room.getRoomByOid(userRoomnoInt);
        if(userRoom == null)
        {
        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Room number is invalid, please enter correct room number");
        return ;
        }
        
        Integer tempRoomtypeid1 = userRoom.getTemproomtypeid();
        Roomtype thisroomtype = qm.findRoomtypeByOid(tempRoomtypeid1);
        roombookedas.setText("Room booked as " + thisroomtype.getName() + " . Available rooms of the type...");
        /**********************************************************************/
        
        
        /**********************************************************************/
        Bookings bookings = qm.getBookingByBookedRoom(userRoom);
        this.bookingG = bookings;
        Guests userGuest = bookings.getCustomerid();
        mGuest.setText("" + userGuest.getName() + userGuest.getLastname());
        /**********************************************************************/
        
        /**********************************************************************/
        //List of all current bookings.
        /**********************************************************************/
        allBookedRoom = qm.findCurrentBookings();
        for (int i = 0; i < allBookedRoom.size(); i++) 
        {
            allBookedRoomsList.add(allBookedRoom.get(i).getRoomno().getOid());
        }
        //List of booked rooms.
        System.out.println("All booked Rooms");
        for (int i = 0; i < allBookedRoomsList.size(); i++) 
        {
            System.out.println("" + allBookedRoomsList.get(i));
        }
        /**********************************************************************/
        
        
        /**********************************************************************/
        // List of all room of specific type.
        /**********************************************************************/ 
        allRoom = qm.findByTemproomtypeid(tempRoomtypeid1);
        for (int i = 0; i < allRoom.size(); i++) 
        {
            allRoomList.add(allRoom.get(i).getOid());
        }
        /**********************************************************************/
        
        /**********************************************************************/
        System.out.println("All Rooms of temproomtypeid!");
        for (int i = 0; i < allRoomList.size(); i++) 
        {
            System.out.println("" + allRoomList.get(i));
        }
        /**********************************************************************/
        
        
        /**********************************************************************/
        finalRoomList = (List<Integer>) CollectionUtils.subtract(allRoomList, allBookedRoomsList);
        
        for (int i = 0; i < finalRoomList.size(); i++) 
        {
            finalList.add(finalRoomList.get(i));
        }
        System.out.println("Final List");
        for(int i = 0 ; i < finalList.size(); i++)
        {
            System.out.println("" + finalList.get(i));
        }
        /**********************************************************************/
        
        
        mRoomList.setItems(finalList);
    }

    public void processDone() 
    {
        QueryManager qm = QueryManager.getInstance();
        Room oldRoom = bookingG.getRoomno();
        
        Integer newUserRoom = mRoomList.getValue();
        Room newRoom = Room.getRoomByOid(newUserRoom);
        qm.updateBookings(newRoom, bookingG.getOid());
        
        /**************************Reset Room Type*****************************/
        Roomtype OriRoomtype = oldRoom.getRoomtypeid();
        Integer tempRoomTypeId = OriRoomtype.getOid();
        int res = qm.updateTempRoomtypeId(tempRoomTypeId, oldRoom.getOid());
        /**********************************************************************/
        
        application.gotoHome();
    }
    
    private Bookings bookingG;
    
    List<Room> allRoom = new ArrayList<>();
    List<Bookings> allBookedRoom = new ArrayList<>();
    List<Integer> allBookedRoomsList = new ArrayList<>();
    List<Integer> allRoomList = new ArrayList<>();
    ObservableList<Integer> finalList = FXCollections.observableArrayList();
    List<Integer> finalRoomList = new ArrayList<>();
}
