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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javax.persistence.Table;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.TimersForLodgeManMgr;
import lodgeman.lalitman.model.pojo.Addon;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.FormValidation;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * FXML Controller class
 *
 * @author vijay
 */

public class AddBookingAddonController implements Initializable 
{
    private LodgeMan application;
    
    @FXML
    private TextField UserRoomno;
    
    @FXML
    private TextField UserBookingID;
    
    @FXML
    private TextField UserGuestName;
    
    @FXML
    private TextField UserContactNo;
    
    @FXML
    private TextField AddonType;
    
    @FXML
    jfxtras.labs.scene.control.CalendarTextField AddonFrom;
    
    @FXML
    private Rectangle thisRectangle;
    
    @FXML
    private TextField AddNoOfDays;
    
    @FXML
    private TextField AddonQty;
    
    @FXML
    private Label LabelGuestName;
    
    @FXML
    private Label LabelContactno;
    
    @FXML
    private Label LabelBookingid;
    
    @FXML
    private Label LabelAddonType;
    
    @FXML
    private Label LabelQty;
    
    @FXML
    private Label LabelFrom;
    
    @FXML
    private Button ButtonDone;
    
    @FXML
    private TableView<Bookingaddon> dTableView;
    
    @FXML
    private TableColumn<Bookingaddon, Integer> tOid;
    
    @FXML
    private TableColumn<Bookingaddon, Date> tFromDate;
    
    @FXML
    private TableColumn<Bookingaddon, Date> tToDate;
    
    @FXML
    private TableColumn<Bookingaddon, Integer> tQty;
    
    @FXML
    private TableColumn<Bookingaddon, Boolean> tActionCol;
    
    @FXML
    private TableColumn<Bookingaddon, Boolean> tDeleteCol;
    
    private Integer gIndex;
    
    ObservableList<Bookingaddon> bookingaddonlist = FXCollections.observableArrayList();
    Bookings bookingG;
    Guests guestsG;
    Room roomG;
    Addon addonG;

    /*
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        AddonFrom.setValue(Calendar.getInstance());
        System.out.println("_______________________________");
        System.out.println("AddBookingAddonController.java");
        System.out.println("_______________________________");
        
        /**********************************************************************/
        UserBookingID.setEditable(false);
        UserGuestName.setEditable(false);
        UserContactNo.setEditable(false);
        AddonType.setEditable(false);
        /**********************************************************************/
        
        /*************************** Visibility *******************************/
        LabelGuestName.setVisible(false);
        LabelContactno.setVisible(false);
        LabelBookingid.setVisible(false);
        LabelAddonType.setVisible(false);
        LabelFrom.setVisible(false);
        LabelQty.setVisible(false);
        dTableView.setVisible(false);
        
        thisRectangle.setVisible(false);
        UserGuestName.setVisible(false);
        UserContactNo.setVisible(false);
        UserBookingID.setVisible(false);
        AddonType.setVisible(false);
        AddonQty.setVisible(false);
        AddonFrom.setVisible(false);
        ButtonDone.setVisible(false);
        /**********************************************************************/
        
                
        /*************************** Table Mapping ****************************/
        tOid.setCellValueFactory(new PropertyValueFactory("oid"));
        tFromDate.setCellValueFactory(new PropertyValueFactory("fromdate"));
        tToDate.setCellValueFactory(new PropertyValueFactory("todate"));
        //tQty.setCellValueFactory(new PropertyValueFactory("qty"));
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
    
