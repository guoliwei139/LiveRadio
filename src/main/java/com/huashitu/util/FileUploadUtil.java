package com.huashitu.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 编辑器上传图片 工具
 *
 * @author Alex_ 林佳宇
 */
public class FileUploadUtil {
    /**
     * 图片全局变量路径
     */
    public final static String NEWS_UPLOAD_PATH = "/layout/upload/img/news";
    public final static String PRODUCT_UPLOAD_PATH = "/layout/upload/img/product";

    private final static List<String> array;
    private static Logger logger = Logger.getLogger(FileUploadUtil.class);

    /**
     * ck 编辑器的图片上传 springMVC
     *
     * @param file 文件
     * @param path 上传的路径 使用全局变量
     * @return 新文件名
     * @throws IOException
     */
    public static String uploadImg(HttpServletRequest request, HttpServletResponse response, MultipartFile file,
                                   String path) throws IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String callback = request.getParameter("CKEditorFuncNum");//ck提交时候一个很重要的参数
            String expandedName = "";//文件扩展名
            //String fileName = file.getOriginalFilename();//上传的文件名
            String uploadContentType = file.getContentType();
            if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
                //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
                expandedName = ".jpg";
            } else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
                //IE6上传的png图片的headimageContentType是"image/x-png"
                expandedName = ".png";
            } else if (uploadContentType.equals("image/gif")) {
                expandedName = ".gif";
            } else if (uploadContentType.equals("image/bmp")) {
                expandedName = ".bmp";
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
                out.println("</script>");
                return null;
            }
            if (file.getSize() > 600 * 1024) {
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");
                out.println("</script>");
                return null;
            }
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = df.format(new Date()) + expandedName;//图片名字
            String realPath = request.getSession().getServletContext().getRealPath(path);
            File fileTwo = new File(realPath);
            if (!fileTwo.exists()) {
                fileTwo.mkdirs();
            }
            file.transferTo(new File(realPath + File.separator + fileName));

            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + path + "/" + fileName + "','')");
            out.println("</script>");
            return fileName;

        } catch (Exception e) {
            logger.error("编辑器图片上传错误");
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 非编辑器上传图片
     *
     * @param file
     * @param path
     * @return
     */
    public static String uploadImage(MultipartFile file, String path, HttpServletRequest request) {
        String expandedName = "";//文件扩展名
        String uploadContentType = file.getContentType();//获取图片的类型
        String fileName = file.getOriginalFilename();//图片名字
        expandedName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//得到图片扩展名字
        expandedName = expandedName.toLowerCase();

        if (array.contains(expandedName)) {
            try {
                fileName = RandCode.randCodeForImage() + "." + expandedName;//新图片名字
                String realPath = request.getSession().getServletContext().getRealPath(path);
                File fileTwo = new File(realPath);
                if (!fileTwo.exists()) {
                    fileTwo.mkdirs();
                }
                //file.transferTo(new File(realPath + File.separator + fileName));
                String a = realPath + File.separator + fileName;
                file.transferTo(new File(realPath + File.separator + fileName));
                return fileName;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                logger.error("上传图片错误");
            }

        }

        return fileName;
    }


    /**
     * 删除图片
     *
     * @param fileName
     * @param path
     */
    @SuppressWarnings("null")
    public static void deleteFile(String fileName, String path) {
        HttpServletRequest request = null;
        String realPath = request.getSession().getServletContext().getRealPath(path);
        File file = new File(realPath + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    static {
        array = new ArrayList<String>();
        array.add("jpg");
        array.add("jpeg");
        array.add("bmp");
        array.add("gif");
        array.add("png");
    }


    /**
     * 上传url图片
     */
    public static String uploadUrlImage(String imageUrl, String path, HttpServletRequest request) {
        String expandedName = imageUrl.substring(imageUrl.lastIndexOf(".") + 1, imageUrl.length());//获取图片的扩展名
        expandedName = expandedName.toLowerCase();
        String fileName = "";
        OutputStream os = null;
        InputStream is = null;
        if (array.contains(expandedName)) {
            try {
                fileName = RandCode.randCodeForImage() + "." + expandedName;//新图片名字
                String realPath = request.getSession().getServletContext().getRealPath(path); //保存路径
                File fileTwo = new File(realPath);
                if (!fileTwo.exists()) {
                    fileTwo.mkdirs();
                }
                URL url = new URL(imageUrl);
                URLConnection con = url.openConnection();
                is = con.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                String a = realPath + File.separator + fileName;
                os =  new FileOutputStream(realPath + File.separator + fileName);
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传图片错误");
            }finally {
                try {
                    os.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }
}