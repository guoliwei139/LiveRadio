package com.huashitu.controller;

import com.huashitu.config.BaseCRUDController;
import com.huashitu.domain.Live;
import com.huashitu.service.live.ILiveService;
import com.huashitu.util.LBSUtil.Geohash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by levy on 2016/11/23.
 */

@Controller
@RequestMapping("/live")
public class LiveController extends BaseCRUDController {

    @Autowired
    ILiveService liveService;

    @RequestMapping(value = "/add_live",method = RequestMethod.GET)
    public @ResponseBody Object addLive(
            String clientKey,
            String anchorId,
            String title,
            String lon,
            String lat
    ){
        Geohash geohash = new Geohash();
        Live live = new Live();
        live.setAnchorId(Long.parseLong(anchorId));
        live.setTitle(title);
        live.setLon(Double.parseDouble(lon));
        live.setLat(Double.parseDouble(lat));
        live.setGeoLocation(geohash.encode(Double.parseDouble(lon),Double.parseDouble(lat)));
        live.setBeginTime(new Date());
        live.setOnlineStatus(1);
        liveService.insert(live);


        return  live;
    }


}
