package enumeration;

import java.util.*;
import java.util.Map.Entry;

public class Test {
    /**
     * 使用Enumeration遍历Vector和HashTable
     */
    @org.junit.Test
    public void test1() {
        System.out.println("-------------------------------------------------------");
        Vector<String> vector = new Vector();
        vector.add("tom");
        vector.add("jack");
        vector.add("jerry");
        System.out.println("vector content:\n"+vector.toString());
        System.out.println("-------------------------------------------------------");
        Enumeration e1 = vector.elements();
        while (e1.hasMoreElements()) {
            String value = e1.nextElement().toString();
            System.out.println(value);
        }
        System.out.println("-------------------------------------------------------");
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("key1", "value1");
        hashtable.put("key2", "value2");
        hashtable.put("key3", "value3");
        System.out.println("hashtable content:"+hashtable.toString());
        System.out.println("-------------------------------------------------------");
        System.out.println("keys() - returns an Enumeration of the keys of this Hashtable");
        Enumeration e2 = hashtable.keys();
        while (e2.hasMoreElements()) {
            String key = e2.nextElement().toString();
            System.out.println(key);
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("elements() - returns an Enumeration of the values of this Hashtable");
        Enumeration e3 = hashtable.elements();
        while (e3.hasMoreElements()) {
            String value = e3.nextElement().toString();
            System.out.println(value);
        }
        System.out.println("-------------------------------------------------------");
    }
    /**
     * 使用Iterator遍历Vector和HashTable
     */
    @org.junit.Test
    public void test2() {
        System.out.println("-------------------------------------------------------");
        Vector<String> vector = new Vector();
        vector.add("tom");
        vector.add("jack");
        vector.add("jerry");
        System.out.println("vector content:\n"+vector.toString());
        System.out.println("-------------------------------------------------------");
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next().toString();
            System.out.println(value);
        }
        System.out.println("-------------------------------------------------------");
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("key1", "value1");
        hashtable.put("key2", "value2");
        hashtable.put("key3", "value3");
        System.out.println("hashtable content:"+hashtable.toString());
        System.out.println("-------------------------------------------------------");
        System.out.println("keySet() - returns a Set of the keys");
        Iterator<String> iterator1 = hashtable.keySet().iterator();
        while (iterator1.hasNext()) {
            String key = iterator1.next();
            String value = hashtable.get(key);
            System.out.println("key: "+key+";value: "+value);
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("entrySet() - returns a Set of the mappings");
        Iterator<Map.Entry<String,String>> iterator2 = hashtable.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String,String> entry= iterator2.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key: "+key+";value: "+value);
        }
        System.out.println("-------------------------------------------------------");
    }
}
