/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.math.BigInteger;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class NewReservationWithoutAdvanceController implements Initializable 
{
    /*
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    jfxtras.labs.scene.control.CalendarTextField InputDate;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTimeTextField InputTime;
    
    @FXML
    private TextField InputGuestName;
    
    @FXML
    private TextField InputGuestContactno;
    
    @FXML
    private TextField InputGuestCity;
    
    @FXML
    private TextField InputGuestNoOfPeople;
    
    @FXML
    private ComboBox<Roomtype> allRoomtype;
    
    @FXML
    private TextField inputGuestNoOfRoom;
    
    @FXML
    private TextArea inputNote;
    
    ObservableList<Roomtype> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Roomtype> simpleRoomtypelist = qm.findAll("Roomtype.findAll");
        for(int i = 0; i < simpleRoomtypelist.size(); i++)
        {
            finalList.add(simpleRoomtypelist.get(i));
        }
        allRoomtype.setItems(finalList);
        /**********************************************************************/
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewEntity();
    }
    
    public void processReserve()
    {
        /**********************************************************************/
        String username = InputGuestName.getText();
        BigInteger usercontactno;

        String usercontactnoString = InputGuestContactno.getText();
        Long  ss = Long.parseLong(InputGuestContactno.getText());
        usercontactno = BigInteger.valueOf(ss);
        
        String usercity = InputGuestCity.getText();
        Integer usernoofpeople = Integer.parseInt(InputGuestNoOfPeople.getText());
        Integer usernoofroom = Integer.parseInt(inputGuestNoOfRoom.getText());
        Roomtype userthisroomtype = allRoomtype.getValue();
        
        String usernote;
        try
        {
            usernote = inputNote.getText();
        }
        catch(Exception ex)
        {
            usernote = null;
        }
        /**********************************************************************/
        
        /**********************************************************************/
        Calendar fromInputtime = InputTime.getValue();
        Calendar fromInputdate = InputDate.getValue();
        
        Date userInputDate = fromInputdate.getTime();
        Date tempDate = fromInputtime.getTime();
        
        userInputDate.setHours(tempDate.getHours());
        userInputDate.setMinutes(tempDate.getMinutes());
        /**********************************************************************/
        
        /**********************************************************************/
        Date userCurrentDate = new Date();
        QueryManager qm = QueryManager.getInstance();
        Users thisusers = qm.findLoggedInUser();
        LodgeConfig.newReservationWithoutAdvance(username, usernoofroom, userInputDate, usernoofpeople, usercity, userCurrentDate, usercontactno, thisusers, usernote, userthisroomtype);
        application.gotoViewEntity();
        /**********************************************************************/
    }
}
