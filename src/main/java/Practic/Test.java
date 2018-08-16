package Practic;

public class Test {
    public void testProxy(){
        JDKProxy jdkProxy = new JDKProxy();
        Love loveProxy = (Love) jdkProxy.bind(new LoveImpl());
        loveProxy.sayLoveYou();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.testProxy();
    }
}
