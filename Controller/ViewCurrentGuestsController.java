/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import lodgeman.lalitman.controller.table.CurrentCustomerView;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ViewCurrentGuestsController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    @FXML
    private TableView<CurrentCustomerView> allTable;
    
    @FXML
    private TableColumn<CurrentCustomerView, Integer> bookingId;
    
    @FXML
    private TableColumn<CurrentCustomerView, Integer> Roomno;
    
    @FXML
    private TableColumn<CurrentCustomerView, String> guestname;
    
    @FXML
    private TableColumn<CurrentCustomerView, Date> checkindate;
    
    @FXML
    private TableColumn<CurrentCustomerView, Integer> noofpeople;
    
    @FXML
    private TableColumn<CurrentCustomerView, Integer> advancepaid;
    
    List<Room> currentBookedRoom = new ArrayList<Room>();
    HashMap<Integer, Bookings> bookingRoomMap = new HashMap<>();
    ObservableList<CurrentCustomerView> simplelist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {        
        this.application = aThis;
        
        bookingId.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, Integer>("bookingid"));
        Roomno.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, Integer>("roomno"));
        guestname.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, String>("customername"));
        checkindate.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, Date>("checkindate"));
        noofpeople.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, Integer>("noofpeople"));
        advancepaid.setCellValueFactory(new PropertyValueFactory<CurrentCustomerView, Integer>("advancepaid"));
        
        QueryManager qm = QueryManager.getInstance();
        List<Bookings> allBookings = qm.findCurrentBookings();
        
        
        for(int i = 0 ; i < allBookings.size(); i++)
        {
            currentBookedRoom.add(allBookings.get(i).getRoomno());
            bookingRoomMap.put(allBookings.get(i).getRoomno().getOid(),allBookings.get(i));
        }
            
        List<Room> allRoom = qm.findAll("Room.findAll");
        for(int i = 0 ; i < allRoom.size(); i++)
        {
            if(currentBookedRoom.contains(allRoom.get(i)))
            {
                System.out.println("is Booked: " + allRoom.get(i));
                Bookings thisbooking = bookingRoomMap.get(allRoom.get(i).getOid());
                Integer bookingid = thisbooking.getOid();
                Integer roomno  = allRoom.get(i).getOid();
                Integer noofpeople = thisbooking.getNoofpersons();
                Integer advpaid = giveMeAdvancePaid(thisbooking);
                Date checkindate = thisbooking.getFromdate();
                String guestname1 = "" + thisbooking.getCustomerid().getName() + "  " + thisbooking.getCustomerid().getLastname() + "";
                simplelist.add(new CurrentCustomerView(bookingid, roomno, guestname1, checkindate, noofpeople, advpaid));
            }
            else
            {
                System.out.println("is not Booked: " + allRoom.get(i));
                Integer bookingid = 0;
                Integer roomno  = allRoom.get(i).getOid();
                Integer noofpeople = 0;
                Integer advpaid = 0;
                Date checkindate = null;
                simplelist.add(new CurrentCustomerView(bookingid, roomno, "----------------", checkindate, noofpeople, advpaid));
            }
        }      
        allTable.setItems(simplelist);
        
        allTable.setEditable(false);
    }
    
    public Integer giveMeAdvancePaid(Bookings bookings)
    {
        QueryManager qm = QueryManager.getInstance();
        List<Receipts> receipts = qm.getReceiptsByBooking(bookings);
        
        Integer totalAdvance = 0;
        String receiptlistAll = "";
        
        for (int i = 0; i < receipts.size(); i++) 
        {
            totalAdvance = totalAdvance + receipts.get(i).getAdvancepaid();
            receiptlistAll = receiptlistAll + " " + receipts.get(i).getOid();
        }
        return totalAdvance;
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }
    //setApp ends
}
