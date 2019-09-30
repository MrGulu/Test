package designPatterns.singleton.enumSingleton;

public class EnumTest {
    public static void main(String[] args) {
        User user = EnumSingleton.INSTANCE.getInstance();
        System.out.println(user);
    }
}
