/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller.table;

import java.util.Date;
import lodgeman.lalitman.model.pojo.Creditowners;

/**
 *
 * @author vijay
 */
public class CreditbillViewTable 
{

    public CreditbillViewTable(Integer creditbilloid, String creditowners, String guestname, Date entrydate, Integer billno, Integer totalamount, Integer balanceamount, Date cleardate) {
        this.creditbilloid = creditbilloid;
        this.creditowners = creditowners;
        this.guestname = guestname;
        this.entrydate = entrydate;
        this.billno = billno;
        this.totalamount = totalamount;
        this.balanceamount = balanceamount;
        this.cleardate = cleardate;
    }

    public Integer getCreditbilloid() {
        return creditbilloid;
    }

    public void setCreditbilloid(Integer creditbilloid) {
        this.creditbilloid = creditbilloid;
    }

    public String getCreditowners() {
        return creditowners;
    }

    public void setCreditowners(String creditowners) {
        this.creditowners = creditowners;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Integer getBillno() {
        return billno;
    }

    public void setBillno(Integer billno) {
        this.billno = billno;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getBalanceamount() {
        return balanceamount;
    }

    public void setBalanceamount(Integer balanceamount) {
        this.balanceamount = balanceamount;
    }

    public Date getCleardate() {
        return cleardate;
    }

    public void setCleardate(Date cleardate) {
        this.cleardate = cleardate;
    }

    @Override
    public String toString() {
        return "CreditbillViewTable{" + "creditbilloid=" + creditbilloid + ", creditowners=" + creditowners + ", guestname=" + guestname + ", entrydate=" + entrydate + ", billno=" + billno + ", totalamount=" + totalamount + ", balanceamount=" + balanceamount + ", cleardate=" + cleardate + '}';
    }

    
    
    private Integer creditbilloid;
    private String creditowners;
    private String guestname;
    private Date entrydate;
    private Integer billno;
    private Integer totalamount;
    private Integer balanceamount;
    private Date cleardate;
}
