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
public class ViewCurrentCustomersFFloorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ImageView image201;
    @FXML
    private TextField text201;
    
    @FXML
    private ImageView image202;
    @FXML
    private TextField text202;
    
    @FXML
    private ImageView image203;
    @FXML
    private TextField text203;
    
    @FXML
    private ImageView image204;
    @FXML
    private TextField text204;
    
    @FXML
    private ImageView image205;
    @FXML
    private TextField text205;
    
    @FXML
    private ImageView image206;
    @FXML
    private TextField text206;
    
    @FXML
    private ImageView image207;
    @FXML
    private TextField text207;
    
    @FXML
    private ImageView image208;
    @FXML
    private TextField text208;
    
    @FXML
    private ImageView image209;
    @FXML
    private TextField text209;
    
    @FXML
    private ImageView image210;
    @FXML
    private TextField text210;
    
    @FXML
    private ImageView image211;
    @FXML
    private TextField text211;
    
    @FXML
    private ImageView image212;
    @FXML
    private TextField text212;
    
    @FXML
    private ImageView image213;
    @FXML
    private TextField text213;
    
    @FXML
    private ImageView image214;
    @FXML
    private TextField text214;
    
    @FXML
    private ImageView image215;
    @FXML
    private TextField text215;
    
    @FXML
    private ImageView image216;
    @FXML
    private TextField text216;
    
    @FXML
    private ImageView image217;
    @FXML
    private TextField text217;
    
    @FXML
    private ImageView image218;
    @FXML
    private TextField text218;
    
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
       int roomcount = 201;
       
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
            case 201: image201.setImage(image);
                      text201.setText(name);
                      break;
            case 202: image202.setImage(image);
                      text202.setText(name);
                      break; 
            case 203: image203.setImage(image);
                      text203.setText(name);
                      break;
            case 204: image204.setImage(image);
                      text204.setText(name);
                      break;    
            case 205: image205.setImage(image);
                      text205.setText(name);
                      break;    
            case 206: image206.setImage(image);
                      text206.setText(name);
                      break;    
            case 207: image207.setImage(image);
                      text207.setText(name);
                      break;    
            case 208: image208.setImage(image);
                      text208.setText(name);
                      break;    
            case 209: image209.setImage(image);
                      text209.setText(name);
                      break;    
            case 210: image210.setImage(image);
                      text210.setText(name);
                      break;    
            case 211: image211.setImage(image);
                      text211.setText(name);
                      break;    
            case 212: image212.setImage(image);
                      text212.setText(name);
                      break;    
            case 213: image213.setImage(image);
                      text213.setText(name);
                      break;    
            case 214: image214.setImage(image);
                      text214.setText(name);
                      break;    
            case 215: image215.setImage(image);
                      text215.setText(name);
                      break;    
            case 216: image216.setImage(image);
                      text216.setText(name);
                      break;    
            case 217: image217.setImage(image);
                      text217.setText(name);
                      break;    
            case 218: image218.setImage(image);
                      text218.setText(name);
                      break;        
                
        }
        return true;
    }
    
    public void processGroundFloor()
    {
        application.gotoViewCurrentCustomers();
    }
    
    public void procesSecondFloorA()
    {
        application.gotoViewCurrentCustomersSFloorA();
    }
    
    public void processSecondFloorB()
    {
        application.gotoViewCurrentCustomersSFloorB();
    }
    
    public void processGetDetailsRoom201()
    {
        application.gotoCurrentCustomerDetail(220);
    }
    
    public void processGetDetailsRoom202()
    {
        application.gotoCurrentCustomerDetail(202);
    }
    
    public void processGetDetailsRoom203()
    {
        application.gotoCurrentCustomerDetail(203);
    }
    
    public void processGetDetailsRoom204()
    {
        application.gotoCurrentCustomerDetail(204);
    }
    
    public void processGetDetailsRoom205()
    {
        application.gotoCurrentCustomerDetail(205);
    }
    
    public void processGetDetailsRoom206()
    {
        application.gotoCurrentCustomerDetail(206);
    }
    
    public void processGetDetailsRoom207()
    {
        application.gotoCurrentCustomerDetail(207);
    }
    
    public void processGetDetailsRoom208()
    {
        application.gotoCurrentCustomerDetail(208);
    }
    
    public void processGetDetailsRoom209()
    {
        application.gotoCurrentCustomerDetail(209);
    }
    
    public void processGetDetailsRoom210()
    {
        application.gotoCurrentCustomerDetail(210);
    }
    
    public void processGetDetailsRoom211()
    {
        application.gotoCurrentCustomerDetail(211);
    }
    
    public void processGetDetailsRoom212()
    {
        application.gotoCurrentCustomerDetail(212);
    }
    
    public void processGetDetailsRoom213()
    {
        application.gotoCurrentCustomerDetail(213);
    }
    
    public void processGetDetailsRoom214()
    {
        application.gotoCurrentCustomerDetail(214);
    }
    
    public void processGetDetailsRoom215()
    {
        application.gotoCurrentCustomerDetail(215);
    }
    
    public void processGetDetailsRoom216()
    {
        application.gotoCurrentCustomerDetail(216);
    }
    
    public void processGetDetailsRoom217()
    {
        application.gotoCurrentCustomerDetail(217);
    }
    
    public void processGetDetailsRoom218()
    {
        application.gotoCurrentCustomerDetail(219);
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
