/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.sun.javafx.application.PlatformImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lodgeman.lalitman.controller.reports.advancebill;
import lodgeman.lalitman.model.business.Booking.Booking;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.GstMgr;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.business.GuestsB;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.digitstowords;
import lodgeman.lalitman.model.pojo.Addon;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Custgstdetails;
import lodgeman.lalitman.model.pojo.EntityEnum;

import lodgeman.lalitman.model.pojo.Identityprooftype;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
//import sun.security.util.BigInt;

/*
 * FXML Controller class
 *
 * @author vijay
 */

public class GuestAddController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;

    @FXML
    Label mListRoom;
    
    @FXML 
    Pane mypane;
    
    @FXML
    private TextField mName;
    
    @FXML
    private TextField mGrandTotal;
    
    @FXML
    private TextField mtotalAndTax;
    
    @FXML
    private TextField mTaxApplicable;
    
    @FXML
    private TextField mLastname;
    
    @FXML
    private TextField mContactNo;
    
    @FXML
    private TextArea mAddress;
    
    @FXML
    private TextField mCity;

    @FXML
    private TextField mState;
    
    @FXML
    private ComboBox<String> mIdProofTypes;
    
    @FXML
    private TextField mCheckin;
    
    @FXML
    private TextField mCheckout;
    
    @FXML
    private TextField total;
    
    @FXML
    private TextField mAdvance;
    
    @FXML
    private TextField mNoOfPeople;
    
    @FXML
    private TextField mcgstno;
    
     @FXML
    private TextField mcgstcmpmame;
     
     @FXML
    private TextField mcshipingaddr;
     
    @FXML
    private Button bookButton;
    
    @FXML
    private ComboBox<String> selectRoomType;
    
    @FXML 
    private ComboBox<Integer> extrapersonlist;
    
    @FXML 
    private Label advanceErrorMessage;
    
    @FXML
    private TextField eguestmobileno;
    
    @FXML
    private TextField registerno;
    
    @FXML
    private Label newGuestMNoCount;
    
    @FXML
    private Label oldGuestMNoCount;
    
    private List<Integer> mBookedRoomList;
    
    private Date mCheckinG;
    private Date mCheckoutG;
    private Double totalM;
    private Double luxuryTax;
    private String filepathProfileImageG;
    private String filepathIDImageG;
    private Guests ExistingGuest;
    
    private ObservableList<String> oRoomList = FXCollections.observableArrayList(); 
    private ObservableList<Integer> extrapersonbedlist = FXCollections.observableArrayList();
    Custgstdetails m_gt = null ;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        m_gt=null;
        QueryManager qm = QueryManager.getInstance();
        List<Roomtype> allRoomtype = qm.findAll("Roomtype.findAll");
        
        for(int i = 0 ; i < allRoomtype.size();  i++)
        {
            oRoomList.add(allRoomtype.get(i).getName());
        }
        selectRoomType.setItems(oRoomList);
    }

    public void processHome() 
    {
        application.gotoHome();
    }

    public void processBack() 
    {
        application.gotoCheckin();
    }
    
    private Integer queryKey;

    @SuppressWarnings("empty-statement")
    public void setApp(LodgeMan application, List bookedRoomList, Date checkinDate, Date checkoutDate, Double totalMoney, Double taxApplicable, Integer nod) 
    {   
        m_gt=null;
        this.application = application;
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
        Bookings tempregno = qm.getLastBooking();
        Integer regno = tempregno.getRegno();
        if(regno != null)
        {
            regno = regno + 1;
            //registerno.setText("" + regno);
        }
        queryKey = 1;
        System.out.println("Key is : " + queryKey);
        
        bookButton.setDisable(false);
        this.mBookedRoomList = bookedRoomList;
        this.mCheckinG = checkinDate;
        this.mCheckoutG = checkoutDate;
        this.totalM = totalMoney;
        this.luxuryTax = taxApplicable;
        /**********************************************************************/
        
        
        /*******************************Booked Room List***********************/
        String selectRoomList = ": ";
        for (int i = 0; i < bookedRoomList.size(); i++) 
        {
            selectRoomList = selectRoomList + "  " + bookedRoomList.get(i);
        }
        if(bookedRoomList.contains(1111) || bookedRoomList.contains(2222))
        {
            mNoOfPeople.setText("" + nod);
        }
        mListRoom.setText(selectRoomList);
        /**********************************************************************/
        
        /*
        ObservableList<String> simpleList1 = FXCollections.observableArrayList();
        simpleList1.add("Karnataka");
        simpleList1.add("Maharastra");
        simpleList1.add("Tamil Nadu");
        simpleList1.add("Andra Pradesh");
        simpleList1.add("Telengana");
        simpleList1.add("Kerala");
        simpleList1.add("Madya Pradesh");
        simpleList1.add("Gujrat ");
        simpleList1.add("uttar Pradesh");
        simpleList1.add("Bihar");
        simpleList1.add("Andaman and Nicobar Islands dagger");
        simpleList1.add("Andaman and Nicobar");
        simpleList1.add("Arunachal Pradesh");
        simpleList1.add("Assam");
        simpleList1.add("Chandigarh");
        simpleList1.add("Chhattisgarh");
        simpleList1.add("Dadra and Nagar Haveli");
        simpleList1.add("Panaji");
        simpleList1.add("Panaji"); 
        mState.setItems(simpleList1);
        */
        
        /************************Identity type drop down list******************/
        ObservableList<String> simpleList = FXCollections.observableArrayList();
        simpleList.add("Pan Card");
        simpleList.add("Aaddhar Card");
        simpleList.add("License");
        simpleList.add("Passport");
        simpleList.add("Election Card");
        simpleList.add("Ration Card");
        simpleList.add("Others");
        mIdProofTypes.setItems(simpleList);
        mIdProofTypes.setVisible(false);
        extrapersonbedlist.add(1);
        extrapersonbedlist.add(2);
        extrapersonbedlist.add(3);
        extrapersonbedlist.add(4);
        extrapersonlist.setItems(extrapersonbedlist);
        /**********************************************************************/
        
        
        /**********************************************************************/
        SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
        String ss = df.format(checkinDate);
        String sd = df.format(checkoutDate);
        mCheckin.setText(checkinDate.toLocaleString());
        double d = taxApplicable * 0.01 * totalMoney;
        double gtot = totalMoney + d;
        mtotalAndTax.setText("" + Round(totalMoney) + " + " +Round( d));
        mGrandTotal.setText("" + Round(gtot));
        /**********************************************************************/
        
        //these text fields are readonly
        mCheckin.setEditable(false);
        mGrandTotal.setEditable(false);
        mtotalAndTax.setEditable(false);
        JFXPanel jpa = new JFXPanel();
    }
    public double Round(double value)
    {
        double rounded = Math.round(value * 100.0) / 100.0;
        return rounded;
    }
    JFrame window = new JFrame("Test webcam panel");

    void startVid() 
    {
        Webcam webcam = Webcam.getDefault();

        WebcamPanel panel = new WebcamPanel(webcam, new Dimension(200, 200), true);
        panel.setFPSDisplayed(true);


        JPanel jp = new JPanel();
        window.add(jp);
        jp.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        isVidStarted = true;

    }
    
    boolean isVidStarted = false;

    void stopVid() 
    {
        window.setVisible(false);
        window.dispose();
        isVidStarted = false;
    }

    public void btnClick(ActionEvent e) throws IOException 
    {
        if (isVidStarted) 
        {
            stopVid();
            takePicture();
            Webcam.getDefault().close();
        }
        else 
        {
            startVid();
        }
    }

    public void btnClick1(ActionEvent e) throws IOException 
    {
        if (isVidStarted) 
        {
            stopVid();
            takePicture1();
            Webcam.getDefault().close();
        }
        else 
        {
            startVid();
        }
    }

    public void takePicture() throws IOException 
    {
        // automatically open if webcam is closed
        
        Webcam.setAutoOpenMode(true);

        // get image
        BufferedImage image = Webcam.getDefault().getImage();
        QueryManager qm = QueryManager.getInstance();
        
        Long lastGuestID = qm.getGuestCount();
        DirCreate dir1 = new DirCreate();
        Date inputP = new Date();
        String path1 = dir1.findTargetDir(inputP);
        String path12  = path1.replaceFirst("c", "C");
        String filePathProfile = null;
        //String filePathID = null;
        
        //if(pic)
            filePathProfile = path12 + "/" + "Guest_" + lastGuestID + ".png";
        //else
             //filePathID = path12 + "/" + "Guest_id_prof_" + lastGuestID + ".png";
        
        this.filepathProfileImageG = filePathProfile;
        // save image to PNG file
        //String fileName = "Guest" + oi + ".png";
        ImageIO.write(image, "PNG", new File(filePathProfile));
        Image fxIm = Buffered2Fx(image);
        
        imgview.setImage(fxIm);
    }
    
    public void takePicture1() throws IOException
    {
        Webcam.setAutoOpenMode(true);
        BufferedImage image = Webcam.getDefault().getImage();
        QueryManager qm = QueryManager.getInstance();
        
        Long lastGuestID = qm.getGuestCount();
        DirCreate dir1 = new DirCreate();
        Date inputP = new Date();
        
        String path1 = dir1.findTargetDir(inputP);
        String path12  = path1.replaceFirst("c", "C");
        String filePathID = null;
        
        filePathID = path12 + "/" + "Guest_id_prof_" + lastGuestID + ".png";
        
        this.filepathIDImageG = filePathID;
        ImageIO.write(image, "PNG", new File(filePathID));
        Image fxIm = Buffered2Fx(image);
        imgview1.setImage(fxIm);
    }
    
    public boolean  validate()
    {
        if (mName.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid name");
         mName.requestFocus();
         mName.clear();
         return false;
        }
        
        if (mLastname.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid lastname");
         mLastname.requestFocus();
         mLastname.clear();
         return false;
        }
        if (mContactNo.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid contact number");
         mContactNo.requestFocus();
         mContactNo.clear();
         return false;
        }
        if (mAddress.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid address");
         mAddress.clear();
         mAddress.requestFocus();
         return false;
        }
        if (mCity.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid city");
         mCity.clear();
         mCity.requestFocus();
         return false;
        }
        if (mState.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid state");
         
         mState.requestFocus();
         return false;
        }
       /* if(mIdProofTypes.getValue().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid idprooftypes");
         mIdProofTypes.requestFocus();
         return false;
        }
        */
         if (mAdvance.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid advance amount");
         mAdvance.clear();
         mAdvance.requestFocus();
         return false;
        }
          if (mNoOfPeople.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid noof people");
         mNoOfPeople.clear();
         mNoOfPeople.requestFocus();
         return false;
        }
          if (registerno.getText().isEmpty())
        {
         Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid register number");
         registerno.clear();
         registerno.requestFocus();
         return false;
        }
         
        return true;    
    }
     
    public boolean isRoomBooked(Room r)
    {
        QueryManager qm = QueryManager.getInstance();
        Bookings b = qm.getBookingByBookedRoom(r);
        if(b == null)
             return false;
        else
            return true;    
    }
    
    public void processBook() throws JRException, IOException 
    {
        QueryManager qm = QueryManager.getInstance();
        
        
        if(validate())
        {
            /********************************Validation************************/
            String bookedRooms = "";
            for (int i = 0; i < mBookedRoomList.size(); i++) 
            {
                System.out.println("I must check is room already booked or not for : " + mBookedRoomList.get(i));
                Room rooms = new Room().getRoomByOid(mBookedRoomList.get(i));
                if(isRoomBooked(rooms) == true)
                {
                     Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Room is Already Booked");
                     application.gotoHome();   
                    return;
                }
            }
            
            bookButton.setDisable(true);
            Identityprooftype idtype = new Identityprooftype();
            idtype = Identityprooftype.getIdentityProofTypeByOid(1);
            Users users = qm.findLoggedInUser();
            String status = "Checkin";
            /******************************************************************/
            
            String mNameInput = mName.getText();
            String mLastNameInput = mLastname.getText();
            String noContactInput = mContactNo.getText();
            Long mContact = Long.valueOf(noContactInput);
            String mAddressInput = mAddress.getText();
            String mCityInput = mCity.getText();
            String mStateInput = mState.getText();
            String advancePayment1 = mAdvance.getText();
            
            /********************************Validation************************/
            Integer regno ;
            try
            {
                regno = Integer.parseInt(registerno.getText());
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid register number");
                registerno.requestFocus();
                registerno.clear();
                return;
            }
            /******************************************************************/
            
        
            /********************************Validation************************/
            Integer advancePayment;
            try
            {   
                advancePayment = Integer.parseInt(advancePayment1);
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid Advance");
                mAdvance.requestFocus();
                mAdvance.clear();
                return;
            }
            /******************************************************************/

            
            /********************************Validation************************/
            String sNoOfPeople = mNoOfPeople.getText();
            Integer mNoOfPeopleInt ;
            try
            {
                mNoOfPeopleInt = Integer.parseInt(sNoOfPeople);
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid number of people");
                mNoOfPeople.requestFocus();
                mNoOfPeople.clear();
                return;
            }
            /******************************************************************/
            
             String cgstNo = mcgstno.getText();
             String cshipaddr = mcshipingaddr.getText();
             String cmpname = mcgstcmpmame.getText();
            
        
            /******************************Guest into DB***********************/
            GuestsB.newGuests(mNameInput, mLastNameInput, mContact, mAddressInput, mCityInput, mStateInput, filepathProfileImageG, idtype, filepathIDImageG);
            Guests currentGuest = qm.getLastGuests();
            /******************************************************************/
            
            //save customer gst details
            
            //if(null != m_gt)
            if(false)    
            {
              //update the obj
                
               // Integer oid, Integer gid,String gstno,String gstcmpname
                 QueryManager.getInstance().updateAllIdInGstDetails(m_gt.getOid(),currentGuest.getOid(),cgstNo,cmpname);
            }
            else{
            if(mcgstno.getText().length() > 1)
            {
                 Custgstdetails   obj= new Custgstdetails(0,cgstNo,cmpname,cshipaddr,currentGuest.getOid());
       
                QueryManager.getInstance().Save(EntityEnum.CUSTGSTDETAILS, obj);
   
            }
            }
            /*****************************Extra Bed****************************/
            Integer selectNoExtraBedtemp = extrapersonlist.getValue();
            Integer tempHolder = 0;
            if(selectNoExtraBedtemp != null)
            {
                tempHolder = selectNoExtraBedtemp;
            }
            /******************************************************************/
        
            
            /*************************Booking into DB**************************/
            Room addonRoom = new Room();
            for (int i = 0; i < mBookedRoomList.size(); i++) 
            {
                bookedRooms = bookedRooms + " " + mBookedRoomList.get(i);
                System.out.println("I must do a booking for : " + mBookedRoomList.get(i));
                Room rooms = new Room().getRoomByOid(mBookedRoomList.get(i));
                addonRoom = rooms;
                new Booking().doBooking(mCheckinG, mCheckoutG, currentGuest, users, status, rooms, mNoOfPeopleInt, regno);
                System.out.println("Done booking for : " + rooms);
                //processLaundry(mNoOfPeopleInt, tempHolder, rooms);
            }
            /******************************************************************/
        
            
            /******************************************************************/
            if (bookedRooms.contains("1111")) 
            {
                bookedRooms = "Krishna";
            }
            if (bookedRooms.contains("2222")) 
            {
                bookedRooms = "Kaveri";
            }
            /******************************************************************/
        
        
            /*************************Receipts into DB*************************/
            Bookings bookingsCurrent = new Bookings();
            bookingsCurrent = qm.getLastBooking();
            Date arrdate = new Date();
            String guestname = "" + mNameInput + " " + mLastNameInput;
        
            digitstowords d2w = digitstowords.getInstance(advancePayment);
            String in_words = d2w.getNumberInWords();
            in_words = in_words + " only.";

            LodgeConfig.newReceipts(guestname, bookedRooms, bookingsCurrent.getFromdate(), mNoOfPeopleInt, BigDecimal.valueOf(totalM), BigDecimal.valueOf(luxuryTax), advancePayment, in_words, bookingsCurrent, regno,false);
            /******************************************************************/
        
        
            /*****************************Booking add on into DB***************/
            Receipts receipts = qm.getLastReceipts();
            Integer receiptNo = receipts.getOid();
            Addon addontype = qm.findAddonByOid(1);
            Integer selectNoExtraBed = extrapersonlist.getValue();
            Integer extraperson = 0;
            if(selectNoExtraBed != null) 
            {
                extraperson = selectNoExtraBed;
                System.out.println("->>> : " + selectNoExtraBed);
                for (int i = 0; i < selectNoExtraBed; i++) 
                {
                    LodgeConfig.newBookingAddon(arrdate, null, bookingsCurrent, addonRoom, addontype, 1);
                }
            }
            /******************************************************************/

            
            /*****************************Print Bill***************************/
            DirCreate dir = new DirCreate();
            Date inputP = new Date();
            String path = dir.findTargetDir(inputP);
            System.out.println("Before" + path);
            String path1 = path.replaceFirst("c", "C");
            String filename = path1 + "/" + "AdvanceReceipt_" + receiptNo + ".pdf";
            System.out.println("Pdf should go there: " + filename);
            System.out.println("I am this: " + receiptNo);
            advancebill.printadvancebill(receiptNo, filename);
            /******************************************************************/
            
            application.gotoHome();
        }
    }
    
    public void quickbook() throws JRException, IOException
    {
        if(validate())
        {
            String bookedRooms = "";
            /******************************************************************/
            for (int i = 0; i < mBookedRoomList.size(); i++) 
            {
                //bookedRooms = bookedRooms + " " + mBookedRoomList.get(i);
                System.out.println("I must check is room already booked or not for : " + mBookedRoomList.get(i));
                Room rooms = new Room().getRoomByOid(mBookedRoomList.get(i));
                if(isRoomBooked(rooms) == true)
                {
                     Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Room is Already Booked");
                     application.gotoHome();   
                     return;
                }
            }
            /******************************************************************/
            QueryManager qm = QueryManager.getInstance();
            String advancePayment1 = mAdvance.getText();
            Integer advancePayment;
            try
            {
                advancePayment = Integer.parseInt(advancePayment1);
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid Advance");
                mAdvance.requestFocus();
                mAdvance.clear();
                return;
            }
            Integer regno;
            try
            {
                regno= Integer.parseInt(registerno.getText());
            }   
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid RegNumber");
                registerno.requestFocus();
                registerno.clear();
                return;
            }
            String sNoOfPeople = mNoOfPeople.getText();
            Integer mNoOfPeopleInt;
            try
            {
                mNoOfPeopleInt = Integer.parseInt(sNoOfPeople);
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid Number of people");
                mNoOfPeople.requestFocus();
                mNoOfPeople.clear();
                return;
            }
        
            Users users = qm.findLoggedInUser();
            String status = "Checkin";
        
            Room addonRoom = new Room();    
            for (int i = 0; i < mBookedRoomList.size(); i++) 
            {
                bookedRooms = bookedRooms + " " + mBookedRoomList.get(i);
                System.out.println("I must do a booking for : " + mBookedRoomList.get(i));
                Room rooms = Room.getRoomByOid(mBookedRoomList.get(i));
                addonRoom = rooms;
                new Booking().doBooking(mCheckinG, mCheckoutG, ExistingGuest, users, status, rooms, mNoOfPeopleInt,regno);
                System.out.println("Done booking for : " + rooms);
            }
            Bookings bookingsCurrent = new Bookings();
            bookingsCurrent = qm.getLastBooking();
            Date arrdate = new Date();
            String guestname = "" + ExistingGuest.getName() + " " + ExistingGuest.getLastname();

            digitstowords d2w = digitstowords.getInstance(advancePayment);
            String in_words = d2w.getNumberInWords();
            in_words = in_words + " only.";

            LodgeConfig.newReceipts(guestname, bookedRooms, arrdate, mNoOfPeopleInt, BigDecimal.valueOf(totalM), BigDecimal.valueOf(luxuryTax), advancePayment, in_words, bookingsCurrent,regno,false);

            Receipts receipts = qm.getLastReceipts();
            Integer receiptNo = receipts.getOid();
            
            Addon addontype = qm.findAddonByOid(1);
            
        
            Integer selectNoExtraBed = extrapersonlist.getValue();
            if(selectNoExtraBed != null)
            {                
                System.out.println("->>> : " + selectNoExtraBed);
                for(int i = 0 ; i < selectNoExtraBed; i++)
                {
                    LodgeConfig.newBookingAddon(arrdate, null, bookingsCurrent, addonRoom, addontype, 1);
                }
            }
            /*
             * Generate bills.
             */
            
            DirCreate dir = new DirCreate();
            Date inputP = new Date();
            String path = dir.findTargetDir(inputP);
            //System.out.println("Before" + path);
            String path1 = path.replaceFirst("c", "C");
            String filename = path1 + "/" + "AdvanceReceipt_" + receiptNo + ".pdf";
            //System.out.println("Pdf should go there: " + filename);
            System.out.println("I am printing this: " + receiptNo);
            advancebill.printadvancebill(receiptNo, filename);
            application.gotoHome();   
        }
    }
    
    public void processRoomTypeChange()
    {
        
        QueryManager qm = QueryManager.getInstance();
        
        System.out.println("Modify room type.");
        
        String selectedRoomtype = selectRoomType.getValue();
        Roomtype roomtype = qm.findByName(selectedRoomtype);
        Integer tempRoomTypeId = roomtype.getOid();
        
        double taxamount = 0 ;
        BigDecimal roomrent = roomtype.getRate();
        taxamount = GstMgr.getInstance().CalculateTax(roomtype.getRate().doubleValue());
        
        
        double taxamountint = (double) taxamount;
        double grandtotal = roomrent.doubleValue() + taxamountint;
        
        
        mGrandTotal.setText("" + grandtotal);
        
        String bookedRooms = "";
        double roomrenttotal = 0;
        double taxamounttotal = 0;
        /*'
         * Change tempRoomTypeid.
         */
        for (int i = 0; i < mBookedRoomList.size(); i++) 
        {
            bookedRooms = bookedRooms + " " + mBookedRoomList.get(i);
            System.out.println("I must do a booking for : " + mBookedRoomList.get(i));
            
            roomrenttotal = roomrenttotal + roomrent.doubleValue();
            taxamounttotal = taxamounttotal + taxamountint;
            
            int result = qm.updateTempRoomtypeId(tempRoomTypeId, mBookedRoomList.get(i));
            System.out.println("result: " + result);
            System.out.println("Done booking for : " + mBookedRoomList.get(i));
        }
        
        mtotalAndTax.setText("" + roomrenttotal + " + " + taxamounttotal);
        grandtotal = roomrenttotal + taxamounttotal;
        mGrandTotal.setText("" + grandtotal);
        this.totalM = grandtotal;
    }

    public Image Buffered2Fx(BufferedImage image) throws IOException 
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }
    
    public void procssSeachMobileNo()
    {
        
       String mobilenostring = eguestmobileno.getText();
       long mobileno = Long.valueOf(mobilenostring).longValue();
       
       /***********************************************************************/
       List<Guests> resultGuest = null;
       Guests existingGuest = new Guests();
       QueryManager qm = QueryManager.getInstance();
       try
       {
           resultGuest = qm.findGuestByContactno(mobileno);
       }
       catch(Exception ex)
       {
           System.out.println(" Searched Guest mobile no and guest details Not Found");
       }
       Integer recordfound = resultGuest.size();
       existingGuest = resultGuest.get(recordfound - 1);
       /***********************************************************************/
       
       /***********************************************************************/
       System.out.println("" + existingGuest.getName());
       mName.setText(existingGuest.getName());
       mLastname.setText(existingGuest.getLastname());
       mContactNo.setText("" + existingGuest.getContactno());
       mAddress.setText(existingGuest.getAddress());
       mCity.setText(existingGuest.getCity());
       mState.setText(existingGuest.getState());
       filepathProfileImageG = existingGuest.getImage();
       filepathIDImageG = existingGuest.getIdproofno();
       /***********************************************************************/
       //update Gst Details
        m_gt =  QueryManager.getInstance().findCustgstdetailsBygid(existingGuest.getOid());
       if(m_gt!= null )
       {
            mcgstno.setText(m_gt.getGstno());
    
            mcgstcmpmame.setText(m_gt.getGstcmpname());
     
            mcshipingaddr.setText(m_gt.getGstaddr());
            
            
       }
       
       /***********************************************************************/
       this.ExistingGuest = existingGuest;
       try 
       {
            String pp = existingGuest.getImage();
            File file = new File(pp);
            Image ima = new Image(file.toURI().toString());
            imgview.setImage(ima);
       } 
       catch (Exception e) 
       {
           System.out.println("Error");
       }
       /***********************************************************************/
       
       /***********************************************************************/
       try 
       {
            String pp = existingGuest.getIdproofno();
            File file = new File(pp);
            Image ima = new Image(file.toURI().toString());
            imgview1.setImage(ima);
       }
       catch (Exception e) 
       {
            System.out.println("Error");
       }
       /***********************************************************************/
    }
    
    public void processLaundry(Integer normalPerson, Integer extraperson, Room thisroom)
    {
        QueryManager qm = QueryManager.getInstance();
        
        Items singleBed = new Items();        
        Items doubleBed = new Items();        
        Items pillow = new Items();        
        Items blanket = new Items();        
        Items towel = new Items(); 
        
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
        Amenityroom amenityroom = qm.findAmenityroomByRoom(thisroom);
        /**********************************************************************/
        Iteminstore towelinstore123 = qm.findIteminstoreByItem(towel);
        Integer towelinstoreinqty123 = towelinstore123.getQty();
        Integer newtowelinstore123 = towelinstoreinqty123 - normalPerson;
        if(newtowelinstore123 >= 0)
        {
            qm.updateQtyInIteminStore(towel, newtowelinstore123);
        }
        
        Iteminuse towelinuse123 = qm.findIteminuseByItem(towel);
        Integer towelinuseqty123 = towelinuse123.getQty();
        Integer newtowelinuseqty123 = towelinuseqty123 + normalPerson;
        if(newtowelinuseqty123 >= 0)
        {
            qm.updateQtyInIteminUse(towel, newtowelinuseqty123);
        }
        /**********************************************************************/
        Iteminstore blanketinstore123 = qm.findIteminstoreByItem(blanket);
        Integer blanketinstoreinqty123 = blanketinstore123.getQty();
        Integer newblanketinstore123 = blanketinstoreinqty123 - normalPerson;
        if(newblanketinstore123 >= 0)
        {
            Integer result= qm.updateQtyInIteminStore(blanket, newblanketinstore123);
        }
        Iteminuse blanketinuse123 = qm.findIteminuseByItem(blanket);
        Integer blanketinuseqty123 = blanketinuse123.getQty();
        Integer newblanketinuseqty123 = blanketinuseqty123 + normalPerson;
        if(blanketinuseqty123 >= 0)
        {
            qm.updateQtyInIteminUse(blanket, newblanketinuseqty123);
        }
        /**********************************************************************/
        //Integer res = qm.updateTowelTempQtyInAmenityRoom(normalPerson, amenityroom.getOid());
        Integer res1 = qm.updateTowelBlanketTempQtyInAmenityRoom(normalPerson, normalPerson, amenityroom.getOid());
        /**********************************************************************/
        
        if(extraperson > 0)
        {
            System.out.println("I am extra!");
            /******************************************************************/
            Iteminstore towelinstore = qm.findIteminstoreByItem(towel);
            Integer towelinstoreinqty = towelinstore.getQty();
            Integer newtowelinstore = towelinstoreinqty - extraperson;
            if(newtowelinstore >= 0)
            {
                Integer result8 = qm.updateQtyInIteminStore(towel, newtowelinstore);
            }
            
            Iteminuse towelinuse = qm.findIteminuseByItem(towel);
            Integer towelinuseqty = towelinuse.getQty();
            Integer newtowelinuseqty = towelinuseqty + extraperson;
            if(newtowelinuseqty >= 0)
            {
            Integer result9 = qm.updateQtyInIteminUse(towel, newtowelinuseqty);
            }
            /**********************************************************************/
            //Integer singleBLimit = amenityroom.getSinglebedsheetqtytemp();
            //Integer doubleBLimit = amenityroom.getDoublebedsheetqtytemp();
            //Integer blanketLimit = amenityroom.getBlanketqtytemp();
            //Integer pillowLimit = amenityroom.getPillowqtytemp();
            //Integer towelLimit = normalPerson;
        
            //singleBLimit = singleBLimit + extraperson;
            //blanketLimit = blanketLimit + extraperson;
            //pillowLimit = pillowLimit + extraperson;
            //towelLimit = towelLimit + extraperson;
            /**********************************************************************/
            Iteminstore singlebedinstore = qm.findIteminstoreByItem(singleBed);
            Integer singlebedinstoreinqty = singlebedinstore.getQty();
            Integer newsinglebedinstore = singlebedinstoreinqty - extraperson;
            if(newsinglebedinstore >= 0)
            {
                Integer result = qm.updateQtyInIteminStore(singleBed, newsinglebedinstore);
            }
            
            Iteminuse singlebedinuse = qm.findIteminuseByItem(singleBed);
            Integer singlebedinuseqty = singlebedinuse.getQty();
            Integer newsinglebedinuseqty = singlebedinuseqty + extraperson;
            if(newsinglebedinuseqty >= 0)
            {
            Integer result1 = qm.updateQtyInIteminUse(singleBed, newsinglebedinuseqty);
            }
            /**********************************************************************/
            Iteminstore blanketinstore = qm.findIteminstoreByItem(blanket);
            Integer blanketinstoreinqty = blanketinstore.getQty();
            Integer newblanketinstore = blanketinstoreinqty - extraperson;
            if(newblanketinstore >= 0)
            {
                Integer result4 = qm.updateQtyInIteminStore(blanket, newblanketinstore);
            }
            
            Iteminuse blanketinuse = qm.findIteminuseByItem(blanket);
            Integer blanketinuseqty = blanketinuse.getQty();
            Integer newblanketinuseqty = blanketinuseqty + extraperson;
            if(newblanketinuseqty >= 0)
            {
                Integer result5 = qm.updateQtyInIteminUse(blanket, newblanketinuseqty);
            }
            /**********************************************************************/
            Iteminstore pillowinstore = qm.findIteminstoreByItem(pillow);
            Integer pillowinstoreinqty = pillowinstore.getQty();
            Integer newpillowinstore = pillowinstoreinqty - extraperson;
            if(newpillowinstore >=0)
            {
                Integer result6 = qm.updateQtyInIteminStore(pillow, newpillowinstore);
            }
            Iteminuse pillowinuse = qm.findIteminuseByItem(pillow);
            Integer pillowinuseqty = pillowinuse.getQty();
            Integer newpillowinuseqty = pillowinuseqty + extraperson;
            if(newpillowinuseqty >= 0)
            {
                Integer result7 = qm.updateQtyInIteminUse(pillow, newpillowinuseqty);
            }
            /**********************************************************************/
            Iteminstore towelinstore1 = qm.findIteminstoreByItem(towel);
            Integer towelinstoreinqty1 = towelinstore1.getQty();
            Integer newtowelinstore1 = towelinstoreinqty1 - extraperson;
            if(newtowelinstore1 >= 0)
            {
                Integer result811 = qm.updateQtyInIteminStore(towel, newtowelinstore1);
            }
            
            Iteminuse towelinuse1 = qm.findIteminuseByItem(towel);
            Integer towelinuseqty1 = towelinuse1.getQty();
            Integer newtowelinuseqty1 = towelinuseqty1 + extraperson;
            if(newtowelinuseqty1 >= 0)
            {
                Integer result911 = qm.updateQtyInIteminUse(towel, newtowelinuseqty1);
            }
            /******************************************************************/
            Integer singleBLimit = amenityroom.getSinglebedsheetqtytemp() + extraperson;
            Integer doubleBLimit = amenityroom.getDoublebedsheetqtytemp();
            Integer pillowLimit = amenityroom.getPillowqtytemp() + extraperson;
            Integer towelLimit = normalPerson + extraperson;
            Integer blanketLimit = amenityroom.getBlanketqtytemp() + extraperson;
            qm.updateAmenityroomTempQty(singleBLimit, doubleBLimit, blanketLimit, pillowLimit, towelLimit, amenityroom.getOid());
            /******************************************************************/
        }
    }
    
    public void processNewGuestContactNoCount()
    {
        String len = mContactNo.getText();
        int temp = len.length() + 1;
        newGuestMNoCount.setText("" + temp);
    }
    
    public void prcessOldGuestContactNoCount()
    {
        
        String len = eguestmobileno.getText();
        int temp = len.length() + 1;
        oldGuestMNoCount.setText("" + temp);
    }
    
    @FXML
    ImageView imgview;

    @FXML
    ImageView imgview1;
    
    @FXML
    Button click;
}

