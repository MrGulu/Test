package Proxy.JDKProxy;

public class Test {
    public void testJdkProxy()
    {
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWord proxy = (HelloWord) jdk.bind(new HelloWorldImpl());
        proxy.sayHelloWorld();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.testJdkProxy();
    }
}
