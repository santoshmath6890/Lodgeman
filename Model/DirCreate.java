/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vijay
 */
public class DirCreate
{
    public  String findTargetDir(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mon = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        
        String base ="e:\\radhe\\lalitmahal\\dist\\res";
        
        String path =  base+"\\"+ year+"";
        path = path + "\\"+mnth[mon]+"";
        path = path +"\\"+day+"";
        return path;
    }
/*
   public static void main(String[] args) 
   {
      
      File f = null;
      boolean bool = false;
      Date todayDate = new Date();
      

     DirCreate dc = new DirCreate();
    dc.yearDir("c:/lalitmahal/dist/res",2013);
    dc.yearDir("c:/lalitmahal/dist/res",2014);
    dc.yearDir("c:/lalitmahal/dist/res",2015);
    dc.yearDir("c:/lalitmahal/dist/res",2016);
    dc.yearDir("c:/lalitmahal/dist/res",2017);
    dc.yearDir("c:/lalitmahal/dist/res",2018);
    dc.yearDir("c:/lalitmahal/dist/res",2019);
    dc.yearDir("c:/lalitmahal/dist/res",2020);
     
    System.out.println("The target dir is"+new DirCreate().findTargetDir(new Date()));

    
   }
   
    public void  yearDir(String base, int year)
   {
       createDir(base,""+year+"");
       base = base+"/"+""+year+"";
      
       for(int i=0;i<12;i++)
       {
           createDir(base,mnth[i]);
           String base1 = base+"/"+mnth[i];
           int days = getDayOfMonth(year,i);
           monthDir(base1,days);
       }
   
   }
   public int getDayOfMonth(int year,int month)
   {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    int numDays = calendar.getActualMaximum(Calendar.DATE);
   return numDays;
   }
   public void monthDir(String base,int len)
   {
   for(int i =1 ;i<=len;i++)
   {
       String t = ""+i+"";
       createDir(base,t);
   }
   }
    
   public void createDir(String base,String dirname)
   {
   try{      
         // returns pathnames for files and directory
         dirname = base+ "/"+ dirname;
          boolean mkirs = new File(dirname).mkdir();
         // create directories
      
         
         // print
         System.out.println("Directory created? "+mkirs);
         
      }catch(Exception e){
         // if any error occurs
         e.printStackTrace();
      }
   
   }
   */ 
   
    String[] mnth =  
    {
        "Jan","Feb","Mar","Apr","may","Jun","Jly","Aug","Sep","Oct","Nov","Dec"
    };
}
