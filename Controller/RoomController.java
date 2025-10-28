/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import com.sun.javafx.scene.control.behavior.TableCellBehavior;
import java.net.URL;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class RoomController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private TableView<Room> TableRoom;
    
    @FXML
    private TableColumn<Room, Integer> ColumnRoomno;
    
    @FXML
    private TableColumn<Room, Integer> ColumnFloor;
    
    @FXML
    private TableColumn<Room, Integer> ColumnRoomType;
    
    @FXML
    private TableColumn<Room, Boolean> ColumnAction;
    
    ObservableList<Room> roomolist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    

    public void setApp(LodgeMan application) 
    {
        this.application = application;
        ColumnRoomno.setCellValueFactory(new PropertyValueFactory<Room, Integer>("oid"));
        ColumnFloor.setCellValueFactory(new PropertyValueFactory<Room, Integer>("floorno"));
        ColumnRoomType.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomtypeid"));
        
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Room> allRoom = qm.findAll("Room.findAll");
        for(int i = 0; i < allRoom.size(); i++)
        {
            roomolist.add(allRoom.get(i));
        }
        TableRoom.setItems(roomolist);
        /**********************************************************************/
        
        /**********************************************************************/
        ColumnAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Room, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       ColumnAction.setCellFactory(new Callback<TableColumn<Room, Boolean>, TableCell<Room, Boolean>>() 
        {
            @Override public TableCell<Room, Boolean> call(TableColumn<Room, Boolean> personBooleanTableColumn) 
            {
                return new UpdateRoomCell(TableRoom);
            }
        }
        );
        /**********************************************************************/
    }
    
    public class UpdateRoomCell extends TableCell<Room, Boolean>
    {
        final Button addButton       = new Button("Update");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        
        UpdateRoomCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Room thisroom = (Room) TableRoom.getItems().get(selectedIndex);
                    application.gotoAddRoom(thisroom);
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
        application.gotoSettings();
    }
}
