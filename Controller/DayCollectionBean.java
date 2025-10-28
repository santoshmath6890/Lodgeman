/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.Date;

/**
 *
 * @author RADHAKRISHNA
 */
public class DayCollectionBean {

  /*  DayCollectionBean(Integer creditBillTotal, Integer refundTotal, Integer creditBillRecvTotal, Integer expenseTotal, Integer receiptTotal, Integer receiveTotal, Integer grandTotal, Date fromDate) {
        
    }*/

    public Integer getCrdBill() {
        return crdBill;
    }

    public void setCrdBill(Integer crdBill) {
        this.crdBill = crdBill;
    }

    public Integer getRefundtot() {
        return refundtot;
    }

    public void setRefundtot(Integer refundtot) {
        this.refundtot = refundtot;
    }

    public Integer getCrdBillReceived() {
        return crdBillReceived;
    }

    public void setCrdBillReceived(Integer crdBillReceived) {
        this.crdBillReceived = crdBillReceived;
    }

    public Integer getExpenseTot() {
        return expenseTot;
    }

    public void setExpenseTot(Integer ExpenseTot) {
        this.expenseTot = ExpenseTot;
    }

    public Integer getAdvTotal() {
        return advTotal;
    }

    public void setAdvTotal(Integer advTotal) {
        this.advTotal = advTotal;
    }

    public Integer getReceiveTotal() {
        return receiveTotal;
    }

    public void setReceiveTotal(Integer receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    public Integer getGrandToal() {
        return grandToal;
    }

    public void setGrandToal(Integer GrandToal) {
        this.grandToal = GrandToal;
    }

    public Date getStdate() {
        return stdate;
    }

    public void setStdate(Date stdate) {
        this.stdate = stdate;
    }

    public DayCollectionBean(Integer crdBill, Integer refundtot, Integer crdBillReceived, Integer ExpenseTot, Integer advTotal, Integer receiveTotal, Integer GrandToal, Date stdate) {
        this.crdBill = crdBill;
        this.refundtot = refundtot;
        this.crdBillReceived = crdBillReceived;
        this.expenseTot = ExpenseTot;
        this.advTotal = advTotal;
        this.receiveTotal = receiveTotal;
        this.grandToal = GrandToal;
        this.stdate = stdate;
    }
    
    
    Integer crdBill;
    Integer refundtot;
    Integer crdBillReceived;
    Integer expenseTot;
    Integer advTotal;
    Integer receiveTotal;
    Integer grandToal;
    Date stdate;
}
