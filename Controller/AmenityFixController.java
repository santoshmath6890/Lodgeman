/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
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
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Bookedroomamenities;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.AppInfo;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */

public class AmenityFixController implements Initializable 
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
    
    private Integer oldSingleB;
    private Integer oldDoubleB;
    private Integer oldTowel;
    private Integer oldPillow;
    private Integer oldBlanket;
    
    private Amenityroom thisamenityroom;
    
    Items singleBed = new Items();        
    Items doubleBed = new Items();        
    Items pillow = new Items();        
    Items blanket = new Items();        
    Items towel = new Items(); 
    
    @FXML
    private ComboBox<String> singleOption;
    
    @FXML
    private ComboBox<String> doubleOption;
    
    @FXML
    private ComboBox<String> pillowOption;
    
    @FXML
    private ComboBox<String> blanketOption; 
    
    @FXML
    private ComboBox<String> towelOption;
    
    @FXML
    private Label warningreadyroom;
    
    Bookedroomamenities bookedroomamenitiesG; 
    
    ObservableList<String> thislist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        List<Items> allItems = qm.findAll("Items.findAll");
        
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getItemName().contains("Single"))
            {
                singleBed = allItems.get(i);
                System.out.println("SIngle:" + singleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Double"))
            {
                doubleBed = allItems.get(i);
                System.out.println("Double:" + doubleBed.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Pillow"))
            {
                pillow = allItems.get(i);
                System.out.println("pillow:" + pillow.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Blanket"))
            {
                blanket = allItems.get(i);
                System.out.println("Blanket:" + blanket.getItemName());
                //continue;
            }
            if(allItems.get(i).getItemName().contains("Towel"))
            {
                towel = allItems.get(i);
                System.out.println("towel:" + towel.getItemName() );
                //continue;
            }
        }
        
        /**********************************************************************/
        thislist.add("Store");
        thislist.add("Dirty");
        
        singleOption.setItems(thislist);
        singleOption.setValue("Dirty");
        
        doubleOption.setItems(thislist);
        doubleOption.setValue("Dirty");
        
        pillowOption.setItems(thislist);
        pillowOption.setValue("Dirty");
        
        towelOption.setItems(thislist);
        towelOption.setValue("Dirty");
        
        blanketOption.setItems(thislist);
        blanketOption.setValue("Dirty");
        /**********************************************************************/
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
            oldSingleB = thisbookedroomamenities.getSinglebedsheet();
            
            doubleBedQty.setText("" + thisbookedroomamenities.getDoublebedsheet());
            oldDoubleB = thisbookedroomamenities.getDoublebedsheet();
            
            blanketqty.setText("" + thisbookedroomamenities.getBlanket());
            oldBlanket = thisbookedroomamenities.getBlanket();
            
            pillowqty.setText("" + thisbookedroomamenities.getPillow());
            oldPillow = thisbookedroomamenities.getPillow();
            
            towelqty.setText("" + thisbookedroomamenities.getTowel());
            oldTowel = thisbookedroomamenities.getTowel();
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
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoLaundry();
    }
    
    public void processAddSingleBed()
    {
        Integer qty = Integer.parseInt(singleBedQty.getText());
        qty = qty + 1;
        singleBedQty.setText("" + qty);
    }
    
    public void processMinusSingleBed()
    {
        Integer qty = Integer.parseInt(singleBedQty.getText());
        qty = qty - 1;
        
        if(qty >= 0)
        {
            singleBedQty.setText("" + qty);
        }    
    }
    
    public void processAddDoubleBed()
    {
        Integer qty = Integer.parseInt(doubleBedQty.getText());
        qty = qty + 1;
        doubleBedQty.setText("" + qty);
    }
    
    public void processMinusDoubleBed()
    {
        Integer qty = Integer.parseInt(doubleBedQty.getText());
        qty = qty - 1;
        
        if(qty >= 0)
        {
            doubleBedQty.setText("" + qty);
        }    
    }
    
    public void processAddBlanket()
    {
        Integer qty = Integer.parseInt(blanketqty.getText());
        qty = qty + 1;
        blanketqty.setText("" + qty);
    }
    
    public void processMinusBlanket()
    {
        Integer qty = Integer.parseInt(blanketqty.getText());
        qty = qty - 1;
        
        if(qty >= 0)
        {
            blanketqty.setText("" + qty);
        }    
    }
    
    public void processAddPillow()
    {
        Integer qty = Integer.parseInt(pillowqty.getText());
        qty = qty + 1;
        pillowqty.setText("" + qty);
    }
    
    public void processMinusPillow()
    {
        Integer qty = Integer.parseInt(pillowqty.getText());
        qty = qty - 1;
        
        if(qty >= 0)
        {
            pillowqty.setText("" + qty);
        }
    }
    
    public void prcessAddTowel()
    {
        Integer qty = Integer.parseInt(towelqty.getText());
        qty = qty + 1;
        towelqty.setText("" + qty);
    }
    
    public void processMinusTowel()
    {
        Integer qty = Integer.parseInt(towelqty.getText());
        qty = qty - 1;
        
        if(qty >= 0)
        {
            towelqty.setText("" + qty);
        }
    }
    
    public void processUpdate()
    {
        Integer newSingleB = Integer.parseInt(singleBedQty.getText());
        Integer newDoubleB = Integer.parseInt(doubleBedQty.getText());
        Integer newPillow = Integer.parseInt(pillowqty.getText());
        Integer newBlanket  = Integer.parseInt(blanketqty.getText());
        Integer newTowel = Integer.parseInt(towelqty.getText());
        
        QueryManager qm = QueryManager.getInstance();

        /**********************************************************************/
        if(oldSingleB > newSingleB)
        {
            Integer diff = oldSingleB - newSingleB;
            
            if(singleOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(singleBed);
                Integer qtyIndirty = itemdirty.getQty();
                Integer newAmt = qtyIndirty + diff;
                
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(singleBed, newAmt);
                }
            }   
            else if(singleOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(singleBed);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(singleBed, newAmt);
                }
            }
            
            Iteminuse singleBinuse = qm.findIteminuseByItem(singleBed);
            Integer qtyInUse = singleBinuse.getQty();
            Integer newAmt1 = qtyInUse - diff;
            
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminUse(singleBed, newAmt1);
            }
        }
        else if (oldSingleB < newSingleB)
        {
            Integer diff = newSingleB - oldSingleB;
            Iteminstore iteminstore = qm.findIteminstoreByItem(singleBed);
            Integer qtyInStore = iteminstore.getQty();
            Integer newAmt1 = qtyInStore - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminStore(singleBed, newAmt1);
            }
            
            Iteminuse iteminuse = qm.findIteminuseByItem(singleBed);
            Integer qtyiteminuse = iteminuse.getQty();
            Integer newAmt2 = qtyiteminuse + diff;
            if(newAmt2 >= 0)
            {
                Integer result2 = qm.updateQtyInIteminUse(singleBed, newAmt2);
            }
        }
         /*********************************************************************/
         if(oldDoubleB > newDoubleB)
         {
            Integer diff = oldDoubleB - newDoubleB;
            
            if(doubleOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(doubleBed);
                Integer qtyInStore = itemdirty.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(doubleBed, newAmt);
                }
            }   
            else if(doubleOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(doubleBed);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(doubleBed, newAmt);
                }
            }
            
            Iteminuse singleBinuse = qm.findIteminuseByItem(doubleBed);
            Integer qtyInUse = singleBinuse.getQty();
            Integer newAmt1 = qtyInUse - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminUse(doubleBed, newAmt1);
            }
        }
        else if (oldDoubleB < newDoubleB)
        {
            Integer diff = newDoubleB - oldDoubleB;
            
            Iteminstore iteminstore = qm.findIteminstoreByItem(doubleBed);
            Integer qtyInStore = iteminstore.getQty();
            Integer newAmt1 = qtyInStore - diff;
            
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminStore(doubleBed, newAmt1);
            }
            
            Iteminuse iteminuse = qm.findIteminuseByItem(doubleBed);
            Integer qtyiteminuse = iteminuse.getQty();
            Integer newAmt2 = qtyiteminuse + diff;
            
            if(newAmt2 >= 0)
            {
                Integer result2 = qm.updateQtyInIteminUse(doubleBed, newAmt2);
            }
        }
         /*********************************************************************/
         if(oldPillow > newPillow)
         {
            Integer diff = oldPillow - newPillow;
            
            if(pillowOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(pillow);
                Integer qtyInDirty = itemdirty.getQty();
                Integer newAmt = qtyInDirty + diff;
                
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(pillow, newAmt);
                }
            }   
            else if(pillowOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(pillow);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(pillow, newAmt);
                }
            }
            
            Iteminuse singleBinuse = qm.findIteminuseByItem(pillow);
            Integer qtyInUse = singleBinuse.getQty();
            Integer newAmt1 = qtyInUse - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminUse(pillow, newAmt1);
            }
        }
        else if (oldPillow < newPillow)
        {
            Integer diff = newPillow - oldPillow;
            
            if(pillowOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(pillow);
                Integer qtyInDirty = itemdirty.getQty();
                Integer newAmt = qtyInDirty + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(pillow, newAmt);
                }
            }   
            else if(pillowOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(pillow);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(pillow, newAmt);
                }
            }
            
            Iteminuse iteminuse = qm.findIteminuseByItem(pillow);
            Integer qtyiteminuse = iteminuse.getQty();
            Integer newAmt2 = qtyiteminuse + diff;
            if(newAmt2 >= 0)
            {
                Integer result2 = qm.updateQtyInIteminUse(pillow, newAmt2);
            }
        }
         /*********************************************************************/
         if(oldTowel > newTowel)
         {
            Integer diff = oldTowel - newTowel;
            
            if(towelOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(towel);
                Integer qtyInStore = itemdirty.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(towel, newAmt);
                }
            }   
            else if(towelOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(towel);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(towel, newAmt);
                }
            }
            
            Iteminuse singleBinuse = qm.findIteminuseByItem(towel);
            Integer qtyInUse = singleBinuse.getQty();
            Integer newAmt1 = qtyInUse - diff;
            if(newAmt1 >=0 )
            {
                Integer result1 = qm.updateQtyInIteminUse(towel, newAmt1);
            }
        }
        else if (oldTowel < newTowel)
        {
            Integer diff = newTowel - oldTowel;
            
            Iteminstore iteminstore = qm.findIteminstoreByItem(towel);
            Integer qtyInStore = iteminstore.getQty();
            Integer newAmt1 = qtyInStore - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminStore(towel, newAmt1);
            }
            
            Iteminuse iteminuse = qm.findIteminuseByItem(towel);
            Integer qtyiteminuse = iteminuse.getQty();
            Integer newAmt2 = qtyiteminuse + diff;
            if(newAmt2 >= 0)
            {
                Integer result2 = qm.updateQtyInIteminUse(towel, newAmt2);
            }
        }
         /*********************************************************************/
         if(oldBlanket > newBlanket)
        {
            Integer diff = oldBlanket - newBlanket;
            
            if(blanketOption.getValue().equalsIgnoreCase("Dirty"))
            {
                Itemdirty itemdirty = qm.findItemdirtyByItem(blanket);
                Integer qtyInStore = itemdirty.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInItemDirty(blanket, newAmt);
                }
            }   
            else if(blanketOption.getValue().equalsIgnoreCase("Store"))
            {
                Iteminstore iteminstore = qm.findIteminstoreByItem(blanket);
                Integer qtyInStore = iteminstore.getQty();
                Integer newAmt = qtyInStore + diff;
                if(newAmt >= 0)
                {
                    Integer result = qm.updateQtyInIteminStore(blanket, newAmt);
                }
            }
            
            Iteminuse singleBinuse = qm.findIteminuseByItem(blanket);
            Integer qtyInUse = singleBinuse.getQty();
            Integer newAmt1 = qtyInUse - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminUse(blanket, newAmt1);
            }
        }
        else if (oldBlanket < newBlanket)
        {
            Integer diff = newBlanket - oldBlanket;
            
            Iteminstore iteminstore = qm.findIteminstoreByItem(blanket);
            Integer qtyInStore = iteminstore.getQty();
            Integer newAmt1 = qtyInStore - diff;
            if(newAmt1 >= 0)
            {
                Integer result1 = qm.updateQtyInIteminStore(blanket, newAmt1);
            }
            
            Iteminuse iteminuse = qm.findIteminuseByItem(blanket);
            Integer qtyiteminuse = iteminuse.getQty();
            Integer newAmt2 = qtyiteminuse + diff;
            if(newAmt2 >= 0)
            {
                Integer result2 = qm.updateQtyInIteminUse(blanket, newAmt2);
            }
        }
         /*********************************************************************/
        qm.updateModifyItems(newSingleB, newDoubleB, newPillow, newTowel, newBlanket, bookedroomamenitiesG.getOid());
        
        application.gotoLaundry();
    }
}
