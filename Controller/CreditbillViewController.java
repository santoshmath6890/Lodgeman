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
import lodgeman.lalitman.controller.table.CreditView;
import lodgeman.lalitman.controller.table.CreditbillViewTable;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class CreditbillViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<Creditowners> creditownerChoice;
    
    ObservableList<Creditowners> creditownerlist = FXCollections.observableArrayList();
    ObservableList<CreditbillViewTable> tablecreditlist = FXCollections.observableArrayList();
    
    @FXML
    private TableView<CreditbillViewTable> allCredittable;
    
    @FXML
    private TableColumn<CreditbillViewTable, String> creditowner;
    
    @FXML
    private TableColumn<CreditbillViewTable, String> guestname;
    
    @FXML
    private TableColumn<CreditbillViewTable, Date> entrydate;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> billno ;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> receive;
    
    @FXML
    private TableColumn<CreditbillViewTable, Integer> refund;
    
    @FXML
    private TableColumn<CreditbillViewTable, Date> cleardate;
    
    @FXML
    private TableColumn<CreditbillViewTable, Boolean> tActionCol;
    
    @FXML
    private TextField totalreceive;
    
    @FXML
    private TextField totalrefund;
    
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Creditowners> creditsO = qm.findAll("Creditowners.findAll");
        for(int i = 0 ;i < creditsO.size();i++)
        {
            creditownerlist.add(creditsO.get(i));
        }
        creditownerChoice.setItems(creditownerlist);
        /**********************************************************************/
        
        
        /**********************************************************************/
        creditowner.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, String>("creditowners"));
        guestname.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, String>("guestname"));
        entrydate.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Date>("entrydate"));
        billno.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("billno"));
        receive.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("totalamount"));
        refund.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Integer>("balanceamount"));
        cleardate.setCellValueFactory(new PropertyValueFactory<CreditbillViewTable, Date>("cleardate"));
        /**********************************************************************/
        
        
        totalreceive.setEditable(false);
        totalrefund.setEditable(false);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }            
    
    public void processBack()
    {
        application.gotoConfig();
    }
    
    public boolean  validate ()
    {
   
    
    return true;
    }
    
    public void processSearch()
    {
        Creditowners selectedCreditOwner = creditownerChoice.getValue();
        QueryManager qm = QueryManager.getInstance();
        List<Creditbills> outputlist = qm.findCreditBillsByCreditowner(selectedCreditOwner);
        
        Integer totalreceiveamount = 0 ;
        Integer totalbalancedue = 0;
        
        for(int i = 0; i < outputlist.size();i++)
        {
            /******************************************************************/
            String creditownername = outputlist.get(i).getCreditownerid().getOwnername();
            String billguestname = outputlist.get(i).getBillno().getGuestname();
            Date entrydate = outputlist.get(i).getEntrydate();
            Integer billno = outputlist.get(i).getBillno().getOid();
            Integer receiveamount = outputlist.get(i).getBillno().getCashreceived();
            BigDecimal refundamount = outputlist.get(i).getBillno().getCashrefund();
            Boolean paid = outputlist.get(i).getPaid();
            Date cleardate = outputlist.get(i).getCleardate();
            Integer poid = outputlist.get(i).getOid();
            /******************************************************************/
            
            
            /******************************************************************/
            List<Creditbilltrans> thislist = qm.getCreditBillReceivedByCreditBill(outputlist.get(i));
            Integer total = 0;
            for(int ii = 0 ; ii < thislist.size(); ii++)
            {
                total = total + thislist.get(ii).getAmountrecvd();
            }
            int amtdue = outputlist.get(i).getBillno().getCashreceived() - total;
            /******************************************************************/
            
            totalbalancedue = totalbalancedue + amtdue;
            totalreceiveamount = totalreceiveamount + receiveamount;
            tablecreditlist.add(new CreditbillViewTable(poid,creditownername,billguestname,entrydate,billno,receiveamount,amtdue,cleardate));
        }
        totalreceive.setText("" + totalreceiveamount);
        totalrefund.setText("" + totalbalancedue);
        allCredittable.setItems(tablecreditlist);
        
        /**********************************************************************/
        tActionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CreditbillViewTable, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CreditbillViewTable, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
        
       tActionCol.setCellFactory(new Callback<TableColumn<CreditbillViewTable, Boolean>, TableCell<CreditbillViewTable, Boolean>>() 
        {
            @Override public CreditBillsListCell call(TableColumn<CreditbillViewTable, Boolean> personBooleanTableColumn) 
            {
                return new CreditBillsListCell(allCredittable);
            }
        }
        );
       /***********************************************************************/
    }
    
    public class CreditBillsListCell extends TableCell<CreditbillViewTable, Boolean>
    {
        final Button addButton       = new Button("Receive");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        CreditBillsListCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    CreditbillViewTable creditbillviewtable = (CreditbillViewTable) allCredittable.getItems().get(selectedIndex);
                    Integer poid = creditbillviewtable.getCreditbilloid();
                    QueryManager qm = QueryManager.getInstance();
                    
                    Creditbills thiscreditbills = qm.findCreditbillsByOid(poid);
                    
                    if(thiscreditbills.getPaid() == false)
                    {
                        application.gotoCreditBillRecv(thiscreditbills);
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
