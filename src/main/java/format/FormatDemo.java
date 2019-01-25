package format;


import java.text.DecimalFormat;

public class FormatDemo {

    public void format(String pattern,double value){
        DecimalFormat df=new DecimalFormat(pattern);
        String str=df.format(value);
        System.out.println("使用" + pattern+ "\t格式化数字"+value+"：\t" + str);
    }
    public static void main(String[] args) {
        FormatDemo demo=new FormatDemo();
        demo.format("###,###.###", 111222.34567);
        demo.format("###,###.###", 11222.34567);
        demo.format("000,000.000", 11222.34567);
        demo.format("###,###.###$", 111222.34567);
        demo.format("000,000.000￥", 11222.34567);
        demo.format("##.###%", 0.345678);        // 使用百分数形式
        demo.format("00.###%", 0.0345678);        // 使用百分数形式
        demo.format("###.###\u2030", 0.345678);    // 使用千分数形式

    }

}
