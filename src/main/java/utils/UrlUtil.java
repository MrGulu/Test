package utils;

import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlUtil {

    private static final Logger log = Logger.getLogger(UrlUtil.class);

    public static String getShortUrl (String longUrl){

        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClientBuilder.create().build();
            String appkey = ApolloUtil.getConfig("param","sinaShortUrl.appkey");
            String sinaShortUrl = ApolloUtil.getConfig("param","sinaShortUrl.url");
            longUrl = longUrl.replaceAll("\r|\n", "");
            HttpPost httpPost = new HttpPost(sinaShortUrl);
            List<NameValuePair> params = new ArrayList();
            params.add(new BasicNameValuePair("source", appkey));
            params.add(new BasicNameValuePair("url_long", longUrl));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            response = httpclient.execute(httpPost);

            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonStr = jsonStr.replaceAll("[\\[\\]]", "");
            log.info("jsonStr = " + jsonStr);
            JSONObject json = JSONObject.fromObject(jsonStr);
            return json.getString("url_short");
        } catch (Exception e) {
            log.info("转换短链接失败! longUrl = " + longUrl);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return longUrl;
    }

    /**
     * 替换短信模板中的占位符,占位符为{1},{2}等
     * @param msgTemplate 短信模板
     * @param params 参数
     * @return
     */
    public static String getMsgContent (String msgTemplate, String...params){
        int i = 1;
        for (String param : params) {
            msgTemplate = msgTemplate.replaceAll("\\{" + i + "\\}", param);
            i++;
        }
        return msgTemplate;
    }

}
