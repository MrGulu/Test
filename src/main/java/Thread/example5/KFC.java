package Thread.example5;

import java.util.ArrayList;
import java.util.List;

public class KFC {

    //食物的种类
    String[] names = { "薯条", "烧板", "鸡翅", "可乐" };

    //生产的最大值，到达后可以休息
    static final int Max = 20;

    //存放食物的集合
    List<Food> foods = new ArrayList<Food>();

    // 生产食物的方法
    public void prod(int index) {
        synchronized (this) {
            // 如果食物数量大于20
            while (foods.size() > Max) {
                System.out.println("食材够了");
                this.notifyAll();//这个唤醒是针对生产者和消费者，有all
                try {
                    String name=Thread.currentThread().getName();
                    this.wait();//这个唤醒是针对生产者，没有all
                    System.out.println("生产者："+name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 开始生产食物食物//有一点要注意的
            System.out.println("开始生产食物");
            for (int i = 0; i < index; i++) {
                Food food = new Food(names[(int) (Math.random() * 4)]);
                foods.add(food);
                System.out.println("生产了" + food.getName() + foods.size());
            }
        }
    }

    // 消费食物的方法
    public void consu(int index) {
        synchronized (this) {
            while (foods.size() < index) {
                System.out.println("食材不够了");
                this.notifyAll();//这个唤醒是针对生产者和消费者，有all
                try {
                    String name=Thread.currentThread().getName();
                    this.wait();//这个唤醒是针对消费者，没有all
                    System.out.println("消费者："+name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 足够消费
            System.out.println("开始消费");
            for (int i = 0; i < index; i++) {
                Food food = foods.remove(foods.size() - 1);
                System.out.println("消费了一个" + food.getName() + foods.size());
            }
        }
    }
}
