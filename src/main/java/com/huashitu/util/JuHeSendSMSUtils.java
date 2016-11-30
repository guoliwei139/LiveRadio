package com.huashitu.util;

import com.huashitu.vo.ResSendValidateCodeVO;

import java.net.URLEncoder;

/**
 * 聚合短信发送工具类
 * @author Savage.M
 *
 */
public abstract class JuHeSendSMSUtils {
	/**AccessKey*/
	private final static String KEY = "3ce2ad70af26224727acc131ce167ca1";
	/**传输类型*/
	private final static String DTYPE = "json";
	/**注册短信模版 */
	public final static String TPL_ID_REGISTER = "20736";
	/**忘记密码短信模版 */
	public final static String TPL_ID_FORGET_PWD = "20737";
	/**其他操作短信模版 */
	public final static String TPL_ID_GET_CODE = "20738";
	/**项目签名*/
	private final static String APP_SIGN = "萌主";
	/**短信服务器接口地址*/
	private final static String URL = "http://v.juhe.cn/sms/send";
	
	/**
	 * 发送短信
	 * @param mobile 手机号
	 * @param code 验证码
	 * @param tplId 模板id
	 * @return
	 * @throws Exception
	 */
	public static boolean sendSMS(String mobile, String code, String tplId) throws Exception {
		StringBuffer param = new StringBuffer("key=").append(KEY).append("&mobile=");
		String tpl_value = ""; //模板占位参数值
		//注册模板类型的占位参数设置
		if (TPL_ID_REGISTER.equals(tplId)) {
			tpl_value = URLEncoder.encode("#app#=" + APP_SIGN + "&#code#=" + code, "UTF-8");
		} 
		//找回密码模板类型或获取验证码模板类型的占位参数设置
		else if (TPL_ID_FORGET_PWD.equals(tplId) || TPL_ID_GET_CODE.equals(tplId)) {
			tpl_value = URLEncoder.encode("#code#=" + code, "UTF-8");
		} 
		param.append(mobile).append("&tpl_id=").append(tplId).append("&dtype=").append(DTYPE);
		param.append("&tpl_value=").append(tpl_value);
		String resStr = HttpRequest.sendGet(URL, param.toString());
		JsonMapper jsonMapper = new JsonMapper();
		ResSendValidateCodeVO resSendValidateCodeVO = jsonMapper.fromJson(resStr, ResSendValidateCodeVO.class);
		return resSendValidateCodeVO.getError_code()==0;
	}
}
