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
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
    }
    
     public void processRoomType()
    {
        application.gotoRoomType();
    } 
     
    public void processAmenities()
    {
        application.gotoAmenities();
    }
    
     public void processAddRoomAmenity()
    {
        application.gotoAddRoomAmenity();
    }
     
     public void processRoomupdate()
    {
        application.gotoRoomupdate();
    }
     
    public void processRoomView()
    {
        application.gotoRoomView();
    } 
    
    public void processRoom()
    {
        application.gotoRoom();
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }
}
