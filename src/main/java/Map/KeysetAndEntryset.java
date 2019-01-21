package Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class KeysetAndEntryset {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(8);
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        map.put("4", "D");
        keySet(map);
        System.out.println("------------------------------");
        entrySet(map);
    }

    static void keySet(Map map){
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String) map.get(key);
            System.out.println("Map.keySet() test:");
            System.out.println("key:"+key+";value:"+value);
        }
    }

    static void entrySet(Map map) {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Map.entrySet() test:");
            System.out.println("key:"+key+";value:"+value);
        }
    }
}
