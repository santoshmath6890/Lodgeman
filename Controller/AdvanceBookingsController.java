/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import lodgeman.lalitman.controller.reports.ReservationReceipt;
import lodgeman.lalitman.controller.reports.advancebill;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.digitstowords;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AdvanceBookingsController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputDate;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTimeTextField InputTime;
    
    @FXML
    private TextField Inputadvancepaid;
    
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
    
    public void processReserve() throws JRException, IOException
    {
        QueryManager qm = QueryManager.getInstance();

        /**********************************************************************/
        Calendar fromInputtime = InputTime.getValue();
        Calendar fromInputdate = InputDate.getValue();
        
        Date userInputDate = fromInputdate.getTime();
        Date tempDate = fromInputtime.getTime();
        
        userInputDate.setHours(tempDate.getHours());
        userInputDate.setMinutes(tempDate.getMinutes());
        /**********************************************************************/
        
        
        /**********************************************************************/
        String userguestname = InputGuestName.getText();
        BigInteger usercontactno;

        String usercontactnoString = InputGuestContactno.getText();
        Long  ss = Long.parseLong(InputGuestContactno.getText());
        usercontactno = BigInteger.valueOf(ss);
        
        String usercity = InputGuestCity.getText();
        Integer usernoofpeople = Integer.parseInt(InputGuestNoOfPeople.getText());
        Integer usernoofroom = Integer.parseInt(inputGuestNoOfRoom.getText());
        Roomtype userthisroomtype = allRoomtype.getValue();
        Integer userAdvancepaid = Integer.parseInt(Inputadvancepaid.getText());
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
        //For Receipts.
        /**********************************************************************/
        Bookings thisbooking = qm.findBookingByOid(1000);
        Integer userregnno = 0;
        
        Date arrdateForReceipts = new Date();
        String roomno = "000";//  /*"" + usernoofroom + " " + userthisroomtype.getName() + " Rooms. " + */"Expected Arrival: " + userInputDate;
        System.out.println("This: " + roomno);
        
        digitstowords d2w = digitstowords.getInstance(userAdvancepaid);
        String in_words = d2w.getNumberInWords();
        in_words = in_words + " only.";
        
        LodgeConfig.newReceipts(userguestname, roomno, arrdateForReceipts, usernoofpeople, BigDecimal.ZERO, BigDecimal.ZERO, userAdvancepaid, in_words, thisbooking, 0, true);
        /**********************************************************************/
        
        /**********************************************************************/
        //For ReservationWithAdvance
        /**********************************************************************/
        Users thisusers = qm.findLoggedInUser();
        Receipts receipts = qm.getLastReceipts();
        Integer receiptNo = receipts.getOid();
        LodgeConfig.newReservationWithAdvance(userguestname, usernoofroom, userInputDate, usernoofpeople, ss, usercity, new Date(), userAdvancepaid, thisusers, usernote, userthisroomtype, receipts);
        /**********************************************************************/
        
        /**********************************************************************/
        //Receipts receipts = qm.getLastReceipts();
        //Integer receiptNo = receipts.getOid();
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        System.out.println("Before" + path);
        String path1 = path.replaceFirst("c", "C");
        String filename = path1 + "/" + "AdvanceReceipt_" + receiptNo + ".pdf";
        System.out.println("Pdf should go there: " + filename);
        System.out.println("I am this: " + receiptNo);
        ReservationReceipt.printreservationreceipt(receiptNo, filename);
        application.gotoViewEntity();
        /**********************************************************************/
    }
}
