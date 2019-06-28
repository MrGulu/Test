package practic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("调度真实对象之前的服务");
        method.invoke(target, args);
        System.out.println("调度真实对象之后的服务");
        return null;
    }
}
