/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class BookingExtendController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField mRoomNo;
    
    @FXML
    private TextField mRoomType;    
    
    @FXML
    private TextField  mGuestName;
        
    @FXML
    private TextField mBookingDate;
          
    @FXML
    private TextField mCheckinDate;
              
    @FXML
    private TextField mExpectedCheckoutDate;
    
    @FXML
    private TextField mNewCheckoutDate;
    
    @FXML
    private TextField mUserRoomNo;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
}
