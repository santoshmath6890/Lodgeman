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
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomUpdateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField userRoomno;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        application.gotoConfig();
    }
    
    public void processSearch()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer inputroomno = Integer.parseInt(userRoomno.getText());
        Room room = Room.getRoomByOid(inputroomno);
        
        
    }
}
