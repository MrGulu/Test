package utils;

import java.util.regex.Pattern;

/**
 * Created by lenovo on 2017/12/5.
 */
public class FixedPhoneUtil {

    public static boolean isFixedPhone(String fixedPhone){
//        String reg="^(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
        String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, fixedPhone);
    }
}
