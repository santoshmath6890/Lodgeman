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
public class LaundryController implements Initializable 
{

    /*
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
    
    public void processLaundryNew()
    {
        application.gotoLaundryNewItem();
    }
    
    public void processLaundryTransaction()
    {
        application.gotoLaundryTransaction();
    }
    
    public void processLaundryLog()
    {
        application.gotoLaundryLog();
    }
    
    public void processAmenityTrack()
    {
       application.gotoAmenityTrack();
    }
    
    public void processAmenityFix()
    {
       application.gotoAmenityFix();
    }
    
    public void processAmenityCycle()
    {
        application.gotoAmenityCycle();
    }
    
    public void processRoomReady()
    {
        application.gotoRoomReady();
    }
    
    public void processRoomRecv()
    {
        application.gotoRoomRecv();
    }
}
