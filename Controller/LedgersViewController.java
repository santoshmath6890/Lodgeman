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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.model.pojo.Ledgers;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class LedgersViewController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField dTotalPaid;
    
    @FXML
    private TableColumn<Payments, Integer> oid;
    
    @FXML
    private TableColumn<Payments, Ledgers> ledgertype;
    
    @FXML
    private TableColumn<Payments, Date> paymentdatetime;
    
    @FXML
    private TableColumn<Payments, String> note;
    
    @FXML
    private TableColumn<Payments, Integer> amount;
    
    @FXML
    private TableColumn<Payments, Users> userid;
    
    @FXML
    private TableColumn<Payments, Boolean> tDeleteCol;
    
    @FXML
    private TableView<Payments> allpayments;
    
    @FXML
    private ComboBox<Ledgers> mledgersList;
    
    ObservableList<Ledgers> ledgerlist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        QueryManager qm = QueryManager.getInstance();
        List<Ledgers> allLedgerlist = qm.findAll("Ledgers.findAll");
        for(int i = 0 ; i < allLedgerlist.size(); i++)
        {
            System.out.println("" + allLedgerlist.get(i).toString() );
            ledgerlist.add(allLedgerlist.get(i));
        }
        mledgersList.setItems(ledgerlist);
        
        
        oid.setCellValueFactory(new PropertyValueFactory<Payments, Integer>("oid"));
        ledgertype.setCellValueFactory(new PropertyValueFactory<Payments, Ledgers>("ledgertype"));
        note.setCellValueFactory(new PropertyValueFactory<Payments, String>("note"));
        paymentdatetime.setCellValueFactory(new PropertyValueFactory<Payments, Date>("paymentdatetime"));
        userid.setCellValueFactory(new PropertyValueFactory<Payments, Users>("userid"));
        amount.setCellValueFactory(new PropertyValueFactory<Payments, Integer>("amount"));
        dTotalPaid.setEditable(false);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoAccounts();
    }
    
    public void processSearch()
    {
        Ledgers selectedLedgers = mledgersList.getValue();
        QueryManager qm = QueryManager.getInstance();
        ObservableList<Payments> paymentoblist = FXCollections.observableArrayList();
        
        List<Payments> paymentList = qm.findPaymentsByLedgerId(selectedLedgers);
        Integer simpleTotal = 0;
        for(int i = 0 ; i < paymentList.size(); i++)
        {
            System.out.println("->" + paymentList.get(i).toString());
            paymentoblist.add(paymentList.get(i));
            simpleTotal = simpleTotal +  paymentList.get(i).getAmount();
        }
        Integer finalamount = selectedLedgers.getOpeningBalance() + simpleTotal;
        
        allpayments.setItems(paymentoblist);
        dTotalPaid.setText("" + finalamount);
        
    }
    
    public void processTodayExpenses()
    {
        QueryManager qm = QueryManager.getInstance();
        ObservableList<Payments> paymentoblist = FXCollections.observableArrayList();
        
        Userlog userlog = qm.getLastUserlog();
        Date currentDate = new Date();
        Integer expense_total = 0;
        
        List<Payments> allPaymentsList = qm.findPaymentsBetween(userlog.getStartdatetime(), currentDate);
        for(int i = 0; i < allPaymentsList.size(); i++)
        {
            expense_total = expense_total + allPaymentsList.get(i).getAmount();
            paymentoblist.add(allPaymentsList.get(i));
        }
        allpayments.setItems(paymentoblist);
        
        tDeleteCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Payments, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Payments, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       tDeleteCol.setCellFactory(new Callback<TableColumn<Payments, Boolean>, TableCell<Payments, Boolean>>() 
        {
            @Override public TableCell<Payments, Boolean> call(TableColumn<Payments, Boolean> personBooleanTableColumn) 
            {
                return new DeletePaymentsCell(allpayments);
            }
        }
        );
        
        
        dTotalPaid.setText("" + expense_total);
    }
    
    public class DeletePaymentsCell extends TableCell<Payments, Boolean>
    {
        final Button deleteButton = new Button("Delete");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        DeletePaymentsCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(deleteButton);
            
            deleteButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Payments selectedpayments = (Payments) allpayments.getItems().get(selectedIndex);
                    QueryManager qm = QueryManager.getInstance();
                    int result = qm.deletePaymentsByOid(selectedpayments.getOid());
                    
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
}
