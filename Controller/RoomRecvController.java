/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.NoResultException;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookedroomamenities;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomcleaners;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomRecvController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    private TextField inputRoomno;
    
    @FXML
    private TextField singleBedQty;
    
    @FXML
    private TextField doubleBedQty;
    
    @FXML
    private TextField pillowqty;
    
    @FXML
    private TextField towelqty;
    
    @FXML
    private TextField blanketqty;
    
    @FXML
    private Label singleinstore;
    
    @FXML
    private Label doubleinstore;
    
    @FXML
    private Label pillowinstore;
    
    @FXML
    private Label blanketinstore;
    
    @FXML
    private Label towelinstore;
    
    @FXML
    private Label warningreadyroom;
    
    @FXML
    private ComboBox<Roomcleaners> allRoomcleaners;
    ObservableList<Roomcleaners> allRoomcleanerslistfinal = FXCollections.observableArrayList();
    
    Items singleBed = new Items();        
    Items doubleBed = new Items();        
    Items pillow = new Items();        
    Items blanket = new Items();        
    Items towel = new Items(); 
    
    Amenityroom amenityroomG;
    Room roomG;
    Bookedroomamenities bookedroomamenitiesG;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Roomcleaners> allroomcleanerslist = qm.findAll("Roomcleaners.findAll");
        for(int i = 0; i< allroomcleanerslist.size(); i++)
        {
            allRoomcleanerslistfinal.add(allroomcleanerslist.get(i));
        }
        allRoomcleaners.setItems(allRoomcleanerslistfinal);
        /**********************************************************************/
    }
     
    public void processHome()
    {
        this.application.gotoHome();
    }
    
    public void processBack()
    {
        this.application.gotoLaundry();
    }
    public boolean  validate()
    {
    
      if(inputRoomno.getText().isEmpty() == true)
      {
      Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage() ,
                        "Enter Valid Room Number");
      return false;
      }
      return true;
    }
    public void processSearch()
    {
        if(validate())
        {
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
       Integer roomnoint;
        try
        {
         roomnoint = Integer.parseInt(inputRoomno.getText());
        }
        catch(Exception e)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          inputRoomno.clear();
          inputRoomno.requestFocus();
                 return;
        }
        
        Room thisroom  = Room.getRoomByOid(roomnoint);
        String userRoom = inputRoomno.getText();
        Integer userRoomInt;
         if (inputRoomno == null) 
        {
          Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          inputRoomno.clear();
            return;
         }
        try
        {
         userRoomInt = Integer.parseInt(userRoom);
        }
        catch(Exception e)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          inputRoomno.clear();
          inputRoomno.requestFocus();
                 return;
        }
        //Room thisroom = new Room();
        thisroom = thisroom.getRoomByOid(userRoomInt);
        if (thisroom == null)
        {
             Dialogs.showErrorDialog(AppInfo.getInstance().getmPrimaryStage(),
                         "Room number is invalid, please enter correct room number");
          inputRoomno.clear();
                 return;
        
        }
        try
        {
            Bookedroomamenities thisbookedroomamenities = qm.findBookedRoomAmenitiesByRoomnoAndStatus(thisroom, true);
            this.bookedroomamenitiesG = thisbookedroomamenities;
            
            /******************************************************************/
            singleBedQty.setText("" + thisbookedroomamenities.getSinglebedsheet());
            doubleBedQty.setText("" + thisbookedroomamenities.getDoublebedsheet());
            blanketqty.setText("" + thisbookedroomamenities.getBlanket());
            pillowqty.setText("" + thisbookedroomamenities.getPillow());
            towelqty.setText("" + thisbookedroomamenities.getTowel());
            /******************************************************************/
            
            /******************************************************************/
            List<Items> allItems = qm.findAll("Items.findAll");

            for (int i = 0; i < allItems.size(); i++) 
            {
                if (allItems.get(i).getItemName().contains("Single")) 
                {
                    singleBed = allItems.get(i);
                    System.out.println("SIngle:" + singleBed.getItemName());
                    //continue;
                }
                if (allItems.get(i).getItemName().contains("Double")) 
                {
                    doubleBed = allItems.get(i);
                    System.out.println("Double:" + doubleBed.getItemName());
                    //continue;
                }
                if (allItems.get(i).getItemName().contains("Pillow")) 
                {
                    pillow = allItems.get(i);
                    System.out.println("pillow:" + pillow.getItemName());
                    //continue;
                }
                if (allItems.get(i).getItemName().contains("Blanket")) 
                {
                    blanket = allItems.get(i);
                    System.out.println("Blanket:" + blanket.getItemName());
                    //continue;
                }
                if (allItems.get(i).getItemName().contains("Towel")) 
                {
                    towel = allItems.get(i);
                    System.out.println("towel:" + towel.getItemName());
                    //continue;
                }
            }
            /******************************************************************/
        }
        catch(NoResultException ex)
        {
            warningreadyroom.setText("Room is not ready.");
        }
    }
    }
    public void processSingleBedAdd()
    {
        Integer tempsingleBedQty = Integer.parseInt(singleBedQty.getText());
        tempsingleBedQty++;
        singleBedQty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(singleinstore.getText());
        tempsingleinstore--;
        singleinstore.setText("" + tempsingleinstore);
        */ 
    }
    
    public void processSingleBedMinus()
    {
        Integer tempsingleBedQty = Integer.parseInt(singleBedQty.getText());
        tempsingleBedQty--;
        if(tempsingleBedQty >= 0)
        {
        singleBedQty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(singleinstore.getText());
        tempsingleinstore++;
        singleinstore.setText("" + tempsingleinstore);
        */ 
        }
    }
    
    public void processDoubleBedAdd()
    {
        Integer tempsingleBedQty = Integer.parseInt(doubleBedQty.getText());
        tempsingleBedQty++;
        doubleBedQty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(doubleinstore.getText());
        tempsingleinstore--;
        doubleinstore.setText("" + tempsingleinstore);
        */ 
    }
    
    public void processDoubleBedMinus()
    {
        Integer tempsingleBedQty = Integer.parseInt(doubleBedQty.getText());
        tempsingleBedQty--;
        if(tempsingleBedQty >= 0)
        {
        doubleBedQty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(doubleinstore.getText());
        tempsingleinstore++;
        doubleinstore.setText("" + tempsingleinstore);
        */ 
        }
    }
    
    public void processPillowAdd()
    {
        Integer tempsingleBedQty = Integer.parseInt(pillowqty.getText());
        tempsingleBedQty++;
        pillowqty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(pillowinstore.getText());
        tempsingleinstore--;
        pillowinstore.setText("" + tempsingleinstore);
        */ 
    }
    
    public void  processPillowMinus()
    {
        Integer tempsingleBedQty = Integer.parseInt(pillowqty.getText());
        tempsingleBedQty--;
        if(tempsingleBedQty >= 0)
        {
        pillowqty.setText("" + tempsingleBedQty);
        
        /*
        Integer tempsingleinstore = Integer.parseInt(pillowinstore.getText());
        tempsingleinstore++;
        pillowinstore.setText("" + tempsingleinstore);
        */ 
        }
    }
    
    public void processTowelAdd()
    {
        Integer tempsingleBedQty = Integer.parseInt(towelqty.getText());
        tempsingleBedQty++;
        towelqty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(towelinstore.getText());
        tempsingleinstore--;
        towelinstore.setText("" + tempsingleinstore);
        */ 
    }
    
    public void processTowelMinus()
    {
        Integer tempsingleBedQty = Integer.parseInt(towelqty.getText());
        tempsingleBedQty--;
        
        if(tempsingleBedQty >= 0)
        {
        towelqty.setText("" + tempsingleBedQty);
        
        /*
        Integer tempsingleinstore = Integer.parseInt(towelinstore.getText());
        tempsingleinstore++;
        towelinstore.setText("" + tempsingleinstore);
        */ 
        }
    }
    
    public void processBlanketAdd()
    {
        Integer tempsingleBedQty = Integer.parseInt(blanketqty.getText());
        tempsingleBedQty++;
        blanketqty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(blanketinstore.getText());
        tempsingleinstore--;
        blanketinstore.setText("" + tempsingleinstore);
        */ 
    }
    
    public void processBlanketMinus()
    {
        Integer tempsingleBedQty = Integer.parseInt(blanketqty.getText());
        tempsingleBedQty--;
        if(tempsingleBedQty >= 0)
        {
        blanketqty.setText("" + tempsingleBedQty);
        /*
        Integer tempsingleinstore = Integer.parseInt(blanketinstore.getText());
        tempsingleinstore++;
        blanketinstore.setText("" + tempsingleinstore);
        */ 
        }
    }
    
    public void processUpdate()
    {
        /**********************************************************************/
        Integer finalsingleqty = Integer.parseInt(singleBedQty.getText());
        Integer finaldoubleqty = Integer.parseInt(doubleBedQty.getText());
        Integer finalpillowqty = Integer.parseInt(pillowqty.getText());
        Integer finalblanketqty = Integer.parseInt(blanketqty.getText());
        Integer finaltowelwty = Integer.parseInt(towelqty.getText());
        /**********************************************************************/
        
        QueryManager qm = QueryManager.getInstance();
        //Bookings thisbooking = qm.findCurrentRoomBookings(roomG);
         Bookings thisbooking = qm.findBookingByOid(200);
        Date thisdate = new Date();
        Roomcleaners thisroomcleaners = allRoomcleaners.getValue();
        Integer result = qm.updateBookedRoomAmenitiesRecvd(finalsingleqty, finaldoubleqty, finalpillowqty, finaltowelwty, finalblanketqty, thisdate, thisroomcleaners, bookedroomamenitiesG.getOid(), false);
        
        //Laundry mgmt.
        /**********************************************************************/
        Iteminuse singleiteminuse = qm.findIteminuseByItem(singleBed);
        Integer singlevalue = singleiteminuse.getQty() - finalsingleqty;
        qm.updateQtyInIteminUse(singleBed, singlevalue);
        
        Itemdirty singleindirty = qm.findItemdirtyByItem(singleBed);
        Integer singlevalue1 = singleindirty.getQty() + finalsingleqty;
        qm.updateQtyInItemDirty(singleBed, singlevalue1);
        /**********************************************************************/
        
        /**********************************************************************/
        Iteminuse doubleiteminuse = qm.findIteminuseByItem(doubleBed);
        Integer doublevalue = doubleiteminuse.getQty() - finaldoubleqty;
        qm.updateQtyInIteminUse(doubleBed, doublevalue);
        
        Itemdirty doubleindirty = qm.findItemdirtyByItem(doubleBed);
        Integer doublevalue1 = doubleindirty.getQty() + finaldoubleqty;
        qm.updateQtyInItemDirty(doubleBed, doublevalue1);
        /**********************************************************************/
        
        /**********************************************************************/
        Iteminuse blanketiteminuse = qm.findIteminuseByItem(blanket);
        Integer blanketvalue = blanketiteminuse.getQty() - finalblanketqty;
        qm.updateQtyInIteminUse(blanket, blanketvalue);
        
        Itemdirty blanketindirty = qm.findItemdirtyByItem(blanket);
        Integer blanketvalue1 = blanketindirty.getQty() + finalblanketqty;
        qm.updateQtyInItemDirty(doubleBed, blanketvalue1);
        /**********************************************************************/
        
        /**********************************************************************/
        Iteminuse pillowiteminuse = qm.findIteminuseByItem(pillow);
        Integer pillowvalue = pillowiteminuse.getQty() - finalpillowqty;
        qm.updateQtyInIteminUse(pillow, pillowvalue);
        
        Itemdirty pillowindirty = qm.findItemdirtyByItem(pillow);
        Integer pillowvalue1 = pillowindirty.getQty() + finalpillowqty;
        qm.updateQtyInItemDirty(doubleBed, pillowvalue1);
        /**********************************************************************/
        
        /**********************************************************************/
        Iteminuse toweliteminuse = qm.findIteminuseByItem(towel);
        Integer towelvalue = toweliteminuse.getQty() - finaltowelwty;
        qm.updateQtyInIteminUse(towel, towelvalue);
        
        Itemdirty towelindirty = qm.findItemdirtyByItem(towel);
        Integer towelvalue1 = towelindirty.getQty() + finaltowelwty;
        qm.updateQtyInItemDirty(doubleBed, towelvalue1);
        /**********************************************************************/
        
        application.gotoLaundry();
    }   
}
