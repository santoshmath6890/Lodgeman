/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.TimersForLodgeManMgr;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Identityprooftype;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;


public class CurrentBookingEditController implements Initializable 
{

    @FXML
    private TextField userRoomNo;
    
    private LodgeMan application;
    
    @FXML
    private TextField bookingCheckinDate;
    @FXML
    private Label LabelBookingCheckdate;
    
    @FXML
    private TextField bookingNoOfPersons;
    @FXML
    private Label LabelBookingNoOfPersons;
    
    @FXML
    private TextField bookingregno;
    @FXML
    private Label LabelBookingregno;
    
    @FXML
    private TextField guestName;
    @FXML
    private Label LabelGuestName;
    
    @FXML
    private TextField guestLastname;
    @FXML
    private Label LabelGuestLastName;
    
    @FXML
    private TextArea guestAddress;
    @FXML
    private Label LabelGuestAddress;
    
    @FXML
    private TextField guestContactNo;
    @FXML
    private Label LabelGuestContactNo;
    
    @FXML
    private TextField guestCity;
    @FXML
    private Label LabelGuestCity;
    
    @FXML
    private TextField guestState;
    @FXML
    private Label LabelGuestState;
        
    @FXML
    private ComboBox<Roomtype> bookingroomtypelist;
    @FXML
    private Label LabelBookedAs;
    @FXML
    private Button ButtonChangeRoomType;
    
    @FXML
    private Label LabelRentApplicable;
    
    @FXML 
    private TextField bookingrentapplicable;
    
    @FXML
    private Button ButtonTakePhoto;
    
    @FXML
    private Button ButtonIdProof;
    
    @FXML
    private Button ButtonUpdate;
    
    @FXML
    private Rectangle RectanglePartition;
    //@FXML
    //private Label LabelIdProoftype;
    
    @FXML
    ImageView imgview;

    @FXML
    ImageView imgview1;
    
    private Bookings bookingsG;
    private Guests guestsG;
    private String filepathProfileImageG;
    private String filepathIDImageG;
    private Room roomG;
    
    ObservableList<Roomtype> finalRoomtypeList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application  = aThis;
        bookingCheckinDate.setEditable(false);
        JFXPanel jpa = new JFXPanel();
        System.out.println("__________________________________");
        System.out.println("CurrentBookingEditController.java");
        System.out.println("__________________________________");
        
