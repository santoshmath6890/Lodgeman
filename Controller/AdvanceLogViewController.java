/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AdvanceLogViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    @FXML
    private TableView<Receipts> advancetable;
    
    @FXML
    private TableColumn<Receipts, Integer> roomno;
    
    @FXML
    private TableColumn<Receipts, String> guestname;
    
    @FXML
    private TableColumn<Receipts, Date> arrdate;
    
    @FXML
    private TableColumn<Receipts, Integer> advpaid;
    
    @FXML
    private TableColumn<Receipts, Boolean> ColumnAction;
    
    @FXML
    private TextField totaladvanceamount;
    
    ObservableList<Receipts> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        /**********************************************************************/
        roomno.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("roomno"));
        guestname.setCellValueFactory(new PropertyValueFactory<Receipts, String>("guestname"));
        arrdate.setCellValueFactory(new PropertyValueFactory<Receipts, Date>("arrdate"));
        advpaid.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("advancepaid"));
        /**********************************************************************/
        
        
        Date currentDate = new Date();
        QueryManager qm = QueryManager.getInstance();
        Userlog userlog = qm.getLastUserlog();
        qm.updateEndDateTime(userlog.getOid(), currentDate);
        
        Integer advance_total = 0;
        List<Receipts> allList = qm.findReceiptsTypeBetween(userlog.getStartdatetime(), currentDate, false);
        
        for(int i = 0; i < allList.size(); i++)
        {
            System.out.println("" + allList.get(i));
            advance_total = advance_total + allList.get(i).getAdvancepaid();
            finalList.add(allList.get(i));
        }
        advancetable.setItems(finalList);
        totaladvanceamount.setText("" + advance_total);
        totaladvanceamount.setEditable(false);
        
        ColumnAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receipts, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Receipts, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       ColumnAction.setCellFactory(new Callback<TableColumn<Receipts, Boolean>, TableCell<Receipts, Boolean>>() 
        {
            @Override public TableCell<Receipts, Boolean> call(TableColumn<Receipts, Boolean> personBooleanTableColumn) 
            {
                return new AddBookingaddonCell(advancetable);
            }
        }
        );
    }
    
    public class AddBookingaddonCell extends TableCell<Receipts, Boolean>
    {
        final Button addButton       = new Button("Delete");   
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
                    System.out.println("Hello! I deserve a cake!!");
                    Integer selectedIndex = getTableRow().getIndex();
                    
                    System.out.println("Index: " + selectedIndex);
                    
                    Receipts selectedReceipt = (Receipts) advancetable.getItems().get(selectedIndex);
                    
                    System.out.println("oid: " + selectedReceipt.getOid());
                    Date trial = new Date();
                    trial.setYear(2023);
                    System.out.println("trial: " + trial);
                    
                    QueryManager qm = QueryManager.getInstance();
                    int result = qm.deleteReceiptsByOid(selectedReceipt.getOid());
                    
                    System.out.println("result: " + result);
                    
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
    
    public void processHome()
    {
        application.gotoHome();
    }
    public void processBack()
    {
        application.gotoConfig();
    }
}
