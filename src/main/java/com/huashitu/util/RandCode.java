package com.huashitu.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by jiayu on 2016/9/27.
 * @author linjiayu
 */
public class RandCode {

    /**
     *
     * @return
     */
    public static String randCodeForImage(){

        Format format = new SimpleDateFormat("yyyyMMdd");
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        int blen = base.length();
        Random random = new Random();
        String sRAND = "";
        for(int i = 1; i<= 5; i++){
            int start = random.nextInt(blen);
            sRAND += base.substring(start, start + 1);

        }
        return format.format(new Date()) + "-" + sRAND;
    }
}
