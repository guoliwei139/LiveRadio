package com.huashitu.util;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class StringUtil {
	//十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    /**
     * 使用MD5加密字符串
     * @param inputString
     * @return
     */
    public static String generatePassword(String inputString){
        return encodeByMD5(inputString);
    }
    
    /**
     * 对字符串进行MD5加密
     * @param originString
     * @return
     */
    private static String encodeByMD5(String originString){
    	String resultString = null;
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                resultString = byteArrayToHexString(results);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return resultString;
    }
    
    /** 
     * 转换字节数组为十六进制字符串
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
   /**
    * 将一个字节转化成十六进制形式的字符串
    * @param b
    * @return
    */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
    /**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 利用系统时间生成永不重复的N位数字
	 * @return
	 */
	public static String getNumByCurrTime(int num){
		String longTime = String.valueOf(System.currentTimeMillis());
		return longTime.substring(longTime.length()-num,longTime.length());
	}


	 /**
		 * 生成随机数字字符串
		 * @param length
		 * @return
		 */
		public static String getRandomNumString(int length) { // length表示生成字符串的长度
			String base = "0123456789";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			return sb.toString();
		}
//	/**
//	 * 获取图片描述
//	 * @param path 图片路径
//	 * @return
//	 */
//	public static String getImageTips(String path) {
//		if(StringUtils.isNotBlank(path)){
//			String target = "<img src=\"" + SysStatic.webRootPath + "/" + path + "\" />";
//			return StringUtil.replaceToHtmlQuot(target);
//		}else{
//			return "";
//		}
//	}
	
	/**
	 * 把字符串双引号转换成Html双引号
	 * @param tips 提示信息
	 * @return
	 */
	public static String replaceToHtmlQuot(String tips) {
		return replaceToHtmlQuot(tips, null, null);
	}
	
	/**
	 * 把字符串双引号转换成Html双引号
	 * @param tips 提示信息
	 * @param desc 描述
	 * @return
	 */
	public static String replaceToHtmlQuot(String tips, String desc){
		return replaceToHtmlQuot(tips, desc, null);
	}
	
	/**
	 * 把字符串双引号转换成Html双引号
	 * @param tips 提示信息
	 * @param desc 描述
	 * @param funcDetail js方法信息
	 * @return
	 */
	public static String replaceToHtmlQuot(String tips, String desc, String funcDetail){
		if(StringUtils.isBlank(desc)) {
			desc = "详情";
		}
		StringBuilder targetSb = new StringBuilder();
		if(StringUtils.isNotBlank(tips)){
			tips = tips.replaceAll("\"", "&quot;"); //将双引号替换为HTML的双引号
			//拼接提示HTML内容
			targetSb.append("<a name=\"demo-basic\" title=\"").append(tips).append("\"");
			if(StringUtils.isNotBlank(funcDetail)) {
				funcDetail = " style=\"cursor: pointer;\" onclick=\"" + funcDetail + "\"";
				targetSb.append(funcDetail);
			}
			targetSb.append(">").append(desc).append("</a>");
			
		}
		return targetSb.toString();
	}
	/**
	 * 微信支付中生成签名
	 * @param parameters 字典序后的键值对
	 * @return
	 */
//	public static String createSign(SortedMap<String,Object> parameters){
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        //得到参数拼接字符串
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = String.valueOf(entry.getKey());
//            String v = entry.getValue() != null ? String.valueOf(entry.getValue()) : "";
//            if(StringUtils.isNotBlank(v)) {
//                sb.append(k + "=" + v + "&");
//            }
////            sb.delete(sb.length() - 1, sb.length());
//        }
//        //新增
//        sb.append("key=" + ConstantUtil.PARTNER_KEY);
////        System.out.println("------待md5的的字符串信息---------"+sb.toString());
////        String sign = encodeByMD5(sb.toString()).toUpperCase();
//        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
////        System.out.println(sign);
//        return sign;
//    }
//
	/**
	 * 生成token
	 * @return md5加密的字符串
	 */
	public static String generateToken() {
		return generateToken("");
	}
	
	/**
	 * 生成token
	 * @param info token中包含的信息
	 * @return md5加密的字符串
	 */
	public static String generateToken(String info) {
		Long nowTime = System.currentTimeMillis();
		String randomString = getRandomString(8);
		return encodeByMD5(nowTime + info + randomString + nowTime.toString());
	}
	
	/**
	 * 根据文件名获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		String suffix = "";
		if (StringUtils.isNotBlank(fileName)) {
			suffix = fileName.substring(fileName.lastIndexOf("."));
		}
		return suffix;
	}
	/**
	 * 获取处理空信息后的值
	 * @param val 原来的值
	 * @return
	 */
	public static String getOpVal(Object val) {
		if(val == null || StringUtils.isBlank(String.valueOf(val))) {
			return "";
		}else{
			return String.valueOf(val);
		}
	}
	
    /**
     * 判断字符串是否为空	
     * @param str
     * @return 为空返回true
     */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
	}
	
	
	/**
     * 判断字符串是否为空	
     * @param str
     * @return 非空返回true
     */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	public static String generateOrderSn(){
		String orderSn = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		orderSn = sdf.format(calendar.getTime());
		orderSn +=  getRandomNumString(6);
		return orderSn;
	}



}

