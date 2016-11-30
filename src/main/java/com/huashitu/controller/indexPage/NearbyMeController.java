package com.huashitu.controller.indexPage;

import com.github.pagehelper.PageHelper;
import com.huashitu.common.system.ResCodeManager;
import com.huashitu.config.BaseCRUDController;
import com.huashitu.domain.Live;
import com.huashitu.service.live.ILiveService;
import com.huashitu.util.LBSUtil.Distance2Points;
import com.huashitu.util.ObjectToMap;
import com.huashitu.util.ValueComparator;
import com.huashitu.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by levy on 2016/11/22.
 */
@Controller
public class NearbyMeController extends BaseCRUDController {

    @Autowired
    ILiveService liveService;

    @RequestMapping(value = "/nearby", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getNearbyLive(
            String clientKey,
            String lon,
            String lat,
            String pageNum,
            String pageSize
    ) {

        double lon1 = Double.parseDouble(lon);
        double lat1 = Double.parseDouble(lat);
        //分页,获取在线直播列表
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Live> livingList = liveService.selectOnlineLive(1);
        Map distMap = new HashMap();
        for (Live living : livingList) {
            double lon2 = living.getLon();
            double lat2 = living.getLat();
            double dist = Distance2Points.GetDistance(lon1, lat1, lon2, lat2);
            distMap.put(String.valueOf(living.getId()), dist);

        }
        //按照dist距离值从小到达排序map
        ValueComparator sortDist = new ValueComparator(distMap);
        TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(sortDist);
        sortedMap.putAll(distMap);
        AjaxResult ajaxResult = new AjaxResult();

//        ObjectToMap.getinstance().objectToHashmap(ajaxResult, sortedMap);
        ResCodeManager.getInstance().setResSuccess(ajaxResult, sortedMap);

        return mapper.toJson(ajaxResult);
    }

}
