/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Reservationwadvance;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ViewAdvanceBookingController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    @FXML
    private TableView<Reservationwadvance> tablereservationwithadvance;
    
    @FXML
    private TableColumn<Reservationwadvance, String> name;
    
    @FXML
    private TableColumn<Reservationwadvance, Roomtype> roomtype;
    
    @FXML
    private TableColumn<Reservationwadvance, Integer> noofrooms;
    
    @FXML
    private TableColumn<Reservationwadvance, Date> arrivaldate;
    
    @FXML
    private TableColumn<Reservationwadvance, String> note;
    
    @FXML
    private TableColumn<Reservationwadvance, Long> contactno;
    
    @FXML
    private TableColumn<Reservationwadvance, String> city;
    
    @FXML
    private TableColumn<Reservationwadvance, Date> reservationdate;
    
    @FXML
    private TableColumn<Reservationwadvance, Integer> advancepaid;
    
    @FXML
    private TableColumn<Reservationwadvance, Receipts> receiptsno;
    
    @FXML
    private TableColumn<Reservationwadvance, Boolean> action;
    
    ObservableList<Reservationwadvance> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        /**********************************************************************/
        name.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, String>("bookedby"));
        roomtype.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Roomtype>("roomtypeid"));
        noofrooms.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Integer>("noofrooms"));
        arrivaldate.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Date>("arrivaldatetime"));
        note.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, String>("note"));
        contactno.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Long>("contactno"));
        city.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, String>("city"));
        reservationdate.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Date>("currentdate"));
        advancepaid.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Integer>("advancepaid"));
        receiptsno.setCellValueFactory(new PropertyValueFactory<Reservationwadvance, Receipts>("receipts"));
        /**********************************************************************/
        
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Reservationwadvance> thislist = qm.findAll("Reservationwadvance.findAll");
        for(int i = 0; i < thislist.size(); i++)
        {
            finalList.add(thislist.get(i));
        }
        tablereservationwithadvance.setItems(finalList);
        /**********************************************************************/
        
        /**********************************************************************/
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationwadvance, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Reservationwadvance, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       action.setCellFactory(new Callback<TableColumn<Reservationwadvance, Boolean>, TableCell<Reservationwadvance, Boolean>>() 
        {
            @Override public TableCell<Reservationwadvance, Boolean> call(TableColumn<Reservationwadvance, Boolean> personBooleanTableColumn) 
            {
                return new DeleteReservationCell(tablereservationwithadvance);
            }
        }
        );
        /**********************************************************************/
        
    }
    
    public void prcessHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewEntity();
    }
    
    public class DeleteReservationCell extends TableCell<Reservationwadvance, Boolean>
    {
        final Button addButton       = new Button("Delete");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        
        DeleteReservationCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Reservationwadvance thisreservation = (Reservationwadvance) tablereservationwithadvance.getItems().get(selectedIndex);
                    QueryManager qm = QueryManager.getInstance();
                    Integer result = qm.deleteReservationWITHadvanceByOid(thisreservation.getOid());
                    application.gotoViewEntity();
                }
            }
            );
        }
        
        @Override protected void updateItem(Boolean item, boolean empty) 
        {
            super.updateItem(item, empty);
            
            if (!empty) 
            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            }
        }    
    }
    
}
