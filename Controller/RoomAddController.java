/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomAddController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField mRoomNo;
    
    @FXML
    private TextField mFloorNo;
    
    @FXML
    private ComboBox<Roomtype> bookingroomtypelist;
    
    ObservableList<Roomtype> finalRoomtypeList = FXCollections.observableArrayList();
    
    
    Room roomG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis, Room thisroom) 
    {
        this.application = aThis;
        this.roomG = thisroom;
        mRoomNo.setText("" + thisroom.getOid());
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
        List<Roomtype> allRoomtype = qm.findAll("Roomtype.findAll");
        for(int i = 0 ; i < allRoomtype.size(); i++)
        {
            finalRoomtypeList.add(allRoomtype.get(i));
        }
        
        bookingroomtypelist.setItems(finalRoomtypeList);
        for(int i = 0 ; i < finalRoomtypeList.size();i++)
        {
            if(finalRoomtypeList.get(i).getOid() == thisroom.getRoomtypeid().getOid())
            {
                bookingroomtypelist.setValue(finalRoomtypeList.get(i));
            }
        }
        /**********************************************************************/
        
        mFloorNo.setText("" + thisroom.getFloorno());
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoRoom();
    }
    
    public void processUpdate()
    {
        Integer newRoomno = Integer.parseInt(mRoomNo.getText());
        Integer newFloorno = Integer.parseInt(mFloorNo.getText());
        Roomtype newRoomtype = bookingroomtypelist.getValue();
        
        QueryManager qm = QueryManager.getInstance();
        int result = qm.updateRoom(roomG.getOid(), newRoomtype, newFloorno);
        application.gotoRoom();
    }
    
    public void processCancel()
    {
        application.gotoRoom();
    }
}
