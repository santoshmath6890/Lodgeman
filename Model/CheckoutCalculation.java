/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import lodgeman.lalitman.controller.table.ReceiptsTable;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
//import lodgeman.lalitman.model.pojo.Receipts1;
//import lodgeman.lalitman.model.pojo.Receipts2;
import lodgeman.lalitman.model.pojo.Roomtype;

/**
 *
 * @author vijay
 */
public class CheckoutCalculation 
{
    public CheckoutCalculation(Bookings thisBookings) 
    {
        this.thisBookings = thisBookings;
        
        this.totalAdvancePaid = getTotalAdvancePaid();
        this.totalBookingAddon = getBookingAddonTotal();
        
        mainCalculation();
    }
    
    private void mainCalculation()
    {
        QueryManager qm = QueryManager.getInstance();
        Date nowDate = new Date();
        
        Integer tempRoomtypeid = thisBookings.getRoomno().getTemproomtypeid();
        Roomtype thisRoomtype = qm.findRoomtypeByOid(tempRoomtypeid);
        List<Bookingaddon> bookingaddon = qm.findBookingAddonByBookingid(thisBookings);
        
        CalcRoomRent r = new CalcRoomRent(thisBookings.getFromdate(), nowDate, thisRoomtype.getRate(), new BigDecimal(200) ,bookingaddon);
        r.calculate();
        List<DayTariff> li = r.getmTotalTarrif();
        TotalDaysTariff tot = r.getMtotDaysTariff();
        this.grandTotalWithTax = tot.getGrandTotal();
        this.totalTaxApplicable = tot.getGrandTotalTax();
        
        Double diff = totalAdvancePaid - grandTotalWithTax;
        diff = Generic.roundOfDouble(diff, 2);
        if (diff > 0) 
        {
            this.cashRefund = diff;
            this.cashReceive = 0.0;
        } 
        else if (diff < 0) 
        {
            diff = (diff) * (-1);
            this.cashRefund = 0.0;
            this.cashReceive = diff;
        } 
        else if (diff == 0) 
        {   
            this.cashRefund = 0.0;
            this.cashReceive = 0.0;
        }
    }
    
    
    
    public Double getBookingAddonTotal()
    {
        QueryManager qm = QueryManager.getInstance();
        List<Bookingaddon> bookingaddon = qm.findBookingAddonByBookingid(thisBookings);
            
        Double totalOther = 0.0;
            
        Date todayDate = new Date();
            
        for(int i = 0 ; i < bookingaddon.size(); i++)
        {
            Double amount = 0.0;
            if(bookingaddon.get(i).getTodate() == null)
            {
                Integer noofDay = Generic.noOfDaysBetween(bookingaddon.get(i).getFromdate(), todayDate);
                amount = bookingaddon.get(i).getAddontype().getRate().doubleValue() * noofDay;
            }
            else
            {
                Integer noofDay = Generic.noOfDaysBetween(bookingaddon.get(i).getFromdate(), bookingaddon.get(i).getTodate());
                amount = bookingaddon.get(i).getAddontype().getRate().doubleValue() * noofDay;
            }
            totalOther = totalOther + amount;
        }
        
        return totalOther;
    }
    
    public Integer getTotalAdvancePaid()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer totalAdvance = 0;
        
        List<Receipts> receipts = qm.getReceiptsByBooking(thisBookings);
        for (int i = 0; i < receipts.size(); i++) 
        {
            totalAdvance = totalAdvance + receipts.get(i).getAdvancepaid();
            //recptlist.add(new ReceiptsTable(receipts1.get(i).getOid(), "Advance", receipts1.get(i).getArrdate(), receipts1.get(i).getAdvancepaid()));
            //receiptlistAll = receiptlistAll + " " + receipts1.get(i).getOid();
        }
        
        return totalAdvance;
    }
    
    public Roomtype getBookedRoomType()
    {
        QueryManager qm = QueryManager.getInstance();
        Integer tempRoomtype = thisBookings.getRoomno().getTemproomtypeid();
        Roomtype thisRoomtype = qm.findRoomtypeByOid(tempRoomtype);
        return thisRoomtype;
    }
    
    public Integer getNoOfDaysBookings()
    {
        Date currentDate = new Date();
        Integer noofdays = Generic.noOfDaysBetween(thisBookings.getFromdate(), currentDate);
        return noofdays;
    }
    
    
    public List getAdvancePaidList()
    {
        QueryManager qm = QueryManager.getInstance();
        List<Receipts> receiptsList1 = qm.getReceiptsByBooking(thisBookings);
        return receiptsList1;
    }
    
    public List getBookingAddonList()
    {
        QueryManager qm = QueryManager.getInstance();
        List<Bookingaddon> bookingaddon = qm.findBookingAddonByBookingid(thisBookings);
        return bookingaddon;
    }

    
    
    public Double getTotalTaxApplicable()
    {
        return totalTaxApplicable;
    }
    
    public Double getGrandTotalBill()
    {
        return grandTotalWithTax;
    }
    
    public Double getCashReceived()
    {
        return cashReceive;
    }           
    
    public Double getCashRefund()
    {
        return cashRefund;
    }
            

    Double cashReceive;
    Double cashRefund;
    Double grandTotalWithTax;
    Double totalTaxApplicable;
    Integer totalAdvancePaid;
    Double totalBookingAddon;
    Bookings thisBookings;
}
