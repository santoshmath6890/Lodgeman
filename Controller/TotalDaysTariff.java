/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.List;

/**
 *
 * @author svastrad
 */
public class TotalDaysTariff {

    public TotalDaysTariff() {
    	grandTotal = 0.0;                
	grandTotalTax= 0.0;           
	grandExtraBedTax= 0.0;     
	TotalBill= 0.0;        
    }
    
    boolean calc(List<DayTariff> daytarrifLst)
    {
        if(daytarrifLst.size() == 0)
            return false;
        double taxtotal =0.0;
        double roomtotal = 0.0;
        double extrabedtotal = 0.0;
        double tot=0.0;
        DayTariff day;
        for(int i = 0;i<daytarrifLst.size();i++)
        {
            day = daytarrifLst.get(i);
            taxtotal+=day.getTaxtotal().doubleValue();
            extrabedtotal +=day.getExtraBedRent().doubleValue();
            roomtotal+=day.getRoomrent().doubleValue();                    
        }
        tot = roomtotal+extrabedtotal+taxtotal;
        setGrandExtraBedTax(extrabedtotal);
        setGrandTotal(roomtotal);
        setTotalBill(tot);
        setGrandTotalTax(taxtotal);
    return true;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getGrandTotalTax() {
        return grandTotalTax;
    }

    public void setGrandTotalTax(Double grandTotalTax) {
        this.grandTotalTax = grandTotalTax;
    }

    public Double getGrandExtraBedTax() {
        return grandExtraBedTax;
    }

    public void setGrandExtraBedTax(Double grandExtraTotalTax) {
        this.grandExtraBedTax = grandExtraTotalTax;
    }

    public Double getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(Double TotalBill) {
        this.TotalBill = TotalBill;
    }
 
    
    
        private  Double grandTotal;
        private  Double grandTotalTax;
        private  Double grandExtraBedTax;
	private  Double TotalBill;
}
