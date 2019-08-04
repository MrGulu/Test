package String;

import java.util.HashSet;
import java.util.Iterator;

public class StringImmutable {
    public static void main(String[] args) {
        String s1 = " 13587645834";
        String s2 = " 18587645834";
        String s3 = " 17587645834";
        HashSet<String> set1 = new HashSet<>();
        set1.add(s1);
        set1.add(s2);
        set1.add(s3);
        System.out.println("set1:" + set1);
        /**
         * 并不能达到效果，因为String虽然是引用类型，但它是不可变类对象（immutable class）
         * 下面的s只是一个新的String类型的引用，虽然取的时候指向的是set1中对应位置的String对象，
         * 但是经过s = s.trim()操作之后，s就指向了s.trim()操作之后生成的一个新的String对象！
         * 但是原来位于set1中的String类型对象（s1、s2、s3）并不会发生改变！
         *
         * String 对象称为不可变的（只读），因为一旦创建了该对象，就不能修改该对象的值。
         * 有的时候看来似乎修改了，实际是string经过了特殊处理，每次改变值时都会建立一个新的string对象，
         * 变量会指向这个新的对象，而原来的还是指向原来的对象，所以不会改变。
         */
        for (String s :
                set1) {
            s = s.trim();
            System.out.println(s);
        }
        System.out.println("set1 trim:" + set1);
        /**
         * 下面是将trim操作之后生成的新的String对象添加到一个新的HashSet当中，没有问题。
         */
        HashSet<String> set2 = new HashSet<>();
        if (!set1.isEmpty()) {
            for (String s :
                    set1) {
                set2.add(s.trim());
            }
        }
        System.out.println("set2 new:" + set2);
        /**
         * 使用iterator可以进行删除，但是没有add方法，无法进行添加操作。
         * iterator方法，在调用next方法时，会检查count值，如果count值发生改变，则会抛出异常！
         * 这里说的发生改变，指的是在使用iterator遍历的过程中，使用了原集合对象进行add或者remove操作，
         * 并不是iterator在遍历过程中不能进行add或remove操作，比如listiterator对象，不光有remove方法，
         * 同时还有add方法可以添加元素。
         */
        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            iterator.remove();
            //如果放开，抛异常，但是不放开，就只能做删除操作。详情看ListLoopRemove.java类。
//            set1.add(s.trim());
        }
        System.out.println("set1 iterator:" + set1);
    }
}
