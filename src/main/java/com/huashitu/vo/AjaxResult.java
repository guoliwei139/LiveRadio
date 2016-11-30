package com.huashitu.vo;

/**
 * json vo  返回结果
 * @author linjiayu
 *
 * @param <T>
 */
public class AjaxResult<T> {
//    private boolean success;
//    private String msg;
//
//    private boolean hasError;
//    private String error;
//
//    private T obj;
//    private List<T> list;
//
//    //-1：失败 ，1：成功
//    private int resCode;
//
//    private int total ; //数量
//
//    private int row ; //总数量

    private String ret;

    private String ret_code;

    private String ret_info;

    private T content;

    public AjaxResult() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_info() {
        return ret_info;
    }

    public void setRet_info(String ret_info) {
        this.ret_info = ret_info;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
