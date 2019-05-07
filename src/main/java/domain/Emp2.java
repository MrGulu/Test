package domain;

import lombok.*;

import java.io.Serializable;

//@Data(staticConstructor = "staticConstructor")
@Setter
@Getter
@ToString
@RequiredArgsConstructor(staticName = "staticConstructor")
//@Accessors(chain = true,prefix = "f")
/**
 * 1.在使用@Data(staticConstructor = "staticConstructor")时，生成的构造函数就是private，
 *  并且有一个static的方法为staticConstructor，通过这个静态方法调用私有的构造函数完成对象创建。
 * 2.上面的方式其实与@RequiredArgsConstructor(staticName = "staticConstructor")这种方式
 *  效果是一样的。
 * 3.上面两种方式设置完成后，再创建对象的时候就是使用指定的静态方法了，此时如果@NonNull private String required;
 *  修饰了某个属性，那么在使用静态方法时，同时也需要传入这个参数！
 *
 */
public class Emp2 implements Serializable {
    private static final long serialVersionUID = -1652225403544482114L;
    private String name;
    private Integer age;
    @NonNull private String required;
//    /**prefix 若为true，则getter和setter方法会忽视属性名的指定前缀（遵守驼峰命名）*/
//    private Double fSalary;
}
