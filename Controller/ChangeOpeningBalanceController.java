/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ChangeOpeningBalanceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField oldopeningbalance;
    
    @FXML
    private TextField newopeningbalance;
    
    @FXML
    private Label warningmsg;
    
    Userlog userlogG;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        QueryManager qm = QueryManager.getInstance();
        Date currentDate = new Date();
        Users currentUser1 = qm.findLoggedInUser();
        Userlog userlog = qm.getLastUserlog();
        this.userlogG = userlog;
        
        oldopeningbalance.setText("" + userlog.getOpeningbalance());
        oldopeningbalance.setEditable(false);
    }
    
    public void processUpdate()
    {
         if(validate())
        {
            Integer OpeningBalance;
            try{
             OpeningBalance = Integer.parseInt(newopeningbalance.getText());
            }
            catch (Exception e)
            {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter valid Opening Balance");
             newopeningbalance.clear();
             newopeningbalance.requestFocus();
            return ;
            }
        
        QueryManager qm = QueryManager.getInstance();
        try
        {
            Integer newOB = Integer.parseInt(newopeningbalance.getText());
            int result = qm.updateOpeningbalance(userlogG.getOid(), newOB);
            application.gotoConfig();
        }
        catch(Exception ex)
        {
            warningmsg.setText("Invalid opening balance!");
            setApp(application);
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
    
    public boolean  validate ()
    {
    if(newopeningbalance.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter valid Opening Balance");
            return false;
        }
    
    return true;
    }
    
}
