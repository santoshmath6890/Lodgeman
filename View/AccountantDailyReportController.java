/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lodgeman.lalitman.model.business.DirCreate;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.QueryManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 * FXML Controller class
 *
 * @author svastrad
 */
public class AccountantDailyReportController implements Initializable 
{
    private LodgeMan application;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    jfxtras.labs.scene.control.CalendarTextField rptdate;
    
    @FXML
    Button btn1;
    
    public void setApp(LodgeMan aThis) 
    {
       
       this.application = aThis;
    }
    
    public void processHome()
    {
        application.gotoHome();
    }
    
    public void processBack()
    {
        application.gotoHistory();
    }
    
    public void processAcc(ActionEvent ae) throws JRException
    {
        Calendar fromInputdate = rptdate.getValue();
        System.out.println("the selected Date is "+rptdate.getValue());
        Date st = fromInputdate.getTime();
        Date ed = fromInputdate.getTime();
        st.setHours(0);
        st.setMinutes(0);
        st.setSeconds(00);
        ed.setHours(23);
        ed.setMinutes(59);
        ed.setSeconds(59);
        
        QueryManager qm = QueryManager.getInstance();
        List<Bills> allBillList = qm.findBillsBetween(st, ed);
        CustomAccRptFactory austAcc = new CustomAccRptFactory();
        
        for(int i=0;i<allBillList.size();i++)
        {
            Bills b = allBillList.get(i);
            Integer taxable = new Integer(0);
            Integer exampted = new Integer(0);
            
            if(b.getAdditionalluxurytax() == BigDecimal.ZERO)
            {    
                exampted = b.getOtherservices().intValue();
            }    
            else
            {    
                taxable = b.getOtherservices().intValue();
            }    
            austAcc.insert(new CustomAccRpt(b.getBilldate(), b.getOid(), exampted, taxable, b.getAdditionalluxurytax().intValue(), b.getTotalbill().intValue()));
        }
        
        Map parameters = new HashMap();
	//parameters.put("ReportTitle", "Address Report");
	//parameters.put("DataFile", "CustomBeanFactory.java - Bean Array");

        DirCreate dir = new DirCreate();
        Date thisDate = new Date();
        String path = dir.findTargetDir(thisDate);
        System.out.println("Before" + path);
	String test = JasperFillManager.fillReportToFile("e:\\radhe\\lalitmahal\\dist\\res\\accdayreport.jasper",null, new JRBeanArrayDataSource(austAcc.getBeanArray(),false));
        JasperExportManager.exportReportToPdfFile("e:\\radhe\\lalitmahal\\dist\\res\\accdayreport.jrprint", path + "\\AccountantDayReport_" + st.getDay() + ".pdf");
    }
}
