package designPatterns.singleton.enumSingleton;

/**
 * 枚举实现安全单例模式
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
    }
}

