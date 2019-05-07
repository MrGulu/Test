package utils;

import java.util.Calendar;
import java.util.Random;

public class CreateSerialNumberUtil {

    /**
     * @return serialNumber
     * @Description 获取审批进度查询中的所需serialNumber
     */
    public static String createSerialNumber(String applicationCode) {
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String date = DateUtils.getDate2NumberString8(DateUtils.getCurrentDate());
        String serialNumber = applicationCode + date + Calendar.getInstance().getTimeInMillis() + rannum;
        return serialNumber;
    }

}
