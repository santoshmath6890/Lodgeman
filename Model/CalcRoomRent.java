/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lodgeman.lalitman.model.pojo.Bookingaddon;

/**
 *
 * @author vijay
 */
public class CalcRoomRent 
{
    public CalcRoomRent(Date mStartDate, Date mEndDate, BigDecimal roomRent, BigDecimal mExtraRent, List<Bookingaddon> mExtraBedList) 
    {
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.roomRent = roomRent;
        this.mExtraRent = mExtraRent;
        
        if(mExtraBedList == null)
            this.mExtraBedList = new ArrayList<Bookingaddon>();
        else
            this.mExtraBedList = mExtraBedList;
        
        mTotalTarrif = new ArrayList<DayTariff>();
        mtotDaysTariff = new TotalDaysTariff();
    }
    
    private Integer mextraBed;

    public CalcRoomRent(Date mStartDate, Date mEndDate, Double roomRent, List<Bookingaddon> mExtraBedList) 
    {
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.roomRent = BigDecimal.valueOf(roomRent);
        
        if(mExtraBedList == null)
        {
            this.mExtraBedList = new ArrayList<Bookingaddon>();
        }
        else
        {
            this.mExtraBedList = mExtraBedList;
        }
        
        mTotalTarrif = new ArrayList<DayTariff>();
        mtotDaysTariff = new TotalDaysTariff();
    }
    
    public void calculate()
    {
        //step1: calculate the number of days between check-in date and checkout date
        Integer stayDays = noOfDaysBetween(mStartDate,mEndDate);
        
        // step 2: for each day create a Daytariff object and insert into list 
        //          initialize Daytariff  with start date(date and time) and end date(+ 24 hours -1) ,room rate 
        //          next day will be 24 + next 24 hours
        //          till check out date
        
        DayTariff tempday;
        tempday = new DayTariff();
        tempday.setStartDate(mStartDate);
        long prevStartDateVal = mStartDate.getTime()+DAYCOUNTINHOURS;
        tempday.setEndDate( new Date(prevStartDateVal-1));
        tempday.setRoomrent(roomRent);
        mTotalTarrif.add(tempday);
        stayDays --;
        
        for(int i = 1; i<stayDays; i++)
        {
            tempday = new DayTariff();
            tempday.setStartDate(new Date(prevStartDateVal));
            prevStartDateVal = prevStartDateVal+DAYCOUNTINHOURS;
            tempday.setEndDate( new Date(prevStartDateVal-1));
            tempday.setRoomrent(roomRent);
            mTotalTarrif.add(tempday);
        
        }
        
        if(stayDays != 0)
        {
            tempday = new DayTariff();
            tempday.setStartDate(new Date(prevStartDateVal));        
            tempday.setEndDate(mEndDate);
            tempday.setRoomrent(roomRent);
            mTotalTarrif.add(tempday);
        }
        stayDays++;
        //step 3: Now insert all the extra bed charges one by one
        //        1> get the number of days extra bed used
        //        2> If start date is between the DayTraffic then Add extra charge
        //        3> set flag and rate also
           Bookingaddon tempextrabed;
            
           for(int j=0;j<mExtraBedList.size();j++)
           {
               
               tempextrabed = mExtraBedList.get(j);
               
               if(tempextrabed.getTodate() == null)
               {
                   tempextrabed.setTodate(new Date());
                   System.out.println("I dont come here!");
               }
               Integer numDaysExtraBedUsed =noOfDaysBetween(tempextrabed.getFromdate(),tempextrabed.getTodate());
               System.out.println("No of Days Extra Bed Used: " + numDaysExtraBedUsed);
               
               int extraindex = findEtraBedIndex(tempextrabed.getFromdate(),tempextrabed.getTodate(),stayDays.intValue());
               System.out.println("extraindex: " + extraindex);
               
               int numbeds = tempextrabed.getQty();
               System.out.println("numbeds: " + numbeds);
               if(extraindex != -1)
               {
                    insertExtraBedRate(extraindex,numDaysExtraBedUsed.intValue(),numbeds);
               }
           }
        mtotDaysTariff.calc(mTotalTarrif);
    }
    
    public boolean insertExtraBedRate(int extraindex,int numDaysExtraBedUsed,int numbeds)
    {
        DayTariff tempday ;
        for(int i =0;i<numDaysExtraBedUsed;i++)
        {
           tempday =  mTotalTarrif.get(i+extraindex);
           
           tempday.setNoOfExtraBed(numbeds);
           tempday.setExtraBedRent(mExtraRent.doubleValue());
        }
        
        return false;
    }
    
    public int  findEtraBedIndex(Date stDate,Date edDate,int stayDays)
    {
        DayTariff tempday ;
        for(int i = 0;i<stayDays;i++)
        {
            tempday = mTotalTarrif.get(i);
            if (tempday.between(stDate.getTime(),edDate.getTime()) == true)
            return i;
        }
        return -1;
    }
    
    public  Integer noOfDaysBetween(Date fromDate, Date toDate)
    {
        long sec = (toDate.getTime() - fromDate.getTime())/1000;
         int hours = (int) (sec/3600);
         Integer noofdaysint = 0;
         
         int extendlimit = 2;
         if(hours <= 24 + extendlimit)
         {
             noofdaysint = 1;
         }
         else
         {
             int rem = hours % 24;  
             noofdaysint = hours/24;
                if(rem > 1)
                {
                    noofdaysint++;
                }
         }
         
         return noofdaysint;
    }
    
    long DAYCOUNTINHOURS = 24*3600*1000;
    Date mStartDate;

    public List<DayTariff> getmTotalTarrif() 
    {
        return mTotalTarrif;
    }

    public TotalDaysTariff getMtotDaysTariff() 
    {
        return mtotDaysTariff;
    }
    
    Date mEndDate;
    BigDecimal roomRent;
    BigDecimal mExtraRent;
    List<Bookingaddon> mExtraBedList;
    List<DayTariff> mTotalTarrif;
    TotalDaysTariff mtotDaysTariff;
    
}
