package innerClass;

import lombok.extern.slf4j.Slf4j;

/**
 * 内部类和静态内部类的区别
 * <p>
 * 内部类：
 * 1、内部类中的变量和方法不能声明为静态的。
 * 2、内部类实例化：B是A的内部类，实例化B：A.B b = new A().new B()。
 * 3、内部类可以引用外部类的静态或者非静态属性及方法。
 * <p>
 * 静态内部类：
 * 1、静态内部类属性和方法可以声明为静态的或者非静态的。
 * 2、实例化静态内部类：B是A的静态内部类，A.B b = new A.B()。
 * 3、静态内部类只能引用外部类的静态的属性及方法。
 * <p>
 * inner classes——内部类
 * static nested classes——静态嵌套类
 * <p>
 * 其实人家不叫静态内部类，只是叫习惯了，从字面就很容易理解了。
 * 内部类依靠外部类的存在为前提，而静态嵌套类则可以完全独立，明白了这点就很好理解了。
 * <p>
 * <p>
 * 非静态内部类中的变量和方法不能声明为静态的原因:
 * <p>
 * 静态类型的属性和方法，在类加载的时候就会存在于内存中。使用某个类的静态属性和方法，
 * 那么这个类必须要加载到虚拟机中。但是非静态内部类并不随外部类一起加载，只有在实例化外部类之后才会加载。
 * <p>
 * 我们设想一个场景：在外部类并没有实例化，内部类还没有加载的时候如果调用内部类的静态成员或方法，
 * 内部类还没有加载，却试图在内存中创建该内部类的静态成员，就会产生冲突。
 * 所以非静态内部类不能有静态成员变量或静态方法
 *
 * @author tangwenlong
 */
@Slf4j
public class Outer {

    private static final String STATIC_FIELD = "STATIC_FIELD";


    private String outName = "outName";

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    private static String helloWorldOut() {
        return "helloWorldOut";
    }

    /**
     * 非静态内部类
     */
    class inner {

        private String innerName = "innerName";

        public String get() {
            return "inner访问:" +
                    "\n外部类普通成员：" + outName +
                    "\n外部类静态成员：" + STATIC_FIELD +
                    "\n外部类静态方法：" + helloWorldOut();
        }

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

//        public static String helloWorldInner() {
//            return "helloWorldInner";
//        }
    }

    /**
     * 静态内部类
     */
    static class staticInner {

        private String staticInnerName = "staticInnerName";

        public String get() {
            return "staticInner访问:" +
//                    "外部类普通成员：" + outName +
                    "\n外部类静态成员：" + STATIC_FIELD +
                    "\n外部类静态方法：" + helloWorldOut();
        }

        public String getStaticInnerName() {
            return staticInnerName;
        }

        public void setStaticInnerName(String staticInnerName) {
            this.staticInnerName = staticInnerName;
        }

        public static String helloWorldStaticInner() {
            return "helloWorldStaticInner";
        }
    }


    public static void main(String[] args) {

        Outer.inner inner = new Outer().new inner();
        log.info(inner.get());

        Outer.staticInner staticInner = new Outer.staticInner();
        log.info(staticInner.get());
        System.out.println("***********************************************************");
        Outer outer = new Outer();
        outer.setOutName("test");

        inner = outer.new inner();
        log.info(inner.get());

        staticInner = new Outer.staticInner();
        log.info(staticInner.get());
    }
}
