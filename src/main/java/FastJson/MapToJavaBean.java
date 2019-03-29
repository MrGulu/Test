package FastJson;

import org.junit.Test;
import utils.JacksonUtil;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MapToJavaBean {

    @Test
    public void test() throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        String s = "{\n" +
                "\t\t\"assistToSign\": \"N\",\n" +
                "\t\t\"dataMap\": {\n" +
                "\t\t\t\"run_ind\": \"N\",\n" +
                "\t\t\t\"rest_ind\": \"N\",\n" +
                "\t\t\t\"usr_sts\": \"A\",\n" +
                "\t\t\t\"usr_name\": \"萧炎测试\",\n" +
                "\t\t\t\"is_rate\": \"N\",\n" +
                "\t\t\t\"ext_ind\": \"N\",\n" +
                "\t\t\t\"assist_to_sign\": \"N\",\n" +
                "\t\t\t\"instu_cde\": \"900000000\",\n" +
                "\t\t\t\"usr_tel\": \"18562310580\",\n" +
                "\t\t\t\"usr_cde\": \"xiaoyan\",\n" +
                "\t\t\t\"usr_id_no\": \"370285199110170019\",\n" +
                "\t\t\t\"usr_id_typ\": \"20\",\n" +
                "\t\t\t\"special_role\": \"01\",\n" +
                "\t\t\t\"usr_bch\": \"900000001\",\n" +
                "\t\t\t\"usr_password\": \"6F891A8CC5BD1B5675721501B59E54FF\",\n" +
                "\t\t\t\"manag_ind\": \"N\"\n" +
                "\t\t},\n" +
                "\t\t\"extInd\": \"N\",\n" +
                "\t\t\"instuCde\": \"900000000\",\n" +
                "\t\t\"isRate\": \"N\",\n" +
                "\t\t\"managInd\": \"N\",\n" +
                "\t\t\"moduleId\": \"SUsr\",\n" +
                "\t\t\"primaryKey\": [\n" +
                "\t\t\t\"instu_cde\",\n" +
                "\t\t\t\"usr_cde\"\n" +
                "\t\t],\n" +
                "\t\t\"restInd\": \"N\",\n" +
                "\t\t\"runInd\": \"N\",\n" +
                "\t\t\"specialRole\": \"01\",\n" +
                "\t\t\"tableName\": \"S_USR\",\n" +
                "\t\t\"usrBch\": \"900000001\",\n" +
                "\t\t\"usrCde\": \"xiaoyan\",\n" +
                "\t\t\"usrIdNo\": \"370285199110170019\",\n" +
                "\t\t\"usrIdTyp\": \"20\",\n" +
                "\t\t\"usrName\": \"萧炎测试\",\n" +
                "\t\t\"usrPassword\": \"6F891A8CC5BD1B5675721501B59E54FF\",\n" +
                "\t\t\"usrSts\": \"A\",\n" +
                "\t\t\"usrTel\": \"18562310580\"\n" +
                "\t}";
        Map map = JacksonUtil.jsonToMap(s);
        Usr usr = (Usr) JacksonUtil.convertMap(Usr.class, map);
        System.out.println(usr.toString());
    }
}