        hideFields(false);
    }
    
    public void hideFields(Boolean status)
    {
        RectanglePartition.setVisible(status);
        LabelBookingCheckdate.setVisible(status);
        LabelBookingNoOfPersons.setVisible(status);
        LabelBookedAs.setVisible(status);
        ButtonChangeRoomType.setVisible(status);
        LabelRentApplicable.setVisible(status);
        LabelBookingregno.setVisible(status);
        LabelGuestName.setVisible(status);
        LabelGuestLastName.setVisible(status);
        LabelGuestContactNo.setVisible(status);
        LabelGuestAddress.setVisible(status);
        LabelGuestState.setVisible(status);
        LabelGuestCity.setVisible(status);
        
        ButtonTakePhoto.setVisible(status);
        ButtonIdProof.setVisible(status);
        ButtonUpdate.setVisible(status);
        bookingroomtypelist.setVisible(status);
        bookingCheckinDate.setVisible(status);
        bookingNoOfPersons.setVisible(status);
        bookingregno.setVisible(status);
        bookingrentapplicable.setVisible(status);
        guestName.setVisible(status);
        guestLastname.setVisible(status);
        guestContactNo.setVisible(status);
        guestAddress.setVisible(status);
        guestState.setVisible(status);
        guestCity.setVisible(status);
    }
    
    public boolean  validate()
    {
    
      if(userRoomNo.getText().isEmpty() == true)
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
            Integer userroomno = 0; 
            try
            {
                userroomno = Integer.parseInt(userRoomNo.getText());
            }
            catch(Exception ex)
            {
                System.out.println("Invalid input");
            }
            
            /******************************************************************/
            QueryManager qm = QueryManager.getInstance();
            Room room ;//= new Room();
            room = Room.getRoomByOid(userroomno);
            if (room == null) 
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
                userRoomNo.clear();
                userRoomNo.requestFocus();
                return;
            }
            /******************************************************************/
            
            
            /******************************************************************/
            this.roomG = room;
            Bookings currentBooking = qm.getBookingByBookedRoom(room);
            if(currentBooking == null)
            {
              Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room is not booked please use booked room number");
              userRoomNo.clear();
              userRoomNo.requestFocus();
            }
            /******************************************************************/
            
            this.bookingsG = currentBooking;
            
            /******************************************************************/
            Timersforlodgeman t = qm.getTimervalues();
            Integer flexibleAmount = t.getEditbooking();
            System.out.println("flexi: " + flexibleAmount);
             
            if(TimersForLodgeManMgr.validateTimer(flexibleAmount, currentBooking.getFromdate()) == true)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Edit not possible. Time up.");
                return;
            }
            /******************************************************************/
            hideFields(true);
            
            bookingCheckinDate.setText("" + currentBooking.getFromdate());
            bookingNoOfPersons.setText("" + currentBooking.getNoofpersons());
            bookingregno.setText("" + currentBooking.getRegno());
        
            List<Roomtype> allRoomtype = qm.findAll("Roomtype.findAll");
            for(int i = 0 ; i < allRoomtype.size(); i++)
            {
                finalRoomtypeList.add(allRoomtype.get(i));
            }
        
            bookingroomtypelist.setItems(finalRoomtypeList);
            for(int i = 0 ; i < finalRoomtypeList.size();i++)
            {
                if(finalRoomtypeList.get(i).getOid() == currentBooking.getRoomno().getTemproomtypeid())
                {
                    bookingroomtypelist.setValue(finalRoomtypeList.get(i));
                    BigDecimal rateapplicable = finalRoomtypeList.get(i).getRate();
                    bookingrentapplicable.setText("" + rateapplicable + "Tax");
                }   
            }
        
            Guests currentGuest = currentBooking.getCustomerid();
            this.guestsG = currentGuest;
        
            filepathProfileImageG = guestsG.getImage();
            filepathIDImageG = guestsG.getIdproofno();
        
            guestName.setText(currentGuest.getName());
            guestLastname.setText(currentGuest.getLastname());
            guestAddress.setText(currentGuest.getAddress());
            guestContactNo.setText("" + currentGuest.getContactno());
            guestCity.setText(currentGuest.getCity());
            guestState.setText(currentGuest.getState());
        
            try
            {
                String pp = currentGuest.getImage();
                File file = new File(pp);
                Image ima = new Image(file.toURI().toString());
                imgview.setImage(ima);
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        
            try
            {
                String pp = currentGuest.getIdproofno();
                File file = new File(pp);
                Image ima = new Image(file.toURI().toString());
                imgview1.setImage(ima);
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }
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
    
    public Image Buffered2Fx(BufferedImage image) throws IOException 
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }
    
    public void processChange()
    {
        Roomtype newSelectedRoomtype = bookingroomtypelist.getValue();
        BigDecimal rateapplicable = newSelectedRoomtype.getRate();
        bookingrentapplicable.setText("" + rateapplicable + "Tax");
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processUpdate()
    {
        Integer newNoOfPerson = Integer.parseInt(bookingNoOfPersons.getText());
        Integer newRegNo = Integer.parseInt(bookingregno.getText());
        QueryManager qm = QueryManager.getInstance();
        int result = qm.updateBookingsNoOfPersonRegno(newNoOfPerson, newRegNo, bookingsG.getOid());
        System.out.println("And we have... : " + result);
        
        Roomtype selectedValue = bookingroomtypelist.getValue();
        Integer selectedValueInt = selectedValue.getOid();
        Integer resultagain = qm.updateTempRoomtypeId(selectedValueInt, roomG.getOid());
        System.out.println("" + resultagain);
        
        String name = guestName.getText();
        String lastname = guestLastname.getText();
        String contactno = guestContactNo.getText();
        String address = guestAddress.getText();
        String city = guestCity.getText();
        String state = guestState.getText();
        Identityprooftype prooftype = Identityprooftype.getIdentityProofTypeByOid(1);
        Integer oid = guestsG.getOid();
        Long mContact = Long.valueOf(contactno);
        
        int resultonceagain = qm.updateGuests(name, lastname, mContact, address, city, state, filepathProfileImageG, filepathIDImageG, prooftype, oid);
        application.gotoHome();
    }
}
