package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static String sNum = "零壹贰叁肆伍陆柒捌玖负";

    private static String sUni = "整分角圆拾佰仟万拾佰仟亿拾佰仟万拾佰仟亿拾佰仟万拾佰仟";


    /**
     * 判断字符串是否为空或null
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)
                || "undefined".equalsIgnoreCase(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str     判空字符串
     * @param message 字符串为空时抛出异常message
     * @return 返回非空字符串
     * @description 字符串为空抛出空指针异常
     */
    public static String requireNonEmpty(String str, String message) {
        if (isEmpty(str)) {
            throw new NullPointerException(message);
        }
        return str;
    }

    /**
     * @param str 要替换空白字符的字符串
     * @return String 替换空白字符之后的字符串
     * @description 去除字符串空白字符，不限于空格，包括首尾、中间
     */
    public static String replaceSpace(String str) {
        return str.replaceAll("\\s*", "");
    }

    /**
     * 判断地址的字符长度
     *
     * @param addr
     * @return
     */
    public static int getLength(String addr) {
        int length = 0;
        for (int i = 0; i < addr.length(); i++) {
            String subStr = addr.substring(i, i + 1);
            //汉字  空格 . 均占两个字符
            if (subStr.matches("[\\u4e00-\\u9fa5]") || subStr.matches("\\s") || ".".equals(subStr)) {
                length += 2;
            } else {
                length++;
            }

        }
        return length;
    }

    public static Double string2double(String v) {
        if (isEmpty(v))
            return null;
        try {
            return new Double(v);
        } catch (Exception e) {
        }
        return null;
    }

    public static int string2Int(String v, int defaultV) {
        if (isEmpty(v))
            return defaultV;
        try {
            return new Integer(v).intValue();
        } catch (Exception e) {
        }
        return defaultV;
    }

    public static long string2Long(String v, long defaultV) {
        if (isEmpty(v))
            return defaultV;
        try {
            return new Long(v).longValue();
        } catch (Exception e) {
        }
        return defaultV;
    }

    public static String getFileExtension(String fileName) {
        if ((fileName.lastIndexOf(".") > 0) && (fileName.lastIndexOf(".") < fileName.length() - 1)) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    public static String[] split(String paStr_Source, char paCha_seq, boolean paBoo_SeqTrim) {
        if (paStr_Source == null) {
            return null;
        }
        if (paStr_Source.equals("")) {
            return null;
        }

        while (paStr_Source.charAt(0) == paCha_seq) {
            paStr_Source = paStr_Source.substring(1);
        }

        while (paStr_Source.charAt(paStr_Source.length() - 1) == paCha_seq) {
            paStr_Source = paStr_Source.substring(0, paStr_Source.length() - 1);
        }

        return split(paStr_Source, new StringBuilder().append("").append(paCha_seq).toString());
    }

    public static String[] split(String paStr_Source, String paStr_seq, boolean paBoo_SeqTrim) {
        if ((paStr_Source == null) || (paStr_seq == null)) {
            return null;
        }

        while (paStr_Source.indexOf(paStr_seq) == 0) {
            paStr_Source = paStr_Source.substring(paStr_seq.length());
        }

        while (paStr_Source.indexOf(paStr_seq, paStr_Source.length() - paStr_seq.length()) > -1) {
            paStr_Source = paStr_Source.substring(0, paStr_Source.length() - paStr_seq.length());
        }

        if ((paStr_Source.equals("")) || (paStr_seq.equals(""))) {
            return null;
        }
        return split(paStr_Source, paStr_seq);
    }

    public static String[] split(String paStr_Source, char paCha_seq) {
        return split(paStr_Source, new StringBuilder().append("").append(paCha_seq).toString());
    }

    public static String[] split(String paStr_Source, String paStr_seq) {
        if ((paStr_Source == null) || (paStr_seq == null)) {
            return new String[0];
        }
        if ((paStr_Source.equals("")) || (paStr_seq.equals(""))) {
            return new String[0];
        }
        int int_ArraySize = subStringCount(paStr_Source, paStr_seq);

        if (int_ArraySize == -1) {
            return new String[0];
        }
        paStr_Source = new StringBuilder().append(paStr_Source).append(paStr_seq).toString();

        String[] str_RetArr = new String[int_ArraySize + 1];
        int int_pos = paStr_Source.indexOf(paStr_seq);
        int int_ArrayPos = 0;
        while (int_pos != -1) {
            str_RetArr[(int_ArrayPos++)] = paStr_Source.substring(0, int_pos);
            paStr_Source = paStr_Source.substring(int_pos + paStr_seq.length());
            int_pos = paStr_Source.indexOf(paStr_seq);
        }

        return str_RetArr;
    }

    public static int subStringCount(String paStr_Source, char paCha_seq) {
        if (paStr_Source == null) {
            return -1;
        }
        if ((paStr_Source.equals("")) || (paCha_seq == ' ') || (paCha_seq == 0)) {
            return -1;
        }

        int int_ret = 0;
        int int_pos = paStr_Source.indexOf(paCha_seq);
        while (int_pos != -1) {
            int_ret++;
            int_pos = paStr_Source.indexOf(paCha_seq, int_pos + 1);
        }

        return int_ret;
    }

    public static int subStringCount(String paStr_Source, String paStr_seq) {
        if ((paStr_Source == null) || (paStr_seq == null)) {
            return -1;
        }
        if ((paStr_Source.equals("")) || (paStr_seq.equals(""))) {
            return -1;
        }

        int int_ret = 0;
        int int_pos = paStr_Source.toUpperCase().indexOf(paStr_seq.toUpperCase());

        while (int_pos != -1) {
            int_ret++;
            int_pos = paStr_Source.toUpperCase().indexOf(paStr_seq.toUpperCase(), int_pos + paStr_seq.length());
        }

        return int_ret;
    }

    public static int subStringCount(String paStr_Source, String paStr_seq, boolean paBoo_case) {
        if ((paStr_Source == null) || (paStr_seq == null)) {
            return -1;
        }
        if ((paStr_Source.equals("")) || (paStr_seq.equals(""))) {
            return -1;
        }

        int int_ret = 0;
        int int_pos = paStr_Source.indexOf(paStr_seq);
        while (int_pos != -1) {
            int_ret++;
            int_pos = paStr_Source.indexOf(paStr_seq, int_pos + paStr_seq.length());
        }

        return int_ret;
    }

    public static String escape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (int i = 0; i < src.length(); i++) {
            char j = src.charAt(i);
            if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j))) {
                tmp.append(j);
            } else if (j < 'Ā') {
                tmp.append("%");
                if (j < '\020') {
                    tmp.append("0");
                }
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0;
        int pos = 0;

        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    char ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);

                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    char ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);

                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else if (pos == -1) {
                tmp.append(src.substring(lastPos));
                lastPos = src.length();
            } else {
                tmp.append(src.substring(lastPos, pos));
                lastPos = pos;
            }
        }

        return tmp.toString();
    }

    public static String stringArray2String(String[] a, String sepa) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                sb.append(sepa);
            }
            sb.append(a[i]);
        }
        return sb.toString();
    }

    public static String appendParam(String returnStr, String paramId, String paramValue) {
        if (!returnStr.equals("")) {
            if ((paramValue != null) && (!paramValue.equals(""))) {
                returnStr = new StringBuilder().append(returnStr).append("&").append(paramId).append("=").append(paramValue).toString();
            }
        } else if ((paramValue != null) && (!paramValue.equals(""))) {
            returnStr = new StringBuilder().append(paramId).append("=").append(paramValue).toString();
        }

        return returnStr;
    }

    public static String formatYuanToFen(String input) {
        String out = "";
        NumberFormat ft = NumberFormat.getInstance();
        try {
            Number nbInput = ft.parse(input);

            double fInput = nbInput.doubleValue() * 100.0D;

            ft.setGroupingUsed(false);
            ft.setMaximumFractionDigits(0);

            out = ft.format(fInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return out;
    }

    public static String formatFenToYuan(String input) {
        String out = "";
        NumberFormat ft = NumberFormat.getInstance();
        try {
            Number nbInput = ft.parse(input);

            double fInput = nbInput.doubleValue() / 100.0D;

            ft.setGroupingUsed(false);

            out = ft.format(fInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return out;
    }

    public static String getRandomString(int size) {
        char[] c = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[(Math.abs(random.nextInt()) % c.length)]);
        }
        return sb.toString();
    }

    public static String getRandomNumber(int size) {
        char[] c = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[(Math.abs(random.nextInt()) % c.length)]);
        }
        return sb.toString();
    }

    public static boolean isAIP(String ip) {
        Pattern patt = Pattern.compile("^([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])$");

        Matcher mat = patt.matcher(ip);
        return mat.matches();
    }

    public static String getOnePartOfIP(String ip, int index) {
        String result = null;
        if (!isAIP(ip))
            return null;
        String[] ipArr = ip.split("\\.");
        if (index <= ipArr.length)
            result = ipArr[(index - 1)];
        return result;
    }

    public static String getValueFromSnapshot(String snapshot, String key) {
        String[] snapshots = null;
        String[] pairs = null;
        String result = "";

        if ((snapshot == null) || (snapshot.trim().length() == 0)) {
            return "";
        }
        if ((key == null) || (key.trim().length() == 0)) {
            return "";
        }
        snapshots = split(snapshot, ';');
        for (int i = 0; i < snapshots.length; i++) {
            pairs = split(snapshots[i], '=');
            if ((pairs.length == 2) &&
                    (pairs[0].trim().equals(key.trim()))) {
                result = pairs[1];
                break;
            }

        }

        snapshots = null;
        pairs = null;
        return result;
    }

    public static String treatStringTrimAndLowerCase(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim().toLowerCase();
        return str;
    }

    public static String treatStringTrimAndUpperCase(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim().toUpperCase();
        return str;
    }

    public static boolean isAllNumbric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) < '0') || (str.charAt(i) > '9')) {
                return false;
            }
        }
        return true;
    }

    public static String getLastPartOfDomain(String domain) {
        if ((domain == null) || (domain.indexOf(".") == -1))
            return "";
        String[] domainArr = domain.split("\\.");
        return domainArr[(domainArr.length - 1)];
    }

    public static Map<String, String> getMap4KvString(String kvString, char splitor) {
        String[] snapshots = split(kvString, splitor);
        String[] pairs = null;
        Map result = new HashMap();
        for (int i = 0; i < snapshots.length; i++) {
            pairs = split(snapshots[i], '=');
            if (pairs.length == 2) {
                result.put(pairs[0], pairs[1]);
            }
        }
        return result;
    }

    public static String html(String content) {
        if (content == null)
            return "";
        String html = content;

        html = html.replaceAll("\t", "&nbsp;&nbsp;");
        html = html.replaceAll(" ", "&nbsp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\n", "<br/>");
        return html;
    }

    public static String getKvString4Map(Map<String, String> map, char splitor) {
        Iterator iter = map.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            sb.append(key);
            sb.append("=");
            sb.append((String) map.get(key));
            sb.append(splitor);
        }
        return sb.toString();
    }

    public static String getSafeString(String str) {
        String result = str;
        result = result.replace("{", "");
        result = result.replace("}", "");
        result = result.replace("[", "");
        result = result.replace("]", "");
        result = result.replace(",", "");
        result = result.replace("\"", "");
        result = result.replace("'", "");
        result = result.replace(":", "");
        result = result.replaceAll("\r\n", "\\\\r\\\\n");
        return result;
    }

    public static String getText(String key, Object[] values) {
        try {
            if (isEmpty(key)) {
                return key;
            }
            return new MessageFormat(key).format(values);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return key;
    }

    public static String decoder(String string) {
        try {
            if (!isEmpty(string))
                return URLDecoder.decode(string, "UTF-8");
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return "";
    }

    public static String r2c(String n0) {
        int L = n0.length();
        if (L > 27) {
            return "数值溢出";
        }
        StringBuffer sN = new StringBuffer();
        sN.append(n0);
        sN.deleteCharAt(L - 3);
        L--;
        StringBuffer sT = new StringBuffer();
        if (sN.charAt(L - 1) == '0') {
            sT.insert(0, '整');
        }
        boolean Ziro = false;
        boolean a = false;
        boolean b = false;
        int n = 0;
        for (int i = 1; i <= L; i += 1) {
            n = sN.charAt(L - i) - '0';
            if ((i == 7) || (i == 15)) {
                b = n == 0;
            }
            if (((i > 7 ? 1 : 0) & (i < 11 ? 1 : 0)) == 0) {
                if (((i > 15 ? 1 : 0) & (i < 19 ? 1 : 0)) == 0) ;
            } else b = n == 0 & b;

            a = (i == 1) || (i == 3) || (i == 7) || (i == 11) || (i == 15) || (i == 19) || (i == 23);

            if (n <= 0) {
                if (!(i > 1 & a)) ;
            } else if ((((i == 11) || (i == 19)) & b))
                sT.setCharAt(0, '亿');
            else {
                sT.insert(0, sUni.charAt(i));
            }

            if (((n == 0 ? 1 : 0) & ((Ziro) || (a) ? 1 : 0)) == 0) {
                sT.insert(0, sNum.charAt(n));
            }
            Ziro = n == 0;
        }
        if (n == 0) {
            sT.insert(0, 38646);
        }
        return sT.toString();
    }

    public static String getClassName(String wholeName) {
        int lastIndex = wholeName.lastIndexOf(".");
        return wholeName.substring(lastIndex + 1);
    }

    /**
     * 判断字符串是否中文姓名可以包含·
     *
     * @param str
     * @return
     */
    public static boolean isChineseName(String str) {

        if (str == null || str.length() == 0)
            return false;
        String str1 = str.replaceAll("·", "");
        if (str1.matches("[\\u4E00-\\u9FA5]{2,20}")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断字符串是否外文姓名 可以包含.和空格
     *
     * @param str
     * @return
     */

    public static boolean isForigenName(String str) {

        if (str == null || str.length() == 0)
            return false;

        str += " ";

        if (str.matches("(([A-Z]{1}[a-zA-Z]+(\\.)?)(\\s)+)+")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 返回一个UUID
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 返回一个UUID
     */
    public static String getNameUUIDFromBytes(byte[] bytes) {
        return UUID.nameUUIDFromBytes(bytes).toString().replace("-", "");
    }


}