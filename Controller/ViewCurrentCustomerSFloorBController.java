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
public class ViewCurrentCustomerSFloorBController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ImageView image319;
    @FXML
    private TextField text319;
    
    @FXML
    private ImageView image320;
    @FXML
    private TextField text320;
    
    @FXML
    private ImageView image321;
    @FXML
    private TextField text321;
    
    @FXML
    private ImageView image322;
    @FXML
    private TextField text322;
    
    @FXML
    private ImageView image323;
    @FXML
    private TextField text323;
    
    @FXML
    private ImageView image324;
    @FXML
    private TextField text324;
    
    @FXML
    private ImageView image325;
    @FXML
    private TextField text325;
    
    @FXML
    private ImageView image326;
    @FXML
    private TextField text326;
    
    @FXML
    private ImageView image327;
    @FXML
    private TextField text327;
    
    @FXML
    private ImageView image328;
    @FXML
    private TextField text328;
    
    @FXML
    private ImageView image329;
    @FXML
    private TextField text329;
    
    @FXML
    private ImageView image330;
    @FXML
    private TextField text330;
    
    @FXML
    private ImageView image331;
    @FXML
    private TextField text331;
    
    @FXML
    private ImageView image332;
    @FXML
    private TextField text332;
    
    @FXML
    private ImageView image1111;
    @FXML
    private TextField text1111;
    
    @FXML
    private ImageView image2222;
    @FXML
    private TextField text2222;
    
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
       int roomcount = 319;
       
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
            case 319: image319.setImage(image);
                      text319.setText(name);
                      break;
            case 320: image320.setImage(image);
                      text320.setText(name);
                      break; 
            case 321: image321.setImage(image);
                      text321.setText(name);
                      break;
            case 322: image322.setImage(image);
                      text322.setText(name);
                      break;    
            case 323: image323.setImage(image);
                      text323.setText(name);
                      break;    
            case 324: image324.setImage(image);
                      text324.setText(name);
                      break;    
            case 325: image325.setImage(image);
                      text325.setText(name);
                      break;    
            case 326: image326.setImage(image);
                      text326.setText(name);
                      break;    
            case 327: image327.setImage(image);
                      text327.setText(name);
                      break;    
            case 328: image328.setImage(image);
                      text328.setText(name);
                      break;    
            case 329: image329.setImage(image);
                      text329.setText(name);
                      break;    
            case 330: image330.setImage(image);
                      text330.setText(name);
                      break;    
            case 331: image331.setImage(image);
                      text331.setText(name);
                      break;    
            case 332: image332.setImage(image);
                      text332.setText(name);
                      break;    
            case 1111: image1111.setImage(image);
                      text1111.setText(name);
                      break;    
            case 2222: image2222.setImage(image);
                      text2222.setText(name);
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
      
      public void processSecondFloorA()
    {
       application.gotoViewCurrentCustomersSFloorA();
    }
      
    public void processGetDetailsRoom319()
    {
        application.gotoCurrentCustomerDetail(319);
    }  
    
    public void processGetDetailsRoom320()
    {
        application.gotoCurrentCustomerDetail(320);
    }
    
    public void processGetDetailsRoom321()
    {
        application.gotoCurrentCustomerDetail(321);
    }
    
    public void processGetDetailsRoom322()
    {
        application.gotoCurrentCustomerDetail(322);
    }
    
    public void processGetDetailsRoom323()
    {
        application.gotoCurrentCustomerDetail(323);
    }
    
    public void processGetDetailsRoom324()
    {
        application.gotoCurrentCustomerDetail(324);
    }
    
    public void processGetDetailsRoom325()
    {
        application.gotoCurrentCustomerDetail(325);
    }
    
    public void processGetDetailsRoom326()
    {
        application.gotoCurrentCustomerDetail(326);
    }
    
    public void processGetDetailsRoom327()
    {
        application.gotoCurrentCustomerDetail(327);
    }
    
    public void processGetDetailsRoom328()
    {
        application.gotoCurrentCustomerDetail(328);
    }
    
    public void processGetDetailsRoom329()
    {
        application.gotoCurrentCustomerDetail(329);
    }
    
    public void processGetDetailsRoom330()
    {
        application.gotoCurrentCustomerDetail(330);
    }
    
    public void processGetDetailsRoom331()
    {
        application.gotoCurrentCustomerDetail(331);
    }
    
    public void processGetDetailsRoom332()
    {
        application.gotoCurrentCustomerDetail(332);
    }
    
    public void processGetDetailsRoom1111()
    {
        application.gotoCurrentCustomerDetail(1111);
    }
    
    public void processGetDetailsRoom2222()
    {
        application.gotoCurrentCustomerDetail(2222);
    }
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewCurrentCustomers();
    }
}
