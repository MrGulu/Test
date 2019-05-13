package base.domain;


import base.exception.IBussinessError;

/**
 * @Description:
 * @author: gongbin
 * @date: 2018/4/23 18:29
 * @system name:    工作流引擎
 * @copyright: 长安新生（深圳）金融投资有限公司
 */
public enum RspCodeEnum implements IBussinessError {

    /*
     * 返回错误信息
     * */
    //系统公共异常
    ERR99999("系统异常", "99999"),
    ERR99998("必传参数为空,或参数不符合规范！", "99998"),
    ERR99997("发送信贷异常", "99997"),
    ERR99996("操作数据库异常！", "99996"),
    ERR99995("接口发送失败！", "99995"),
    ERR99994("未查到数据！", "99994"),
    ERR99993("更新数据库失败,请重试！", "99993"),

    ERR99992("发送http请求失败！", "99992"),
    ERR99991("数据有误", "99991"),
    ERR99989("发送短信验证码失败", "99989"),
    ERR99987("获取报文头流水号失败", "99987"),
    ERR99986("获取报文头流水号模板为空", "99986"),
    ERR99988("必传参数%s为空！", "99988"),
    ERR99985("必传参数%s不合规！", "99985"),
    ERR99984("未查到有效数据！", "99984"),
    ERR99990("%s", "99990"),

    ERR95270("请在工作日%s-%s进行操作，感谢您的配合", "95270");


    private String message;
    private String code;

    private RspCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    // 根据code获取message
    public static String rtnMessage(String code) {
        for (RspCodeEnum ce : RspCodeEnum.values()) {
            if (ce.getCode().equals(code)) {
                return ce.getMessage();
            }
        }
        return null;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


    @Override
    public String getCode() {
        return this.code;
    }


    @Override
    public String getMessage(String... details) {
        String detailsMessage = this.message;

        try {
            detailsMessage = String.format(this.message, details);
        } catch (Exception e) {

        }
        return detailsMessage;
    }

}
