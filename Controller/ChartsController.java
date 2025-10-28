/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import lodgeman.lalitman.view.LodgeMan;

/**
 * FXML Controller class
 *
 * @author vijay
 */
public class ChartsController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    private LodgeMan application;
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
    
    @FXML
    BarChart<String, Number> bc;
    
    @FXML
    CategoryAxis xAxis;
    
    @FXML
    NumberAxis yAxis;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    public void setApp(LodgeMan aThis) 
    {
        this.application = aThis;
        
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        
        bc = new BarChart<String,Number>(xAxis,yAxis);
        
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
        
        XYChart.Series series1 = new XYChart.Series();
        
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));
        
        System.out.println("Yes, I do come here");
        bc.getData().addAll(series1);
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
