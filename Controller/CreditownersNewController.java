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
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class CreditownersNewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    private TextField mName;
    
    @FXML
    private TextArea mDesc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
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
    if(mName.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Credit Owner Name");
            return false;
        }
    return true;
    }
    public void processSubmit()
    {
        String name,desc;
         if(validate())
        {
            
            try{
                name = mName.getText();
                desc = mDesc.getText();
            }
            catch (Exception e)
            {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter New Credit Owner Name");
             mName.clear();
             mName.requestFocus();
            return ;
            }
        
       LodgeConfig.newCreditowner(name, desc);
       application.gotoConfig();
    }
}
}
