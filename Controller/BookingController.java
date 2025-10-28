/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.persistence.NoResultException;

import lodgeman.lalitman.model.business.Booking.CheckRoom;
import lodgeman.lalitman.model.business.Booking.RoomStatus;
import lodgeman.lalitman.model.business.GstMgr;
import lodgeman.lalitman.model.business.TimersForLodgeManMgr;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookedroomamenities;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author soft
 */

public class BookingController implements Initializable 
{
    private LodgeMan application;

    @FXML
    private Label label;
    
    @FXML
    private ComboBox<String> bookingtype;
    
    @FXML
    Rectangle rb1;
    
    @FXML
    Rectangle rb2;
    
    @FXML
    Rectangle rb3;    
     
    @FXML
    Rectangle rb4;
    
    @FXML
    Rectangle rb5;
    
    @FXML
    Rectangle rb6;
    
    @FXML
    Rectangle rb7;
    
    @FXML
    Rectangle rb8;
    
    @FXML
    Rectangle rb9;    
     
    @FXML
    Rectangle rb10;
    
    @FXML
    Rectangle rb11;
    
    @FXML
    Rectangle rb12;
    
    @FXML
    Rectangle rb13;
    
    @FXML
    Rectangle rb14;
    
    @FXML
    Rectangle rb15;    
     
    @FXML
    Rectangle rb16;
    
    @FXML
    Rectangle rb17;
    
    @FXML
    Rectangle rb18;
    
    @FXML
    Rectangle rb19;
    
    @FXML
    Rectangle rb20;
    
    @FXML
    Rectangle rb21;    
     
    @FXML
    Rectangle rb22;
    
    @FXML
    Rectangle rb23;
    
    @FXML
    Rectangle rb24;
    
    @FXML
    Rectangle rb25;
    
    @FXML
    Rectangle rb26;
    
    @FXML
    Rectangle rb27;    
     
    @FXML
    Rectangle rb28;
    
    @FXML
    Rectangle rb29;
    
    @FXML
    Rectangle rb30;
    
    @FXML
    Rectangle rb31;
    
    @FXML
    Rectangle rb32;
    
    @FXML
    Rectangle rb33;
     
    @FXML
    Rectangle rb34;
    
    @FXML
    Rectangle rb35;
    
    @FXML
    Rectangle rb36;
    
    @FXML
    Rectangle rb37;
    
    @FXML
    Rectangle rb38;
    
    @FXML
    Rectangle rb39;
     
    @FXML
    Rectangle rb40;
    
    @FXML
    Rectangle rb41;
    
    @FXML
    Rectangle rb42;
    
    @FXML
    Rectangle rb43;
    
    @FXML
    Rectangle rb44;
    
    @FXML
    Rectangle rb45;
     
    @FXML
    Rectangle rb46;
    
    @FXML
    Rectangle rb47;
    
    @FXML
    Rectangle rb48;
    
     @FXML
    Rectangle rb49;
    
    @FXML
    Rectangle rb50;
    
    @FXML
    Rectangle rb51;
     
    @FXML
    Rectangle rb52;
    
    @FXML
    Rectangle rb53;
    
    @FXML
    Rectangle rb54;
    
    @FXML
    Rectangle rb55;
    
    @FXML
    Rectangle rb56;
    
    @FXML
    Rectangle rb57;
     
    @FXML
    Rectangle rb58;
    
    @FXML
    Rectangle rb59;
    
    @FXML
    Rectangle rb60;
    
    @FXML
    Rectangle rb61;
    
    @FXML
    Rectangle rb62;
    
    @FXML
    Rectangle rb63;
     
    @FXML
    Rectangle rb64;
    
    @FXML
    Rectangle rb65;
    
    @FXML
    Rectangle rb66;
    
    @FXML
    Rectangle rb67;
    
    @FXML
    Rectangle rb68;
    
    @FXML
    Rectangle rb69;
    
    @FXML
    Rectangle rb70;
    
    @FXML
    Rectangle rb71;
    
    @FXML
    Rectangle rb72;
    
   /** toggle button */
    
    @FXML
    ToggleButton b1;
    
    @FXML
    ToggleButton b2;
    
    @FXML
    ToggleButton b3;
    
    @FXML
    ToggleButton b4;
    
     @FXML
    ToggleButton b5;
    
    @FXML
    ToggleButton b6;
    
