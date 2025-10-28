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
public class CustomAccRpt {

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getBillno() {
        return billno;
    }
 public Integer getbillno() {
        return billno;
    }
  public Integer getbillNo() {
        return billno;
    }
    public void setBillno(Integer billno) {
        this.billno = billno;
    }

     public Integer getExampted() {
        return exampted;
    }
    public Integer getexampted() {
        return exampted;
    }

    public void setExampted(Integer exampted) {
        this.exampted = exampted;
    }

    
    public Integer gettaxable() {
        return taxable;
    }
    public Integer getTaxable() {
        return taxable;
    }

    public void setTaxable(Integer taxable) {
        this.taxable = taxable;
    }

    public Integer getTaxCollected() {
        return taxCollected;
    }

    public void setTaxCollected(Integer taxCollected) {
        this.taxCollected = taxCollected;
    }

    public Integer getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Integer grossTotal) {
        this.grossTotal = grossTotal;
    }

    public CustomAccRpt(Date billDate, Integer billno, Integer exampted, Integer taxable, Integer taxCollected, Integer grossTotal) {
        this.billno = billno;
        this.billDate = billDate;        
        this.exampted = exampted;
        this.taxable = taxable;
        this.taxCollected = taxCollected;
        this.grossTotal = grossTotal;
    }
    
    private  Integer billno;
    private  Integer exampted;
    private  Date billDate;
    
    
    private  Integer taxable;
    private  Integer taxCollected;
    private  Integer grossTotal;
}
