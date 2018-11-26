package utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/28.
 */

public class DuXMLDocUtil {
    /**
     * 生成短信请求报文
     */
    public static String buildRequestData(Map<String, String> patameterMap) {
        StringBuffer soapRequestData = new StringBuffer();

        soapRequestData.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");

        soapRequestData.append("<REQ>");
        //============================================================================
        soapRequestData.append("<HEAD>");

        soapRequestData.append("<ReqCode>").append(patameterMap.get("ReqCode")).append("</ReqCode>");
        soapRequestData.append("<MsgId>").append(patameterMap.get("MsgId")).append("</MsgId>");
        soapRequestData.append("<MsgNo>").append(patameterMap.get("MsgNo")).append("</MsgNo>");
        soapRequestData.append("<MsgRef>").append(patameterMap.get("MsgRef")).append("</MsgRef>");

        //获取当前机器日期\时间
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmssSSS");
        String strStamp=sdf.format(date);

        String workData = strStamp.substring(0, 8);
        String workTime = strStamp.substring(8, 14);

        soapRequestData.append("<WorkDate>").append(workData).append("</WorkDate>");
        soapRequestData.append("<WorkTime>").append(workTime).append("</WorkTime>");


        soapRequestData.append("</HEAD>");
        //============================================================================
        soapRequestData.append("<MSG>");

        soapRequestData.append("<SendMsg>").append(patameterMap.get("SendMsg")).append("</SendMsg>");
        soapRequestData.append("<SendNo>").append(patameterMap.get("SendNo")).append("</SendNo>");
        soapRequestData.append("<SendUser>").append(patameterMap.get("SendUser")).append("</SendUser>");
        soapRequestData.append("<SendDt>").append(patameterMap.get("SendDt")).append("</SendDt>");
        soapRequestData.append("<Type>").append("88").append("</Type>");

        soapRequestData.append("</MSG>");
        //============================================================================
        soapRequestData.append("</REQ>");
        return soapRequestData.toString();
    }

    /**
     * 发送xml请求
     */
    public static String sendXmlRequest(String url,String method, String soapRequestData) {
        Service service = new Service() ;

        Call call;
        String result="";
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(url) ;
            call.setOperationName(method) ;//ws方法名
            //一个输入参数,如果方法有多个参数,复制多条该代码即可,参数传入下面new Object后面
//			call.addParameter("parameter1",org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN) ;
//			call.addParameter("parameter2",org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN) ;
//			call.setReturnType(XMLType.XSD_STRING) ;
            call.setUseSOAPAction(true) ;

            result = (String) call.invoke(new Object[]{soapRequestData}) ;

            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
        return result.trim();
    }

    /**
     * 解析xml
     */
    public static Map<String,String> readStringXmlOut(String xml) {
        Map<String,String> map = new HashMap<String,String>();
        Document doc = null;
        // 将字符串转为XML
        try {
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称
            System.out.println("根节点：" + rootElt.getName());

            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator("HEAD");
            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                // 拿到head节点下的子节点title值
                String ErrorCode = recordEle.elementTextTrim("ErrorCode");
                System.out.println("ErrorCode:" + ErrorCode);
                map.put("ErrorCode", ErrorCode);

                String ErrorMsg = recordEle.elementTextTrim("ErrorMsg");
                System.out.println("ErrorMsg:" + ErrorMsg);
                map.put("ErrorMsg", ErrorMsg);
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
