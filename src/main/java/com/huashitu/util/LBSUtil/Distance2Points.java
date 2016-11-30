package com.huashitu.util.LBSUtil;

/**
 * 基于两点的经纬度来计算两点的距离类
 * Created by levy on 2016/11/22.
 */

public class Distance2Points {

    private static final double EARTH_RADIUS = 6371000;//赤道半径(单位m)


    /**
     * 转化为弧度(rad)
     */

    private static double rad(double d)

    {

        return d * Math.PI / 180.0;

    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     *
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     */

    public static double GetDistance(double lon1, double lat1, double lon2, double lat2)

    {

        double radLat1 = rad(lat1);

        double radLat2 = rad(lat2);

        double a = radLat1 - radLat2;

        double b = rad(lon1) - rad(lon2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));

        s = s * EARTH_RADIUS;

        s = Math.round(s * 10000) / 10000;

        return s;

    }

    /**
     * 比较二维数组最后一列中distance的值大小之后排序
     * @param array
     * @return
     */

    public static String[][] getOrder(String[][] array){

        for (int j = 0; j < array.length ; j++) {

            for (int bb = 0; bb < array.length - 1; bb++) {

                String[] ss;

                int a1=Integer.valueOf(array[bb][2]);  //转化成int型比较大小

                int a2=Integer.valueOf(array[bb+1][2]);

                if (a1>a2) {

                    ss = array[bb];

                    array[bb] = array[bb + 1];

                    array[bb + 1] = ss;



                }

            }

        }

        return array;

    }

}
