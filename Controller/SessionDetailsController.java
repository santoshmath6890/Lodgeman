/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lodgeman.lalitman.controller.reports.CreditBillReport;
import lodgeman.lalitman.controller.reports.DayReport;
import lodgeman.lalitman.controller.reports.Sessionreport;
import lodgeman.lalitman.controller.reports.advancereport;
import lodgeman.lalitman.controller.reports.billreport;
import lodgeman.lalitman.controller.reports.expensereport;
//import lodgeman.lalitman.controller.reports.anugrahaAdvanceReport;
//import lodgeman.lalitman.controller.reports.anugrahaBillReport;
//import lodgeman.lalitman.controller.reports.anugrahaCreditBillReport;
//import lodgeman.lalitman.controller.reports.anugrahaExpenseReport;
//import lodgeman.lalitman.controller.reports.aradhanaAdvanceReport;
//import lodgeman.lalitman.controller.reports.aradhanaBillReport;
//import lodgeman.lalitman.controller.reports.aradhanaCreditBillReport;
//import lodgeman.lalitman.controller.reports.aradhanaExpenseReport;
import lodgeman.lalitman.controller.table.SessionDetailTable;
import lodgeman.lalitman.model.business.Generic;
import lodgeman.lalitman.model.pojo.Daysession;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.view.LodgeMan;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class SessionDetailsController implements Initializable 
{
    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    
    @FXML
    private jfxtras.labs.scene.control.CalendarTextField TextFielduserDate;

    @FXML
    private TableView<SessionDetailTable> TableSessionDetails;
    
    @FXML
    private TableColumn<SessionDetailTable, String> user;
    
    @FXML
    private TableColumn<SessionDetailTable, Date> from;
    
    @FXML
    private TableColumn<SessionDetailTable, Date> to;
    
    @FXML
    private TableColumn<SessionDetailTable, Double> openingbalance;
    
    @FXML
    private TableColumn<SessionDetailTable, Double> closingbalance;
    
    @FXML
    private TableColumn<SessionDetailTable, Double> cashincounter;
    
    @FXML
    private TableColumn<SessionDetailTable, Boolean> action;
    
    private Daysession daysessionObj;
    
    @FXML
    private TableColumn<SessionDetailTable, Double> difference;
    private ObservableList<SessionDetailTable> SessionDetailOBList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        TextFielduserDate.setValue(Calendar.getInstance());
        this.application = aThis;
        
        tablemapping();
        
        QueryManager qm = QueryManager.getInstance();
        
        Daysession lastsession1 = qm.getLastDaysession();
        this.daysessionObj = lastsession1;
        
        Userlog lastUserlog = qm.getLastUserlog();
        Daysession lastUserlogDaysessonid = lastUserlog.getDaysessionid();
        
        List<Userlog> userlogList = qm.findUserlogByDaysession(lastUserlogDaysessonid);
        
        for(int i = 0 ; i < userlogList.size(); i++)
        {
            System.out.println("Log : " + i + "-> : " + userlogList.get(i).getUserid().getLoginname());
            System.out.println("OB :" + userlogList.get(i).getOpeningbalance());
            
            Integer Tclosingbalance = userlogList.get(i).getClosingbalance();
            
            Integer Topeningbalance = userlogList.get(i).getOpeningbalance();

            Integer Tdifference = userlogList.get(i).getDifference();
            
            SessionDetailTable a = new SessionDetailTable(userlogList.get(i).getOid(), userlogList.get(i).getUserid(), userlogList.get(i).getStartdatetime(), userlogList.get(i).getEnddatetime(), Topeningbalance, Tclosingbalance, userlogList.get(i).getCashincounter(), Tdifference);
            
            SessionDetailOBList.add(a);
        }
        
        TableSessionDetails.setItems(SessionDetailOBList);
        
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SessionDetailTable, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SessionDetailTable, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
        );
       
        action.setCellFactory(new Callback<TableColumn<SessionDetailTable, Boolean>, TableCell<SessionDetailTable, Boolean>>() 
        {
            @Override public TableCell<SessionDetailTable, Boolean> call(TableColumn<SessionDetailTable, Boolean> personBooleanTableColumn) 
            {
                return new SessionDetailsPrintCell(TableSessionDetails);
            }
        }
        );
    }
    
    public void tablemapping()
    {
        user.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, String>("user"));
        from.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Date>("from"));
        to.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Date>("to"));
        openingbalance.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Double>("openingbalance"));
        closingbalance.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Double>("closingbalance"));
        cashincounter.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Double>("cashincounter"));
        difference.setCellValueFactory(new PropertyValueFactory<SessionDetailTable, Double>("difference"));
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHome();
    }
    
    public void processDayReport() throws JRException, IOException
    {
        QueryManager qm = QueryManager.getInstance();
        Calendar UserCalendar = TextFielduserDate.getValue();
        Date InputUserDate = UserCalendar.getTime();
        
        List<Daysession> daysessionOid = qm.getDaysessionByStartDate(InputUserDate);
        this.daysessionObj = daysessionOid.get(0);
        
        String path1 = Generic.giveMePath();
        String fileN = path1 + "/" + "DayReport__" + ".pdf";
        DayReport.printdayreport(daysessionObj.getOid(), fileN);
    }
    
    public void processGetSessions()
    {
        Calendar c = TextFielduserDate.getValue();
        SessionDetailOBList.clear();
        
        Date InputUserDate = c.getTime();
        System.out.println("Input Date : " + InputUserDate);
        
        QueryManager qm = QueryManager.getInstance();
        List<Daysession> daysessionList = qm.getDaysessionByStartDate(InputUserDate);
        
        for(int i = 0; i < daysessionList.size(); i++)
        {
            System.out.println("daysession : " + daysessionList.get(i).getOid());
            List<Userlog> userlogList = qm.findUserlogByDaysession(daysessionList.get(i));
            for(int j = 0 ; j < userlogList.size(); j++)
            {
                System.out.println("Log : " + i + "-> : " + userlogList.get(j).getUserid().getLoginname());
                System.out.println("OB :" + userlogList.get(j).getOpeningbalance());
            
                Integer Tclosingbalance = userlogList.get(j).getClosingbalance();
            
                Integer Topeningbalance = userlogList.get(j).getOpeningbalance();
            
                Integer Tdifference = userlogList.get(j).getDifference();
            
                SessionDetailTable a = new SessionDetailTable(userlogList.get(j).getOid(), userlogList.get(j).getUserid(), userlogList.get(j).getStartdatetime(), userlogList.get(j).getEnddatetime(), Topeningbalance, Tclosingbalance, userlogList.get(j).getCashincounter(), Tdifference);
                SessionDetailOBList.add(a);
            }
        
            TableSessionDetails.setItems(SessionDetailOBList);
        }
        
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SessionDetailTable, Boolean>, ObservableValue<Boolean>>() 
        {
            @Override 
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SessionDetailTable, Boolean> features) 
            {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        }
        );
       
        action.setCellFactory(new Callback<TableColumn<SessionDetailTable, Boolean>, TableCell<SessionDetailTable, Boolean>>() 
        {
            @Override public TableCell<SessionDetailTable, Boolean> call(TableColumn<SessionDetailTable, Boolean> personBooleanTableColumn) 
            {
                return new SessionDetailsPrintCell(TableSessionDetails);
            }
        }
        );
    }
    
    public class SessionDetailsPrintCell extends TableCell<SessionDetailTable, Boolean>
    {
        final Button addButton       = new Button("Print");   
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        SessionDetailsPrintCell(final TableView table)
        {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            
            addButton.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent actionEvent) 
                {
                    Integer selectedIndex = getTableRow().getIndex();
                    SessionDetailTable selectedSession = (SessionDetailTable) table.getItems().get(selectedIndex);
                    System.out.println("Session : " + selectedSession.getOid());
                    
                    Timestamp t1 = new Timestamp(selectedSession.getFrom().getTime());
                    Timestamp t2 = new Timestamp(selectedSession.getTo().getTime());
                    
                    
                    String usernameSession = selectedSession.getUser().getLoginname();
                    String path1 = Generic.giveMePath();
                    String filename = Generic.getFilename(new Date());
                    
                    /**** File Names ****/
                    String filename_advancereport = "" + path1 + "/" + "Old_ADVANCE_REPORT_" + filename + "_" + usernameSession + ".pdf";
                    String filename_billreport = "" + path1 + "/" + "Old_BILL_REPORT_" + filename + "_" + usernameSession + ".pdf";
                    String filename_expensereport = "" + path1 + "/" + "Old_EXPENSE_REPORT_" + filename + "_" + usernameSession + ".pdf";
                    String filename_sessionreport = "" + path1 + "/" + "Old_SESSION_REPORT_" + filename + "_" + usernameSession + ".pdf";
                    String filename_creditbillreport = "" + path1 + "/" + "Old_CREDITBILLREPORT" + filename + "_" + usernameSession + ".pdf";
                    String filename_creditbillrecvreport = "" + path1 + "/" + "Old_CREDITBILLRECVREPORT" + filename + "_" + usernameSession + ".pdf";
                    
                    /*
                    String filename_advancereport2 = "" + path1 + "/" + "Anugraha_Old_ADVANCE_REPORT_" + filename + "" + usernameSession + ".pdf";
                    String filename_billreport2 = "" + path1 + "/" + "Anugraha_Old_BILL_REPORT_" + filename + "" + usernameSession + ".pdf";
                    String filename_expensereport2 = "" + path1 + "/" + "Anugraha_Old_EXPENSE_REPORT_" + filename + "" + usernameSession + ".pdf";
                    String filename_sessionreport2 = "" + path1 + "/" + "Anugraha_Old_SESSION_REPORT_" + filename + "" + usernameSession + ".pdf";
                    String filename_creditbillreport2 = "" + path1 + "/" + "Anugraha_Old_CREDITBILLREPORT" + filename + "" + usernameSession + ".pdf";
                    String filename_creditbillrecvreport2 = "" + path1 + "/" + "Anugraha_Old_CREDITBILLRECVREPORT" + filename + "" + usernameSession + ".pdf";
                    */
                    
                    try 
                    {
                        //aradhanaAdvanceReport.printadvancereport(selectedSession.getUser().getOid(), t1, t2, filename_advancereport);
                        advancereport.printadvancereport(selectedSession.getUser().getOid(), t1, t2, filename_advancereport);
                    } 
                    catch (JRException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    try 
                    {
                        //aradhanaBillReport.printbillreport(t1, t2, filename_billreport);
                        billreport.printbillreport(t1, t2, filename_billreport);
                    }
                    catch (JRException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    try 
                    {
                        //aradhanaExpenseReport.printexpencereport(t1, t2, filename_expensereport);
                        expensereport.printexpencereport(t1, t2, filename_expensereport);
                    }
                    catch (JRException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    try 
                    {
                        Sessionreport.printsessionreport( selectedSession.getOid(), filename_sessionreport);
                    }
                    catch (JRException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try 
                    {
                        //aradhanaCreditBillReport.printcreditbillreport(t1, t2, filename_creditbillreport);
                        CreditBillReport.printcreditbillreport(t1, t2, filename_creditbillreport);
                    }
                    catch (JRException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(SessionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
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
