package Exception;

/**
 * @author tangwenlong
 */

public enum RspCustomEnum implements ICustomError {
    /**
     * 逗号隔开，最后用;结束
     */
    //返回错误信息
    ERR10001("抛出10001异常了，异常名称%s", "10001"),;

    private String message;

    private String code;

    /**
     * enum构造函数默认就是私有的
     */
    private RspCustomEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    @SuppressWarnings("all")
    public String getMessage(String... details) {
        String detailsMessage = this.message;
        try {
            detailsMessage = String.format(this.message, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailsMessage;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return message
     */
    public static String returnMsgWithCode(String code) {
        for (RspCustomEnum customEnum : RspCustomEnum.values()) {
            if (customEnum.getCode().equals(code)) {
                return customEnum.getMessage();
            }
        }
        return null;
    }
}
