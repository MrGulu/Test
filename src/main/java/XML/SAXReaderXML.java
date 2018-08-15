package XML;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public class SAXReaderXML {
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        File xmlfile = new File("D:/books.xml");
        String xml = "<books><book><author>Thomas</author><title>Java从入门到放弃</title><publisher>UCCU</publisher>" +
                "</book><book><author>小白</author><title>MySQL从删库到跑路</title><publisher>GoDie</publisher></book>" +
                "<book><author>PHPer</author><title>BestPHP</title><publisher>PHPchurch</publisher></book></books>";
        Document fileDocument = reader.read(xmlfile);//从xml文件获取数据
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));//读取xml字符串，注意这里要转成输入流
        Element root = document.getRootElement();//获取根元素
        List<Element> childElements = root.elements();//获取当前元素下的全部子元素

        for (Element child : childElements) {//循环输出全部book的相关信息
            List<Element> books = child.elements();
            for (Element book : books) {
                String name = book.getName();//获取当前元素名
                String text = book.getText();//获取当前元素值
                System.out.println(name + ":" + text);
            }
        }
        //获取第二条书籍的信息
        Element book2 = childElements.get(1);
        Element author = book2.element("author");//根据元素名获取子元素
        Element title = book2.element("title");
        Element publisher = book2.element("publisher");
        System.out.println("作者：" + author.getText());//获取元素值
        System.out.println("书名：" + title.getText());
        System.out.println("出版社：" + publisher.getText());
    }
}
