/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import java.util.List;

import lodgeman.lalitman.model.pojo.*;
import static lodgeman.lalitman.model.pojo.EntityEnum.*;



/**
 *
 * @author svastrad
 */
public class BookingCMI {

    public BookingCMI() 
    {
        
    }

    public void downloadAll()
    {
        mRoomList = QueryManager.getInstance().findAll("Room.finall");
        mRoomTypeList = QueryManager.getInstance().findAll("Roomtype.findAll");
        mBooking = QueryManager.getInstance().findAll("Booking.findAll");
        mGuests =  QueryManager.getInstance().findAll("Guests.findAll");
        mRoomTariff = QueryManager.getInstance().findAll("Roomtariff.findall");
        mRoomamenities = QueryManager.getInstance().findAll("Roomamenities.findAll"); 
        mOutoforder =  QueryManager.getInstance().findAll("mOutoforder.findAll"); 
        mDnrlist = QueryManager.getInstance().findAll("Dnrlist.findAll");
        mIdentityprooftype = QueryManager.getInstance().findAll("Dnrlist.findAll");
        mBookingcalender = QueryManager.getInstance().findAll("Bookingcalender.findAll");
        
        System.out.println("Hello" + mRoomTypeList);
    }
    void insert(Object obj,EntityEnum type )
    {
        switch(type)
        {
            
            case BOOKINGCALENDER:
                mBooking.add((Booking) obj);
                break;
            case BOOKINGS:
            case GUESTS:
                mGuests.add((Guests) obj);
            case DNRLIST:
            case ROOM:
            case ROOMTYPE:
        }
    
    }
     void delete(Object obj,EntityEnum type )
    {
        switch(type)
        {
         // TO DO
         //DELETE the entries and one more method update need to be added    
            case BOOKINGCALENDER:
                             
            case BOOKINGS:
            case GUESTS:
                mGuests.add((Guests) obj);
            case DNRLIST:
            case ROOM:
            case ROOMTYPE:
        }
    
    }
     
    
    
    List<Room> mRoomList;
    List<Roomtype> mRoomTypeList;       
    List<Booking> mBooking;
    List<Guests> mGuests;
    List<Roomtariff> mRoomTariff;
    List<Roomamenities> mRoomamenities;
    List<Outoforder> mOutoforder;
    List<Dnrlist> mDnrlist;
    List<Identityprooftype> mIdentityprooftype;
    List<Bookingcalender> mBookingcalender;
    
}