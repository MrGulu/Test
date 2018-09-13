package Thread.example5;

public class Waiter extends Thread{
    KFC kfc;
    //KFC要传入，保证每一个服务员和用户在同一个KFC对象内
    public Waiter(KFC kfc) {
        this.kfc=kfc;
    }
    @Override
    public void run() {
        int size=(int)(Math.random()*5)+5;//每次生产的数量
        while (true) {
            kfc.prod(size);//传入每次生产的数量
        }

    }
}