    @FXML
    ToggleButton b7;
    
    @FXML
    ToggleButton b8;
    
     @FXML
    ToggleButton b9;
    
    @FXML
    ToggleButton b10;
    
    @FXML
    ToggleButton b11;
    
    @FXML
    ToggleButton b12;
    
     @FXML
    ToggleButton b13;
    
    @FXML
    ToggleButton b14;
    
    @FXML
    ToggleButton b15;
    
    @FXML
    ToggleButton b16;
    
     @FXML
    ToggleButton b17;
    
    @FXML
    ToggleButton b18;
    
    @FXML
    ToggleButton b19;
    
    @FXML
    ToggleButton b20;
    
     @FXML
    ToggleButton b21;
    
    @FXML
    ToggleButton b22;
    
    @FXML
    ToggleButton b23;
    
    @FXML
    ToggleButton b24;
    
     @FXML
    ToggleButton b25;
    
    @FXML
    ToggleButton b26;
    
    @FXML
    ToggleButton b27;
    
    @FXML
    ToggleButton b28;
    
     @FXML
    ToggleButton b29;
    
    @FXML
    ToggleButton b30;
    
    @FXML
    ToggleButton b31;
    
    @FXML
    ToggleButton b32;
    
    @FXML
    ToggleButton b33;
    
    @FXML
    ToggleButton b34;
    
    @FXML
    ToggleButton b35;
    
    @FXML
    ToggleButton b36;
    
     @FXML
    ToggleButton b37;
    
    @FXML
    ToggleButton b38;
    
    @FXML
    ToggleButton b39;
    
    @FXML
    ToggleButton b40;
    
     @FXML
    ToggleButton b41;
    
    @FXML
    ToggleButton b42;
    
    @FXML
    ToggleButton b43;
    
    @FXML
    ToggleButton b44;
    
     @FXML
    ToggleButton b45;
    
    @FXML
    ToggleButton b46;
    
    @FXML
    ToggleButton b47;
    
    @FXML
    ToggleButton b48;
    
     @FXML
    ToggleButton b49;
    
    @FXML
    ToggleButton b50;
    
    @FXML
    ToggleButton b51;
    
    @FXML
    ToggleButton b52;
    
     @FXML
    ToggleButton b53;
    
    @FXML
    ToggleButton b54;
    
    @FXML
    ToggleButton b55;
    
    @FXML
    ToggleButton b56;
    
     @FXML
    ToggleButton b57;
    
    @FXML
    ToggleButton b58;
    
    @FXML
    ToggleButton b59;
    
    @FXML
    ToggleButton b60;
    
     @FXML
    ToggleButton b61;
    
    @FXML
    ToggleButton b62;
    
    @FXML
    ToggleButton b63;
    
    @FXML
    ToggleButton b64;
    
    @FXML
    ToggleButton b65;
    
    @FXML
    ToggleButton b66;
    
    @FXML
    ToggleButton b67;
    
    @FXML
    ToggleButton b68;
    
    @FXML
    ToggleButton b69;
    
    @FXML
    ToggleButton b70;
    
    @FXML
    ToggleButton b71;
    
    @FXML
    ToggleButton b72;
    
    @FXML
    TextField noDays;
    
    @FXML
    TextField noHours;
    
    @FXML
    Button currentBooking;   
    
    @FXML
    private Button reservationBooking;
    
    @FXML
    private Button quickBooking;
    
    @FXML
    Button checkRooms;
    
    @FXML
    GridPane roomGrid;
    
    @FXML
    private Button kaveriButton;
    
    @FXML
    private Button krishnaButton;
    
    private Date mCheckout;
    private Date mCheckin;
    
    @FXML
    private Label doublevacantroom;
    
    @FXML
    private Label singlevacantroom;
    
