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
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomtypeUpdateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField InputName;
    
    @FXML
    private TextField InputDesc;
    
    @FXML
    private TextField InputmaxChildren;
    
    @FXML
    private TextField InputmaxAdult;
    
    @FXML
    private TextField InputRate;
    
    Roomtype roomtypeG;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis, Roomtype thisroomtype) 
    {
        this.application = aThis;
        this.roomtypeG = thisroomtype;
        
        InputName.setText(thisroomtype.getName());
        InputDesc.setText(thisroomtype.getDescription());
        InputRate.setText("" + thisroomtype.getRate());
        InputmaxAdult.setText("" + thisroomtype.getMaxadult());
        InputmaxChildren.setText("" + thisroomtype.getMaxchildren());
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoRoomType();
    }
    
    public void processUpdate()
    {
        String newName  = InputName.getText();
        String newDesc = InputDesc.getText();
        int newAdult = Integer.parseInt(InputmaxAdult.getText());
        int newChildren = Integer.parseInt(InputmaxChildren.getText());
        double newRate = Double.parseDouble(InputRate.getText());
        
        QueryManager qm = QueryManager.getInstance();
        int result = qm.updateRoomtype(roomtypeG.getOid(), newName, newDesc, newChildren, newAdult, newRate);
        application.gotoRoomType();
    }
          
}
