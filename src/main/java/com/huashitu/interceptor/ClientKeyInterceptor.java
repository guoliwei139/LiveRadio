package com.huashitu.interceptor;

import com.huashitu.common.system.ResCodeManager;
import com.huashitu.util.CheckUtils;
import com.huashitu.util.JsonMapper;
import com.huashitu.vo.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by levy on 2016/11/17.
 */
public class ClientKeyInterceptor implements HandlerInterceptor {

    private List<String> excludedUrls;

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
//        String url = request.getRequestURI().replace(request.getContextPath(),"");
//        //检查请求网址是否属于不走拦截器的路径
//        if(excludedUrls != null && excludedUrls.size() > 0){
//            for(String uri : excludedUrls){
//                if(url.contains(uri)){
//                    return true;
//                }
//            }
//        }
        //获取请求中clientKey参数
        String clientKey = request.getParameter("clientKey");
        //检查clentKey
        if (StringUtils.isNotBlank(clientKey)) {
            AjaxResult ajaxResult = CheckUtils.checkClientKey(clientKey);
            if ("success".equals(ajaxResult.getRet())){
                System.out.println("=====【ClientKeyInterceptor】preHandle====clientKey验证成功"+clientKey);
               return true;
            }else {
                System.out.println("=====【ClientKeyInterceptor】preHandle====clientKey验证失败");
                response.getWriter().write(jsonMapper.toJson(ajaxResult));
                return false;
            }

        } else {
            AjaxResult ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.CLIENT_KEY_NULL_ERROR);
            response.getWriter().write(jsonMapper.toJson(ajaxResult));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
