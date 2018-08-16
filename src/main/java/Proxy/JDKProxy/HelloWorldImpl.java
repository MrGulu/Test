package Proxy.JDKProxy;

public class HelloWorldImpl implements Proxy.JDKProxy.HelloWord {
    @Override
    public void sayHelloWorld() {
        System.out.println("Hello World");
    }
}
