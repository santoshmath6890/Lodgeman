/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import java.util.Date;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.EntityEnum;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Users;

/**
 *
 * @author svastrad
 */
public class Booking 
{

    public Booking(Date mStartDate, Date mEndDate, Guests mGuest, String mStatus, Room mRoom, int mNumberOfPerson) 
    {
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mGuest = mGuest;
        this.mStatus = mStatus;
        this.mRoom = mRoom;
        this.mNumberOfPerson = mNumberOfPerson;
    }

    public Booking() 
    {
        
    }

    public Date getmStartDate() 
    {
        return mStartDate;
    }

    public void setmStartDate(Date mStartDate) 
    {
        this.mStartDate = mStartDate;
    }

    public Date getmEndDate() 
    {
        return mEndDate;
    }

    public void setmEndDate(Date mEndDate) 
    {
        this.mEndDate = mEndDate;
    }

    public Guests getmGuest() 
    {
        return mGuest;
    }

    public void setmGuest(Guests mGuest) 
    {
        this.mGuest = mGuest;
    }

    public String getmStatus() 
    {
        return mStatus;
    }

    public void setmStatus(String mStatus) 
    {
        this.mStatus = mStatus;
    }

    public Room getmRoom() 
    {
        return mRoom;
    }

    public void setmRoom(int roomNumer) 
    {
        QueryManager qm = QueryManager.getInstance();
        //TO DO: need to get the room object from room number (int)
        mRoom = (Room) qm.findAll("Room.findByOid");
        //this.mRoom = mRoom;
    }

    public int getmNumberOfPerson() 
    {
        return mNumberOfPerson;
    }

    public void setmNumberOfPerson(int mNumberOfPerson) 
    {
        this.mNumberOfPerson = mNumberOfPerson;
    }

    @Override
    public String toString() 
    {
        return "Booking{" + "mStartDate=" + mStartDate + ", mEndDate=" + mEndDate + ", mGuest=" + mGuest + ", mStatus=" + mStatus + ", mRoom=" + mRoom + ", mNumberOfPerson=" + mNumberOfPerson + '}';
    }
    
    public boolean doBooking(Date fromDate, Date fromTo, Guests guests, Users users, String status, Room room, Integer noOfPersons,Integer regno)
    {
        Bookings bookings = new Bookings(new Integer(0), status, noOfPersons);
        bookings.setFromdate(fromDate);
        bookings.setFromto(fromTo);
        bookings.setCustomerid(guests);
        bookings.setUserid(users);
        bookings.setRoomno(room);
        bookings.setRegno(regno);
        
       QueryManager qm = QueryManager.getInstance();
       qm.Save(EntityEnum.BOOKINGS, bookings);        
       System.out.println("Bookings saved.");
       
       return false;          
    }
    
    public boolean doVaildate()
    {
        
        
        
        return false;
    }
    
    
     
    private Date mStartDate;
    private Date mEndDate;
    private Guests mGuest;
    private  String mStatus;
    private Room mRoom;
    private int  mNumberOfPerson;    
}
