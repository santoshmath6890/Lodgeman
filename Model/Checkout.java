/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

/**
 *
 * @author svastrad
 */
public class Checkout {

    public Checkout(Integer mRoomno) {
        this.mRoomno = mRoomno;
    }

    public Integer getmRoomno() {
        return mRoomno;
    }

    public void setmRoomno(Integer mRoomno) {
        this.mRoomno = mRoomno;
    }

    @Override
    public String toString() {
        return "Checkout{" + "mRoomno=" + mRoomno + '}';
    }
    
    
    private Integer mRoomno;
}
