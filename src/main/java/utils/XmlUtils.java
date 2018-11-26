/**
 *
 */
package utils;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.*;
import java.lang.annotation.Annotation;

/**
 * XML工具类
 *
 * @author wangqi
 */
public final class XmlUtils {

    private static XStream xstream;

    static {
        setXstream(new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))));
        getXstream().autodetectAnnotations(true);
    }

    public static String toXML(Object obj) {
        getXstream().aliasSystemAttribute(null, "class");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(outputStream, "UTF-8");
            getXstream().toXML(obj, writer);
            String xml = outputStream.toString("UTF-8");
            return xml;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromXML(String xml, Class responseClass) {

        Annotation annotation = responseClass.getAnnotation(XStreamAlias.class);
        String annotationValue = (String) AnnotationUtils.getValue(annotation);

        XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);

        xstream.alias(annotationValue,responseClass);

        return xstream.fromXML(xml);
    }

    public static String toXML(Object obj, Object... alias) {
        for (int i = 0; i < alias.length; i = i + 2) {
            getXstream().alias(alias[i].toString(), (Class) alias[i + 1]);
        }
        return getXstream().toXML(obj);
    }

    public static XStream getXstream() {
        return xstream;
    }

    public static void setXstream(XStream xstream) {
        XmlUtils.xstream = xstream;
    }


}
