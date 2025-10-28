/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;
import org.ini4j.Wini;
import java.io.File;
import java.io.IOException;

/**
 *ac 990
 * super delux 750
 * 
 * @author Hp Pc
 */
public class GstMgr {
    double slab1,slab2,slab3;
double tax1,tax2,tax3;
boolean m_init;

public void getRate(String filename) throws IOException
    {
        
       
        Wini ini = new Wini(new File(filename));
        slab1 = ini.get("GST", "slab1", double.class);
        tax1 = ini.get("GST", "tax1", double.class);
        slab2 = ini.get("GST", "slab2", double.class);
        tax2 = ini.get("GST", "tax2", double.class);
        slab3 = ini.get("GST", "slab3", double.class);
        tax3 = ini.get("GST", "tax3", double.class);
        
        
        
    }
    public int getTax(double total) {
       double tax = 0; 
       if((total < 50000) &&( total > slab3))
           tax = tax3;
        if((total < slab3) &&( total > slab2))
           total = tax3;
        if((total < slab2) &&( total > slab1))
            tax = tax2;
        
        if((total < slab1) &&( total > 0))
           tax = tax1;
    
       return (int)tax;
       
       
    }
   public  double CalculateTax(double total) {
       double tax = 0; 
       if((total < 50000) &&( total > slab3))
           tax = total *(tax3+10)*0.01;
        if((total < slab3) &&( total > slab2))
           total = total *tax3*0.01;
        if((total < slab2) &&( total > slab1))
            tax = total *tax2*0.01;
        
        if((total < slab1) &&( total > 0))
           tax = total *tax1*0.01;
    
       return tax;
       
       
    }
    private GstMgr() {
        
     m_init = false;
    }
      
    public static GstMgr getInstance() {
        
        
      
        if(!GstMgrHolder.INSTANCE.m_init)
        {
            GstMgrHolder.INSTANCE.m_init = true;
            try {
                GstMgrHolder.INSTANCE.getRate("c:\\Tax.ini");
            }
            catch(Exception e)
            {
             
            }
        }
        return GstMgrHolder.INSTANCE;
    }
    
    private static class GstMgrHolder {

        private static final GstMgr INSTANCE = new GstMgr();
        
    }
}
