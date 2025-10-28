/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.view.LodgeMan;
import sun.plugin.dom.core.Text;
/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomtypeAddController implements Initializable 
{
    /**
     * Initializes the controller class.
     */    
    private LodgeMan application;
    
    @FXML
    TextField mRoomTypeName;
    
    @FXML
    TextArea mRoomDesc;
    
    @FXML
    TextField mMaxAdult;
    
    @FXML
    TextField mMaxChild;  
    
    @FXML 
    TextField rate;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    public void setApp(LodgeMan application) 
    {
        this.application = application;        
    }
    
    public void processHome()
    {
        application.gotoHome();                
    }
    
    public void processBack()
    {
        application.gotoRoomType();
    }
    
    public void processSubmit()
    {
        String roomType = mRoomTypeName.getText();
        String roomDesc = mRoomDesc.getText();
        
        String maxChildString = mMaxChild.getText();
        Integer maxChild = Integer.parseInt(maxChildString);
        
        String maxAdlutString = mMaxAdult.getText();
        Integer maxAdult = Integer.parseInt(maxAdlutString);
        
        String rateString = rate.getText();
        BigDecimal  rate1 = new BigDecimal(rateString);
        
        if(LodgeConfig.newRoomType(roomType,roomDesc, maxAdult, maxChild, rate1))
        {
            application.gotoRoomType();
        }
        else
        {
            System.out.println("Room Type cannot be saved.");
            application.gotoRoomTypeAdd();
        }
    }    
}
