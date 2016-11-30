package com.huashitu.common.system;
import java.io.File;
import java.io.Serializable;

/**
 * 
 * @Title:系统中常量的定义类  
 * @Desription:系统中常量的定义类
 * @Company:MDKG
 * @ClassName:SysStatic.java
 * @Author:Mengfh
 * @CreateDate:2013-6-7 下午6:15:18  
 * @UpdateUser:Mengfh  
 * @Version:0.1
 */
@SuppressWarnings("serial") 
public class SysStatic implements Serializable{
	/**项目根路径*/
	public static String webRootPath;
	
	/**请求响应正常状态*/
	public static final Integer REQUEST_CODE_OK=1;
	/**请求响应错误状态*/
	public static final Integer REQUEST_CODE_ERROR=0;
	/**公用对象正常标识 1*/
	public static final Integer NORMALDELFLAG = 1;
	/**公用对象删除标识 2*/
	public static final Integer DELFLAG = 2;
	/** 禁用标识 0：正常*/
	public static final Integer ABLE=0;
	/** 禁用标识 0：禁用*/
	public static final Integer DISABLE=1;
	/**审核状态，通过：1*/
	public static final Integer VERIFY_YES = 1;
	/**审核状态，不：2*/
	public static final Integer VERIFY_NO = 2;
	
	public static final String FILE_ROOT_PATH="/usr/java/tomcat/commfile/byt/";
	/**对象类型-商家*/
	public static final Integer RECORDTYPE_SHOP=2;
	/**对象类型-圈子*/
	public static final Integer RECORDTYPE_BBS=1;
	/**服务器临时文件路径*/
	public static final String TEMP_FILE_PATH = System.getProperty("user.dir") + File.separator + "tempFile" + File.separator;
	

	
	/**推送类型，1：升级企业*/
	public static final String PUSH_TYPE_UPGRADE_EP = "1";
	/**推送类型，2：升级VIP*/
	public static final String PUSH_TYPE_UPGRADE_VIP = "2";
	/**推送类型，3：发送活动评论*/
	public static final String PUSH_TYPE_SEND_ACTIVITY_COMMENT = "3";
	/**推送类型，4：发送留言墙评论*/
	public static final String PUSH_TYPE_SEND_SHARE_COMMENT = "4";
	/**推送类型，5：投递简历已被企业查看*/
	public static final String PUSH_TYPE_RESUME_SEEN_BY_EP = "5";
	/**推送类型，6：删除简历*/
	public static final String PUSH_TYPE_DELETE_RESUME = "6";
	/**推送类型，7：删除招聘*/
	public static final String PUSH_TYPE_DELETE_RECRUIT = "7";
	/**推送类型，8：职场资讯*/
	public static final String PUSH_TYPE_JOB_NEWS = "8";
	/**推送类型，9：行业资讯*/
	public static final String PUSH_TYPE_INDUSTRY_NEWS = "9";
}
