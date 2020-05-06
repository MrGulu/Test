package proxy.cglibProxy;

import proxy.service.HelloWord;
import proxy.service.HelloWorldImpl;

public class Test {

    public static void main(String[] args) {
        CglibProxyExample cglibProxyExample = new CglibProxyExample();
        HelloWord proxy = (HelloWord) cglibProxyExample.getProxy(HelloWorldImpl.class);
        proxy.sayHelloWorld();
    }
}
