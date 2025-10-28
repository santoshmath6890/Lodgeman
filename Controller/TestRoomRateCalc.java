/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.Date;
import java.util.List;

/**
 *
 * @author svastrad
 */
public class TestRoomRateCalc {
    
    public static void main(String a[])
    {
        // check in date today 
        // check out date +1 
        Date stDate;
        Date edDate;
        
        stDate= new Date();
        int day = stDate.getDate();
        
        edDate = new Date();
        edDate.setDate(day+1);
        
        CalcRoomRent r ;
        r = new CalcRoomRent(stDate, edDate, 495, null);
        r.calculate();
        printResult(r);
    
        r = new CalcRoomRent(stDate, edDate, 550, null);
         r.calculate();
         printResult(r);
        //2 day stay check 
        
        edDate.setDate(day+2);
        r = new CalcRoomRent(stDate, edDate, 495, null);
         r.calculate();
        printResult(r);
        r = new CalcRoomRent(stDate, edDate, 550, null);
         r.calculate();
         printResult(r);
        //5 day stay check 
        edDate.setDate(day+5);
        r = new CalcRoomRent(stDate, edDate, 495, null);
         r.calculate();
         printResult(r);
        
        r = new CalcRoomRent(stDate, edDate, 550, null);
         r.calculate();
         printResult(r);
    }

    private static void printResult(CalcRoomRent r) {
        System.out.println("=============================================================================");
        List<DayTariff> li =  r.getmTotalTarrif();
         TotalDaysTariff tot = r.getMtotDaysTariff();
         DayTariff d;
         for(int i =0;i<li.size();i++)
         {
             d = li.get(i);
             System.out.println("Stat Date: "+d.getStartDate() + "End Date:  " + d.getEndDate() + " Room Rent:  "+d.getRoomrent()+" Extra bed: "+ d.getExtraBedRent()+ " Tax Amt:  "+ d.getTaxtotal() + " *** ");
             
             
         }
         System.out.println("grand RoomTotal:"+ tot.getGrandTotal()+ "Grand extra total"+tot.getGrandExtraBedTax()+"Grand Tax toal"+ tot.getGrandTotalTax()+"Total ="+tot.getTotalBill()   );
         System.out.println("=============================================================================");
    }
    
}
