package proxy.JDKProxy;

import proxy.service.HelloWord;
import proxy.service.HelloWorldImpl;

public class Test {

    public static void main(String[] args) {
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWord proxy = (HelloWord) jdk.bind(new HelloWorldImpl());
        proxy.sayHelloWorld();
    }
}
