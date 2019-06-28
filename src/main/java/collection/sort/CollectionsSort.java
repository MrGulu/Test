package collection.sort;

import java.util.*;

class Emp{
    private int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * @author tangwenlong
 */
public class CollectionsSort {
    private static List<Emp> empList;

    static {
        Emp emp1 = new Emp(2, "Guan YunChang");
        Emp emp2 = new Emp(1, "Zhang Fei");
        Emp emp3 = new Emp(3, "Liu Bei");
        empList = Arrays.asList(emp1, emp2, emp3);
    }

    public static void main(String[] args) {
        Integer[] intArray = new Integer[]{34, 1, 66, 23, 67, 12};
        List<Integer> intArrayList = Arrays.asList(intArray);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", "a");
        map1.put("name", "first");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", "c");
        map2.put("name", "last");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);
        System.out.println("**************开始排序*************");
        System.out.println("**********sortIntArrayWithCollections升序**********");
        sortIntArrayWithCollections(intArrayList);
        System.out.println("**********sortIntArrayWithListSort降序**********");
        sortIntArrayWithListSort(intArrayList);
        System.out.println("**********sortMapStringWithCollections升序**********");
        sortMapStringWithCollections(mapList);
        System.out.println("**********sortMapStringWithListSort降序**********");
        sortMapStringWithListSort(mapList);
        System.out.println("**********sortDomain升序**********");
        sortDomain(empList);
        System.out.println("**********sortDomainWithReversed降序**********");
        Comparator<Emp> comparator = new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                /*按员工编号正序排序*/
                return o1.getId() - o2.getId();
                /*按员工编号逆序排序*/
//                return o2.getId()-o1.getId();
                /*按员工姓名正序排序*/
                //return o1.getName().compareTo(o2.getName());
                /*按员工姓名逆序排序*/
                //return o2.getName().compareTo(o1.getName());
            }
        };
        Collections.sort(empList,comparator.reversed());
        System.out.println(empList);
    }

    public static void sortIntArrayWithCollections(List<Integer> list) {
        Collections.sort(list);
        System.out.println(list);
    }

    public static void sortIntArrayWithListSort(List<Integer> list) {
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //升序
//                return o1-o2;
                //降序
                return o2 - o1;
            }
        });
        System.out.println(list);
    }

    public static void sortMapStringWithCollections(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                //升序
                return o1.get("id").toString().compareTo(o2.get("id").toString());
            }
        });
        System.out.println(list);
    }

    public static void sortMapStringWithListSort(List<Map<String, Object>> list) {
        list.sort(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                //降序
                return o2.get("id").toString().compareTo(o1.get("id").toString());
            }
        });
        System.out.println(list);
    }

    public static void sortDomain(List<Emp> list) {
        Collections.sort(list, new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                /*按员工编号正序排序*/
                return o1.getId() - o2.getId();
                /*按员工编号逆序排序*/
                //return o2.getId()-o1.getId();
                /*按员工姓名正序排序*/
                //return o1.getName().compareTo(o2.getName());
                /*按员工姓名逆序排序*/
                //return o2.getName().compareTo(o1.getName());
            }
        });
        System.out.println(list);
    }
}
/**
 * 总结：

 1.对于String或Integer这些已经实现Comparable接口的类来说，可以直接使用Collections.sort方法传入list参数
 来实现默认方式（正序）排序；

 2.如果不想使用默认方式（正序）排序，可以通过Collections.sort传入第二个参数类型为Comparator来自定义排序规则；

 3.对于自定义类型(如本例子中的Emp)，如果想使用Collections.sort的方式一进行排序，
 可以通过实现Comparable接口的compareTo方法来进行，如果不实现，则参考第2点；

 4.jdk1.8的Comparator接口有很多新增方法，其中有个reversed()方法比较实用，是用来切换正序和逆序的。
 */






//接下来看看第一种形式的实现，首先让Emp类继承Comparable接口并重写compareTo方法（为了和上面的排序方式区别开，此次按照员工姓名逆序排列）：
//
//
//public class Emp implements Comparable<Emp>{
//
//    /*属性、getter/setter方法、toString方法及构造方法略*/
//    @Override
//    public int compareTo(Emp emp) {
//        /*按员工编号正序排序*/
//        //return this.getEmpno()-emp.getEmpno();
//        /*按员工编号逆序排序*/
//        //return emp.getEmpno()-this.getEmpno();
//        /*按员工姓名正序排序*/
//        //return this.getEname().compareTo(emp.getEname());
//        /*按员工姓名逆序排序*/
//        return emp.getEname().compareTo(this.getEname());
//    }
//}
//
//        使用Collections.sor方法的第一种形式实现：
//
//private static void sortEmpByDefaultMode()
//        {
//        System.out.println("before sort:");
//        PrintUtil.showList(empList);
//        System.out.println("=========================");
//        Collections.sort(empList);
//        System.out.println("after sort:");
//        PrintUtil.showList(empList);
//        }
