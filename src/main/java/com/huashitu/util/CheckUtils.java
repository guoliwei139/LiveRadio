package com.huashitu.util;

import com.huashitu.common.system.ResCodeManager;
import com.huashitu.vo.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import net.sf.json.JSONObject;

//import com.midian.common.system.ErrorConstants;
//import com.midian.common.system.RetManager;

/**
 * 检查工具类
 *
 * @author Darling
 */
public class CheckUtils {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CheckUtils.class);

    final static Logger logger = LoggerFactory.getLogger(CheckUtils.class);
    final static String ANDROID_CLIENT_KEY = "A_U8L3B7kePj9Xj993Ee";
    final static String IOS_CLIENT_KEY = "I_6Aj7LJ3jiIo0bHJldj";
    final static long expire = 60 * 60 * 1000;// client_key过期时长

    /**
     * 校验客户端秘钥（client_key）
     *
     * @param client_key
     * @return
     */
    public static AjaxResult checkClientKey(String client_key) {
        // try {
        // client_key =URLDecoder.decode(client_key, "UTF-8");
        // } catch (UnsupportedEncodingException e) {
        // e.printStackTrace();
        // }
        // DES解密
        AjaxResult ajaxResult;
        String clientKey = null;
        Map<String,Object> hashMap = new HashMap<String,Object>();
        try {
            String s = client_key.replaceAll("%2B", "+");
            clientKey = EncryptionUtils.decryptString(s);
            // Key+time

            //String[] clientKeyArray = clientKey.split(",");

            String key = clientKey.substring(0, 20);
            String time = clientKey.substring(20);
            // 验证时间

            // 判断是否过期，返回错误
            if (new Date().getTime() - expire > Long.parseLong(time)) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.CLIENT_KEY_EXPIRE_ERROR);
            } else {
                // 验证Key
                if (ANDROID_CLIENT_KEY.equals(key) || IOS_CLIENT_KEY.equals(key)) {// android or ios
                    ajaxResult = new AjaxResult();

                    hashMap.put("key",key);

                    ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
                } else {
                    ajaxResult = new AjaxResult();
                    ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.CLIENT_KEY_ERROR);
                }
            }
        } catch (Exception e) {
            LOG.error("=====【CheckUtils】checkClientKey=====检查clientKey异常" + e.getMessage());
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.CLIENT_KEY_ERROR);
        }
        return ajaxResult;
    }

//	/**
//	 * 校验客户端秘钥（client_key）
//	 *
//	 * @param client_key
//	 * @param invokeFunc
//	 *            调用的函数，如果错误时日志中使用
//	 * @return
//	 */
//	public static JSONObject checkClientKey(String client_key, String invokeFunc) {
//		// try {
//		// client_key =URLDecoder.decode(client_key, "UTF-8");
//		// } catch (UnsupportedEncodingException e) {
//		// e.printStackTrace();
//		// }
//		// DES解密
//		String clientKey = EncryptionUtils.decryptString(client_key.replaceAll("%2B", "+"));
//
//		JSONObject json = new JSONObject();
//		// Key+time
//		//String[] clientKeyArray = clientKey.split(",");
//		String key = clientKey.substring(0, 20);
//		String time = clientKey.substring(20);
//		// 验证时间
//		if (new Date().getTime() - expire > Long.parseLong(time)) {// 判断是否过期，返回错误
//			logger.error("[" + invokeFunc + "]" + ErrorConstants.TIMEOUT);
//			RetManager.getInstance().setRetError(json, RetManager.common_client_key_expire);
//			return json;
//		}
//		if (ANDROID_CLIENT_KEY.equals(key)) {// android
//			return json;
//		} else if (IOS_CLIENT_KEY.equals(key)) {// ios
//			return json;
//		} else {
//			logger.error("[" + invokeFunc + "]" + ErrorConstants.CLIENT_KEY_ERROR);
//			RetManager.getInstance().setRetError(json, RetManager.common_client_key_error);
//		}
//		return json;
//	}
//
//	/**
//	 * 空传参检查
//	 *
//	 * @param map
//	 *            参数集合
//	 * @param invokeFunc
//	 *            调用的函数，如果错误时日志中使用
//	 * @return
//	 */
//	public static JSONObject checkParam(HashMap<String, String> map, String invokeFunc) {
//		JSONObject json = new JSONObject();
//		StringBuilder sb = new StringBuilder();
//		for(Entry<String, String> entry : map.entrySet()) {
//			if (StringUtils.isBlank(entry.getValue())) {
//				sb.append(entry.getKey()).append(",");
//			}
//		}
//		if (sb.length() > 0) {
//			String errMsg = "";
//			if(StringUtils.isNotBlank(invokeFunc)) {
//				errMsg += "[" + invokeFunc + "]";
//			}
//			errMsg += ErrorConstants.NOT_ENOUGH_PARAMS + "," + sb.substring(0, sb.length() - 1);
//			logger.error(errMsg);
//			RetManager.getInstance().setRet(json, RetManager.common_error,RetManager.common_invalid_param, RetManager.getInstance().getRetInfo(RetManager.common_invalid_param)+":"+sb.substring(0, sb.length() - 1));
//		}
//		return json;
//	}
//
//	/**
//	 * 传参检查
//	 *
//	 * @param map
//	 *            参数集合
//	 * @return
//	 */
//	public static JSONObject checkParam(HashMap<String, String> map) {
//		return checkParam(map, null);
//	}
//
	/**
	 * 获取解密后的client_key标识是从哪种客户端登录的
	 *
	 * @param client_key
	 * @return
	 */
	public static String getClientKey(String client_key) {
		// DES解密
		String clientKey = EncryptionUtils.decryptString(client_key.replaceAll("%2B", "+"));
		//String[] clientKeyArray = clientKey.split(",");
		String key = clientKey.substring(0, 20);
		if (ANDROID_CLIENT_KEY.equals(key)) {// android
			return "a";
		} else if (IOS_CLIENT_KEY.equals(key)) {// ios
			return "i";
		} else {
			return "";
		}
	}

//	public static void main(String[] args) {
////		System.out.println(EncryptionUtils.encryptString(ANDROID_CLIENT_KEY + new Date().getTime()));
//		JsonMapper jsonMapper = new JsonMapper();
//
//		AjaxResult ajaxResult = checkClientKey("33333333333333333333333333333");
//		System.out.println(jsonMapper.toJson(ajaxResult));
//	}
}
