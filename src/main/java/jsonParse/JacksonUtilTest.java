package jsonParse;

import org.junit.Test;
import utils.JacksonUtil;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JacksonUtilTest {

    //map转javaBean
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

    /**
     * 测试jackson工具
     * 最后要转成json字符串的方法，最后调用的是同一个方法：
     * return objectMapper.writeValueAsString(Object obj);
     * 可以转化任何的java类型
     * 如果是json字符串转化成其他类型，就需要使用具体的方法，转成实体类、容器类等。
     */
    //根据list中map中的某个值对list中的map元素进行排序，字符串类型的"01"、"02"、"03"升序~
    @Test
    public void test1() {
        String s = "[\n" +
                "\t{\n" +
                "\t\t\"custName\": \"秦谦谦\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370828199202261626\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"个人测试个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843123651978&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0NzkyMyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxMjM2NTAyMTkz.51551b22fdf938d6e3a07e0588026cda&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370828199202261626\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"个人测试-委托扣款授权书\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000018\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843123862845&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiIyNjcwMDUyMzAyNjg2Njc0OTMiLCJjb250cmFjdElkIjoiIiwiZXhwaXJlQXQiOiIxNTYxNDQ3OTIzIiwiYWNjb3VudCI6Ijc0MDIwOTk3In0uMTU2MDg0MzEyMzg2MjEyMDE_.8932fcde7ed3727e46934b477c612de0&catalogId=267005230268667493&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286744\",\n" +
                "\t\t\"apptTyp\": \"01\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"custName\": \"测试哈哈哈\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370283199501114310\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"个人测试个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843147911432&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0Nzk0NyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxNDc5MTE1NDEx.0a0d73b4907fd3827fe1294e0e01f249&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286755\",\n" +
                "\t\t\"apptTyp\": \"03\"\n" +
                "\t},\n" +
                "\t\t{\n" +
                "\t\t\"custName\": \"唐文龙\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370283199501114310\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"个人测试个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843147911432&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0Nzk0NyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxNDc5MTE1NDEx.0a0d73b4907fd3827fe1294e0e01f249&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286755\",\n" +
                "\t\t\"apptTyp\": \"02\"\n" +
                "\t}\n" +
                "]";
        Set<Map> set = JacksonUtil.jsonToCollectionSet(s, Set.class, Map.class);
        System.out.println(set);
        System.out.println(JacksonUtil.objectToJson(set));
        System.out.println("*************************************");

        List<Map> maps = JacksonUtil.jsonToCollectionList(s, List.class, Map.class);
        maps = maps.stream()
                .sorted(Comparator.comparing(map -> map.get("apptTyp").toString()))
                .collect(Collectors.toList());
        System.out.println("*************************************");
        System.out.println(JacksonUtil.objectToJson(maps));
    }
}
