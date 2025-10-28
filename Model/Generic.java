/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import lodgeman.lalitman.controller.backupandrestore.BackupAndRestore;
import lodgeman.lalitman.controller.backupandrestore.FileHide;
import lodgeman.lalitman.model.pojo.Users;

/**
 *
 * @author vijay
 */
public class Generic 
{
    private Generic()
    {
        
    }
    
    public static Generic getInstance() 
    {
        return GenericHolder.INSTANCE;
    }
    
    private static class GenericHolder 
    {

        private static final Generic INSTANCE = new Generic();
    }
    
    public static Integer noOfDaysBetween(Date fromDate, Date toDate)
    {
        long sec = (toDate.getTime() - fromDate.getTime())/1000;
         int hours = (int) (sec/3600);
         Integer noofdaysint = 0;
         
         int extendlimit = 2;
         if(hours <= 24 + extendlimit)
         {
             noofdaysint = 1;
         }
         else
         {
             int rem = hours % 24;  
             noofdaysint = hours/24;
                if(rem > 1)
                {
                    noofdaysint++;
                }
         }
         
         return noofdaysint;
    }
    
    public static Double roundOfDouble(Double value, Integer places)
    {
        if (places < 0) 
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    public static void databaseBackup() throws Exception
    {
        String path1 = giveMePath();
        Date currentDate = new Date();
        
        Integer Bdate = currentDate.getDate();
        Integer Bmonth = currentDate.getMonth();
        Integer Bminutes = currentDate.getMinutes();
        Integer Bsecs = currentDate.getSeconds();
        
        String path2 = path1 + "\\" + "Backup_" + Bdate + "_" + Bmonth + "_" + Bminutes + "_" + Bsecs + ".sql";

        BackupAndRestore db = new BackupAndRestore();
        db.backupDB("loademan", "root", "1234", path2);

        //encrypt it
        new FileHide("DES/ECB/PKCS5Padding", path2).encrypt();
            
        String path3 = path1 + "\\" + "dbdump_" + Bdate + "_" + Bmonth + "_" + Bminutes + "_" + Bsecs  + ".zip";
        db.zipIt("test.sql", path2 + ".enc", path3);

        try 
        {
            File file = new File(path2 + ".enc");
            File file2 = new File(path2);

            if (file.delete()) 
            {
                 System.out.println(file.getName() + " is deleted!");
            } 
            else 
            {
                System.out.println("Delete operation is failed.");
            }
             
            if (file2.delete()) 
            {
                System.out.println(file.getName() + " is deleted!");
            } 
            else 
            {
                System.out.println("Delete operation is failed.");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public static String giveMePath()
    {
        DirCreate dir = new DirCreate();
        Date inputP = new Date();
        String path = dir.findTargetDir(inputP);
        System.out.println("Path : " + path);
        //String path1 = path.replaceFirst("c", "C");
        return path;
    }
    
    public static String createDir(String base,String dirname)
    {
        dirname = base + "/" + dirname;
        try
        {      
            boolean mkirs = new File(dirname).mkdir();
            System.out.println("Directory created : " + mkirs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dirname;
   }
    
    public static String getFilename(Date currentDate)
    {
        return "" + currentDate.getDate() + "_" + currentDate.getMonth() + "_" + currentDate.getHours() + "_" + currentDate.getMinutes() + "_" + currentDate.getSeconds() + "_" ;
    }
   
}