     @FXML
    private Label acsinglevacantroom;
      @FXML
    private Label acdoublevacantroom;
    
            
    @FXML
    private ComboBox<Integer> dirtyrooms;
    ObservableList<Integer> finaldirtyrooms = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }  
    
    public void addtoggleGroup()
    {
        //Tooltip t = new Tooltip("Hello Worldjhghjhhghjghj hg hjghgj 450000") ;
        //t.setMinSize(100, 100);
        //t.setMaxSize(80, 100);
        //b1.setTooltip(t);
        
        b1.setToggleGroup(tg);
        b2.setToggleGroup(tg);
        b3.setToggleGroup(tg);
        b4.setToggleGroup(tg);
        b5.setToggleGroup(tg);
        b6.setToggleGroup(tg);
        b7.setToggleGroup(tg);
        b8.setToggleGroup(tg);
        b9.setToggleGroup(tg);
        b10.setToggleGroup(tg);
        b11.setToggleGroup(tg);
        b12.setToggleGroup(tg);
        b13.setToggleGroup(tg);
        b14.setToggleGroup(tg);
        b15.setToggleGroup(tg);
        b16.setToggleGroup(tg);
        b17.setToggleGroup(tg);
        b18.setToggleGroup(tg);
        b19.setToggleGroup(tg);
        b20.setToggleGroup(tg);
        b21.setToggleGroup(tg);
        b22.setToggleGroup(tg);
        b23.setToggleGroup(tg);
        b24.setToggleGroup(tg);
        b25.setToggleGroup(tg);
        b26.setToggleGroup(tg);
        b27.setToggleGroup(tg);
        b28.setToggleGroup(tg);
        b29.setToggleGroup(tg);
        b30.setToggleGroup(tg);
        b31.setToggleGroup(tg);
        b32.setToggleGroup(tg);
        b33.setToggleGroup(tg);
        b34.setToggleGroup(tg);
        b35.setToggleGroup(tg);
        b36.setToggleGroup(tg);
        b37.setToggleGroup(tg);
        b38.setToggleGroup(tg);
        b39.setToggleGroup(tg);
        b40.setToggleGroup(tg);
        b41.setToggleGroup(tg);
        b42.setToggleGroup(tg);
        b43.setToggleGroup(tg);
        b44.setToggleGroup(tg);
        b45.setToggleGroup(tg);
        b46.setToggleGroup(tg);
        b47.setToggleGroup(tg);
        b48.setToggleGroup(tg);
        b49.setToggleGroup(tg);
        b50.setToggleGroup(tg);
        b51.setToggleGroup(tg);
        b52.setToggleGroup(tg);
        b53.setToggleGroup(tg);
        b54.setToggleGroup(tg);
        b55.setToggleGroup(tg);
        b56.setToggleGroup(tg);
        b57.setToggleGroup(tg);
        b58.setToggleGroup(tg);
        b59.setToggleGroup(tg);
        b60.setToggleGroup(tg);
        b61.setToggleGroup(tg);
        b62.setToggleGroup(tg);
        b63.setToggleGroup(tg);
        b64.setToggleGroup(tg);
        b65.setToggleGroup(tg);
        b66.setToggleGroup(tg);
        b67.setToggleGroup(tg);
        b68.setToggleGroup(tg);
        b69.setToggleGroup(tg);
        b70.setToggleGroup(tg);
        b71.setToggleGroup(tg);
        b72.setToggleGroup(tg);
    }
    
    public void sample(ActionEvent event)
    {
         ToggleButton t = (ToggleButton)event.getSource();
        
        System.out.println("toggle Id"+ t.isSelected());
        if(t.isSelected()) 
        {
            String name = "r"+t.getId();
           //Rectangle ap = (Rectangle) t.getParent();
           
            //Rectangle.fillProperty().set(Color.YELLOW);
            //rlist.add(new Integer(null));
        }
        else
        {
            //Rectangle.fillProperty().set(Color.GREEN);
        }
    }

    public void setApp(LodgeMan application) 
    {
        roomGrid.setVisible(false);
     //   kaveriBedNo.setVisible(false);
      //  krishnaBedNo.setVisible(false);
    //    kaveriButton.setVisible(false);
    //    krishnaButton.setVisible(false);
        
        time.setValue( Calendar.getInstance());
        //cal.setValue(Calendar.getInstance());
        BedNoList.add(1);
        BedNoList.add(2);
        BedNoList.add(3);
        BedNoList.add(4);
        BedNoList.add(5);
        BedNoList.add(6);
        BedNoList.add(7);
        BedNoList.add(8);
        
        //krishnaBedNo.setItems(BedNoList);
        //kaveriBedNo.setItems(BedNoList);
        
        addtoggleGroup();
        selectRoomList =  new ArrayList<>();
        this.application = application; 
        init_btn2RoomNumberMap();        
    }
    
    public void setBgColor(int index,Color c ) 
    {
        switch (index) {
            case 1:
                rb1.setFill(c);
                break;
            case 2:
                rb2.setFill(c);
                break;
            case 3:
                rb3.setFill(c);
                break;
            case 4:
                rb4.setFill(c);
                break;
            case 5:
                rb5.setFill(c);
                break;
            case 6:
                rb6.setFill(c);
                break;
            case 7:
                rb7.setFill(c);
                break;
            case 8:
                rb8.setFill(c);
                break;
            case 9:
                rb9.setFill(c);
                break;
            case 10:
                rb10.setFill(c);
                break;
            case 11:
                rb11.setFill(c);
                break;
            case 12:
                rb12.setFill(c);
                break;
            case 13:
                rb13.setFill(c);
                break;
            case 14:
                rb14.setFill(c);
                break;
            case 15:
                rb15.setFill(c);
                break;
            case 16:
                rb16.setFill(c);
                break;
            case 17:
                rb17.setFill(c);
                break;
            case 18:
                rb18.setFill(c);
                break;
            case 19:
                rb19.setFill(c);
                break;
            case 20:
                rb20.setFill(c);
                break;
            case 21:
                rb21.setFill(c);
                break;
            case 22:
                rb22.setFill(c);
                break;
            case 23:
                rb23.setFill(c);
                break;
            case 24:
                rb24.setFill(c);
                break;
            case 25:
                rb25.setFill(c);
                break;
            case 26:
                rb26.setFill(c);
                break;
            case 27:
                rb27.setFill(c);
                break;
            case 28:
                rb28.setFill(c);
                break;
            case 29:
                rb29.setFill(c);
                break;
            case 30:
                rb30.setFill(c);
                break;
            case 31:
                rb31.setFill(c);
                break;
            case 32:
                rb32.setFill(c);
                break;
            case 33:
                rb33.setFill(c);
                break;
            case 34:
                rb34.setFill(c);
                break;
            case 35:
                rb35.setFill(c);
                break;
            case 36:
                rb36.setFill(c);
                break;
            case 37:
                rb37.setFill(c);
                break;
            case 38:
                rb38.setFill(c);
                break;
            case 39:
                rb39.setFill(c);
                break;
            case 40:
                rb40.setFill(c);
                break;
            case 41:
                rb41.setFill(c);
                break;
            case 42:
                rb42.setFill(c);
                break;
            case 43:
                rb43.setFill(c);
                break;
            case 44:
                rb44.setFill(c);
                break;
            case 45:
                rb45.setFill(c);
                break;
            case 46:
                rb46.setFill(c);
                break;
            case 47:
                rb47.setFill(c);
                break;
            case 48:
                rb48.setFill(c);
                break;
            case 49:
                rb49.setFill(c);
                break;
            case 50:
                rb50.setFill(c);
                break;
            case 51:
                rb51.setFill(c);
                break;
            case 52:
                rb52.setFill(c);
                break;
            case 53:
                rb53.setFill(c);
                break;
            case 54:
                rb54.setFill(c);
                break;
            case 55:
                rb55.setFill(c);
                break;
            case 56:
                rb56.setFill(c);
                break;
            case 57:
                rb57.setFill(c);
                break;
            case 58:
                rb58.setFill(c);
                break;
            case 59:
                rb59.setFill(c);
                break;
            case 60:
                rb60.setFill(c);
                break;
            case 61:
                rb61.setFill(c);
                break;
            case 62:
                rb62.setFill(c);
                break;
            case 63:
                rb63.setFill(c);
                break;
            case 64:
                rb64.setFill(c);
                break;
            case 65:
                rb65.setFill(c);
                break; 
            case 66:
                rb66.setFill(c);
                break;    
            case 67:
                rb67.setFill(c);
                break;    
            case 68:
                rb68.setFill(c);
                break;    
            case 69:
                rb69.setFill(c);
                break;    
            case 70:
                rb70.setFill(c);
                break;    
            case 71:
                rb71.setFill(c);
                break;    
            case 72:
                rb72.setFill(c);
                break;    
        }

    }
    
    public void setBgColor(String id,Color c)
    {
       String s = id.substring(1);
       setBgColor(Integer.parseInt(s),c);        
    }
    
    public void processCurrentBooking()
    {
        this.mBookingType = 1;
        
        System.out.println("Booking type: " + mBookingType);
        
        /*
        noHours.setVisible(false);
        hourslabel.setVisible(false);
        cal.setVisible(false);
        datelabel.setVisible(false);
        time.setVisible(false);
        timelabel.setVisible(false);
        
        noDays.setVisible(true);
        daylabel.setVisible(true);
        
        checkRooms.setDisable(false);
        */ 
    }
    
    public void processReservation()
    {
        this.mBookingType = 1;
        System.out.println("Booking type: " + mBookingType);
        
        cal.setVisible(true);
        time.setVisible(true);
        datelabel.setVisible(true);
        timelabel.setVisible(true);
        daylabel.setVisible(true);
        
        noHours.setVisible(false);
        hourslabel.setVisible(false);
        
        checkRooms.setDisable(false);
    }
    
    public void processQuickBooking()
    {
        this.mBookingType = 1;
        System.out.println("Booking type: " + mBookingType);
        noHours.setVisible(true);
        hourslabel.setVisible(true);
        
        cal.setVisible(false);
        datelabel.setVisible(false);
        time.setVisible(false);
        timelabel.setVisible(false);
        daylabel.setVisible(false);
        
        checkRooms.setDisable(false);
    }
    
    public void processCheckRooms(ActionEvent event)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        mBookingType = 1;
        
        switch(mBookingType)
        {
            
            case 1 : System.out.println("Checkin");                     
                     Integer noD = 1;   
                     Date currentDate = new Date();
                     QueryManager qm = QueryManager.getInstance();
                     
                     /*********************************************************/
                     Calendar UserTime = time.getValue();
                     Date startD = new Date();
                     Date startD1 = UserTime.getTime();
                     startD.setHours(startD1.getHours());
                     startD.setMinutes(startD1.getMinutes());
                     System.out.println("Checkin date: " + startD);
                     this.mCheckin = startD;
                     /*********************************************************/
                     
                     /*********User time more than specified time**************/
                     Timersforlodgeman t = qm.getTimervalues();
                     Integer flexibleAmount = t.getCheckintimeval();
                     System.out.println("flexi: " + flexibleAmount);
             
                     if(TimersForLodgeManMgr.validateRange(flexibleAmount, startD) == true)
                     {
                        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Invalid time. Time up.");
                        time.setValue( Calendar.getInstance());
                        return;
                     }
                     /*********************************************************/
                     
                     /***************User time before login time***************/
                     Userlog userlog = qm.getLastUserlog();
                     if(TimersForLodgeManMgr.validateLoginTimer(userlog.getStartdatetime(), startD) == true)
                     {
                        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Invalid time. Before Login time.");
                        time.setValue( Calendar.getInstance());
                        return;
                     }
                     /*********************************************************/
                     
                     /*********************************************************/
                     Calendar ss = Calendar.getInstance();
                     ss.setTime(startD);
                     ss.add(Calendar.DATE, noD);
                     Date endD = ss.getTime();
                     this.mCheckout = endD;
                     System.out.println("End date: " + df.format(endD));
                     /*********************************************************/
                     
                     booking_init(1, noD, startD, endD);        
                     roomGrid.setVisible(true);
                   //  kaveriBedNo.setVisible(true);
                    // krishnaBedNo.setVisible(true);
                   //  kaveriButton.setVisible(true);
                   //  krishnaButton.setVisible(true);
                     break;
            
            case 2 : System.out.println("Reservation");
                     
                     /*
                      * No of days.
                      */
                     /* 
                     String noODays1  = noDays.getText();
                     Integer noDs = Integer.parseInt(noODays1);
                     System.out.println("No of days: " + noDs);                     
                     
                     /*
                      * User entered time. 
                      */
                     //Calendar UserTime = time.getValue();

                     /*
                      * Checkin date
                      */   
                     /*
                     Calendar s;
                     s = cal.getValue();
                     Date startD = s.getTime();
                     //Date startD1 = UserTime.getTime();
                     startD.setHours(startD1.getHours());
                     startD.setMinutes(startD1.getMinutes());
                     
                     this.mCheckin = startD;
                     System.out.println("Start date: " + df.format(startD));
                     
                     /*
                      * Checkout date
                      */
                     /*
                     Calendar ss = Calendar.getInstance();
                     ss.setTime(startD);
                     ss.add(Calendar.DATE, noDs);
                     Date endD = ss.getTime();
                     this.mCheckout = endD;
                     System.out.println("End date: " + df.format(endD));
                     
                     booking_init(1, noDs, startD, endD);        
                     roomGrid.setVisible(true);
                     */
                     
                     break;
            
            case 3 : System.out.println("Quick");
            
                     /*
                      * User Hours
                      */   
            
                     /*   
                     String noH = noHours.getText();
                     Integer noHs = Integer.parseInt(noH);
                     
                     /*
                      * Checkin date.
                      */
                     /* 
                     Date startDt = new Date();        
                     System.out.println(df.format(startDt));
                     this.mCheckin = startDt;
                     */
                     /*
                      * Checkout date.
                      */
                     
                     /*
                     Calendar c1 = Calendar.getInstance();
                     c1.add(Calendar.HOUR, noHs);
                     Date endDt = c1.getTime();
                     System.out.println(df.format(endDt));
                     this.mCheckout = endDt;      
                     
                     booking_init(1, noHs, startDt, endDt);        
                     roomGrid.setVisible(true);
                     */
                     break;
            
            default: System.out.println("Not Valid");
                     break;
        }  
    }
    
    public void booking_init(int type,Integer noOfday,Date stDate,Date edDate) 
    {

         CheckRoom cr;
         Integer doubleVacantNo = 0;
         Integer singleVancantNo = 0;
         Integer acdoubleVacantNo = 0;
         Integer acsingleVancantNo = 0;
         
         
         switch(type)
         {
             case 1:
                 cr = new CheckRoom (stDate, edDate, noOfday, Integer.MIN_VALUE);
                      break;
                 
             case 2:
                  cr = new CheckRoom(stDate, edDate, new Integer(0) , Integer.MIN_VALUE);
                       break;
             case  3://hours need to be handled
                  cr = new CheckRoom(new Date(), edDate, new Integer("0"), Integer.MIN_VALUE);
                       break;
             default:
                       cr = new CheckRoom();
        }
         
        //System.out.println("I do not come here!"); 
         
        ObservableList<Toggle> sdf = tg.getToggles();
        List rlist = QueryManager.getInstance().getRoomNumberList();
        Map stat;
        
        if(type == 1)
        {    
             stat = cr.Check(false);
             
        }     
        else 
        {    
             stat = cr.Check(false);
        }     
         
        //System.out.println("I do not come here and here!");  
        System.out.println(rlist.size());
        
        for (int i = 0; i < rlist.size(); i++) 
        {
            Boolean rs = (Boolean) stat.get(rlist.get(i));
            ToggleButton b = (ToggleButton) sdf.get(i);
            b.setText(rlist.get(i).toString());
            
            System.out.println("Room Number is  "+rlist.get(i)+"and status is"+rs.booleanValue());
            
            Integer rm = (Integer) rlist.get(i);
            QueryManager qm = QueryManager.getInstance();
            Room thisroom = Room.getRoomByOid(rm);
            
            /**** Room Button Css ****/
            if(thisroom.getRoomtypeid().getOid() == 1)
            {
                    //doubleVacantNo++;
                    b.setStyle("-fx-background-color: linear-gradient(#f45422, #be1d22);-fx-background-radius: 30;-fx-background-insets: 0;-fx-text-fill: white;-fx-font-size: 18px;");
            }
            else if(thisroom.getRoomtypeid().getOid() == 3)
            {
                    //singleVancantNo++;
                    b.setStyle("-fx-background-color: linear-gradient(#484949, #382c22);-fx-background-radius: 30;-fx-background-insets: 0;-fx-text-fill: white;-fx-font-size: 18px;");    
            }
            /******************/
            
            
            //Amenityroom roomamenity = qm.findAmenityroomByRoom(thisroom);
            
            if (rs.booleanValue()) 
            {
                String name = (String) b.getId();        
                
                Color c1 = Color.BISQUE;
                setBgColor(name,c1);

                /**************************************************************/
                if(thisroom.getRoomtypeid().getOid() == 1)
                {
                  doubleVacantNo++;  
                }
                if(thisroom.getRoomtypeid().getOid() == 3)
                {
                    singleVancantNo++;
                }  
                  if(thisroom.getRoomtypeid().getOid() == 10)
                {
                    acdoubleVacantNo++;
                } 
                    if(thisroom.getRoomtypeid().getOid() == 9)
                {
                    acsingleVancantNo++;
                } 
                /**************************************************************/
            } 
            else
            {
                String name = (String) b.getId();        
                Color c1 = Color.ROSYBROWN;
                setBgColor(name,c1);
                b.setDisable(true);
            }
        }
        doublevacantroom.setText("" + doubleVacantNo);
        singlevacantroom.setText("" + singleVancantNo);
        acdoublevacantroom.setText("" + acdoubleVacantNo);
        acsinglevacantroom.setText("" + acsingleVancantNo);
    }
    
    public void processGetRoomReady()
    {
        Items singleBed = new Items();        
        Items doubleBed = new Items();        
        Items pillow = new Items();        
        Items blanket = new Items();        
        Items towel = new Items(); 
        
        QueryManager qm = QueryManager.getInstance();
        Integer thisroomInt = dirtyrooms.getValue();
        
        Room thisroom = Room.getRoomByOid(thisroomInt);
        Amenityroom amenityroom = qm.findAmenityroomByRoom(thisroom);
        
        Integer stemp = amenityroom.getSinglebedsheetqty();
        Integer dtemp = amenityroom.getDoublebedsheetqty();
        Integer ptemp = amenityroom.getPillowqty();
        
        Integer ttemp = 0;
        Integer btemp = 0;
        
        qm.updateAmenityroomTempQty(stemp, dtemp, btemp, ptemp, ttemp, amenityroom.getOid());
        /**********************************************************************/
        List<Items> allItems = qm.findAll("Items.findAll");
        
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getItemName().contains("Single"))
            {
                singleBed = allItems.get(i);
                System.out.println("SIngle:" + singleBed.getItemName());
            }
            if(allItems.get(i).getItemName().contains("Double"))
            {
                doubleBed = allItems.get(i);
                System.out.println("Double:" + doubleBed.getItemName());
            }
            if(allItems.get(i).getItemName().contains("Pillow"))
            {
                pillow = allItems.get(i);
                System.out.println("pillow:" + pillow.getItemName());
            }
            if(allItems.get(i).getItemName().contains("Blanket"))
            {
                blanket = allItems.get(i);
                System.out.println("Blanket:" + blanket.getItemName());
            }
            if(allItems.get(i).getItemName().contains("Towel"))
            {
                towel = allItems.get(i);
                System.out.println("towel:" + towel.getItemName() );
            }
        }
        /**********************************************************************/
        Iteminstore singlebedinstore = qm.findIteminstoreByItem(singleBed);
        Integer singlebedinstoreinqty = singlebedinstore.getQty();
        Integer newsinglebedinstore = singlebedinstoreinqty - stemp;
        Integer result = qm.updateQtyInIteminStore(singleBed, newsinglebedinstore);
        
        Iteminuse singlebedinuse = qm.findIteminuseByItem(singleBed);
        Integer singlebedinuseqty = singlebedinuse.getQty();
        Integer newsinglebedinuseqty = singlebedinuseqty + stemp;
        Integer result1 = qm.updateQtyInIteminUse(singleBed, newsinglebedinuseqty);
        /**********************************************************************/
        Iteminstore doublebedinstore = qm.findIteminstoreByItem(doubleBed);
        Integer doublebedinstoreinqty = doublebedinstore.getQty();
        Integer newdoublebedinstore = doublebedinstoreinqty - dtemp;
        Integer result2 = qm.updateQtyInIteminStore(doubleBed, newdoublebedinstore);
        
        Iteminuse doublebedinuse = qm.findIteminuseByItem(doubleBed);
        Integer doublebedinuseqty = doublebedinuse.getQty();
        Integer newdoublebedinuseqty = doublebedinuseqty + dtemp;
        Integer result3 = qm.updateQtyInIteminUse(doubleBed, newdoublebedinuseqty);
        /**********************************************************************/
        Iteminstore pillowinstore = qm.findIteminstoreByItem(pillow);
        Integer pillowinstoreinqty = pillowinstore.getQty();
        Integer newpillowinstore = pillowinstoreinqty - ptemp;
        Integer result6 = qm.updateQtyInIteminStore(pillow, newpillowinstore);
        
        Iteminuse pillowinuse = qm.findIteminuseByItem(pillow);
        Integer pillowinuseqty = pillowinuse.getQty();
        Integer newpillowinuseqty = pillowinuseqty + ptemp;
        Integer result7 = qm.updateQtyInIteminUse(pillow, newpillowinuseqty);
        /**********************************************************************/
        
        finaldirtyrooms.clear();
        dirtyrooms.setItems(finaldirtyrooms);
        processCheckRooms(null);
    }
     
    public void toggleClick(ActionEvent e)
    {
    
    ToggleButton t = (ToggleButton)e.getSource();
    t.getId();
    
    String name = (String) t.getId();        
    System.out.println("Name: " + name);
    String s = name.substring(1);
        
    
    Integer roomNo;
    
    //roomNo = roomNo + 200;    
    roomNo = new Integer(t.getText());
    
    if(selectRoomList.contains(roomNo) && selectRoomList.size() > 0)
    {
        Color c2 = Color.GREEN;
        setBgColor(name,c2);        
        selectRoomList.remove(roomNo);
        System.out.println("Removing. Room in list:");
        for(int i = 0 ; i < selectRoomList.size(); i++)
        {
            System.out.print(" : " + selectRoomList.get(i));
        }
    }
    else
    {
        Color c1 = Color.YELLOW;
        setBgColor(name,c1);        
        selectRoomList.add(roomNo);              
        System.out.println("Adding. Room in list:");
        for(int i = 0 ; i < selectRoomList.size(); i++)
        {
            System.out.print(" : " + selectRoomList.get(i));
        }
    }    
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void takeDate()
    {
        
    }
    
    public void clickProced()
    {   
        Integer t = 0;
        Double total = 0.0;
        Double finalTotal = 0.0;
        Double taxApplicable= 0.0;
        QueryManager qm = QueryManager.getInstance();
        
        if(selectRoomList.size() == 0)
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , 
                        "Please Select the Room");
            return ;
        }
        
        if(selectRoomList.size() > 1)
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , 
                        "Please Select Only One Room");
            return ;
        }
        
        int noD = 1;
        for(int i = 0 ; i < selectRoomList.size(); i++ )
        {
            //Wrong function name! - (findRoomTypeByRoom)
            Room room = qm.findRoomTypeByRoom(selectRoomList.get(i));
            //System.out.println("Room: " + selectRoomList.get(i));
            
            Roomtype OriRoomtype = room.getRoomtypeid();
            Integer tempRoomTypeId = OriRoomtype.getOid();
            int res = qm.updateTempRoomtypeId(tempRoomTypeId, room.getOid());
            
            Integer temprt = room.getTemproomtypeid();
            Roomtype roomtype = qm.findRoomtypeByOid(temprt);
            
            total = (roomtype.getRate().doubleValue() * noD);
            finalTotal = finalTotal + total;
             taxApplicable = (double)GstMgr.getInstance().getTax(total.doubleValue());
        }
        
        if(total >= 550)
        {
            //taxApplicable = 0;
        }
        
        application.gotoGuestAdd(selectRoomList, mCheckin, mCheckout, finalTotal, taxApplicable, noD);
    }
    
   /* public void processKrishna()
    {
      Integer selectBedNo = krishnaBedNo.getValue();
      System.out.println("At last!");
      Double totalrent = selectBedNo * 200.0;        
      selectRoomList.add(1111);
      application.gotoGuestAdd(selectRoomList, mCheckin, mCheckout, totalrent, 4.0, selectBedNo);
    }
    
    public void processKaveri()
    {
      Integer selectBedNo = kaveriBedNo.getValue();
      System.out.println("At last!");
      Double totalrent = selectBedNo * 200.0;        
      selectRoomList.add(2222);
      application.gotoGuestAdd(selectRoomList, mCheckin, mCheckout, totalrent, 4.0, selectBedNo);
    }*/

    private void init_btn2RoomNumberMap() 
    {
        btn2RoomNum = new HashMap();
        
    }
        
    private List<Integer>  selectRoomList;
    
    @FXML
    Label daylabel;
    
    @FXML
    Label hourslabel;
    
    @FXML
    Label datelabel;
    
    @FXML
    Label timelabel;
    
    @FXML
    ToggleGroup  tg = new ToggleGroup();
    
    //@FXML
    //private ComboBox<Integer> krishnaBedNo;
    
   // @FXML
  //  private ComboBox<Integer> kaveriBedNo;
    
    @FXML
    TextField cal;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTimeTextField time;
    
    ObservableList<Integer> BedNoList  = FXCollections.observableArrayList();
    private Integer mBookingType;    
    Map btn2RoomNum;    
}
