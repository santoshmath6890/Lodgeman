
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lodgeman.lalitman.controller.table.DailyActivitiesTable;
import lodgeman.lalitman.model.business.SessionCalculation;
import lodgeman.lalitman.model.pojo.Bills;
//import lodgeman.lalitman.model.pojo.Bills1;
//import lodgeman.lalitman.model.pojo.Bills2;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;


public class DailyActivitiesController implements Initializable 
{
    
    private LodgeMan application;
    
    @FXML
    private TableView<DailyActivitiesTable> TableDA;
    
    @FXML
    private TableColumn<DailyActivitiesTable, String> ColumnTimings;
    
    @FXML
    private TableColumn<DailyActivitiesTable, Integer> ColumnCheckinNo;
    
    @FXML
    private TableColumn<DailyActivitiesTable, Integer> ColumnCheckoutNo;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField TextFieldCalendar;

    ObservableList<DailyActivitiesTable> ListDailyActivities = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        System.out.println("_______________________________");
        System.out.println("DailyActivitiesController.java");
        System.out.println("_______________________________");
        
        ColumnTimings.setCellValueFactory(new PropertyValueFactory<DailyActivitiesTable, String>("timings"));
        ColumnCheckinNo.setCellValueFactory(new PropertyValueFactory<DailyActivitiesTable, Integer>("checkinno"));
        ColumnCheckoutNo.setCellValueFactory(new PropertyValueFactory<DailyActivitiesTable, Integer>("checkoutno"));
        
        Date stDate = new Date();
        calculateDailyActivities(stDate);
    }
    
    public void calculateDailyActivities(Date startDate)
    {
        ObservableList<DailyActivitiesTable> ListDailyActivities1 = FXCollections.observableArrayList();
        QueryManager qm = QueryManager.getInstance();
        
        Date endDate = new Date();
        endDate.setDate(startDate.getDate());
        endDate.setMonth(startDate.getMonth());
        endDate.setYear(startDate.getYear());
        
        for(int i = 0; i < 22; i = i + 3)
        {
            startDate.setHours(i);
            startDate.setMinutes(00);
            startDate.setSeconds(1);
            
            endDate.setHours(i+3);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            
            SessionCalculation thisSession = new SessionCalculation(startDate, endDate);
            
            List<Bookings> allBookings = qm.findBookingsBetweenDates(startDate, endDate);
            
            List<Bills> allBills1 = qm.findBillsBetween(startDate, endDate);
            //List<Bills2> allBills2 = qm.findBills2Between(startDate, endDate);
            Integer totalBills = allBills1.size();
            
            System.out.println("***********************************************");
            System.out.println("From: " + startDate);
            System.out.println("To: " + endDate);
            System.out.println("Checkin total: " + allBookings.size());
            System.out.println("Checkout total: " + totalBills);
            System.out.println("***********************************************");
            
            /******************************************************************/
            int tempendint = i + 3;
            int tempstartint = i;
            String st;
            String et;
            if(tempstartint < 12)
            {
                st = "" + tempstartint + " AM";
            }
            else if(tempstartint == 12)
            {
                st = "" + tempstartint + " PM";
            }
            else
            {
                tempstartint = tempstartint - 12;
                st = "" + tempstartint + " PM";
            }
            
            if(tempendint < 12)
            {
                et = "" + tempendint + " AM";
            }
            else if(tempendint == 12)
            {
                et = "" + tempendint + "PM";
            }
            else
            {
                tempendint = tempendint - 12;
                et = "" + tempendint + "PM";
            }
            /******************************************************************/
            
            String tempString = "" + st + "   -   " + et + "";
            ListDailyActivities1.add(new DailyActivitiesTable(tempString, allBookings.size(), totalBills));
        }
        
        TableDA.setItems(ListDailyActivities1);
    }
    
    public void processSearch()
    {
       Calendar ca = TextFieldCalendar.getValue();
       Date okay = ca.getTime();
        System.out.println("" + okay);
       calculateDailyActivities(okay);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
}
