/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomtype;

/**
 *
 * @author svastrad
 */
public class RoomStatus {

    public RoomStatus(Room mRoomNo, Roomtype mRoomType, boolean mStatus, Guests mGuest, int mNumberOfFreeDays) {
        this.mRoomNo = mRoomNo;
        this.mRoomType = mRoomType;
        this.mStatus = mStatus;
        this.mGuest = mGuest;
        this.mNumberOfFreeDays = mNumberOfFreeDays;
    }

    public Room getmRoomNo() {
        return mRoomNo;
    }

    public void setmRoomNo(Room mRoomNo) {
        this.mRoomNo = mRoomNo;
    }

    public Roomtype getmRoomType() {
        return mRoomType;
    }

    public void setmRoomType(Roomtype mRoomType) {
        this.mRoomType = mRoomType;
    }

    public boolean ismStatus() {
        return mStatus;
    }

    public void setmStatus(boolean mStatus) {
        this.mStatus = mStatus;
    }

    public Guests getmGuest() {
        return mGuest;
    }

    public void setmGuest(Guests mGuest) {
        this.mGuest = mGuest;
    }

    public int getmNumberOfFreeDays() {
        return mNumberOfFreeDays;
    }

    public void setmNumberOfFreeDays(int mNumberOfFreeDays) {
        this.mNumberOfFreeDays = mNumberOfFreeDays;
    }

    @Override
    public String toString() {
        return "RoomStatus{" + "mRoomNo=" + mRoomNo + ", mRoomType=" + mRoomType + ", mStatus=" + mStatus + ", mGuest=" + mGuest + ", mNumberOfFreeDays=" + mNumberOfFreeDays + '}';
    }
    
    
private Room mRoomNo;
private Roomtype mRoomType;
private boolean  mStatus;
private Guests mGuest;
private int mNumberOfFreeDays;
}
