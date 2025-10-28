/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.NoResultException;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminlaundry;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Itemmastertrans;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.Itemtrans;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class LaundryTransactionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    @FXML
    private TextField receivedFrom;
    
    @FXML
    private TextField receivedBy;
    
    @FXML
    private TextField transactionDate;
    
    @FXML
    private TextField transItemName;
    
    @FXML
    private TextField transItemRecv;
    
    @FXML
    private TextField transItemSent;
    
    @FXML
    private TextField transItemSubtotal;
    
    @FXML
    private ComboBox<Items> itemslist;
    
    private Items itemG;
    private Itemmastertrans lastMasterTransG;
    
    ObservableList<Items> finalItemList = FXCollections.observableArrayList();
    
    @FXML
    private Label dirtyItems;
    
    @FXML
    private Label laundryItems;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
       this.application = aThis;
       
       QueryManager qm = QueryManager.getInstance();
       List<Items> allItems = qm.findAll("Items.findAll");
       for(int i = 0 ; i < allItems.size(); i++)
       {
           finalItemList.add(allItems.get(i));
       }
       itemslist.setItems(finalItemList);
    }
    
    public void processCalculateSubTotal()
    {
        Integer recvdAmount = Integer.parseInt(transItemRecv.getText());
        Double subT = (double) recvdAmount * itemG.getItemRate();
        transItemSubtotal.setText("" + subT);
    }
    
    public void processNewTransaction()
    {
        String inputrecvfrom = receivedFrom.getText();
        String inputrecvto = receivedBy.getText();
        Date currentDate = new Date();
        Integer total = 0;
        QueryManager qm = QueryManager.getInstance();
        Users currentUser = qm.findLoggedInUser();
        LodgeConfig.newMasterLaundryTransaction(inputrecvfrom, inputrecvto, currentDate, total, currentUser);
        
        Itemmastertrans lastMasterTrans = qm.findLastItemmastertrans();
        System.out.println("Last master oid: " + lastMasterTrans.getOid());
        this.lastMasterTransG = lastMasterTrans;
    } 
    
    public void processDone()
    {
        Items selectItem = itemslist.getValue();
        Integer recvInt = Integer.parseInt(transItemRecv.getText());
        Integer sentInt = Integer.parseInt(transItemSent.getText());
        double subtotal = selectItem.getItemRate() * recvInt;
        System.out.println("Last master oid: " + lastMasterTransG.getOid());
        Integer balance = 0;
        
        QueryManager qm = QueryManager.getInstance();
        try
        {
            Itemtrans lastItemTrans = qm.findByLastItemTransaction(selectItem);
            balance = lastItemTrans.getBalance(); //old_balance
        }
        catch(NoResultException e)
        {
            System.out.println("First Transaction!");
        }
        Integer carryforward = balance - recvInt;
        Integer newbalance = sentInt + carryforward;
        
        /**********************************************************************/
        Iteminlaundry iteminlaundry = qm.findIteminLaundryByItem(selectItem);
        Integer newqty = iteminlaundry.getQty() - recvInt;
        Integer result = qm.updateQtyInIteminlaundry(selectItem, newqty);
        /**********************************************************************/
        Iteminstore iteminstore = qm.findIteminstoreByItem(selectItem);
        Integer newstoreqty = iteminstore.getQty() + recvInt;
        Integer result2 = qm.updateQtyInIteminStore(selectItem, newstoreqty);
        /**********************************************************************/
        Itemdirty itemdirty = qm.findItemdirtyByItem(selectItem);
        Integer newdirtyqty = itemdirty.getQty() - sentInt;
        Integer result3 = qm.updateQtyInItemDirty(selectItem, newdirtyqty);
        /**********************************************************************/
        Iteminlaundry iteminlaundry2 = qm.findIteminLaundryByItem(selectItem);
        Integer newlaundryitem = iteminlaundry2.getQty() + sentInt;
        Integer result4 = qm.updateQtyInIteminlaundry(selectItem, newlaundryitem);
        /**********************************************************************/
        
        
        LodgeConfig.newItemTransaction(recvInt, sentInt, subtotal, lastMasterTransG, selectItem, newbalance);
        transItemRecv.setText("");
        transItemSent.setText("");
    }
    
    public void processFinishTransaction()
    {
       QueryManager qm = QueryManager.getInstance();
       List<Itemtrans> somelist =  qm.findItemTransByMasterTrans(lastMasterTransG);
       double finaltotal = 0;
       for(int i = 0; i < somelist.size(); i++)
       {
           finaltotal = finaltotal + somelist.get(i).getSubtotal();
       }
       qm.UpdateItemmastertransTotal(lastMasterTransG.getOid(), finaltotal);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoLaundry();
    }
    
    public void processCheck()
    {
        
        QueryManager qm = QueryManager.getInstance();
        Items selectedItem  = itemslist.getValue();
        Itemdirty itemdirty = qm.findItemdirtyByItem(selectedItem);
        String message1 = "Dirty Items:" + itemdirty.getQty() + "";
        dirtyItems.setText(message1);
        
        Iteminlaundry iteminlaundry = qm.findIteminLaundryByItem(selectedItem);
        String message2 = "Items in laundry:" + iteminlaundry.getQty() + "";
        laundryItems.setText(message2);
        
    }
}
