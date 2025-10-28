/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business;

/**
 *
 * @author vijay
 */
public class KeyValuePair 
{
    private final Integer key;
    private final String value;
    
    public KeyValuePair(Integer key, String value) 
    {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() 
    {
        return key;
    }
    
    public String getValue()
    {
        return value;
    }

    public String toString() 
    {
        return value;
    }    
    
}
