/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
public class ViewCurrentCustomersController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private ImageView image101;
    @FXML
    private TextField text101;
    
    @FXML
    private ImageView image102;
    @FXML
    private TextField text102;
    
    @FXML
    private ImageView image103;
    @FXML
    private TextField text103;
    
    @FXML
    private ImageView image104;
    @FXML
    private TextField text104;
    
    @FXML
    private ImageView image105;
    @FXML
    private TextField text105;
    
    @FXML
    private ImageView image106;
    @FXML
    private TextField text106;
    
    @FXML
    private ImageView image107;
    @FXML
    private TextField text107;
    
    @FXML
    private ImageView image108;
    @FXML
    private TextField text108;
    
    @FXML
    private ImageView image109;
    @FXML
    private TextField text109;
    
    @FXML
    private ImageView image110;
    @FXML
    private TextField text110;
    
    @FXML
    private ImageView image111;
    @FXML
    private TextField text111;
    
    @FXML
    private ImageView image112;
    @FXML
    private TextField text112;
    
    @FXML
    private ImageView image113;
    @FXML
    private TextField text113;
    
    @FXML
    private ImageView image114;
    @FXML
    private TextField text114;
    
    @FXML
    private ImageView image115;
    @FXML
    private TextField text115;
    
    @FXML
    private ImageView image116;
    @FXML
    private TextField text116;
    
    @FXML
    private ImageView image117;
    @FXML
    private TextField text117;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
       this.application  = aThis;
       QueryManager qm = QueryManager.getInstance();
              
       List<Room> allRooms = qm.findAll("Room.findAll");
       int roomcount = 101;
       
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
           
           if(roomcount > 117)
           {
               break;
           }
       }
    }
    
    public boolean setImage(Integer roomnoint, Image image, String name)
    {
        switch(roomnoint)
        {
            case 101: image101.setImage(image);
                      text101.setText(name);
                      break;
            case 102: image102.setImage(image);
                      text102.setText(name);
                      break; 
            case 103: image103.setImage(image);
                      text103.setText(name);
                      break;
            case 104: image104.setImage(image);
                      text104.setText(name);
                      break;    
            case 105: image105.setImage(image);
                      text105.setText(name);
                      break;    
            case 106: image106.setImage(image);
                      text106.setText(name);
                      break;    
            case 107: image107.setImage(image);
                      text107.setText(name);
                      break;    
            case 108: image108.setImage(image);
                      text108.setText(name);
                      break;    
            case 109: image109.setImage(image);
                      text109.setText(name);
                      break;    
            case 110: image110.setImage(image);
                      text110.setText(name);
                      break;    
            case 111: image111.setImage(image);
                      text111.setText(name);
                      break;    
            case 112: image112.setImage(image);
                      text112.setText(name);
                      break;    
            case 113: image113.setImage(image);
                      text113.setText(name);
                      break;    
            case 114: image114.setImage(image);
                      text114.setText(name);
                      break;    
            case 115: image115.setImage(image);
                      text115.setText(name);
                      break;    
            case 116: image116.setImage(image);
                      text116.setText(name);
                      break;    
            case 117: image117.setImage(image);
                      text117.setText(name);
                      break;    
        }
        return true;
    }
    
    public void processFirstFloor()
    {
        application.gotoViewCurrentCustomerFFloor();
    }
    
    public void processSecondFloorA()
    {
       application.gotoViewCurrentCustomersSFloorA();
    }
    
    public void processSecondFloorB()
    {
       application.gotoViewCurrentCustomersSFloorB();
    }
    
    public void processGetDetailsRoom101()
    {
        application.gotoCurrentCustomerDetail(101);
    }
    
    public void processGetDetailsRoom102()
    {
        application.gotoCurrentCustomerDetail(102);
    }
    
    public void processGetDetailsRoom103()
    {
        application.gotoCurrentCustomerDetail(103);
    }
    
    public void processGetDetailsRoom104()
    {
        application.gotoCurrentCustomerDetail(104);
    }
    
    public void processGetDetailsRoom105()
    {
        application.gotoCurrentCustomerDetail(105);
    }
    
    public void processGetDetailsRoom106()
    {
        application.gotoCurrentCustomerDetail(106);
    }
    
    public void processGetDetailsRoom107()
    {
        application.gotoCurrentCustomerDetail(107);
    }
    
    public void processGetDetailsRoom108()
    {
        application.gotoCurrentCustomerDetail(108);
    }
    
    public void processGetDetailsRoom109()
    {
        application.gotoCurrentCustomerDetail(109);
    }
    
    public void processGetDetailsRoom110()
    {
        application.gotoCurrentCustomerDetail(110);
    }
    
    public void processGetDetailsRoom111()
    {
        application.gotoCurrentCustomerDetail(111);
    }
    
    public void processGetDetailsRoom112()
    {
        application.gotoCurrentCustomerDetail(112);
    }
    
    public void processGetDetailsRoom113()
    {
        application.gotoCurrentCustomerDetail(113);
    }
    
    public void processGetDetailsRoom114()
    {
        application.gotoCurrentCustomerDetail(114);
    }
    
    public void processGetDetailsRoom115()
    {
        application.gotoCurrentCustomerDetail(115);
    }
    
    public void processGetDetailsRoom116()
    {
        application.gotoCurrentCustomerDetail(116);
    }
    
    public void processGetDetailsRoom117()
    {
        application.gotoCurrentCustomerDetail(117);
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    
}
