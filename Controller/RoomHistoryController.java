/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.NoResultException;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomHistoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    @FXML
    private TableView<Bills> TableRoomHistory;
    
    @FXML
    private TableColumn<Bills, Integer> ColumnRoomno;
    
    @FXML
    private TableColumn<Bills, String> ColumnGuestName;
    
    @FXML
    private TableColumn<Bills, Date> ColumnCheckinDate;
    
    @FXML
    private TableColumn<Bills, Date> ColumnCheckoutDate;
    
    @FXML
    private TableColumn<Bills, Integer> ColumnBillOid;
    
    @FXML
    private TableColumn<Bills, Integer> ColumnTotalBill;
    
    @FXML
    private TextField InputUserno;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputFromDate;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputToDate;
    
    ObservableList<Bills> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        ColumnRoomno.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("roomno"));
        ColumnGuestName.setCellValueFactory(new PropertyValueFactory<Bills, String>("guestname"));
        ColumnCheckinDate.setCellValueFactory(new PropertyValueFactory<Bills, Date>("checkindate"));
        ColumnCheckoutDate.setCellValueFactory(new PropertyValueFactory<Bills, Date>("checkoutdate"));
        ColumnBillOid.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("oid"));
        ColumnTotalBill.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("totalbill"));
        
        /**********************************************************************/
        Calendar thistime = Calendar.getInstance();
        thistime.add(Calendar.DAY_OF_MONTH, -10);
        InputFromDate.setValue(thistime);
        InputToDate.setValue(Calendar.getInstance());
        /**********************************************************************/
    }
public boolean  validate()
    {
    
      if(InputUserno.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
      return false;
      }
      return true;
    }
        
    public void processSearch()
    {
        if(validate())
        {
        Integer userroomnoint = Integer.parseInt(InputUserno.getText());
        Room thisroomno = Room.getRoomByOid(userroomnoint);
        
        Calendar fromcal = InputFromDate.getValue();
        Date fromdate = fromcal.getTime();
        
        Calendar tocal = InputToDate.getValue();
        Date todate = tocal.getTime();
        
        QueryManager qm = QueryManager.getInstance();
        List<Bookings> thislist = qm.findRoomHistoryFromDate(fromdate, todate, thisroomno);
        for(int i = 0 ; i < thislist.size(); i++)
        {
            System.out.println("" + thislist.get(i));
            try
            {
            Bills onebill = qm.findBillsByBookings(thislist.get(i));
            finalList.add(onebill);
            }
            catch(NoResultException ex)
            {
                System.out.println("No result! Carry on!!");
            }
        }
        TableRoomHistory.setItems(finalList);
    }
    }
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHistory();
    }
    
}
