/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.controller.reports.advancebill;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.digitstowords;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AdvanceNewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField mUserRoom;
    
    @FXML
    private TextField dGuestName;
    
    @FXML
    private TextField dGuestContactNo;
    
    @FXML
    private TextField dGuestId;
    
    @FXML
    private TextField mUserAmount;
    
    @FXML
    private ImageView dGuestPhoto;
    
    @FXML
    private Label dInvalidRoom;
    
    @FXML
    private Label dInvalidGuestPhoto;
    
    @FXML
    private TableView TableAdvanceList;
    
    @FXML
    private Label LabelCustomerName;
    
    @FXML
    private Label LabelContactno;
    
    @FXML
    private Label LabelCustomerID;
    
    @FXML
    private Label LabelEnterAmount;
    
    @FXML
    private Button ButtonAdd;
    
    @FXML
    private TableColumn<Receipts, Integer> ColumnId;
    
    @FXML
    private TableColumn<Receipts, String> ColumnGuestname;
    
    @FXML
    private TableColumn<Receipts, String> ColumnRoomno;
    
    @FXML
    private TableColumn<Receipts, Date> ColumnReceiptdate;
    
    @FXML
    private TableColumn<Receipts, Integer> ColumnAdvancepaid;
    
    @FXML
    private ObservableList<Receipts> TableReceiptList = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Receipts, Boolean> ColumnAction;
    
    private Room roomG;
    private Bookings bookingG;
    private Guests guestsG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        System.out.println("__________________________");
        System.out.println("AdvanceNewController.java");
        System.out.println("__________________________");
        
        /**********************************************************************/
        ColumnId.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("oid"));
        ColumnRoomno.setCellValueFactory(new PropertyValueFactory<Receipts, String>("roomno"));
        ColumnGuestname.setCellValueFactory(new PropertyValueFactory<Receipts, String>("guestname"));
        ColumnReceiptdate.setCellValueFactory(new PropertyValueFactory<Receipts, Date>("arrdate"));
        ColumnAdvancepaid.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("advancepaid"));
        dGuestName.setEditable(false);
        dGuestContactNo.setEditable(false);
        dGuestId.setEditable(false);
        mUserAmount.setEditable(false);
        /**********************************************************************/
        
        /************************* Visibility *********************************/
        LabelCustomerName.setVisible(false);
        LabelContactno.setVisible(false);
        LabelCustomerID.setVisible(false);
        LabelEnterAmount.setVisible(false);
        
        ButtonAdd.setVisible(false);
        dGuestName.setVisible(false);
        dGuestContactNo.setVisible(false);
        dGuestId.setVisible(false);
        mUserAmount.setVisible(false);
        TableAdvanceList.setVisible(false);
        /**********************************************************************/
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public boolean  validate()
    {
    
      if(mUserRoom.getText().isEmpty() == true)
      {
        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid room number");
        mUserRoom.clear();
        mUserRoom.requestFocus();
        return false;
      }
   
     return true;
    }
     
    public void processCheckRoom()
    {
        if(validate())
        {
            dInvalidRoom.setText("");
            dInvalidGuestPhoto.setText("");
        
            System.out.println("CheckRoom");
            QueryManager qm = QueryManager.getInstance();
            String userRoom = mUserRoom.getText();
            Integer userRoomInt;
            
            if (mUserRoom == null) 
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
                mUserRoom.clear();
                return;
            }
            try
            {
                userRoomInt = Integer.parseInt(userRoom);
            }
            catch(Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
                mUserRoom.clear();
                return;
            }
            Room thisroom = new Room();
            thisroom = thisroom.getRoomByOid(userRoomInt);
            if (thisroom == null)
            {
                 Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
                 mUserRoom.clear();
                 return;
            }
            
            System.out.println("Process User Room");
            try
            {
                userRoom = mUserRoom.getText();
            }
            catch(Exception e)
            {
                dInvalidRoom.setText("Invalid Room. Please try again.");
            }
            Room room = Room.getRoomByOid(userRoomInt);
            this.roomG = room;
        
            Bookings bookings = null;
            try
            {
                bookings = qm.getBookingByBookedRoom(room);
                if(bookings == null)
                {
                    Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room is not booked please use booked room number");
                    mUserRoom.clear();
        
                }
            }
            catch(Exception s )
            {
                dInvalidRoom.setText("Invalid Room. Please try again.");
                return;
            }
            this.bookingG = bookings;
            
            /************************* Visibility *****************************/
            LabelCustomerName.setVisible(true);
            LabelContactno.setVisible(true);
            LabelCustomerID.setVisible(true);
            LabelEnterAmount.setVisible(true);
        
            ButtonAdd.setVisible(true);
            dGuestName.setVisible(true);
            dGuestContactNo.setVisible(true);
            dGuestId.setVisible(true);
            mUserAmount.setVisible(true);
            TableAdvanceList.setVisible(true);
            /******************************************************************/
            
            
            /******************************************************************/
            List<Receipts> allList = qm.getReceiptsByBooking(bookings);
            for(int i = 0; i < allList.size(); i++)
            {
                System.out.println("" + allList.get(i));
                TableReceiptList.add(allList.get(i));
            }
            TableAdvanceList.setItems(TableReceiptList);
            /******************************************************************/
            
            
            /******************************************************************/
            Guests guestDetails = bookings.getCustomerid();
            this.guestsG = guestDetails;
            dGuestName.setText(guestDetails.getName() + guestDetails.getLastname());
            dGuestContactNo.setText("" + guestDetails.getContactno());
            dGuestId.setText("" + guestDetails.getOid());
        
            try
            {
                String imagePath = guestDetails.getImage();
                File imagFile = new File(imagePath);
                Image ima = new Image(imagFile.toURI().toString());
                dGuestPhoto.setImage(ima);
            }
            catch(Exception e)
            {
                dInvalidGuestPhoto.setText("Photo not available.");
            }
            mUserAmount.setEditable(true);
            /******************************************************************/
        }
    }
    
    public void processDone() throws JRException, IOException
    {
        System.out.println("Done");
        Integer userAmount = 0;
        
        try
        {
            userAmount   = Integer.parseInt(mUserAmount.getText());
        }
        catch(Exception s)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter valid Amount");
            System.out.println("Wrong format!");
            mUserAmount.clear();
            mUserAmount.requestFocus();
            return;
        }
        
        digitstowords d2w = digitstowords.getInstance(userAmount);
        String in_words = d2w.getNumberInWords();
        in_words = in_words + " only.";
        
        String guestname = "" + guestsG.getName() + "  " + guestsG.getLastname();
        String bookedRooms = "" + roomG;
        Integer temproomtypeid = roomG.getTemproomtypeid();
        QueryManager qm = QueryManager.getInstance();
        Roomtype userroomtype = qm.findRoomtypeByOid(temproomtypeid);
        //List<Receipts> receiptList = qm.getReceiptsByBooking(bookingG);
        Integer regno = bookingG.getRegno();
        if(regno == null)
        {
            regno = 100;
        }
        Date currentDate = new Date();
        LodgeConfig.newReceipts(guestname, bookedRooms,currentDate , bookingG.getNoofpersons(),userroomtype.getRate(),BigDecimal.ZERO, userAmount, in_words, bookingG, regno,true);
        
        //QueryManager qm = QueryManager.getInstance();
        Receipts receipts = qm.getLastReceipts();
        Integer receiptNo = receipts.getOid();

        /*
         * Generate bills.
         */
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        System.out.println("Before" + path);
        String path1 = path.replaceFirst("c", "C");
        String filename = path1 + "/" + "AdvanceReceipt_" + receiptNo + ".pdf";
        
        System.out.println("I am this: " + receiptNo);
        advancebill.printadvancebill(receiptNo, filename);
        application.gotoHome();
    }
    
    }