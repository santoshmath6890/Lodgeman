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
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lodgeman.lalitman.controller.reports.paymentreceipt;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Groups;
import lodgeman.lalitman.model.pojo.Ledgers;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class PaymentsNewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ComboBox<Ledgers> mledgersList;
    
    @FXML
    private TextArea mNote;
    
    @FXML
    private TextField mAmount;
    
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
        Groups thisgroups = qm.getGroupsByOid(4);
        List<Ledgers> allLedgerlist = qm.getLedgersByGroup(thisgroups);
        for(int i = 0 ; i < allLedgerlist.size(); i++)
        {
            System.out.println("" + allLedgerlist.get(i).toString() );
            ledgerlist.add(allLedgerlist.get(i));
        }
        
        mledgersList.setItems(ledgerlist);
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
    if(mAmount.getText().isEmpty())
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter Amount");
            return false;
        }
    return true;
    }
     
    public void processSubmit() throws JRException, IOException
    {
       if(validate())
       {
            Ledgers selectedLedgers = mledgersList.getValue();
            Integer amount;   
            try
            {
                amount = Integer.parseInt(mAmount.getText());
            }
            catch (Exception e)
            {
                Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() , "Enter Amount");
                mAmount.clear();
                mAmount.requestFocus();
                return ;
            }
        
        String note;
        try
        {
            note = mNote.getText();
        }
        catch(Exception ex)
        {
            note = null;
        }
        
        QueryManager qm = QueryManager.getInstance();
        Users currentUser = qm.findLoggedInUser();
        Date paymentdatetime = new Date();
        LodgeConfig.newPayments(paymentdatetime, selectedLedgers, note, currentUser,amount);
        
        /**********************************************************************/
        Payments lastpayments = qm.findLastPayments();
        Integer lastpaymentno = lastpayments.getOid();
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        String filename = path1 + "/" + "PaymentReceipt_" + lastpaymentno + ".pdf"; 
        paymentreceipt.printpaymentreceipt(lastpaymentno, filename);
        /**********************************************************************/
        
        application.gotoAccounts();
    }
       
    }
}
