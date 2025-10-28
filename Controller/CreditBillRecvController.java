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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lodgeman.lalitman.controller.reports.creditamountreceipt;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class CreditBillRecvController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    Creditbills creditbillG;
    
    @FXML
    private TextField InputCreditowner;
    
    @FXML
    private TextField InputGuestName;
    
    @FXML
    private TextField InputPayableAmount;
    
    @FXML
    private TextField InputCashRecvd;
    
    @FXML
    private TextField InputAmountDue;
    
    @FXML
    private TextField UserAmount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(LodgeMan aThis, Creditbills thiscreditbills) 
    {
        this.creditbillG = thiscreditbills;
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        
        InputCreditowner.setText("" + thiscreditbills.getCreditownerid().getOwnername());
        InputGuestName.setText("" + thiscreditbills.getBillno().getGuestname());
        InputPayableAmount.setText("" + thiscreditbills.getBillno().getCashreceived());
        
        List<Creditbilltrans> thislist = qm.getCreditBillReceivedByCreditBill(creditbillG);
        Integer total = 0;
        for(int i = 0 ; i < thislist.size(); i++)
        {
            total = total + thislist.get(i).getAmountrecvd();
        }
        InputCashRecvd.setText("" + total);
        int temp = thiscreditbills.getBillno().getCashreceived() - total;
        InputAmountDue.setText("" + temp);
    }
    
    public void processSubmit() throws JRException, IOException
    {
        QueryManager qm = QueryManager.getInstance();
        Integer amt = Integer.parseInt(UserAmount.getText());
        Date currentDate = new Date();
        LodgeConfig.newCreditBillTrans(amt, currentDate, creditbillG, creditbillG.getBillno());
        
        
        List<Creditbilltrans> thislist = qm.getCreditBillReceivedByCreditBill(creditbillG);
        Integer total = 0;
        
        for(int i = 0 ; i < thislist.size(); i++)
        {
            total = total + thislist.get(i).getAmountrecvd();
        }
        
        if(total >= creditbillG.getBillno().getCashreceived())
        {
            Boolean paidvalue = true;
            Date thisdate = new Date();
            Integer result = qm.updateCreditbillsClearDateAndPaid(creditbillG.getOid(), thisdate, paidvalue);
        }
        
        /**********************************************************************/
        Creditbilltrans lastcredittrans = qm.findLastCreditbilltrans();
        Integer lastcredittransno = lastcredittrans.getOid();
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        String filename = path1 + "/" + "CreditReceipt_" + lastcredittransno + ".pdf"; 
        creditamountreceipt.printcreditamountreceipt(lastcredittransno, filename);
        /**********************************************************************/
        
        application.gotoCreditbillView();
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoCreditbillView();
    }
}
