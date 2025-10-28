/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Booking;

/**
 *
 * @author svastrad
 */
public class BookingMgr {
    
    private BookingMgr() {
        mCmi = new BookingCMI();
    }
    
    public static BookingMgr getInstance() {
        return BookingMgrHolder.INSTANCE;
    }
    
    private static class BookingMgrHolder {

        private static final BookingMgr INSTANCE = new BookingMgr();
    }
private BookingCMI mCmi;
}
