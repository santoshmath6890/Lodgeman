/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.util.Date;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Timersforlodgeman;

/*
 *
 * @author svastrad
 */
public class TimersForLodgeManMgr 
{
    private TimersForLodgeManMgr() 
    {
        timerModge = new Timersforlodgeman();
    }
    
    public static TimersForLodgeManMgr getInstance() 
    {
        return TimersForLodgeManMgrHolder.INSTANCE;
    }
    
    private Timersforlodgeman timerModge;
    
    private static class TimersForLodgeManMgrHolder 
    {
        private static final TimersForLodgeManMgr INSTANCE = new TimersForLodgeManMgr();
    }
    
    public static boolean validateTimer(Integer flexibleAmount, Date cmprDate)
    {
        Date currentDate = new Date();
        long inMin = ((currentDate.getTime() - cmprDate.getTime())/(1000 * 60));
        System.out.println("Minutes: " + inMin);
        if(inMin >= flexibleAmount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static boolean validateLoginTimer(Date logintime, Date usertime)
    {
        System.out.println("LoginTime: " + logintime);
        System.out.println("UserTime: " + usertime);
        long ilogin = ((usertime.getTime() - logintime.getTime())/(1000 * 60));
        System.out.println("iLogin: " + ilogin);
        if(ilogin < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public static boolean validateRange(Integer flexibleAmount, Date cmprDate)
    {
        Date currentDate = new Date();
        long inMin = ((currentDate.getTime() - cmprDate.getTime())/(1000 * 60));
        System.out.println("Minutes: " + inMin);
        
        if(inMin <= 0)
        {
            inMin = inMin * -1;
            System.out.println("Minutes: " + inMin);
            if(inMin >= flexibleAmount)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(inMin >= flexibleAmount)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
