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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.controller.reports.DuplicateReceipt;
import lodgeman.lalitman.controller.reports.duplicatereport;
import lodgeman.lalitman.controller.table.BillLogTable;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author RADHAKRISHNA
 */
public class DuplicateCheckinReceiptsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private LodgeMan application;

    @FXML
    Label mListRoom;
    
    @FXML 
    Pane mypane;
    
    @FXML
    private TableView<ReceiptsTable> receiptT;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mOid;
    
    @FXML
    private TableColumn<ReceiptsTable, String> mGuestname;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mRoomno;
    
    @FXML
    private TableColumn<ReceiptsTable, Date> mArrdate;
    
    @FXML
    private TableColumn<ReceiptsTable, String> mNoofpersons;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mRoomrentperday;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mLuxurytax;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mAdvancepaid;
    
    @FXML
    private TableColumn<ReceiptsTable, Integer> mRegno;
     
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField InputDate;
    
    @FXML
    private TableView<Receipts> dTableView;
    
    @FXML
    private TableColumn<ReceiptsTable, Boolean> tActionCol;
     
    
    
    private ObservableList<Receipts> oReceiptList = FXCollections.observableArrayList(); 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
              
       this.application = aThis;
       
       mOid.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("oid"));
       mGuestname.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, String>("guestname"));
       mRoomno.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("roomno"));
       mArrdate.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Date>("arrdate")); 
       mNoofpersons.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, String>("noofpersons"));
       mRoomrentperday.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("roomrentperday"));
       mLuxurytax.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("luxurytax"));
       mAdvancepaid.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("advancepaid"));
       //mRegno.setCellValueFactory(new PropertyValueFactory<ReceiptsTable, Integer>("regno"));
    }
    
    
    
    
     public void processHome() 
    {
        application.gotoHome();
    }

    public void processBack() 
    {
        application.gotoHome();
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
        List<Receipts> allReceiptList = qm.findReceiptsBeBetween(fromDate, toDate);
        for(int i = 0 ; i < allReceiptList.size(); i++)
        {
            oReceiptList.add(allReceiptList.get(i));
            
        }
        
       dTableView.setItems(oReceiptList);
    /**********************************************************************/
       
                      
                    
     tActionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ReceiptsTable, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ReceiptsTable, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
    
       tActionCol.setCellFactory(new Callback<TableColumn<ReceiptsTable, Boolean>, TableCell<ReceiptsTable, Boolean>>() 
        {
            @Override public TableCell<ReceiptsTable, Boolean> call(TableColumn<ReceiptsTable, Boolean> personBooleanTableColumn) 
            {
                return new AddBookingaddonCell(dTableView);
            }
        
        } );
    }
    
    public class AddBookingaddonCell extends TableCell<ReceiptsTable, Boolean>
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
                    Integer selectedIndex = getTableRow().getIndex();
                    Receipts receiptsviewtable = (Receipts) dTableView.getItems().get(selectedIndex);
                    Integer poid = receiptsviewtable.getOid();
                    /*********************/
                     Receipts recpt = qm.findReceiptByOid(poid);
        
        
                    Integer recptno = recpt.getOid();
                    Integer recpt1 = recpt.getOid();
                    System.out.println(recpt);
                    
                    System.out.println(recptno);
                    String path1 = Generic.giveMePath();
                    String filename = path1 + "/" + "DuplicateReceipt_" + recptno + ".pdf"; 
                    System.out.println("File name : " + filename);
                    try {
                        DuplicateReceipt.printduplicatereceipt(recpt1, filename);
                    } catch (JRException ex) {
                        Logger.getLogger(DuplicateCheckinReceiptsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DuplicateCheckinReceiptsController.class.getName()).log(Level.SEVERE, null, ex);
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


   
    

