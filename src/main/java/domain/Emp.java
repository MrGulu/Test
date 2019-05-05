package domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 */
//在Java中，如果没有提供任何构造函数，那么编译器会提供一个默认无参的构造函数；
//但是如果你指定了构造函数，且没有指定无参构造函数，那么这个类是没有构造函数的。
@Data
@ToString(exclude = {"testExclude1","testExclude2"})
//@ToString(of = {"testExclude1","testExclude2"})
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 1.@Data注解中包含@RequiredArgsConstructor注解，如果某个属性像下面testRequired
 *  一样使用@NonNull注解修饰，那么在使用构造器创建对象的时候，这个参数就是必须传入的！
 *  但是如果加了@Data注解，同时又加了@NoArgsConstructor注解或者@AllArgsConstructor注解，
 *  那么会覆盖掉@RequiredArgsConstructor注解的效果，就不需要任何参数或者需要全部参数，
 *  相当于只是有一个无参构造器或者全参构造器而没有任何其他方面的限制。
 *  但是，即使是覆盖了@RequiredArgsConstructor注解的效果，如果累的属性使用
 *  @NonNull 注解修饰，那么在构建这个对象的时候，如果没有指定这个非空的值，会抛出NPE！
 * 2.@Accessors(chain = true)的效果是可以通过emp.setName("张三").setAge(18).setSalary(9999.99);
 *  的方式进行链式的赋值。
 * 3.@ToString(exclude = {"testExclude1","testExclude2"})的效果是toString()方法中
 *  排除掉testExclude1和testExclude2这两个属性。
 *  @ToString(of = {"testExclude1","testExclude2"})的效果是toString()方法
 *  中只输出testExclude1和testExclude2这两个属性。
 * 4.使用了@Builder注解，就为本类实现了建造者(Builder)变种设计模式。但是在使用了其他构造函数
 *  的注解之后，这两种方式创建对象是同时存在的。
 */
public class Emp implements Serializable {
    private static final long serialVersionUID = -1652225403544482114L;
    private String name;
    private Integer age;
    private Double salary;
    private String testExclude1;
    private String testExclude2;
    @NonNull private String testRequired;
    private final String testFinal = null;
}
