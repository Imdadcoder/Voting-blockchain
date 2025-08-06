/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author fgfdg
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
 
public class HashMapExample 
{
    public static void main(String[] args) throws CloneNotSupportedException 
    {
        HashMap map = new HashMap();
         
        map.put(1,  "A");
        map.put(2,  "B");
        map.put(3,  "C");
        map.put(2,  "D");
        System.out.println(map);
        Iterator<Integer> itr = map.keySet().iterator();
 
while (itr.hasNext()) 
{
    Integer key = itr.next();
    String value = map.get(key).toString();
     
    System.out.println("The key is :: " + key + ", and value is :: " + value );
}
 
System.out.println("//Iterate over entries set");
 
Iterator<Entry<Integer, String>> entryIterator = map.entrySet().iterator();
 
while (entryIterator.hasNext()) 
{
    Entry<Integer, String> entry = entryIterator.next();
     
    System.out.println("The key is :: " + entry.getKey() + ", and value is :: " + entry.getValue() );
}
    }
}
