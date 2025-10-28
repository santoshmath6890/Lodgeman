/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javax.persistence.NoResultException;
import lodgeman.lalitman.controller.reports.LaundryReport;
import lodgeman.lalitman.controller.reports.RoomAmenity;
import lodgeman.lalitman.controller.table.AmenityTrackTable;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookedroomamenities;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.Itemtrans;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */

public class AmenityTrackController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<Bookedroomamenities> tableAmeinityTrack;
    
    @FXML
    private TableColumn columnRoomno;
    
    @FXML
    private TableColumn roomcleanersent;
    @FXML
    private TableColumn roomcleanerby;
    
    @FXML
    private TableColumn columnBlanketsent;
    @FXML
    private TableColumn columnBlanketrecv;
    
    @FXML
    private TableColumn columnSingleBedsheetsent;
    @FXML
    private TableColumn columnSingleBedsheetrecv;
    
    @FXML
    private TableColumn columnDoubleBedsheetsent;
    @FXML
    private TableColumn columnDoubleBedsheetrecv;
    
    @FXML
    private TableColumn columnTowelsent;
    @FXML
    private TableColumn columnTowelrecv;
    
    @FXML
    private TableColumn columnPillowsent;
    @FXML
    private TableColumn columnPillowrecv;
    
    @FXML
    private TableColumn<AmenityTrackTable, Boolean> tActionCol;
    
    @FXML
    private ComboBox<Items> itemOptionList;
    
    @FXML
    private TextField itemInUse;
    
    @FXML
    private TextField itemInStore;
    
    @FXML
    private TextField itemInLaundry;
    
    private Integer totalsinglebedG;
    
    List<Room> currentBookedRoom = new ArrayList<Room>();
    HashMap<Integer, Bookings> bookingRoomMap = new HashMap<>();
    
    ObservableList<Bookedroomamenities> finalList = FXCollections.observableArrayList();
    List<AmenityTrackTable> tempList = FXCollections.observableArrayList();
    ObservableList<Items> tempfinallist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        columnRoomno.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Room>("roomno"));
        
        columnSingleBedsheetsent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("singlebedsheet"));
        columnBlanketsent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("blanket"));
        columnDoubleBedsheetsent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("doublebedsheet"));
        columnPillowsent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("pillow"));
        columnTowelsent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("towel"));
        
        columnSingleBedsheetrecv.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("singlebedsheetrecv"));
        columnDoubleBedsheetrecv.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("doublebedsheetrecv"));
        columnPillowrecv.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("pillowrecv"));
        columnBlanketrecv.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("blanketrecv"));
        columnTowelrecv.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("towelrecv"));
        
        roomcleanersent.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("sentby"));
        roomcleanerby.setCellValueFactory(new PropertyValueFactory<Bookedroomamenities, Integer>("receivedby"));
        
        QueryManager qm = QueryManager.getInstance();
        List<Bookedroomamenities> thislist = qm.findAll("Bookedroomamenities.findAll");
        for(int i = 0 ; i < thislist.size(); i++)
        {
            finalList.add(thislist.get(i));
        }
        tableAmeinityTrack.setItems(finalList);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoAccounts();
    }
    
    public void processPrint() throws JRException, IOException
    {
       Integer p = (int)(Math.random() * 9999);
        
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        System.out.println("my path" + path1);
        String filename = path1 + "/" + "RoomAmenityReport" + p +  ".pdf";
        //AskAdvanceReport.printsessionreport(transID, filename);
        RoomAmenity.printroomamenityreport(filename); 
    }
}
    
    