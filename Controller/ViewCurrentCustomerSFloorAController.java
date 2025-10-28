/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */


public class ViewCurrentCustomerSFloorAController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ImageView image301;
    @FXML
    private TextField text301;
    
    @FXML
    private ImageView image302;
    @FXML
    private TextField text302;
    
    @FXML
    private ImageView image303;
    @FXML
    private TextField text303;
    
    @FXML
    private ImageView image304;
    @FXML
    private TextField text304;
    
    @FXML
    private ImageView image305;
    @FXML
    private TextField text305;
    
    @FXML
    private ImageView image306;
    @FXML
    private TextField text306;
    
    @FXML
    private ImageView image307;
    @FXML
    private TextField text307;
    
    @FXML
    private ImageView image308;
    @FXML
    private TextField text308;
    
    @FXML
    private ImageView image309;
    @FXML
    private TextField text309;
    
    @FXML
    private ImageView image310;
    @FXML
    private TextField text310;
    
    @FXML
    private ImageView image311;
    @FXML
    private TextField text311;
    
    @FXML
    private ImageView image312;
    @FXML
    private TextField text312;
    
    @FXML
    private ImageView image313;
    @FXML
    private TextField text313;
    
    @FXML
    private ImageView image314;
    @FXML
    private TextField text314;
    
    @FXML
    private ImageView image315;
    @FXML
    private TextField text315;
    
    @FXML
    private ImageView image316;
    @FXML
    private TextField text316;
    
    @FXML
    private ImageView image317;
    @FXML
    private TextField text317;
    
    @FXML
    private ImageView image318;
    @FXML
    private TextField text318;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
       this.application = aThis;
       QueryManager qm = QueryManager.getInstance();
              
       List<Room> allRooms = qm.findAll("Room.findAll");
       int roomcount = 301;
       
       for(int i = 0; i < allRooms.size(); i++)
       {
           System.out.println("roomcount: " + roomcount);
           try
           {   
               System.out.println("Trying for: " + allRooms.get(i).getOid());
               Bookings thisbookings = qm.findCurrentRoomBookings(allRooms.get(i));
               Guests thisguest = thisbookings.getCustomerid();
               String pp = thisguest.getImage();
               String name = thisguest.getName() + "  " + thisguest.getLastname();
               File file = new File(pp);
               Image ima = new Image(file.toURI().toString());
               setImage(allRooms.get(i).getOid(), ima, name);
           } 
           catch (Exception e) 
           {
               System.out.println("Error");
           }
           roomcount++;
       }
    }
    
    public boolean setImage(Integer roomnoint, Image image, String name)
    {
        switch(roomnoint)
        {
            case 301: image301.setImage(image);
                      text301.setText(name);
                      break;
            case 302: image302.setImage(image);
                      text302.setText(name);
                      break; 
            case 303: image303.setImage(image);
                      text303.setText(name);
                      break;
            case 304: image304.setImage(image);
                      text304.setText(name);
                      break;    
            case 305: image305.setImage(image);
                      text305.setText(name);
                      break;    
            case 306: image306.setImage(image);
                      text306.setText(name);
                      break;    
            case 307: image307.setImage(image);
                      text307.setText(name);
                      break;    
            case 308: image308.setImage(image);
                      text308.setText(name);
                      break;    
            case 309: image309.setImage(image);
                      text309.setText(name);
                      break;    
            case 310: image310.setImage(image);
                      text310.setText(name);
                      break;    
            case 311: image311.setImage(image);
                      text311.setText(name);
                      break;    
            case 312: image312.setImage(image);
                      text312.setText(name);
                      break;    
            case 313: image313.setImage(image);
                      text313.setText(name);
                      break;    
            case 314: image314.setImage(image);
                      text314.setText(name);
                      break;    
            case 315: image315.setImage(image);
                      text315.setText(name);
                      break;    
            case 316: image316.setImage(image);
                      text316.setText(name);
                      break;    
            case 317: image317.setImage(image);
                      text317.setText(name);
                      break;    
            case 318: image318.setImage(image);
                      text318.setText(name);
                      break;
                
        }
        return true;
    }
    
     public void processGroundFloor()
    {
        application.gotoViewCurrentCustomers();
    }
     
     public void processFirstFloor()
    {
        application.gotoViewCurrentCustomerFFloor();
    }
     
    public void processSecondFloorB()
    {
        application.gotoViewCurrentCustomersSFloorB();
    } 
    
    public void processGetDetailsRoom301()
    {
        application.gotoCurrentCustomerDetail(301);
    }
    
    public void processGetDetailsRoom302()
    {
        application.gotoCurrentCustomerDetail(302);
    }
    
    public void processGetDetailsRoom303()
    {
        application.gotoCurrentCustomerDetail(303);
    }
    
    public void processGetDetailsRoom304()
    {
        application.gotoCurrentCustomerDetail(304);
    }
    
    public void processGetDetailsRoom305()
    {
        application.gotoCurrentCustomerDetail(305);
    }
    
    public void processGetDetailsRoom306()
    {
        application.gotoCurrentCustomerDetail(306);
    }
    
    public void processGetDetailsRoom307()
    {
        application.gotoCurrentCustomerDetail(307);
    }
    
    public void processGetDetailsRoom308()
    {
        application.gotoCurrentCustomerDetail(308);
    }
    
    public void processGetDetailsRoom309()
    {
        application.gotoCurrentCustomerDetail(309);
    }
    
    public void processGetDetailsRoom310()
    {
        application.gotoCurrentCustomerDetail(310);
    }
    
    public void processGetDetailsRoom311()
    {
        application.gotoCurrentCustomerDetail(311);
    }
    
    public void processGetDetailsRoom312()
    {
        application.gotoCurrentCustomerDetail(312);
    }
    
    public void processGetDetailsRoom313()
    {
        application.gotoCurrentCustomerDetail(313);
    }
    
    public void processGetDetailsRoom314()
    {
        application.gotoCurrentCustomerDetail(314);
    }
    
    public void processGetDetailsRoom315()
    {
        application.gotoCurrentCustomerDetail(315);
    }
    
    public void processGetDetailsRoom316()
    {
        application.gotoCurrentCustomerDetail(316);
    }
    
    public void processGetDetailsRoom317()
    {
        application.gotoCurrentCustomerDetail(317);
    }
    
    public void processGetDetailsRoom318()
    {
        application.gotoCurrentCustomerDetail(318);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewEntity();
    }
    
}
