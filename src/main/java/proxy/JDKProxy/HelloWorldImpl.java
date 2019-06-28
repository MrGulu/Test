package proxy.JDKProxy;

public class HelloWorldImpl implements proxy.JDKProxy.HelloWord {
    @Override
    public void sayHelloWorld() {
        System.out.println("Hello World");
    }
}
