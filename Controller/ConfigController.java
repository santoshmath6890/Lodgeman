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
    

public class ConfigController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application)
    {
        this.application = application;
    }
    
    public void processRoom()
    {
        application.gotoRoom();
    }
    
    public void processRoomType()
    {
        application.gotoRoomType();
    }   
    
    public void processAmenities()
    {
        application.gotoAmenities();
    }
    
    public void processCreditOwners()
    {
        application.gotoCreditownersNew();
    }
    
    public void processCreditbillView()
    {
        application.gotoCreditbillView();
    }
    
    public void processBookingLogView()
    {
        application.gotoBookingLogView();
    }
    
    public void processAdvanceLogView()
    {
        application.gotoAdvanceLogView();
    }
    
    public void processRoomupdate()
    {
        application.gotoRoomupdate();
    }
    
    public void processAddRoomAmenity()
    {
        application.gotoAddRoomAmenity();
    }
    
    public void processRoomView()
    {
        application.gotoRoomView();
    }
    
    public void processMyAccout()
    {
        application.gotoMyAccount();
    }
    
    public void processAskMoreAdvance()
    {
        application.gotoAskMoreAdvance();;
    }
    
    public void processCancelBooking()
    {
        application.gotoBookingCancel();
    }
    
    public void processViewCurrentGuests()
    {
        application.gotoViewCurrentGuests();
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processSetting()
    {
        application.gotoSettings();
    }
       
    public void processChangeOpeningBalance()
    {
        application.gotoChangeOpeningBalance();
    }
    
    public void processHistory()
    {
        application.gotoHistory();
    }
    
    public void processReports()
    {
        application.gotoReports();
    }
    
    public void processCurrentExtraBeds()
    {
        application.gotoCurrentExtraBeds();
    }
    
    public void processAddAditionalAdvance()
    {      
          application.gotoAditionalAdvance();
    }
}
