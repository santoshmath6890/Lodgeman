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
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomAmenityAddController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    @FXML
    private TextField userRoomno;
    
    @FXML
    private ComboBox<Integer> singlebedsheetqty;
    
    @FXML
    private ComboBox<Integer> doublebedsheetqty;
    
    @FXML
    private ComboBox<Integer> pillowqty;
    
    @FXML
    private ComboBox<Integer> blanketqty;
    
    @FXML
    private ComboBox<Integer> towelqty;
    
    private Room roomG;
    
    //ObservableList<Items> bedsheettypelist = FXCollections.observableArrayList();
    ObservableList<Integer> qtylist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        List<Items> allItems  = qm.findAll("Items.findAll");
        /*
        for(int i = 0; i < allItems.size(); i++)
        {
            bedsheettypelist.add(allItems.get(i));
        }
        bedsheettype.setItems(bedsheettypelist);
        */
        for(int i = 0; i < 9; i++ )
        {
            qtylist.add(i);
        }
        doublebedsheetqty.setItems(qtylist);
        singlebedsheetqty.setItems(qtylist);
        pillowqty.setItems(qtylist);
        blanketqty.setItems(qtylist);
        towelqty.setItems(qtylist);
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
        Integer roomno = Integer.parseInt(userRoomno.getText());
        QueryManager qm = QueryManager.getInstance();
        Room room = new Room();
        try
        {
            room = Room.getRoomByOid(roomno);
            this.roomG = room;
        }
        catch(Exception ex)
        {
            System.out.println("Invalid room");
        }
    }
    
    public void processSubmit()
    {
        QueryManager qm = QueryManager.getInstance();
        List<Items> allItems = qm.findAll("Items.findAll");
        Integer thissingleqty = singlebedsheetqty.getValue();
        Integer thisdoubleqty = doublebedsheetqty.getValue();
        Integer thispillowqty = pillowqty.getValue();
        Integer thisblanketqty = blanketqty.getValue();
        Integer thistowelqty = towelqty.getValue();
        Items singleBed = new Items();        
        Items doubleBed = new Items();        
        Items pillow = new Items();        
        Items blanket = new Items();        
        Items towel = new Items();        
        
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getItemName().contains("Single"))
            {
                singleBed = allItems.get(i);
                System.out.println("SIngle:" + singleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Double"))
            {
                doubleBed = allItems.get(i);
                System.out.println("Double:" + doubleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Pillow"))
            {
                pillow = allItems.get(i);
                System.out.println("pillow:" + pillow.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Blanket"))
            {
                blanket = allItems.get(i);
                System.out.println("Blanket:" + blanket.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Towel"))
            {
                towel = allItems.get(i);
                System.out.println("towel:" + towel.getItemName() );
                //continue;
            }
        }
        
        LodgeConfig.newRoomAmenity(thissingleqty, thissingleqty, thisdoubleqty, thisdoubleqty, thispillowqty, thispillowqty, thisblanketqty, thisblanketqty, thistowelqty, thistowelqty,roomG, singleBed, doubleBed,pillow, blanket,towel);
        application.gotoConfig();
    }
    
}
