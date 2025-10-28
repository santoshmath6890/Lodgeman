/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Service;

import java.util.Date;
import lodgeman.lalitman.model.pojo.Foodorder;

/**
 *
 * @author vijay
 */
public class Foodorders 
{

    public Foodorders(Integer mBookingId, Integer mMenuItemId, Integer mQty, Integer mTotal, Date mOrderTime, Date mDeliveryTime) {
        this.mBookingId = mBookingId;
        this.mMenuItemId = mMenuItemId;
        this.mQty = mQty;
        this.mTotal = mTotal;
        this.mOrderTime = mOrderTime;
        this.mDeliveryTime = mDeliveryTime;
    }

    public Integer getmBookingId() {
        return mBookingId;
    }

    public void setmBookingId(Integer mBookingId) {
        this.mBookingId = mBookingId;
    }

    public Integer getmMenuItemId() {
        return mMenuItemId;
    }

    public void setmMenuItemId(Integer mMenuItemId) {
        this.mMenuItemId = mMenuItemId;
    }

    public Integer getmQty() {
        return mQty;
    }

    public void setmQty(Integer mQty) {
        this.mQty = mQty;
    }

    public Integer getmTotal() {
        return mTotal;
    }

    public void setmTotal(Integer mTotal) {
        this.mTotal = mTotal;
    }

    public Date getmOrderTime() {
        return mOrderTime;
    }

    public void setmOrderTime(Date mOrderTime) {
        this.mOrderTime = mOrderTime;
    }

    public Date getmDeliveryTime() 
    {
        return mDeliveryTime;
    }

    public void setmDeliveryTime(Date mDeliveryTime) 
    {
        this.mDeliveryTime = mDeliveryTime;
    }
    
    public boolean newFoodOrders()
    {
      System.out.println("Hello");
      return false;  
    }

    @Override
    public String toString() 
    {
        return "Foodorders{" + "mBookingId=" + mBookingId + ", mMenuItemId=" + mMenuItemId + ", mQty=" + mQty + ", mTotal=" + mTotal + ", mOrderTime=" + mOrderTime + ", mDeliveryTime=" + mDeliveryTime + '}';
    }  
    
    private Integer mBookingId;
    private Integer mMenuItemId;
    private Integer mQty;
    private Integer mTotal;
    private Date mOrderTime;
    private Date mDeliveryTime;
}
