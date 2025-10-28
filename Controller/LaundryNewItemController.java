/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class LaundryNewItemController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    @FXML
    private TextField itemname;
    
    @FXML
    private TextArea itemdesc;
    
    @FXML
    private TextField itemrate;
    
    @FXML
    private TextField itemtotalqty;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
    }
    
    public void gotoHome()
    {
        application.gotoHome();
    }
    
    public void gotoBack()
    {
        application.gotoAccounts();
    }
    
    public boolean  validate ()
    {
    if(itemname.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Amenity Details");
            return false;
        }
     if(itemrate.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Amenity Details");
            return false;
        }
      if(itemtotalqty.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Amenity Details");
            return false;
        }
    return true;
    }
    
    
    public void processSubmit()
    {
        double inputrate;
        Integer inputtotalqty;
        if(validate())
        {
        String inputname = itemname.getText();
        String inputdesc = itemdesc.getText();
        
        
        try
         {
          inputrate = Double.parseDouble(itemrate.getText());
        inputtotalqty = Integer.parseInt(itemtotalqty.getText());    
            }
       catch (Exception e)
            {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Amenity Details");
             itemrate.clear();
             itemrate.requestFocus();
              itemtotalqty.clear();
             itemtotalqty.requestFocus();
            return ;
            }
        
        LodgeConfig.newLaundryItem(inputname, inputdesc, inputrate, inputtotalqty);
        QueryManager qm = QueryManager.getInstance();
        Items latestItem = qm.getLastItems();
        
        Integer qty = 0;
        
        LodgeConfig.newItemDirty(qty, latestItem);
        LodgeConfig.newItemInLaundry(qty, latestItem);
        LodgeConfig.newItemInStore(qty, latestItem);
        LodgeConfig.newItemInUse(qty, latestItem);
        
        application.gotoLaundry();
    }
}
}