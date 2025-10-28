/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.math.BigInteger;
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
import lodgeman.lalitman.model.pojo.Reservationwoadvance;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ViewReservationWOAdvanceController implements Initializable 
{
    /*
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<Reservationwoadvance> tablereservationdetails;
    
    @FXML
    private TableColumn<Reservationwoadvance, Date> columnreservationdate;
    
    @FXML
    private TableColumn<Reservationwoadvance, String> columnreservationname;
    
    @FXML
    private TableColumn<Reservationwoadvance, Roomtype> columnreservationroomtype;
    
    @FXML
    private TableColumn<Reservationwoadvance, Integer> columnreservationroomno;
    
    @FXML
    private TableColumn<Reservationwoadvance, Date> columnarrivaldate;
    
    @FXML
    private TableColumn<Reservationwoadvance, Boolean> columnupdate;
    
    @FXML
    private TableColumn<Reservationwoadvance, Boolean> columndelete;
    
    @FXML
    private TableColumn<Reservationwoadvance, String> columnnote;
    
    @FXML
    private TableColumn<Reservationwoadvance, BigInteger> columncontactno;
    
    ObservableList<Reservationwoadvance> finalList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        QueryManager qm = QueryManager.getInstance();
        
        /**********************************************************************/
        columnreservationdate.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, Date>("currentdate"));
        columnreservationname.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, String>("bookedby"));
        columnreservationroomtype.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, Roomtype>("roomtypeid"));
        columnreservationroomno.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, Integer>("noofrooms"));
        columnarrivaldate.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, Date>("arrivaldatetime"));
        columnnote.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, String>("note"));
        columncontactno.setCellValueFactory(new PropertyValueFactory<Reservationwoadvance, BigInteger>("contactno"));
        /**********************************************************************/
        
        List<Reservationwoadvance> thislist = qm.findAll("Reservationwoadvance.findAll");
        for(int i = 0; i < thislist.size(); i++)
        {
            finalList.add(thislist.get(i));
        }
        tablereservationdetails.setItems(finalList);
        
        /**********************************************************************/
        columnupdate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationwoadvance, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Reservationwoadvance, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       columnupdate.setCellFactory(new Callback<TableColumn<Reservationwoadvance, Boolean>, TableCell<Reservationwoadvance, Boolean>>() 
        {
            @Override public TableCell<Reservationwoadvance, Boolean> call(TableColumn<Reservationwoadvance, Boolean> personBooleanTableColumn) 
            {
                return new UpdateReservationCell(tablereservationdetails);
            }
        }
        );
       /***********************************************************************/
       
       /***********************************************************************/
       columndelete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationwoadvance, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Reservationwoadvance, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       columndelete.setCellFactory(new Callback<TableColumn<Reservationwoadvance, Boolean>, TableCell<Reservationwoadvance, Boolean>>() 
        {
            @Override public TableCell<Reservationwoadvance, Boolean> call(TableColumn<Reservationwoadvance, Boolean> personBooleanTableColumn) 
            {
                return new DeleteReservationCell(tablereservationdetails);
            }
        }
        );
       /***********************************************************************/
       
    }
    
    public class DeleteReservationCell extends TableCell<Reservationwoadvance, Boolean>
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
                    QueryManager qm = QueryManager.getInstance();
                    Integer selectedIndex = getTableRow().getIndex();
                    Reservationwoadvance reservationwoadvance = (Reservationwoadvance) tablereservationdetails.getItems().get(selectedIndex);
                    qm.deleteReservationwoadvanceByOid(reservationwoadvance.getOid());
                    application.gotoHome();
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
    
    public class UpdateReservationCell extends TableCell<Reservationwoadvance, Boolean>
    {
        final Button addButton       = new Button("Update");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        
        UpdateReservationCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    application.gotoHome();
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
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoViewEntity();
    }
    
}
