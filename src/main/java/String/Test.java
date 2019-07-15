package String;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ReadProperties;
import utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static final Logger logger = LoggerFactory.getLogger(Test.class);
    @org.junit.Test
    public void test() {
        Map map = new HashMap();
        try {
            map.put("QuestionJJCCBConfirm",new String(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties","questionJJCCBConfirm","CONF_HOME").getBytes("utf-8"),"utf-8"));
            System.out.println(map);
        } catch (UnsupportedEncodingException e) {
            logger.error("九江业务话术获取时异常！",e);
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test1() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(Double.valueOf(0.06));
        System.out.println(result);
    }

    /**
     * 34.2%转化成0.342
     * @throws ParseException
     */
    @org.junit.Test
    public void test2() throws ParseException {
        //字符串类型的百分数
        String str="34.2%";
        //NumberFormat是一个工厂，可以直接getXXX创建，而getPercentInstance()
        NumberFormat nf=NumberFormat.getPercentInstance();
        //提供了带有 ParsePosition 和 FieldPosition 的parse 和 format 方法的形式，parse(xx)表示解析给定字符串开头的文本，生成一个数值。   逐步地解析字符串的各部分
        Number m = nf.parse(str);
        System.out.println(m.toString());
    }

    /**
     * 0.06转化成6%
     * @throws ParseException
     */
    @org.junit.Test
    public void test3() throws ParseException {
        //字符串类型的百分数
        String str="0.06";
        //NumberFormat是一个工厂，可以直接getXXX创建，而getPercentInstance()
        NumberFormat nf=NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        //提供了带有 ParsePosition 和 FieldPosition 的parse 和 format 方法的形式，parse(xx)表示解析给定字符串开头的文本，生成一个数值。   逐步地解析字符串的各部分
        String m = nf.format(Double.valueOf(str));
        System.out.println(m);
    }

    /**
     * 0.06转化成6.00%
     */
    @org.junit.Test
    @SuppressWarnings("all")
    public void test4() {
        double result1 = 0.06;
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(result1);
        System.out.println(r);//great

        String applSeq = "1212";
        int timesLimit = 10;
        System.out.println("预审批业务：" + applSeq + "获取泰美斯结果发送接口次数未达到限制次数(" + timesLimit + "次）！仍可继续查询！");
    }

    @org.junit.Test
    public void test5() {
        String date = "2026.01.29";
        System.out.println(date.replaceAll("\\.", "-"));
        String date2 = "20260129";
        StringBuilder stringBuilder = new StringBuilder(date2);
        System.out.println(stringBuilder.insert(4, "-").insert(7, "-"));
    }

    @org.junit.Test
    public void test6() {
        int i = 123;
        int j = 22;
        System.out.println(Integer.valueOf(i).equals(j));
    }

    @org.junit.Test
    public void test7() {
        StringBuilder vviResultBuilder = new StringBuilder();
        if (StringUtils.isEmpty(vviResultBuilder.toString())) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

}
