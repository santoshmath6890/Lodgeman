/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.controller.reports.duplicatereport;
import lodgeman.lalitman.controller.reports.finalbill;
import lodgeman.lalitman.controller.reports.newbillreport;
import lodgeman.lalitman.controller.table.BillLogTable;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;
import org.ietf.jgss.Oid;

/**
 * FXML Controller class
 *
 * @author RADHAKRISHNA
 */
public class DuplicateCheckoutBillsController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
   private LodgeMan application; 
    @FXML
    private Button btnGo;
    
    @FXML
    private ComboBox <Integer> cmbBilllist;
   
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputDate;
    
     @FXML
    private TableView<Bills> dTableView;
    
    @FXML
    private TableColumn<BillLogTable, Integer> mOid;
    
    @FXML
    private TableColumn<BillLogTable, Integer> mRoomno;
    
    @FXML
    private TableColumn<BillLogTable, Date> mBillDate;
    
    @FXML
    private TableColumn<BillLogTable, String> mGuestName;
    
     @FXML
    private TableColumn<BillLogTable, Date> mChkinDate;
    
     @FXML
    private TableColumn<BillLogTable, Date> mChkoutDate;
     
     @FXML
    private TableColumn<BillLogTable, Integer> mNoofDays;
     
      @FXML
    private TableColumn<BillLogTable, Integer> mAdvance;
      
    @FXML
    private TableColumn<BillLogTable, Boolean> tActionCol;
    
    //11111111111111111111111111111111111111111111111111111
    
   ObservableList<Bills> billslist = FXCollections.observableArrayList();
              
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    public void setApp(LodgeMan aThis) 
    {
              
       this.application = aThis;
       
       mOid.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("oid"));
       mRoomno.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("roomno"));
       mBillDate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("billdate"));
       mGuestName.setCellValueFactory(new PropertyValueFactory<BillLogTable, String>("guestname")); 
       mChkinDate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkindate"));
       mChkoutDate.setCellValueFactory(new PropertyValueFactory<BillLogTable, Date>("checkoutdate"));
       mNoofDays.setCellValueFactory(new PropertyValueFactory<BillLogTable, Integer>("noofdays"));
    }
    
    public void processGo() throws JRException, IOException
    {
        
        System.out.println("Go Entered");
        Calendar userInput = InputDate.getValue();
        Calendar userInput1 = InputDate.getValue();
        userInput1.set(Calendar.HOUR_OF_DAY, 00);
        userInput1.set(Calendar.MINUTE, 00);
        userInput1.set(Calendar.SECOND, 01);
        Date fromDate = userInput1.getTime();
        System.out.println("From Date: " + fromDate);
        
        
        userInput.set(Calendar.HOUR_OF_DAY, 23);
        userInput.set(Calendar.MINUTE, 59);
        userInput.set(Calendar.SECOND, 59);
        Date toDate = userInput.getTime();
        System.out.println("To Date: " + toDate);
        
        QueryManager qm =QueryManager.getInstance();
        List<Bills> allBillList1 = qm.findBillsBetween(fromDate, toDate);
        for(int i = 0 ; i < allBillList1.size(); i++)
        {
            billslist.add(allBillList1.get(i));
            System.out.println("oid" + allBillList1.get(i).getOid());
        }
        
       dTableView.setItems(billslist);
    /**********************************************************************/
       
                      
                    
     tActionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillLogTable, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<BillLogTable, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
    
       tActionCol.setCellFactory(new Callback<TableColumn<BillLogTable, Boolean>, TableCell<BillLogTable, Boolean>>() 
        {
            @Override public TableCell<BillLogTable, Boolean> call(TableColumn<BillLogTable, Boolean> personBooleanTableColumn) 
            {
                return new AddBookingaddonCell(dTableView);
            }
        
        } );
    }
    
    public class AddBookingaddonCell extends TableCell<BillLogTable, Boolean>
    {
        final Button addButton       = new Button("Duplicate");
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
                    
                    QueryManager qm =QueryManager.getInstance(); 
                    //**************************/
                     Integer selectedIndex = getTableRow().getIndex();
                    Bills billtable = (Bills) dTableView.getItems().get(selectedIndex);
                    Integer poid = billtable.getOid();
                    /*********************/
                    
                     Bills bill = qm.findBillsByOid(poid);
        
        
                    Integer billno = bill.getOid();
                    Integer billno1 = bill.getOid();
                    System.out.println(bill);
                    
                    System.out.println(billno);
                    String path1 = Generic.giveMePath();
                    String filename = path1 + "/" + "DuplicateBill_" + billno + ".pdf"; 
                    System.out.println("File name : " + filename);
                    try {
                        duplicatereport.printduplicatereport(billno1, filename);
                    } catch (JRException ex) {
                        Logger.getLogger(DuplicateCheckoutBillsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DuplicateCheckoutBillsController.class.getName()).log(Level.SEVERE, null, ex);
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
}

        
  
   
    
         
  
    
              