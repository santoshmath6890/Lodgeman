/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.File;
import java.net.URL;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lodgeman.lalitman.controller.related.AutoFillTextBox;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class FXMLController implements Initializable 
{
    private LodgeMan application;
    ObservableList<String> data = FXCollections.observableArrayList("abc xyz","cde","hij","klm","ccd");
    
    @FXML
    private AutoFillTextBox autoFill;
    
    @FXML
    private TextField guestRoomno;
    
    @FXML
    private TextField guestBookingFromDate;
    
    @FXML
    private TextField guestBookingToDate;
    
    @FXML
    private TextField guestCustomerID;
    
    @FXML
    private TextField guestBookedBy;
    
    @FXML
    private TextField guestName;
    
    @FXML
    private TextField guestContactNo;
    
    @FXML
    private TextArea guestAddress;
    
    @FXML
    private TextField guestCity;
    
    @FXML
    private TextField guestState;
    
    @FXML
    private TextField IdProofType;
    @FXML
    private Label LabelRoomNo;
    @FXML
    private Label LabelFromDate;
    @FXML
    private Label LabelToDate;
    @FXML
    private Label LabelCustomerId;
    @FXML
    private Label LabelGuestName;
    @FXML
    private Label LabelContactNo;
    @FXML
    private Label LabelAddress;
    @FXML
    private Label LabelCity;        
    @FXML
    private Label LabelState;   
    @FXML
    private Button ButtonGetDetails; 
    
    @FXML
    private ImageView guestImage;
    
    List<Bookings> currentBookingList = null;
    HashMap<String, Integer> point = new HashMap<>();
    
    /*
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        QueryManager qm = QueryManager.getInstance();
        List<Bookings> currentbooks = qm.findCurrentBookings();
        this.currentBookingList = currentbooks;
        
        for(int i = 0; i < currentbooks.size(); i++)
        {
            System.out.println(currentbooks.get(i));
            String fullname = "" + currentbooks.get(i).getCustomerid().getName() + " " + currentbooks.get(i).getCustomerid().getLastname() + "" ;
            point.put(fullname, i);
            autoFill.addData(fullname);
        }
        //autoFill.setData(data);
    }    

    public void setApp(LodgeMan aThis) 
    {
         this.application = aThis;
         guestRoomno.setEditable(false);
         guestBookingFromDate.setEditable(false);
         guestBookingToDate.setEditable(false);
         guestName.setEditable(false);
         guestContactNo.setEditable(false);
         guestAddress.setEditable(false);
         guestCity.setEditable(false);
         guestState.setEditable(false);
         guestCustomerID.setEditable(false);
         /*********************************Visibility*************************/
         LabelRoomNo.setVisible(false);
         LabelFromDate.setVisible(false);
         LabelToDate.setVisible(false);
         LabelCustomerId.setVisible(false);
         LabelGuestName.setVisible(false);
         LabelContactNo.setVisible(false);
         LabelAddress.setVisible(false);
         LabelCity.setVisible(false);
         LabelState.setVisible(false);
         
         guestRoomno.setVisible(false);
         guestBookingFromDate.setVisible(false);
         guestBookingToDate.setVisible(false);
         guestCustomerID.setVisible(false);
         guestName.setVisible(false);
         guestContactNo.setVisible(false);
         guestAddress.setVisible(false);
         guestCity.setVisible(false);
         guestState.setVisible(false);
         /*********************************************************************/
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processSearch()
    {
        //guestImage = null ;
        String selectedGuestName = autoFill.getText();
        Integer impIndex = point.get(selectedGuestName);
        
        String room = "" + currentBookingList.get(impIndex).getRoomno().toString();
        /*************************************************************************/
         LabelRoomNo.setVisible(true);
         LabelFromDate.setVisible(true);
         LabelToDate.setVisible(true);
         LabelCustomerId.setVisible(true);
         LabelGuestName.setVisible(true);
         LabelContactNo.setVisible(true);
         LabelAddress.setVisible(true);
         LabelCity.setVisible(true);
         LabelState.setVisible(true);
         ButtonGetDetails.setVisible(true);
         guestRoomno.setVisible(true);
         guestBookingFromDate.setVisible(true);
         guestBookingToDate.setVisible(true);
         guestCustomerID.setVisible(true);
         guestName.setVisible(true);
         guestContactNo.setVisible(true);
         guestAddress.setVisible(true);
         guestCity.setVisible(true);
         guestState.setVisible(true);
         /*************************************************************************/
        
        guestRoomno.setText(room);
        
        guestBookingFromDate.setText("" + currentBookingList.get(impIndex).getFromdate());
        guestBookingToDate.setText("" + currentBookingList.get(impIndex).getFromto());
        guestCustomerID.setText("" + currentBookingList.get(impIndex).getCustomerid().getOid());
        guestContactNo.setText("" + currentBookingList.get(impIndex).getCustomerid().getContactno());
        guestAddress.setText("" + currentBookingList.get(impIndex).getCustomerid().getAddress());
        guestCity.setText("" + currentBookingList.get(impIndex).getCustomerid().getCity());
        guestState.setText("" + currentBookingList.get(impIndex).getCustomerid().getState());
        guestName.setText("" + currentBookingList.get(impIndex).getCustomerid().getName() + " " + currentBookingList.get(impIndex).getCustomerid().getLastname());
        
        try
        {
            String pp = currentBookingList.get(impIndex).getCustomerid().getImage();
            File file = new File(pp);
            Image ima = new Image(file.toURI().toString());
            guestImage.setImage(ima);
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }
    
}
