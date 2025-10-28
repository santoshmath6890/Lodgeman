/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;

/**
 *
 * @author svastrad
 */
public class CheckRoom 
{

    public CheckRoom(Date startDate, Date endDate, Integer NoOfDays, Integer LookForNextNoOfDay) 
    {
        roomNumberStatus = new HashMap<Integer,Boolean>();
       // mRoomStatusList = new ArrayList<RoomStatus>();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mNoOfDays = NoOfDays;
        this.mLookForNextNoOfDay = LookForNextNoOfDay;
        mRoomList = QueryManager.getInstance().findAll("Room.findAll");
        fillAllRoomListStatus();
    }
    public CheckRoom()
    {
        roomNumberStatus = new HashMap();
        fillAllRoomListStatus();
    }
    
    public Date getStartDate() 
    {
        return mStartDate;
    }

    public void setStartDate(Date startDate) 
    {
        this.mStartDate = startDate;
    }

    public Date getEndDate() 
    {
        return mEndDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.mEndDate = endDate;
    }

    public Integer getNoOfDays() 
    {
        return mNoOfDays;
    }

    public void setNoOfDays(Integer NoOfDays) 
    {
        this.mNoOfDays = NoOfDays;
    }

    public Integer getLookForNextNoOfDay() 
    {
        return mLookForNextNoOfDay;
    }

    public void setLookForNextNoOfDay(Integer LookForNextNoOfDay) 
    {
        this.mLookForNextNoOfDay = LookForNextNoOfDay;
    }
    
    public  Map Check(boolean isCurrent)
    {
      List<Room>  bookedRoomList; //getOccupiedRoomList
        if(isCurrent)
        bookedRoomList=  QueryManager.getInstance().getOccupiedRoomList(new Date());
       else
         bookedRoomList  =  QueryManager.getInstance().getCurrentBooking(mStartDate,mEndDate);
       for(int i =0;i<bookedRoomList.size();i++)
       {
           roomNumberStatus.put(bookedRoomList.get(i).getOid(),new Boolean(false));           
       }
       return roomNumberStatus;
    }
    
    
    public void fillAllRoomListStatus()
    {
        List<Integer> roomNumberlist =QueryManager.getInstance().getRoomNumberList();
         for(int i =0 ;i < roomNumberlist.size();i++)
       {         
           System.out.println("RoomList"+ roomNumberlist.get(i));
            //mRoomStatusList.add(new RoomStatus(mRoomList.get(i), mRoomList.get(i).getRoomtypeid(), true, null, 0));
            roomNumberStatus.put(roomNumberlist.get(i), new Boolean(true));
            
       }
    }
    
public Map  getResult()
 {
 return roomNumberStatus; 
 }
    
    private Date mStartDate;
    private Date mEndDate;
    private Integer mNoOfDays;
    private Roomtype mroomtype; 
    private Integer mLookForNextNoOfDay; 
    //private List<RoomStatus> mRoomStatusList;
    List<Room> mRoomList;
    
    HashMap<Integer,Boolean> roomNumberStatus;
}
