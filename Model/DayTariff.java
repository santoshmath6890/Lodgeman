/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author vijay
 */
public class DayTariff 
{
    public DayTariff() 
    {
      this.roomrent = BigDecimal.ZERO;
      this.extraBedRent = 0.0;
      this.isTaxApplicable = false;
      this.taxtotal = 0.0;
      noOfExtraBed = 0;
        //this.startDate = startDate;
        //this.endDate = endDate;  
    }

    public Integer getNoOfExtraBed() {
        return noOfExtraBed;
    }

    public void setNoOfExtraBed(Integer noOfExtraBed) {
        //this.noOfExtraBed = noOfExtraBed;
        this.noOfExtraBed = this.noOfExtraBed.intValue()+ noOfExtraBed.intValue();
    }

    public DayTariff(BigDecimal roomrent, Double extraBedRent, boolean isTaxApplicable, Double taxtotal, Date startDate, Date endDate,Integer numBed) {
        this.roomrent = roomrent;
        this.extraBedRent = extraBedRent;
        this.isTaxApplicable = isTaxApplicable;
        this.taxtotal = taxtotal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noOfExtraBed = numBed;
    }

    boolean between(long dateval1, long dateval2) 
    {
        boolean flag =false;
        
        System.out.println("dateval1: " + dateval1);
        System.out.println("startDate.getTime() :" + startDate.getTime());
        System.out.println("dateval2: " + dateval2);
        System.out.println("endDate.getTime() : " + endDate.getTime());
        if(dateval2 <= endDate.getTime())
        {
           dateval2 = endDate.getTime();
        }
        
        if((dateval1 >= startDate.getTime()) &&(dateval1 <= endDate.getTime()) &&(dateval2 >= endDate.getTime()))
            flag =true;
        
        return flag;
    }
    
        private BigDecimal roomrent;
	private Double extraBedRent;
	private boolean isTaxApplicable;
	private Double taxtotal;
	private Date startDate;
	private Date endDate;
        private Double dayTotal;
        private Integer noOfExtraBed;
     

    void calculateTotal() {
           dayTotal = roomrent.intValue() + extraBedRent.intValue() + taxtotal.doubleValue();
           
        }
    
    public BigDecimal getRoomrent() {
        return roomrent;
    }

    public void setRoomrent(BigDecimal roomrent) 
    {
        
        this.roomrent = roomrent;
        
        if(this.roomrent.intValue() > 500)
        {
            this.setIsTaxApplicable(true);
            this.calculateTax();
            
        }
        calculateTotal();
    }

    public Double getExtraBedRent() {
        return extraBedRent;
    }

    public void setExtraBedRent(Double extraBedRent) {
        this.extraBedRent = extraBedRent*noOfExtraBed;
        
        if(this.roomrent.intValue()+extraBedRent.intValue() > 500)
        {
            this.setIsTaxApplicable(true);
            this.calculateTax();
        }
        calculateTotal();
    }

    public boolean isIsTaxApplicable() {
        return isTaxApplicable;
    }

    public void setIsTaxApplicable(boolean isTaxApplicable) {
        this.isTaxApplicable = isTaxApplicable;
    }

    public Double getTaxtotal() {
        return taxtotal;
    }

    public void setTaxtotal(double taxtotal) {
        this.taxtotal = taxtotal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Double getDayTotal() {
        return dayTotal;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    private void calculateTax() 
    {
        System.out.println("Hi! How are you?");
        double d = getRoomrent().intValue() +getExtraBedRent().intValue();
        double tax =   tax = GstMgr.getInstance().CalculateTax(getRoomrent().doubleValue());
        
       
        this.setTaxtotal(tax);
    }
    
}
