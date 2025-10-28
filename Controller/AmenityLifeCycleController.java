/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lodgeman.lalitman.controller.reports.AskAdvanceReport;
import lodgeman.lalitman.controller.reports.LaundryReport;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminlaundry;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AmenityLifeCycleController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ComboBox<Items> allItems;
    
    @FXML
    private TextField iteminuse;
    
    @FXML
    private TextField iteminstore;
    
    @FXML
    private TextField iteminlaundry;
    
    @FXML
    private TextField itemdirty;
    
    private Items  thisitem;
    
    ObservableList<Items> finalitemlist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        List<Items> allitemslist = qm.findAll("Items.findAll");
        for(int i = 0; i < allitemslist.size(); i++)
        {
            finalitemlist.add(allitemslist.get(i));
        }
        allItems.setItems(finalitemlist);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoLaundry();
    }
    
    public void show()
    {
        QueryManager qm = QueryManager.getInstance();
        Items selectitem = allItems.getValue();
        this.thisitem = selectitem; 
        
        Iteminuse selectediteminuse = qm.findIteminuseByItem(selectitem);
        iteminuse.setText("" + selectediteminuse.getQty());
        
        Iteminstore selectediteminstore = qm.findIteminstoreByItem(selectitem);
        iteminstore.setText("" + selectediteminstore.getQty());
        
        Itemdirty selecteditemdirty = qm.findItemdirtyByItem(selectitem);
        itemdirty.setText("" + selecteditemdirty.getQty());
        
        Iteminlaundry selectedIteminlaundry = qm.findIteminLaundryByItem(selectitem);
        iteminlaundry.setText("" + selectedIteminlaundry.getQty());
    }
    
    public void processRemoveInuse()
    {
        QueryManager qm = QueryManager.getInstance();
        Iteminuse selectediteminuse = qm.findIteminuseByItem(thisitem);
        
        if(selectediteminuse.getQty() > 0)
        {
            Integer oldvalueinuse = selectediteminuse.getQty();
            Integer result1 = qm.updateQtyInIteminUse(thisitem, oldvalueinuse - 1);
            Integer tempinuse = oldvalueinuse - 1;
            iteminuse.setText("" + tempinuse);
            
            Itemdirty selecteditemdirty = qm.findItemdirtyByItem(thisitem);
            Integer oldvalueindirty = selecteditemdirty.getQty();
            Integer result2 = qm.updateQtyInItemDirty(thisitem, oldvalueindirty + 1);
            Integer tempindirty = oldvalueindirty + 1;
            itemdirty.setText("" + tempindirty);
        }
    }
    
    public void processRemoveInStore()
    {
        QueryManager qm = QueryManager.getInstance();
        Iteminstore selectediteminstore = qm.findIteminstoreByItem(thisitem);
        
        if(selectediteminstore.getQty() > 0)
        {
            Integer oldvalueinstore = selectediteminstore.getQty();
            Integer result1= qm.updateQtyInIteminStore(thisitem, oldvalueinstore - 1);
            Integer tempinstore = oldvalueinstore - 1;
            iteminstore.setText("" + tempinstore);
            
            Iteminuse selectediteminuse = qm.findIteminuseByItem(thisitem);
            Integer oldvalueinuse = selectediteminuse.getQty();
            Integer result2 = qm.updateQtyInIteminUse(thisitem, oldvalueinuse + 1);
            Integer tempvalueinuse = oldvalueinuse + 1;
            iteminuse.setText("" + tempvalueinuse);
        }
    }
    
    public void processRemoveInLaundry()
    {
        QueryManager qm = QueryManager.getInstance();
        Iteminlaundry selectediteminlaundry = qm.findIteminLaundryByItem(thisitem);
        
        if(selectediteminlaundry.getQty() > 0)
        {
            Integer oldvalueinlaundry = selectediteminlaundry.getQty();
            Integer result1= qm.updateQtyInIteminlaundry(thisitem, oldvalueinlaundry - 1);
            Integer tempinlaundry = oldvalueinlaundry - 1;
            iteminlaundry.setText("" + tempinlaundry);
            
            Iteminstore selectediteminstore = qm.findIteminstoreByItem(thisitem);
            Integer oldvalueinstore = selectediteminstore.getQty();
            Integer result2 = qm.updateQtyInIteminStore(thisitem, oldvalueinstore + 1);
            Integer tempvalueinstore = oldvalueinstore + 1;
            iteminstore.setText("" + tempvalueinstore);
        }
    }
    
    public void processRemoveInDirty()
    {
        QueryManager qm = QueryManager.getInstance();
        Itemdirty selecteditemdirty = qm.findItemdirtyByItem(thisitem);
        
        if(selecteditemdirty.getQty() > 0)
        {
            Integer oldvalueinitemdirty = selecteditemdirty.getQty();
            Integer result1= qm.updateQtyInItemDirty(thisitem, oldvalueinitemdirty - 1);
            Integer tempinitemdirty = oldvalueinitemdirty - 1;
            itemdirty.setText("" + tempinitemdirty);
            
            Iteminlaundry selectediteminlaundry = qm.findIteminLaundryByItem(thisitem);
            Integer oldvalueinlaundry = selectediteminlaundry.getQty();
            Integer result2 = qm.updateQtyInIteminlaundry(thisitem, oldvalueinlaundry + 1);
            Integer tempvalueinlaundry = oldvalueinlaundry + 1;
            iteminlaundry.setText("" + tempvalueinlaundry);
        }
    }
    
    public void processPrint() throws JRException, IOException
    {
        Integer p = (int)(Math.random() * 9999);
        
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        System.out.println("my path" + path1);
        String filename = path1 + "/" + "LaundryReport" + p +  ".pdf";
        //AskAdvanceReport.printsessionreport(transID, filename);
        LaundryReport.printlaundryreport(filename);
    }
}
