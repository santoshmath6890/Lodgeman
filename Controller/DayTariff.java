/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.Date;
import lodgeman.lalitman.model.business.GstMgr;

/**
 *
 * @author svastrad
 */
public class DayTariff 
{

    public DayTariff() 
    {
      this.roomrent = 0;
        this.extraBedRent = 0;
        this.isTaxApplicable = false;
        this.taxtotal = 0.0;
        //this.startDate = startDate;
        //this.endDate = endDate;  
    }

    
    public DayTariff(Integer roomrent, Integer extraBedRent, boolean isTaxApplicable, Double taxtotal, Date startDate, Date endDate) 
    {
        this.roomrent = roomrent;
        this.extraBedRent = extraBedRent;
        this.isTaxApplicable = isTaxApplicable;
        this.taxtotal = taxtotal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    boolean between(long dateval)
    {
        boolean flag =false;
        
        if((dateval >= startDate.getTime()) &&(dateval <= endDate.getTime()))
        {    
            flag =true;
        }
        
        return flag;
    }
    
    
    private Integer roomrent;
    private Integer extraBedRent;
    private boolean isTaxApplicable;
    private Double taxtotal;
    private Date startDate;
    private Date endDate;
    private Double dayTotal;
     

    void calculateTotal()
    {
       dayTotal = roomrent.intValue() + extraBedRent.intValue() + taxtotal.doubleValue();
    }
        
    public Integer getRoomrent() 
    {
        return roomrent;
    }

    public void setRoomrent(Integer roomrent) 
    {
        this.roomrent = roomrent;
        if(this.roomrent.intValue() > 500)
        {
            this.setIsTaxApplicable(true);
            this.calculateTax();
        }
    }

    public Integer getExtraBedRent() 
    {
        return extraBedRent;
    }

    public void setExtraBedRent(Integer extraBedRent) 
    {
        this.extraBedRent = extraBedRent;
        if(this.roomrent.intValue() > 500)
        {
            this.setIsTaxApplicable(true);
            this.calculateTax();
        }
    }

    public boolean isIsTaxApplicable() 
    {
        return isTaxApplicable;
    }

    public void setIsTaxApplicable(boolean isTaxApplicable) 
    {
        this.isTaxApplicable = isTaxApplicable;
    }

    public Double getTaxtotal() 
    {
        return taxtotal;
    }

    public void setTaxtotal(double taxtotal) 
    {
        this.taxtotal = taxtotal;
    }

    public Date getStartDate() 
    {
        return startDate;
    }

    public void setStartDate(Date startDate) 
    {
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
        double d = getRoomrent().intValue() + getExtraBedRent().intValue();
        System.out.println("Room Total : " + d);
        //double tax = (d) * 0.12;
                double tax = GstMgr.getInstance().CalculateTax(getRoomrent().doubleValue());

        this.setTaxtotal(tax);
    }
}
