package Exception;

import custom.exception.BusinessException;

public class TestSimple {
    public static void main(String[] args) {
        try {
            String s = null;
            s.toString();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
