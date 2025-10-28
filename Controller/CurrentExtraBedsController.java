/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.NoResultException;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class CurrentExtraBedsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<Bookingaddon> TableView;
     
    @FXML 
    private TableColumn <Bookingaddon, Room> ColumnUserRoomNo;
    
    @FXML
    private TableColumn<Bookingaddon ,Date> ColumnFromDate;
    
    ObservableList<Bookingaddon> finallist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        ColumnFromDate.setCellValueFactory(new PropertyValueFactory<Bookingaddon, Date>("fromdate"));
        ColumnUserRoomNo.setCellValueFactory(new PropertyValueFactory<Bookingaddon, Room>("roomno"));
        QueryManager qm = QueryManager.getInstance();
        
        List<Bookings> allCurrentBookings = qm.findCurrentBookings();
        for(int i = 0; i < allCurrentBookings.size(); i++)
        {
            try
            {
                List<Bookingaddon> tempbookingaddon = qm.findBookingAddonByBookingid(allCurrentBookings.get(i));
                for(int ii = 0; ii < tempbookingaddon.size(); ii++)
                {
                    System.out.println("Inside the den...");
                    System.out.println("To Date : " + tempbookingaddon.get(ii).getTodate());
                    if(tempbookingaddon.get(ii).getTodate() == null)
                    {
                        System.out.println("In to the table");
                        finallist.add(tempbookingaddon.get(ii));
                    }
                }
            }
            catch(NoResultException ex)
            {
                System.out.println("Not for this booking. Carry on.");
            }
        }
        TableView.setItems(finallist);
        
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
