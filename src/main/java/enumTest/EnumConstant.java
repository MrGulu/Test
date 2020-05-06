package enumTest;

import base.exception.IBussinessError;
import utils.StringUtils;

import java.util.Objects;

/**
 * @author tangwenlong
 * @description 枚举测试类
 * @link https://www.cnblogs.com/liaokailin/p/3196253.html
 */

public enum EnumConstant implements IBussinessError {
    /**
     * 必传字段校验
     */
    CONSTANT_10001("10001", "必传字段为空！"),
    CONSTANT_10002("10002", "必传字段%s为空！");

    private String code;
    private String msg;

    EnumConstant(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage(String... details) {
        if (Objects.isNull(details)) {
            return this.msg;
        }
        return String.format(this.msg, details);
    }

    public static String returnMsg(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new NullPointerException("param can not be null !");
        }
        for (EnumConstant enumConstant : values()) {
            if (code.equals(enumConstant.code)) {
                return enumConstant.msg;
            }
        }
        return "";
    }


    public static void main(String[] args) {
        System.out.println(EnumConstant.returnMsg("-99999"));
        System.out.println(EnumConstant.returnMsg("10001"));
        System.out.println("*********************************************************");
        System.out.println(EnumConstant.CONSTANT_10002.getMessage("idNo"));
    }
}
