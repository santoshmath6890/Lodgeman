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
import javax.persistence.Table;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */


public class RoomtypeController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    
    private LodgeMan application;
    
    @FXML
    TableView<Roomtype> TableRoomtype;
     
    @FXML
    TableColumn<Roomtype, String> Columnname;
     
    @FXML
    TableColumn<Roomtype, String> Columndesc;
     
    @FXML
    TableColumn<Roomtype, Integer> Columnadult;
     
    @FXML
    TableColumn<Roomtype, Integer> Columnchildren;
    
    @FXML
    TableColumn<Roomtype, Integer> Columnrate;
    
    @FXML
    TableColumn<Roomtype, Boolean> ColumnAction;
    
    ObservableList<Roomtype> roomtypelist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan application) 
    {
        this.application = application;
        
        Columnname.setCellValueFactory(new PropertyValueFactory<Roomtype, String>("name"));
        Columndesc.setCellValueFactory(new PropertyValueFactory<Roomtype, String>("description"));
        Columnadult.setCellValueFactory(new PropertyValueFactory<Roomtype, Integer>("maxadult"));
        Columnchildren.setCellValueFactory(new PropertyValueFactory<Roomtype, Integer>("maxchildren"));
        Columnrate.setCellValueFactory(new PropertyValueFactory<Roomtype, Integer>("rate"));
        
        /**********************************************************************/
        QueryManager qm = QueryManager.getInstance();
        List<Roomtype> allRoomtype = qm.findAll("Roomtype.findAll");
        for(int i = 0; i < allRoomtype.size(); i++)
        {
            roomtypelist.add(allRoomtype.get(i));
        }
        TableRoomtype.setItems(roomtypelist);
        /**********************************************************************/
        
        
        /**********************************************************************/
        ColumnAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Roomtype, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Roomtype, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
       );
       
       ColumnAction.setCellFactory(new Callback<TableColumn<Roomtype, Boolean>, TableCell<Roomtype, Boolean>>() 
        {
            @Override public TableCell<Roomtype, Boolean> call(TableColumn<Roomtype, Boolean> personBooleanTableColumn) 
            {
                return new UpdateRoomtypeCell(TableRoomtype);
            }
        }
        );
       /**********************************************************************/
        
    }
    
     public class UpdateRoomtypeCell extends TableCell<Roomtype, Boolean>
    {
        final Button addButton       = new Button("Update");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        
        UpdateRoomtypeCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    Roomtype thisroomtype = (Roomtype) TableRoomtype.getItems().get(selectedIndex);
                    
                    QueryManager qm = QueryManager.getInstance();
                    Date currentDate = new Date();
                    application.gotoRoomtypeUpdate(thisroomtype);
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
        application.gotoConfig();
    }
    
    public void processAddRoomType()
    {
        application.gotoRoomTypeAdd();
    }
}
