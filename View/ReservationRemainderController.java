/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lodgeman.lalitman.component.eventMan.eventtype.Once;
import lodgeman.lalitman.model.pojo.EntityEnum;
import lodgeman.lalitman.model.pojo.Events;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Reservations;
import org.quartz.SchedulerException;

/**
 * FXML Controller class
 *
 * @author svastrad
 */
public class ReservationRemainderController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    @FXML
    Button reservebtn;
    @FXML
    TextField txtCustName;
    
     @FXML
     TextArea txtCustAddr;
      @FXML
    TextField txtCustNum1;
       @FXML
    TextField txtCustNum2;
     @FXML
    TextField txtAdvRcvd;
     @FXML
     ComboBox<Integer> cmbRoomType;

     @FXML
     jfxtras.labs.scene.control.CalendarTextField advDate;
     
     @FXML
     jfxtras.labs.scene.control.CalendarTimeTextField time1;
     
     @FXML
     jfxtras.labs.scene.control.CalendarTimeTextField time2;
     
     @FXML
     jfxtras.labs.scene.control.CalendarTextField currDate;
    private LodgeMan application;
     
    public void reserveClck(ActionEvent ae) throws SchedulerException
    {
        Date dt = new Date();
        long tt = dt.getTime();
        tt = tt +5000;
        dt.setTime(tt);
       // Once on = new Once("reserve", "rsv", dt, "reserve1", "rsv1");
        if(txtCustName.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Customer Name "
                        + "Customer Name is Empty");
            return ;
        }
             String cname  =  txtCustName.getText();
             //validate for number in text Name
             
        if (txtCustNum1.getText().isEmpty() )
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Customer Number "
                        + "Customer Number is Empty");
            return ;
        }
        
        Long cNumber = Long.valueOf(txtCustNum1.getText());
        Long cNum2 = Long.valueOf(txtCustNum2.getText());
        Integer cAdvance;
        String cAddr =    txtCustAddr.getText();
        try
        {
         cAdvance =    Integer.parseInt(txtAdvRcvd.getText());
        }
        catch(Exception e)
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Advace Amount is not a Number"
                        + "Please fill Acvance Amt with number");
        return;
        }
        

                     Calendar s;
                     Calendar UserTime = time1.getValue();
                     s = advDate.getValue();
                     Date ReservationDate = s.getTime();
                      Date startD1 = UserTime.getTime();
                     ReservationDate.setHours(startD1.getHours());
                     ReservationDate.setMinutes(startD1.getMinutes());
                     System.out.println("Reservation date: " + ReservationDate);
                     
                     
                      
                      UserTime = time2.getValue();
                     s = currDate.getValue();
                      Date currentDate = s.getTime();
                       startD1 = UserTime.getTime();
                     currentDate.setHours(startD1.getHours());
                     currentDate.setMinutes(startD1.getMinutes());
                     System.out.println("current date: " + currentDate);
                     
                     
                     
        //roomType = cmbRoomType.
        Reservations r = new  Reservations(null, cname, cAddr, cNumber.intValue(), cNum2.intValue(), ReservationDate, currentDate,cAdvance.intValue(),0 , 0);
        
        Once on = new Once(""+cNumber+"", "rsv", ReservationDate, ""+cNumber+"", "rsv");
        on.schedulePopUpJob(); 
        QueryManager.getInstance().Save(EntityEnum.RESERVATION, r);
        Events ev = new Events(null, 1, currentDate, 1, ReservationDate, 1, 1, 1, 1, ""+cNumber+"");
        QueryManager.getInstance().Save(EntityEnum.EVENTS, ev);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } 
    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
    }
}
