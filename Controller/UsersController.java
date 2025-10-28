/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import lodgeman.lalitman.view.LodgeMan;

/*
 * FXML Controller class
 *
 * @author vijay
 */

public class UsersController implements Initializable 
{
    private LodgeMan application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application) 
    {
        this.application = application;
    }
    
    public void processHome()
    {
        application.gotoHome();
    }    
    
    public void processNewUser()
    {
        System.out.println("I am here!");
        application.gotoNewUser();
    }
}
