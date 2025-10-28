/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.Itemtrans;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class LaundryLogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<Itemtrans> tabletransaction;
    
    @FXML
    private TableColumn<Itemtrans, Items> itemname;
    
    @FXML
    private TableColumn<Itemtrans, Integer> sentqty;
    
    @FXML
    private TableColumn<Itemtrans, Integer> receivedqty;
    
    @FXML
    ObservableList<Itemtrans> finalList = FXCollections.observableArrayList();
    
    @FXML
    private TextField qtywithlaundry;
    
    @FXML
    private ComboBox<Items> itemsoptions;
    
    ObservableList<Items> finalList1 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        itemname.setCellValueFactory(new PropertyValueFactory<Itemtrans, Items>("itemid"));
        sentqty.setCellValueFactory(new PropertyValueFactory<Itemtrans, Integer>("receivedCount"));
        receivedqty.setCellValueFactory(new PropertyValueFactory<Itemtrans, Integer>("sentCount"));
        
        QueryManager qm = QueryManager.getInstance();
        List<Itemtrans> templist = qm.findAll("Itemtrans.findAll");
        for(int i = 0; i < templist.size(); i++)
        {
            finalList.add(templist.get(i));
        }
        tabletransaction.setItems(finalList);
        
        List<Items> allItems = qm.findAll("Items.findAll");
        for(int i = 0 ; i < allItems.size(); i++)
        {
            finalList1.add(allItems.get(i));
        }
        itemsoptions.setItems(finalList1);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoLaundry();
    }
    
    public void processFind()
    {
     Items item = itemsoptions.getValue();
     QueryManager qm = QueryManager.getInstance();
     
     Integer y = (Integer)qm.findItemtransTotalRecvCount(item);
     
     System.out.println("Count is: " + y);
    }
}
