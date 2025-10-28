/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.business.KeyValuePair;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Roomtype;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 * FXML Controller class
 *
 * @author svastrad
 */
public class AccMonthlyReportController implements Initializable 
{
    @FXML
    ComboBox<String> cmbMonth; 
    
    @FXML
    ComboBox<String> cmbYear;
    
    private LodgeMan application;
    
    private String inputMonth;
    
    @FXML
    private ComboBox<KeyValuePair> ComboBoxmonth;
    
    @FXML
    private ComboBox<Integer> ComboBoxyear;
    
    private ArrayList<CustomAccMonthlyRpt> custMnthBean  = new ArrayList();
    
    ObservableList<KeyValuePair> ComboBoxmonthList = FXCollections.observableArrayList();
    ObservableList<Integer> ComboBoxyearList = FXCollections.observableArrayList();
    
    public void setApp(LodgeMan aThis) throws JRException 
    {
        this.application = aThis;
        
        ComboBoxyearList.add(2025);
        ComboBoxyearList.add(2026);
        ComboBoxyearList.add(2027);
         ComboBoxyearList.add(2028);
        ComboBoxyearList.add(2029);
        ComboBoxyearList.add(2030);
        ComboBoxyear.setItems(ComboBoxyearList);
        
        ComboBoxmonthList = getComboBoxMonthFilled();
        ComboBoxmonth.setItems(ComboBoxmonthList);
        
        //Date d = new Date();
        //d.setMonth(10);
        //calcMontlyRpt(d);
    }
    
    public void processGetReport() throws JRException
    {
        KeyValuePair selectedPair = ComboBoxmonth.getValue();
        Integer selectedYear = ComboBoxyear.getValue();
        //System.out.println("selected pair" + selectedPair.getKey());
        this.inputMonth = selectedPair.getValue();
        
        Date inputDate = new Date();
        
        //System.out.println("I have:" + selectedPair.getKey());
        inputDate.setMonth(selectedPair.getKey());
        inputDate.setYear(selectedYear - 1900);
        
        System.out.println("Input Date : " + inputDate);
        calcMontlyRpt(inputDate);
    }
    
    public ObservableList<KeyValuePair> getComboBoxMonthFilled()
    {
        ObservableList<KeyValuePair> tempList = FXCollections.observableArrayList();
        
        tempList.add(new KeyValuePair(0,"January"));
        tempList.add(new KeyValuePair(1,"February"));
        tempList.add(new KeyValuePair(2,"March"));
        tempList.add(new KeyValuePair(3,"April"));
        tempList.add(new KeyValuePair(4,"Map"));
        tempList.add(new KeyValuePair(5,"June"));
        tempList.add(new KeyValuePair(6,"July"));
        tempList.add(new KeyValuePair(7,"August"));
        tempList.add(new KeyValuePair(8,"September"));
        tempList.add(new KeyValuePair(9,"October"));
        tempList.add(new KeyValuePair(10,"November"));
        tempList.add(new KeyValuePair(11,"December"));
        
        return tempList;
    }
    
    public int  getMonthEnd(int num)
    {
        switch(num)
        {
            case 0: return 31;                                 
            case 1: return 28;
            case 2: return 31;
            case 3: return 30;            
            case 4: return 31;    
            case 5: return 30; 
            case 6: return 31;
            case 7: return 31; 
            case 8: return 30;
            case 9: return 31; 
            case 10: return 30; 
            case 11: return 31;                 
        }
        return 0;
    }
    
    public void calcMontlyRpt(Date stDate) throws JRException
    {
        CustomAccRptFactory austAcc = new CustomAccRptFactory();
        stDate.setHours(00);
        stDate.setMinutes(0);
        stDate.setSeconds(1);
        
        
        Date edDate = new Date();
        edDate.setTime(stDate.getTime());
        System.out.println("start date " + stDate);
        System.out.println("end Date " + edDate);
        int month = getMonthEnd(stDate.getMonth());
        
        /**********************************************************************/
        for(int ii=1; ii <= month; ii++)
        {
            Integer taxable = 0;
            Integer exampted = 0;
            BigDecimal totaltax = BigDecimal.ZERO;
            BigDecimal totalbill = BigDecimal.ZERO;
            
            Date st = stDate;
            Date ed = edDate;
            
            st.setDate(ii);
            st.setHours(0);
            st.setMinutes(0);
            st.setSeconds(00);
            
            ed.setDate(ii);
            ed.setHours(23);
            ed.setMinutes(59);
            ed.setSeconds(59);
            
            QueryManager qm = QueryManager.getInstance();
            List<Bills> allBillList = qm.findBillsBetween(st, ed);
            
            Date tempDate = new Date();
            
            tempDate.setDate(st.getDate());
            tempDate.setMonth(st.getMonth());
            tempDate.setHours(st.getHours());
            tempDate.setMinutes(st.getMinutes());
            tempDate.setYear(st.getYear());
            int  firstbillId=0 ,lastbillId =0;
            if(allBillList.size() > 1)
            {
              firstbillId =allBillList.get(0).getOid(); 
              lastbillId =allBillList.get(allBillList.size()).getOid(); 
            }
            String billrange = firstbillId +"-"+lastbillId;
            for(int i=0;i<allBillList.size();i++)
            {
                
                Bills b = allBillList.get(i);
                if(b.getAdditionalluxurytax() == BigDecimal.ZERO)
                {
                    exampted = exampted + b.getOtherservices().intValue();
                }
                else
                {
                    taxable =  taxable + b.getOtherservices().intValue();
                }
                totaltax = totaltax.add(b.getAdditionalluxurytax());
                totalbill = totalbill.add( b.getTotalbill());
                //austAcc.insert(new CustomAccRpt(b.getBilldate(), b.getOid(), exampted, taxable, b.getAdditionalluxurytax(), b.getTotalbill()));
            }
            
            CustomAccMonthlyRpt a = new CustomAccMonthlyRpt(billrange, tempDate, taxable, totaltax.intValue(), totalbill.intValue());
            custMnthBean.add(a); 
        }
        /**********************************************************************/
        
        austAcc.addMonthlyBean(custMnthBean);
        
        DirCreate dir = new DirCreate();
        Date thisDate = new Date();
        String path = dir.findTargetDir(thisDate);
        
        //System.out.println("Before: " + path);
	
        String test = JasperFillManager.fillReportToFile("c:\\radhe\\lalitmahal\\dist\\res\\accmonthlyrep.jasper",null, new JRBeanArrayDataSource(austAcc.getMonthlyBeanArry(),false));
        JasperExportManager.exportReportToPdfFile("c:\\radhe\\lalitmahal\\dist\\res\\accmonthlyrep.jrprint", path + "\\accMonthlyReport_" + inputMonth + ".pdf");
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHistory();
    }
}
