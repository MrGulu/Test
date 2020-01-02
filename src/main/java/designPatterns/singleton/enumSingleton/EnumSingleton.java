package designPatterns.singleton.enumSingleton;

/**
 * 枚举实现安全单例模式
 * @author tangwenlong
 */
public enum EnumSingleton {
    /**
     * User类单例对象
     */
    INSTANCE;

    private User user;

    EnumSingleton() {
        user = new User();
    }

    public User getInstance() {
        return user;
    }

    public static void main(String[] args) {
        User user = EnumSingleton.INSTANCE.getInstance();
        user.setName("tang").setAge(18).setAddress("China");
        System.out.println(user);
        //获取的还是同一个User对象，对其修改，都是修改同一个对象
        User user2 = EnumSingleton.INSTANCE.getInstance();
        user2.setName("yu").setAge(18).setAddress("China");
        System.out.println(user);
        System.out.println(user2);
        //true，说明同一个对象
        System.out.println(user == user2);
    }
}