      if(UserRoomno.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
      return false;
      }
      return true;
    }
     
    public void processUserRoomno()
    {
         if(validate())
         {
        QueryManager qm = QueryManager.getInstance();
        /*
         * Process the User entered room.
         */
        System.out.println("Process User Room");
        String userRoom = UserRoomno.getText();
        Integer userRoomInt = Integer.parseInt(userRoom);
        Room room = new Room();
        
        room = room.getRoomByOid(userRoomInt);
        if (room == null) {
          Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          UserRoomno.clear();
                 return;
             }
        this.roomG = room;
        /*
         * Booking, checkin details of this room
         */
        Bookings bookings = qm.getBookingByBookedRoom(room);
        if(bookings == null)
        {
              Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room is not booked please use booked room number");
                UserRoomno.clear();
            }
            /******************************************************************/
            
            /*********************** Visibility *******************************/
            LabelGuestName.setVisible(true);
            LabelContactno.setVisible(true);
            LabelBookingid.setVisible(true);
            LabelAddonType.setVisible(true);
            LabelFrom.setVisible(true);
            LabelQty.setVisible(true);
            dTableView.setVisible(true);
            
            thisRectangle.setVisible(true);
            UserGuestName.setVisible(true);
            UserContactNo.setVisible(true);
            UserBookingID.setVisible(true);
            AddonType.setVisible(true);
            AddonQty.setVisible(true);
            AddonFrom.setVisible(true);
            ButtonDone.setVisible(true);
            /******************************************************************/
        
            this.bookingG = bookings;
            UserBookingID.setText("" + bookings.getOid());
            /*
            * Guest object for that booking
            */
            Guests guestDetails = bookings.getCustomerid();
            this.guestsG = guestDetails;
            UserGuestName.setText(guestDetails.getName() + guestDetails.getLastname());
            UserContactNo.setText("" + guestDetails.getContactno());
        
        /*
         * Get addon type. Hardcoded to get extra bed.
         */
        Addon addon = qm.findAddonByOid(1);
        this.addonG = addon;
        AddonType.setText(addon.getAddon());
        
        /*
         * Default values.
         */
        AddonQty.setText("" + 1);
        
        List<Bookingaddon> bookingaddon = qm.findBookingAddonByBookingid(bookings);
        for(int i = 0 ; i < bookingaddon.size(); i++)
        {
            bookingaddonlist.add(bookingaddon.get(i));
        }
        
       dTableView.setItems(bookingaddonlist);
       
       tActionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bookingaddon, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Bookingaddon, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       tActionCol.setCellFactory(new Callback<TableColumn<Bookingaddon, Boolean>, TableCell<Bookingaddon, Boolean>>() 
        {
            @Override public TableCell<Bookingaddon, Boolean> call(TableColumn<Bookingaddon, Boolean> personBooleanTableColumn) 
            {
                return new AddBookingaddonCell(dTableView);
            }
        }
        );
       
       tDeleteCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bookingaddon, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Bookingaddon, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       tDeleteCol.setCellFactory(new Callback<TableColumn<Bookingaddon, Boolean>, TableCell<Bookingaddon, Boolean>>() 
        {
            @Override public TableCell<Bookingaddon, Boolean> call(TableColumn<Bookingaddon, Boolean> personBooleanTableColumn) 
            {
                return new DeleteBookingaddonCell(dTableView);
            }
        }
        );
       
    }
    }
    public class AddBookingaddonCell extends TableCell<Bookingaddon, Boolean>
    {
        final Button addButton       = new Button("Stop");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        
        AddBookingaddonCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Bookingaddon selectedaddon = (Bookingaddon) dTableView.getItems().get(selectedIndex);
                    
                    QueryManager qm = QueryManager.getInstance();
                    Date currentDate = new Date();
                    
                    if(selectedaddon.getTodate() == null)
                    {    
                        Integer result = qm.updateBookingAddonToDate( currentDate,selectedaddon.getOid());
                        System.out.println("I pass? :: " + result);
                        
                        /******************************************************/
                        /*
                        Amenityroom amenityroom = qm.findAmenityroomByRoom(roomG);
                        Integer singleBedQtyTemp = amenityroom.getSinglebedsheetqtytemp();
                        singleBedQtyTemp = singleBedQtyTemp - 1;
                        Items thisSingleBedsheet = amenityroom.getSinglebedsheet();
                        Integer result1 = qm.updateSingleTempQtyInAmenityRoom( singleBedQtyTemp, amenityroom.getOid());
                        */
                        /******************************************************/
                        
                        /******************************************************/
                        /*
                        Iteminuse iteminuse = qm.findIteminuseByItem(thisSingleBedsheet);
                        Integer newvalue = iteminuse.getQty() - 1;
                        Integer result2 = qm.updateQtyInIteminUse(thisSingleBedsheet, newvalue);
                        
                        Itemdirty itemdirty = qm.findItemdirtyByItem(thisSingleBedsheet);
                        Integer newvalue1 = itemdirty.getOid() + 1;
                        Integer result3 = qm.updateQtyInItemDirty(thisSingleBedsheet, newvalue1);
                        */
                        /******************************************************/
                        
                        application.gotoHome();
                    }
                    else
                    {
                        System.out.println("Invalid.");
                    }
                }
            }
            );
        }
        
        @Override protected void updateItem(Boolean item, boolean empty) 
        {
            super.updateItem(item, empty);
            
            if (!empty) 
            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            }
        }    
    }
    
    public class DeleteBookingaddonCell extends TableCell<Bookingaddon, Boolean>
    {
        final Button deleteButton = new Button("Delete");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        DeleteBookingaddonCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(deleteButton);
            
            deleteButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    QueryManager qm = QueryManager.getInstance();
                    
                    Integer selectedIndex = getTableRow().getIndex();
                    Bookingaddon selectedaddon = (Bookingaddon) dTableView.getItems().get(selectedIndex);
                     
                    /**********************************************************/
                    Timersforlodgeman t = qm.getTimervalues();
                    Integer flexibleAmount = t.getExtrabedDel();
                    System.out.println("flexi: " + flexibleAmount);
             
                    if(TimersForLodgeManMgr.validateTimer(flexibleAmount, selectedaddon.getFromdate()) == true)
                    {
                        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Cancel not possible. Time up.");
                        return;
                    }
                    else
                    {
                        int result = qm.deleteBookingaddonByOid(selectedaddon.getOid());
                    }
                    /**********************************************************/
                    
                    /*
                    Amenityroom amenityroom = qm.findAmenityroomByRoom(roomG);
                    Integer singleBedQtyTemp = amenityroom.getSinglebedsheetqtytemp();
                    singleBedQtyTemp = singleBedQtyTemp - 1;
                    Items thisSingleBedsheet = amenityroom.getSinglebedsheet();
                    Integer result1 = qm.updateSingleTempQtyInAmenityRoom( singleBedQtyTemp, amenityroom.getOid());
                    */
                    /******************************************************/
                    
                    /******************************************************/
                    /*
                    Iteminuse iteminuse = qm.findIteminuseByItem(thisSingleBedsheet);
                    Integer newvalue = iteminuse.getQty() - 1;
                    Integer result2 = qm.updateQtyInIteminUse(thisSingleBedsheet, newvalue);
                    
                    Iteminstore iteminstore = qm.findIteminstoreByItem(thisSingleBedsheet);
                    Integer newvalue1 = iteminstore.getQty() + 1;
                    Integer result3 = qm.updateQtyInItemDirty(thisSingleBedsheet, newvalue1);
                    */
                     /******************************************************/
                    
                    application.gotoHome();
                }
            }
            );
        }
        
        @Override protected void updateItem(Boolean item, boolean empty) 
        {
            super.updateItem(item, empty);
            
            if (!empty) 
            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            }
        }    
    }
    
    public void processDone()
    {   
        
        Calendar addonfromvalue = AddonFrom.getValue();
        Date addonvaluedatefrom = addonfromvalue.getTime();
        System.out.println("From" + addonvaluedatefrom);
        
        Integer addnoofqty = Integer.parseInt(AddonQty.getText());
        
        for ( int i = 0 ; i < addnoofqty; i++ )
        {
            LodgeConfig.newBookingAddon(addonvaluedatefrom, null, bookingG, roomG, addonG, 1);
        }
        
        application.gotoHome();
    }
}
