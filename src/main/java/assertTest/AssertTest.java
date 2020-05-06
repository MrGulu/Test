package assertTest;

import org.junit.Test;

/**
 * @author tangwenlong
 * @description link： https://blog.csdn.net/u010142437/article/details/51388082
 * 一、语法形式：
 * Java2在1.4中新增了一个关键字：assert。在程序开发过程中使用它创建一个断言(assertion)，它的
 * 语法形式有如下所示的两种形式：
 * 1、assert condition;
 * 这里condition是一个必须为真(true)的表达式。如果表达式的结果为true，那么断言为真，并且无任何行动
 * 如果表达式为false，则断言失败，则会抛出一个AssertionError对象。这个AssertionError继承于Error对象，
 * 而Error继承于Throwable，Error是和Exception并列的一个错误对象，通常用于表达系统级运行错误。
 * 2、asser condition:expr;
 * 这里condition是和上面一样的，这个冒号后跟的是一个表达式，通常用于断言失败后的提示信息，说白了，
 * 它是一个传到AssertionError构造函数的值，如果断言失败，该值被转化为它对应的字符串，并显示出来。
 * <p>
 * <p>
 * 要想让断言起效用，即让断言语句在运行时确实检查，在运行含有assert的程序时，必须指定-ea选项
 * 如：为了能够让上面的程序运行，我们执行下面代码：
 * <p>
 * java -ea TestAssert
 * 在IDEA中在VM选项中添加-ea启用断言，也可以使用-da选项使断言无效（默认为无效），
 * 同样，也可以通过在-ea或-da后面指定包名来使一个包的断言有效或无效。
 * 例如，要使一个com.test包中的断言
 * 无效，可以使用：
 * -da:com.test
 * 要使一个包中的所有子包中的断言能够有效或无效，在包名后加上三个点。例如：
 * -ea:com.test...
 * <p>
 * 三、注意事项：
 * 理解断言最重要的一点是必须不依赖它们完成任何程序实际所需的行为。
 * 理由是正常发布的代码都是断言无效的，即正常发布的代码中断言语句都不不执行的（或不起作用的），如果一不小心，我们可以错误地使用断言
 */
public class AssertTest {

    @Test
    public void test1() {
        assertTest(null);
    }

    private static void assertTest(String s) {
        assert s != null : "s should not be null!";
        System.out.println(s);
    }
}
