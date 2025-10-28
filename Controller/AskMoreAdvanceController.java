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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lodgeman.lalitman.controller.reports.AskAdvanceReport;
import lodgeman.lalitman.controller.table.AskAdvance;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.business.CheckoutCalculation;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.business.LodgeConfig.LodgeConfig;
import lodgeman.lalitman.model.pojo.Askadvance;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class AskMoreAdvanceController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    private TableView<AskAdvance> tAskAdvanceTable;
    
    @FXML
    private TableColumn<AskAdvance, Integer> mRoomno;
    
    @FXML
    private TableColumn<AskAdvance, String> mCustomerName;
    
    @FXML
    private TableColumn<AskAdvance, Date> mCheckinDate;
    
    @FXML
    private TableColumn<AskAdvance, Integer> mAdvancePaid;
    
    @FXML
    private TableColumn<AskAdvance, Integer> mRentAmount;
    
    @FXML
    private TableColumn<AskAdvance, Integer> mDifference;
    
    ObservableList<AskAdvance> askadvancelist = FXCollections.observableArrayList();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        tablemapping();
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        Date CurrentDate = new Date();
        
        
        List<Bookings> currentBookingsList = qm.findCurrentBookings();
        for(int i = 0; i < currentBookingsList.size();i++ )
        {
            
            Guests guestDetails = currentBookingsList.get(i).getCustomerid();
            Bookings thisBooking = currentBookingsList.get(i);
            CheckoutCalculation thisCheckout = new CheckoutCalculation(thisBooking);
            
            
            /**** Advance Receipts paid ****/
            Integer totalAdvance = 0;
            totalAdvance = thisCheckout.getTotalAdvancePaid();
            /********************************/
        
            
            /**** Booking Add ons List. E.g. Extra bed ****/
            Double totalOther = 0.0;
            totalOther = thisCheckout.getBookingAddonTotal();
            /*********************************************/
        
            double grandtotal = thisCheckout.getGrandTotalBill();
            int grandtotal1 = (int) grandtotal ;
        
            
            /**** receive or refund ****/
            int diff = totalAdvance - grandtotal1;
            if (diff < 0) 
            {
                diff = (diff) * (-1);
                Integer roomno = thisBooking.getRoomno().getOid();
                String guestname = thisBooking.getCustomerid().getName() + "  " + thisBooking.getCustomerid().getLastname();
                Integer advancepaid = totalAdvance;
                
                AskAdvance askadvance = new AskAdvance(roomno,guestname,thisBooking.getFromdate(),advancepaid, grandtotal1,diff);
                askadvancelist.add(askadvance);
            }
            /***************************/
            
        }
        
        
        tAskAdvanceTable.setItems(askadvancelist);
    }
    
    public void tablemapping()
    {
        mRoomno.setCellValueFactory(new PropertyValueFactory<AskAdvance, Integer>("roomno"));
        mCustomerName.setCellValueFactory(new PropertyValueFactory<AskAdvance, String>("customerName"));
        mCheckinDate.setCellValueFactory(new PropertyValueFactory<AskAdvance, Date>("checkindate"));
        mAdvancePaid.setCellValueFactory(new PropertyValueFactory<AskAdvance, Integer>("advancepaid"));
        mRentAmount.setCellValueFactory(new PropertyValueFactory<AskAdvance, Integer>("rentamount"));
        mDifference.setCellValueFactory(new PropertyValueFactory<AskAdvance, Integer>("difference"));
    }
    
    public void processPrint() throws JRException, IOException
    {
        Integer transID = 0;
        QueryManager qm = QueryManager.getInstance();
        try
        {
            Askadvance lasttransactionid = qm.findLastTransactionAskadvance();
            transID = lasttransactionid.getTransactionid() + 1;
        }
        catch(Exception ex)
        {
            System.out.println("I am here!");
        }
        for(int i = 0; i < askadvancelist.size(); i++)
        {
            if(LodgeConfig.newAskadvance(transID, askadvancelist.get(i).getRoomno(),askadvancelist.get(i).getCustomerName(),askadvancelist.get(i).getCheckindate(), askadvancelist.get(i).getAdvancepaid(),askadvancelist.get(i).getRentamount(), askadvancelist.get(i).getDifference()) == true)
            {
                System.out.println("Yayy!");
            }
        }
        
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        String path1  = path.replaceFirst("c", "C");
        System.out.println("my path" + path1);
        String filename = path1 + "/" + "AskAdvance" + transID + ".pdf";
        AskAdvanceReport.printsessionreport(transID, filename);
    }
            
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoConfig();
    }

}
