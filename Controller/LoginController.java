/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lodgeman.lalitman.component.eventMan.eventaction.PopupListner;
import lodgeman.lalitman.component.eventMan.eventtype.Once;
import lodgeman.lalitman.model.pojo.Users;
import lodgeman.lalitman.view.LodgeMan;
import lodgeman.lalitman.model.*;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.Service.Foodorders;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.AppInfo;
import org.quartz.SchedulerException;



public class LoginController implements Initializable 
{
    
    @FXML
    private Label wrongInfo;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private TextField mOpeningBalance;
    
    @FXML
    private Button exitButton;
    
    private LodgeMan application;
    Stage primaryStage;
    /*
     * Initializes the controller class.
     */
    
    Generic generic = Generic.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application) 
    {
        this.application =  application;
    }
    
    public void setApp1(LodgeMan aThis, Integer closingbalance) 
    {
        this.application = aThis;
        mOpeningBalance.setText("" + closingbalance);
        mOpeningBalance.setEditable(false);
        exitButton.setVisible(false);
        primaryStage = AppInfo.getInstance().getmPrimaryStage();
    }
    
    public boolean  validate()
    {
    
      if(username.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "User Name is Empty");
      return false;
      }
      
      if(password.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Password is  Empty");
      return false;
      }
      if(mOpeningBalance.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Opening Balance is  Empty");
      return false;
      }
      return true;
    }
    
    
    
    public void processLogin(ActionEvent event) throws ParseException, SchedulerException
    {
        
     if(validate())
     {
      PopupListner plt =  PopupListner.getInstance();
        Date dt = new Date();
        long tt = dt.getTime();
        tt = tt +60000;
        dt.setTime(tt);
        System.out.println("The current Thread is "+Thread.currentThread());
       
               
        QueryManager qm = QueryManager.getInstance();
        
            String uname = username.getText();
            String pword = password.getText();
            Integer openingbalance;
            try
            {
             openingbalance = Integer.parseInt(mOpeningBalance.getText());
            }
            catch(NumberFormatException ne)
            {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                       "Please Enter Correct opening balance(Numeric)");
            mOpeningBalance.clear();
            
            return;
            }
       
            
            if(qm.validateUser(uname, pword,openingbalance))
        {
            
       
            application.gotoHome();
        }
        else
        {
            Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Wrong information. Please try again...");           
        }
            
    }
    }
    
    
    public void processExit(ActionEvent event)
    {
        Platform.exit();
    }

}
