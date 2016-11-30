package com.huashitu.config;


import com.huashitu.util.JsonMapper;
import com.huashitu.vo.AjaxResult;

import java.io.Serializable;

/**
 * base处理
 * @author linjiayu
 * @param <T>
 * @param <ID>
 */
public abstract class BaseCRUDController<T,ID extends Serializable> {

    protected static JsonMapper mapper = JsonMapper.nonDefaultMapper();

    protected static AjaxResult ajaxlist = new AjaxResult();

    //protected  static AjaxResult result = null;
    //protected abstract BaseService<T , ID> getService();

//    protected final static AjaxResult SUCCESS_RESULT = new AjaxResult(1, true, "操作成功");
//
//    protected final static AjaxResult ERROR_RESULT = new AjaxResult(-1, false, "操作失败");

    protected final static String INDEX="index";
    protected final static String SESSION_ADMIN = "loginAdmin";
    protected final static String SESSION_USER = "loginUser";
    protected final static String ADMINWEB_URL_DIRECTORY="view/jsp/";

//    protected final static String RESULT_OK;
//
//    protected final static String RESULT_ERROR;

//    static{
//        RESULT_ERROR = mapper.toJson(ERROR_RESULT);
//        RESULT_OK = mapper.toJson(SUCCESS_RESULT);
//    }

    /**
     * 重定向配置器
     * @param url
     * @return
     */
    protected final static String CUWebDirectory(String url){
        return "redirect:/"+url;
    }

    //...
}
