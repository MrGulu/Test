package base.domain;


import custom.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;

/**
 * Created by zhengxiang on 2017/8/7.
 */
@Data
@Accessors(chain = true)
public class JsonResponse {

    private String code = null;
    private String message = null;
    private Object data = null;


//    public String getCode() {
//        return code;
//    }
//
//    private void setCode(String code) {
//        this.code = code;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public JsonResponse(){
//
//    }

    /**
     * 执行成功，返回前台消息，无数据
     * （eg.新增成功，更新成功等）
     *
     * @param message 返回前台消息
     * @return
     */


    public static JsonResponse success(String message) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode("0");
        jsonResponse.setMessage(message);
        jsonResponse.setData(new HashMap<Object, Object>());
        return jsonResponse;

    }

    /**
     * 执行成功，返回前台消息，有数据
     * （eg.新增成功，更新成功等）
     *
     * @param message 返回前台消息
     * @param data    返回前台数据
     * @return
     */

    public static JsonResponse success(String message, Object data) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode("0");
        jsonResponse.setMessage(message);
        jsonResponse.setData(data);
        return jsonResponse;

    }


    /**
     * 非异常类错误，返回前台错误码，以及错误消息。
     * （eg：信息关联校验等）
     *
     * @param errcode 返回前台错误码
     * @param message 返回前台错误消息  //请规范编写八位错误码以及错误消息
     * @return
     */

    public static JsonResponse fail(String errcode, String message) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(errcode);
        jsonResponse.setMessage(message);
        jsonResponse.setData(new HashMap<Object, Object>());
        return jsonResponse;

    }

    /**
     * 非异常类错误，返回前台错误码，错误消息以及错误数据。
     * （eg：信息关联校验等）
     *
     * @param errcode 返回前台错误码
     * @param message 返回前台错误消息  //请规范编写八位错误码以及错误消息
     * @param data    封装的详细错误信息
     * @return
     */
    public static JsonResponse fail(String errcode, String message, Object data) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(errcode);
        jsonResponse.setMessage(message);
        jsonResponse.setData(data);
        return jsonResponse;

    }

    /**
     * 非异常类错误，返回前台错误码，以及错误消息。
     *
     * @param rspCodeEnum 错误码枚举类
     * @return
     */
    public static JsonResponse fail(RspCodeEnum rspCodeEnum) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(rspCodeEnum.getCode());
        jsonResponse.setMessage(rspCodeEnum.getMessage());
        jsonResponse.setData(new HashMap<Object, Object>());
        return jsonResponse;
    }

    /**
     * @param rspCodeEnum 枚举类型对象
     * @param args        要替换的可变字符串数组
     * @Description: 非异常类错误，返回前台错误码、错误信息（可变数组替换参数）
     * @Author: tangwenlong
     * @CreateDate: 2018/9/1 10:53
     */
    public static JsonResponse fail(RspCodeEnum rspCodeEnum, String... args) {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            String message = String.format(rspCodeEnum.getMessage(), args);
            jsonResponse.setMessage(message);
        } catch (Exception e) {
            throw new BusinessException("参数不合规范，转换异常！");
        }
        jsonResponse.setCode(rspCodeEnum.getCode());
        jsonResponse.setData(new HashMap<>());
        return jsonResponse;
    }

}


