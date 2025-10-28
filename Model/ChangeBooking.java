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
public class ChangeBooking 
{

    public ChangeBooking(Integer mBookingID, Integer mRoomno, Date mpostponeDate, Date mBookingDate, String mCustomerName, Integer mCustomerNumber) {
        this.mBookingID = mBookingID;
        this.mRoomno = mRoomno;
        this.mpostponeDate = mpostponeDate;
        this.mBookingDate = mBookingDate;
        this.mCustomerName = mCustomerName;
        this.mCustomerNumber = mCustomerNumber;
    }

    public Integer getmBookingID() {
        return mBookingID;
    }

    public void setmBookingID(Integer mBookingID) {
        this.mBookingID = mBookingID;
    }

    public Integer getmRoomno() {
        return mRoomno;
    }

    public void setmRoomno(Integer mRoomno) {
        this.mRoomno = mRoomno;
    }

    public Date getMpostponeDate() {
        return mpostponeDate;
    }

    public void setMpostponeDate(Date mpostponeDate) {
        this.mpostponeDate = mpostponeDate;
    }

    public Date getmBookingDate() {
        return mBookingDate;
    }

    public void setmBookingDate(Date mBookingDate) {
        this.mBookingDate = mBookingDate;
    }

    public String getmCustomerName() {
        return mCustomerName;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public Integer getmCustomerNumber() {
        return mCustomerNumber;
    }

    public void setmCustomerNumber(Integer mCustomerNumber) {
        this.mCustomerNumber = mCustomerNumber;
    }

    @Override
    public String toString() {
        return "ChangeBooking{" + "mBookingID=" + mBookingID + ", mRoomno=" + mRoomno + ", mpostponeDate=" + mpostponeDate + ", mBookingDate=" + mBookingDate + ", mCustomerName=" + mCustomerName + ", mCustomerNumber=" + mCustomerNumber + '}';
    }
    
    public boolean doPostpone()
    {
        return false;
        
    }
    
    public boolean doCancel()
    {
        return false;
    }
    
    public boolean doReservationPostpone()
    {
       return (false);
        
    }
    
    public boolean doReservationPrepone()
    {
        return false;        
    }
    
    public boolean doReservationCancel()
    {
        return false;        
    }
    
    
   private Integer mBookingID;
   private Integer mRoomno;
   private Date mpostponeDate;
   private Date mBookingDate;
   private String mCustomerName;
   private Integer mCustomerNumber;    
}
