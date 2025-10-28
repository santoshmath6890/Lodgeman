/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import java.util.Date;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Users;

/**
 *
 * @author svastrad
 */
public class MultiBooking 
{

    public MultiBooking(Guests guests, Date mStartDate, Date mEndDate, String mStatus, Integer NoOfPerson, Users user) {
        this.guests = guests;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mStatus = mStatus;
        this.NoOfPerson = NoOfPerson;
        this.user = user;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public Date getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Date getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(Date mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public Integer getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(Integer NoOfPerson) {
        this.NoOfPerson = NoOfPerson;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MultiBooking{" + "guests=" + guests + ", mStartDate=" + mStartDate + ", mEndDate=" + mEndDate + ", mStatus=" + mStatus + ", NoOfPerson=" + NoOfPerson + ", user=" + user + '}';
    }
    
    public boolean groupBooking()
    {
        return false;
    }
    
    public boolean doConvertBooking()
    {
        return false;
    }
    private Guests guests;
    private Date mStartDate;
    private Date mEndDate;
    private String mStatus;
    private Integer NoOfPerson;
    private Users user;
}
