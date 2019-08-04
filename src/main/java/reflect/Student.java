package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@SuppressWarnings("all")
public class Student {
    private Integer id;
    public Integer age;
    private String sex;
    public String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //公有构造函数
    public Student(Integer id) {
        this.id = id;
    }
    //私有构造函数
    private Student(String name) {
        System.out.println("name:"+name);
    }
    //公有函数，无参数
    public void sayGood() {
        System.out.println("Good");
    }
    //公有函数，有参数
    public void sayWord(String s) {
        System.out.println("word:"+s);
    }
    //私有函数，无参数
    private void privateTest() {
        System.out.println("private");
    }
    //私有函数，有参数
    private void privateTest2(String s) {
        System.out.println("private param:"+s);
    }
    //私有函数，有参数带返回值
    private String privateTest3(Integer age) {
        return "abc";
    }
    public static void main(String[] args) {
        try {
            //测试构造方法  公有
            Student student = Student.class.getConstructor(Integer.class).newInstance(20144874);
            System.out.println("id:"+"\t"+student.getId());
            System.out.println("student:"+student);

            Class clazz = Student.class;
            Student student2 = (Student) clazz.getConstructor(Integer.class).newInstance(20144874);
            System.out.println("id:"+"\t"+student2.getId());
            System.out.println("student2:"+student2);

            Class clazz2 = Student.class;
            Constructor constructor = clazz2.getConstructor(Integer.class);
            Student student3 = (Student) constructor.newInstance(20144874);
            System.out.println("id:"+"\t"+student3.getId());
            System.out.println("student3:"+student3);
            //测试构造方法  私有
            Constructor constructor1 = Student.class.getDeclaredConstructor(String.class);
            constructor1.setAccessible(true);
            Object object = constructor1.newInstance("gulu");


            //测试方法公有  无参数
            Method method = Student.class.getMethod("sayGood");
            method.invoke(student3);
            //测试方法公有  有参数
            /**
             * 有参数的方法，当输入第一个方法名时，第二个参数自动生成
             */
            Method method1 = Student.class.getMethod("sayWord", String.class);
            method1.invoke(student3, "hello!");
            //测试方法私有  无参数
            Method method2 = Student.class.getDeclaredMethod("privateTest");
            method2.invoke(student3);
            //测试方法私有  有参数
            Method method3 = Student.class.getDeclaredMethod("privateTest2", String.class);
            method3.invoke(student3, "privateParam");
            //测试方法私有  有返回值
            Method method4 = Student.class.getDeclaredMethod("privateTest3", Integer.class);
            method4.setAccessible(true);
            String s = (String) method4.invoke(student3, 22);
            System.out.println(s);


            try {
                //测试成员变量 公有
                Field field = Student.class.getField("age");
                field.set(student3,23);
                System.out.println("验证student3 age："+student3.getAge());
                //测试成员变量 私有
                Field field1 = Student.class.getDeclaredField("sex");
                field1.setAccessible(true);
                field1.set(student3,"female");
                System.out.println("验证private sex："+student3.getSex());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
