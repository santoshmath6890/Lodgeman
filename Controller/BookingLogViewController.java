/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javax.persistence.NoResultException;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.business.TimersForLodgeManMgr;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class BookingLogViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    private TableView<Bills> allBillTable;
    @FXML
    private TableColumn<Bills, Integer> billno;
    @FXML
    private TableColumn<Bills, Room> roomno;
    @FXML
    private TableColumn<Bills, Guests> guestname;
    @FXML
    private TableColumn<Bills, Date> checkindate;
    @FXML
    private TableColumn<Bills, Date> checkoutdate;
    @FXML
    private TableColumn<Bills, Integer> receive;
    @FXML
    private TableColumn<Bills, Integer> refund;
    @FXML
    private TableColumn<Bills, Boolean> wrongbill;
    @FXML
    private TableColumn<Bills, Boolean> creditbill;
    
    @FXML
    private TextField totalreceiveAmount;
    
    @FXML
    private TextField totalrefundAmount;
    
    @FXML
    private ComboBox<Creditowners> allCreditownerslist;
    
    ObservableList<Creditowners> creditownerlist = FXCollections.observableArrayList();
    
    ObservableList<Bills> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        totalreceiveAmount.setEditable(false);
        totalrefundAmount.setEditable(false);
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
       List<Creditowners> creditownerlisttemp = qm.findAll("Creditowners.findAll");
       for(int i = 0; i < creditownerlisttemp.size(); i++)
       {
           creditownerlist.add(creditownerlisttemp.get(i));
       }
       allCreditownerslist.setItems(creditownerlist);
        /**********************************************************************/
        
        /**********************************************************************/
        billno.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("oid"));
        roomno.setCellValueFactory(new PropertyValueFactory<Bills, Room>("roomno"));
        guestname.setCellValueFactory(new PropertyValueFactory<Bills, Guests>("guestname"));
        checkindate.setCellValueFactory(new PropertyValueFactory<Bills, Date>("checkindate"));
        checkoutdate.setCellValueFactory(new PropertyValueFactory<Bills, Date>("checkoutdate"));
        receive.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("cashreceived"));
        refund.setCellValueFactory(new PropertyValueFactory<Bills, Integer>("cashrefund"));
        /**********************************************************************/
        
        /**********************************************************************/
        //QueryManager qm = QueryManager.getInstance();
        Date currentDate = new Date();
        Userlog userlog = qm.getLastUserlog();
        Integer received_total = 0;
        BigDecimal refund_total = BigDecimal.ZERO;
        List<Bills> allBillList = qm.findBillsBetween(userlog.getStartdatetime(), currentDate);
        
        for(int i = 0 ; i < allBillList.size(); i++)
        {
            received_total = received_total + allBillList.get(i).getCashreceived();
            refund_total = refund_total.add( allBillList.get(i).getCashrefund());
            finalList.add(allBillList.get(i));
        }
        
        allBillTable.setItems(finalList);
        totalreceiveAmount.setText("" + received_total );
        totalrefundAmount.setText("" + refund_total);
        /**********************************************************************/
        
        /**********************************************************************/
        wrongbill.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bills, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Bills, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
        wrongbill.setCellFactory(new Callback<TableColumn<Bills, Boolean>, TableCell<Bills, Boolean>>() 
        {
            @Override public TableCell<Bills, Boolean> call(TableColumn<Bills, Boolean> personBooleanTableColumn) 
            {
                return new WrongBillCell(allBillTable);
            }
        }
        );
        /**********************************************************************/
        
        /**********************************************************************/
        creditbill.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bills, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Bills, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
        creditbill.setCellFactory(new Callback<TableColumn<Bills, Boolean>, TableCell<Bills, Boolean>>() 
        {
            @Override public TableCell<Bills, Boolean> call(TableColumn<Bills, Boolean> personBooleanTableColumn) 
            {
                return new CreditBillCell(allBillTable);
            }
        }
        );
        /**********************************************************************/
    }
    
    public class WrongBillCell extends TableCell<Bills, Boolean>
    {
        final Button addButton       = new Button("Wrong Bill");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        WrongBillCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    QueryManager qm = QueryManager.getInstance();
                    
                    /**********************************************************/
                    Integer selectedIndex = getTableRow().getIndex();
                    Bills thisbill = (Bills) allBillTable.getItems().get(selectedIndex);
                    Integer room = thisbill.getRoomno();
                    Room thisroom = Room.getRoomByOid(room);
                    Bookings thisbookings = qm.findBookingsByFromdateAndRoomno(thisbill.getCheckindate(), thisroom);
                    /**********************************************************/
                    
                    /**********************************************************/
                    try
                    {
                        Bookings noresultexpected = qm.findCurrentRoomBookings(thisroom);
                    }
                    catch(NoResultException e)
                    {
                        Integer resultDaytariff = qm.deleteDaytariffByBillno(thisbill);
                        
                        /******************************************************/
                        Timersforlodgeman t = qm.getTimervalues();
                        Integer flexibleAmount = t.getWrongbill();
                        System.out.println("flexi: " + flexibleAmount);
             
                        if(TimersForLodgeManMgr.validateTimer(flexibleAmount, thisbill.getBilldate()) == true)
                        {
                            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Wrong bill not possible. Time up.");
                            return;
                        }
                        else
                        {    
                            Integer resultBillDelete = qm.deleteBillsByOid(thisbill.getOid());
                            Integer resultUpdateCheckin = qm.updateBookingCheckinWrongBill(thisbookings.getOid());
                        }
                        /******************************************************/
                        
                        application.gotoConfig();
                    }
                    /**********************************************************/
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
    
    public class CreditBillCell extends TableCell<Bills, Boolean>
    {
        final Button creditButton       = new Button("Credit Bill");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        CreditBillCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(creditButton);
            
            creditButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Bills thisbill = (Bills) allBillTable.getItems().get(selectedIndex);
                    
                    QueryManager qm = QueryManager.getInstance();
                    Creditowners selectededcreditowner = null;
                    /**********************************************************/
                    selectededcreditowner = allCreditownerslist.getValue();
                    if(selectededcreditowner == null)
                    {
                        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Please select Credit owner.");
                        application.gotoBookingLogView();
                    }
                    /**********************************************************/
                    
                    /**********************************************************/
                    try
                    {
                        Creditbills noresultcreditbills = qm.findCreditbillsByBillno(thisbill);
                        Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Bill added in Credit Bills.");
                    }
                    catch(NoResultException ex)
                    {
                        /******************************************************/
                        Timersforlodgeman t = qm.getTimervalues();
                        Integer flexibleAmount = t.getCreditbill();
                        System.out.println("flexi: " + flexibleAmount);
             
                        if(TimersForLodgeManMgr.validateTimer(flexibleAmount, thisbill.getBilldate()) == true)
                        {
                            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Credit bill not possible. Time up.");
                            return;
                        }
                        else
                        {    
                            Date entryDate = new Date();
                            LodgeConfig.newCreditbills(entryDate, false, null, selectededcreditowner, thisbill);
                            System.out.println("Done");
                        }
                        /******************************************************/
                        
                        application.gotoConfig();
                    }
                    /**********************************************************/
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
    
    public void processHome()
    {
        application.gotoHome();
    } 
    
    public void processBack()
    {
        application.gotoConfig();
    }
}
