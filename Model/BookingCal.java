/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import java.util.Date;

/**
 *
 * @author svastrad
 */
public class BookingCal {
    
   long  getDateNum(Date d)
    {
       long  milliSec =  d.getTime();
       
       long hours = milliSec/(1000*60*60);        
       return hours;    
    }
   long getDateNum(int year,int month,int day,int hrs)
   {
       Date d = new Date(year, month, day, hrs, 0);
       long  milliSec =  d.getTime();
       long hours = milliSec/(1000*60*60);    
       return hours;        
   }

   void setCalData(int startMonth,int EndMonth ,int fromblock,int frommask, int toblock,int tomask)
   {
       String month1,month2;
       if(startMonth == EndMonth)
       {
            month1 = getMonthData(startMonth);
            char chr1 = month1.charAt(fromblock);
            char chr2 = month1.charAt(toblock);
       }
       else
       {
            month1 = getMonthData(startMonth);
            month2 = getMonthData(startMonth);
       }
       
   }

    private String getMonthData(int startMonth) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
