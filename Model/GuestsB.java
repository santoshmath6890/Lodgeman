/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import lodgeman.lalitman.model.pojo.EntityEnum;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Identityprooftype;
import lodgeman.lalitman.model.pojo.QueryManager;

/**
 *
 * @author vijay
 */
public class GuestsB
{
    public static boolean newGuests(String name, String lastname, Long contactno, String address,String city,String state, String image, Identityprooftype identityprooftype,String idprooflen)
    { 
       Guests guests = new Guests(new Integer(0), name, lastname, contactno, address, city, state); 
       guests.setImage(image);
       guests.setIdprooftype(identityprooftype);
       guests.setIdproofno(idprooflen);
       
       QueryManager qm = QueryManager.getInstance();
       qm.Save(EntityEnum.GUESTS, guests);        
       System.out.println("Guests saved.");
       return false;   
    }  
}
