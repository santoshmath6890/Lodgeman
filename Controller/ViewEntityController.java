/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ViewEntityController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } 

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processAskMoreAdvance()
    {
        application.gotoAskMoreAdvance();;
    }
    
    public void processViewCurrentGuests()
    {
        application.gotoViewCurrentGuests();
    }
    
    public void processCancelBooking()
    {
        application.gotoBookingCancel();
    }
    
    public void processCurrentBookingEdit()
    {
        application.gotoCurrentBookingEdit();
    }
    
    public void processAmenityTrack()
    {
       application.gotoAmenityTrack();
    }
    
    public void processViewCurrentCustomers()
    {
        application.gotoViewCurrentCustomers();
    }
    
    public void processReservation()
    {
        application.gotoReservationWithoutadvance();
    }
    
    public void processVewReservationWOAdvance()
    {
        application.gotoViewReservationWOAdvance();
    }
    
    public void processAdvanceBookings()
    {
        application.gotoAdvanceBookings();
    }
    
    public void processAdjustReservationPayments()
    {
        application.gotoAdjustReservationPayments();
    }
    
    public void processViewAdvanceBookings()
    {
        application.gotoViewAdvanceBookings();
    }
}
