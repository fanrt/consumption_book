package cn.fanrt.utils;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-31 23:39
 **/
public class JsonObj {

    /** 请求结果代码 **/
    private String code = ResponseContstants.RESULT_SUCCESS_CODE ;
    /** 请求结果提示信息 **/
    private String msg ;
    /** 请求结果的数据 **/
    private Object data ;

    public JsonObj() {

    }

    /**
     * @param code
     * @param msg
     * @param data
     */
    public JsonObj(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param code
     * @param msg
     * @param data
     */
    public JsonObj(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setResult(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public void setResult(String code, String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setFailResult(String msg){
        this.code =  ResponseContstants.RESULT_FAIL_CODE;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return ResponseContstants.RESULT_SUCCESS_CODE.equals(this.code);
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg 要设置的 msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data 要设置的 data
     */
    public void setData(Object data) {
        this.data = data;
    }
}
