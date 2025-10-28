/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.Date;

/**
 *
 * @author vijay
 */
public class BookingView {

    public BookingView(Integer Id, Date CheckIn, Date CheckOut, String Status, Integer RoomNumber) 
    {
        this.Id = Id;
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
        this.Status = Status;
        this.RoomNumber = RoomNumber;
    }

    public Integer getId() 
    {
        return Id;
    }

    public void setId(Integer Id) 
    {
        this.Id = Id;
    }

    public Date getCheckIn() 
    {
        return CheckIn;
    }

    public void setCheckIn(Date CheckIn) 
    {
        this.CheckIn = CheckIn;
    }

    public Date getCheckOut() 
    {
        return CheckOut;
    }

    public void setCheckOut(Date CheckOut) 
    {
        this.CheckOut = CheckOut;
    }

    public String getStatus() 
    {
        return Status;
    }

    public void setStatus(String Status) 
    {
        this.Status = Status;
    }

    public Integer getRoomNumber() 
    {
        return RoomNumber;
    }

    public void setRoomNumber(Integer RoomNumber) 
    {
        this.RoomNumber = RoomNumber;
    }
    
    Integer Id;
    Date CheckIn;
    Date CheckOut;
    String Status;
    Integer RoomNumber;
}
