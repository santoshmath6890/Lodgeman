/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import java.util.ArrayList;
import java.util.Collection;
import lodgeman.lalitman.view.CustomAccRpt;

/**
 *
 * @author RADHAKRISHNA
 */
public class DayCollectionBeanFactory {
    private static  ArrayList<DayCollectionBean> custBean = new ArrayList() ;
    private static DayCollectionBean accRpt[];
   public static void  insert(DayCollectionBean cr)
   {
       custBean.add(cr);
   }
   public static Object[] getBeanArray()
	{
            if(custBean.size()!= 0)
            {
            accRpt = new DayCollectionBean[custBean.size()];
            for(int i = 0 ;i<custBean.size();i++)
            {
               accRpt[i] = custBean.get(i);
            }
            
         return  accRpt;  
            }
            return null;
        }

   public static Collection getBeanCollection()
	{
       return custBean;
            
        }
    
}
