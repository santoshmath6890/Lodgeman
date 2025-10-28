/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import java.util.Date;

/**
 *
 * @author svastrad
 */
public class CustomAccMonthlyRpt {

    public CustomAccMonthlyRpt(String billRange, Date billDate, Integer taxableTotal, Integer taxCollectedTotal, Integer grossTotal) {
        this.billRange = billRange;
        this.billDate = billDate;
        this.taxableTotal = taxableTotal;
        this.taxCollectedTotal = taxCollectedTotal;
        this.grossTotal = grossTotal;
    }

    public String getbillRange() {
        return billRange;
    }

    public void setExamptedTotalbillRange(String billRange) {
        this.billRange = billRange;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getTaxableTotal() {
        return taxableTotal;
    }

    public void setTaxableTotal(Integer taxableTotal) {
        this.taxableTotal = taxableTotal;
    }

    public Integer getTaxCollectedTotal() {
        return taxCollectedTotal;
    }

    public void setTaxCollectedTotal(Integer taxCollectedTotal) {
        this.taxCollectedTotal = taxCollectedTotal;
    }

    public Integer getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Integer grossTotal) {
        this.grossTotal = grossTotal;
    }
    
    
     
    private  String billRange;
    private  Date    billDate;        
    private  Integer taxableTotal;
    private  Integer taxCollectedTotal;
    private  Integer grossTotal;

}
