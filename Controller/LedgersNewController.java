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
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Groups;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class LedgersNewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TextField mLedgerName;
    
    @FXML
    private ComboBox<Groups> mGroupType;
    
    @FXML
    private TextField mOpeningBalance;
    
    @FXML
    private TextArea mDescription;
    
    ObservableList<Groups> groupList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        List<Groups> allGroupsList = qm.findAll("Groups.findAll");
        for ( int i = 0 ; i < allGroupsList.size(); i++)
        {
            System.out.println("->" + allGroupsList.get(i).toString());
            groupList.add(allGroupsList.get(i));
        }
        
        mGroupType.setItems(groupList);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoAccounts();
    }
     
    public boolean  validate ()
    {
    if(mLedgerName.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter Details");
            return false;
        }
    if(mOpeningBalance.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter Details");
            return false;
        }
    
    
    return true;
    }
    
    public void processSubmit()
    {
         if(validate())
            {
        String ledgerName = mLedgerName.getText();
         Integer openingBalance;
           
               
                try{
                    openingBalance = Integer.parseInt(mOpeningBalance.getText());
                    }
                catch (Exception e)
                    {
                     Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter Details");
                     mOpeningBalance.clear();
                     mOpeningBalance.requestFocus();
                     return ;
                    }
           
        Groups selectedGroup = mGroupType.getValue();
        String desc = mDescription.getText();
        
        LodgeConfig.newLedger(ledgerName, openingBalance, desc, selectedGroup);
        application.gotoAccounts();
    }
    }
}
