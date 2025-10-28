/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class CurrentCustomerDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField guestname;
    
    @FXML
    private TextField contactno;
    
    @FXML
    private TextArea address;
    
    @FXML
    private TextField city;
    
    @FXML
    private TextField state;
    
    @FXML
    private TextField checkinDate;
    
    @FXML
    private ImageView customerphoto;
    
    @FXML
    private ImageView customeridphoto;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis, Integer Roomno) 
    {
        this.application = aThis;
        checkinDate.setEditable(false);
        guestname.setEditable(false);
        contactno.setEditable(false);
        address.setEditable(false);
        city.setEditable(false);
        state.setEditable(false);
        QueryManager qm = QueryManager.getInstance();
        Room thisroom = Room.getRoomByOid(Roomno);
        Bookings thisbookings = qm.getBookingByBookedRoom(thisroom);
        Guests thisguests = thisbookings.getCustomerid();
        
        guestname.setText("" + thisguests.getName() + "  " + thisguests.getLastname());
        contactno.setText("" + thisguests.getContactno());
        address.setText("" + thisguests.getAddress());
        city.setText("" + thisguests.getCity());
        state.setText("" + thisguests.getState());
        checkinDate.setText("" + thisbookings.getFromdate());
        
        try
        {
            String pp = thisguests.getImage();
            File file = new File(pp);
            Image ima = new Image(file.toURI().toString());
            customerphoto.setImage(ima);
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        
        try
        {
            String pp = thisguests.getIdproofno();
            File file = new File(pp);
            Image ima = new Image(file.toURI().toString());
            customeridphoto.setImage(ima);
        }
        catch(Exception e)
        {
            System.out.println("Id Error");
        }
    }
    
    public void processBack()
    {
        application.gotoViewCurrentCustomers();
    }
    
}
