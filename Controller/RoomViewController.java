/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.NoResultException;
import lodgeman.lalitman.controller.table.RoomViewTable;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<RoomViewTable> tableRoom;
    
    @FXML
    private TableColumn<RoomViewTable, Integer> roomno;
    
    @FXML
    private TableColumn<RoomViewTable, String> roomtype;
    
    @FXML
    private TableColumn<RoomViewTable, String> bedsheettype ;
    
    @FXML
    private TableColumn<RoomViewTable, Integer> rate;
    
    @FXML
    private TableColumn<RoomViewTable, Integer> bedsheetqty;
    
    ObservableList<RoomViewTable> finallist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        roomno.setCellValueFactory(new PropertyValueFactory<RoomViewTable, Integer>("roomno"));
        roomtype.setCellValueFactory(new PropertyValueFactory<RoomViewTable, String>("roomtype"));
        bedsheettype.setCellValueFactory(new PropertyValueFactory<RoomViewTable, String>("bedsheettype"));
        rate.setCellValueFactory(new PropertyValueFactory<RoomViewTable, Integer>("rate"));
        bedsheetqty.setCellValueFactory(new PropertyValueFactory<RoomViewTable, Integer>("bedsheetqty"));
        
        QueryManager qm  = QueryManager.getInstance();
        List<Room> allRoom = qm.findAll("Room.findAll");
        
        for(int i = 0 ; i < allRoom.size(); i++)
        {
            System.out.println("this room: " + allRoom.get(i).getOid());
            Amenityroom thisamenityroom = new Amenityroom();
            try
            {
                thisamenityroom = qm.findAmenityroomByRoom(allRoom.get(i));
            }
            catch(NoResultException ex)
            {
                System.out.println("No result found!");
                continue;
            }
            Integer tableroomno = allRoom.get(i).getOid();
            String tableroomtype = allRoom.get(i).getRoomtypeid().getName();
            BigDecimal tablerate = allRoom.get(i).getRoomtypeid().getRate();
            //String tablebedsheettype = thisamenityroom.getBedsheettype().getItemName();
            //Integer bedsheetqty = thisamenityroom.getBedsheetqty();
            
            //RoomViewTable simpleobj = new RoomViewTable(tableroomno, tableroomtype, tablerate, tablebedsheettype, bedsheetqty);
            //finallist.add(simpleobj);
        }
        
      tableRoom.setItems(finallist);
        
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
