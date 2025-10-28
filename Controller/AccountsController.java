/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AccountsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
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
        application.gotoHome();
    }
    
    public void processLedgerNew()
    {
        application.gotoLedgersNew();
    }
    
    public void processPaymentsNew()
    {
        application.gotoPaymentNew();
    }
    
    public void processLedgerView()
    {
        application.gotoLedgersView();
    }
    
    public void processLaundry()
    {
        application.gotoLaundry();
    }
    
    public void processReceiveNew()
    {
        application.gotoReceiveNew();
    }
    
    
}
