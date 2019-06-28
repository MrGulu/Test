package collection.array;

import org.junit.Test;

import java.util.*;

public class ArraysTest {
    @Test
    public void test1() {
        int[] intArray = new int[]{5,67,2,44,87};
        Arrays.sort(intArray);
        for (int i:intArray) {
            System.out.println(i);
        }
    }

    /**
     * 排序int数组
     *
     */
    @Test
    public void test2() {
        Integer[] intArray = new Integer[]{5,67,2,44,87};
        List<Integer> list = Arrays.asList(intArray);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //升序
                return o1-o2;
                //降序
//                return o1-o2;
            }
        });
        System.out.println(list);
    }

    /**
     * 排序List中包含Map
     */
    @Test
    public void test() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", "a");
        map1.put("name", "first");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", "c");
        map2.put("name", "last");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        Collections.sort(list,new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int ret = 0;
                //比较两个对象的顺序，如果前者小于、等于或者大于后者，则分别返回-1/0/1
//                升序
//                ret = o1.get("id").toString().compareTo(o2.get("id").toString());
//                降序
                ret = o2.get("id").toString().compareTo(o1.get("id").toString());
                return ret;
            }
        });
        System.out.println(list);
    }
}
