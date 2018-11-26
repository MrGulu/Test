package utils;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2017/9/26.
 */
public class ConvertUtils {
    public static BigDecimal getBigDecimal(Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
           ret = new BigDecimal( value.toString());
        }
        return ret;
    }
}
