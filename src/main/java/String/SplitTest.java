package String;

public class SplitTest {
    public static void main(String[] args) {
        String s = "I Love You!";
        String[] array = s.split(" ");
        for (String a :
                array) {
            prtString(a);
        }
    }

    static void prtString(String s) {
        System.out.println(s);
    }
}
