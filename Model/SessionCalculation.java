/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Receives;

/**
 *
 * @author vijay
 */
public class SessionCalculation 
{

    public SessionCalculation(Date startDatetime, Date endDatetime) 
    {
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    public Date getStartDatetime() 
    {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) 
    {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() 
    {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) 
    {
        this.endDatetime = endDatetime;
    }

    @Override
    public String toString() 
    {
        return "SessionCalculation{" + "startDatetime=" + startDatetime + ", endDatetime=" + endDatetime + '}';
    }
    
    public Integer calulateReceiptsTotal()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer advance_total = 0;
        List<Receipts> allList = qm.findReceiptsBeBetween(startDatetime, endDatetime);
        for(int i = 0; i < allList.size(); i++)
        {
            //System.out.println("" + allList.get(i));
            advance_total = advance_total + allList.get(i).getAdvancepaid();
        }
        return advance_total;
    }
    
    public BigDecimal calulateRefundTotal()
    {
        QueryManager qm = QueryManager.getInstance();
        BigDecimal refund_total = BigDecimal.ZERO;
        List<Bills> allBillList = qm.findBillsBetween(startDatetime,endDatetime);
        for(int i = 0 ; i < allBillList.size(); i++)
        {
            refund_total = refund_total.add( allBillList.get(i).getCashrefund());
        }
        return refund_total;
    }
    
    public Integer calulateReceiveTotal()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer receive_total = 0;
        List<Bills> allBillList = qm.findBillsBetween(startDatetime,endDatetime);
        for(int i = 0 ; i < allBillList.size(); i++)
        {
            receive_total = receive_total + allBillList.get(i).getCashreceived();
        }
        return receive_total;
    }
    
    public Integer calulateCreditBill()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer creditbillamount = 0;
        
        List<Creditbills> allCreditbillList = qm.findCreditbillsBetween(startDatetime, endDatetime, false);
        for(int i = 0; i < allCreditbillList.size(); i++)
        {
            creditbillamount = creditbillamount + allCreditbillList.get(i).getBillno().getCashreceived();
        }
        
        return creditbillamount;
    }
    
    public Integer calculateCreditBillTrans()
    {
        QueryManager qm = QueryManager.getInstance();
        List<Creditbilltrans> allCreditbilltrans = qm.findCreditbilltransBetween(startDatetime, endDatetime);
        Integer ttt = 0;
        for(int i = 0; i < allCreditbilltrans.size(); i++)
        {
            System.out.println("" + allCreditbilltrans.get(i));
            ttt = ttt + allCreditbilltrans.get(i).getAmountrecvd();
        }
        return ttt;
    }
    
    public Integer calulateExpense()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer expense_total = 0;
        List<Payments> allPaymentsList = qm.findPaymentsBetween(startDatetime, endDatetime);
        for(int i = 0; i < allPaymentsList.size(); i++)
        {
            expense_total = expense_total + allPaymentsList.get(i).getAmount();
        }
        return expense_total;
    }
    
    public Integer calculateReceivables()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer receives_total = 0;
        
        List<Receives> allReveivesList = qm.findReceiveBetween(startDatetime, endDatetime);
        for(int i = 0 ; i < allReveivesList.size(); i++)
        {
            receives_total = receives_total + allReveivesList.get(i).getAmount();
        }
        
        return receives_total;
    }
    
    Date startDatetime;
    Date endDatetime;
}
