import com.github.pagehelper.PageHelper;
import com.huashitu.domain.Live;
import com.huashitu.domain.User;
import com.huashitu.service.device.IDeviceService;
import com.huashitu.service.indexPage.IFansAttentionService;
import com.huashitu.service.live.ILiveService;
import com.huashitu.service.pic.IPicService;
import com.huashitu.service.user.IUserService;
import com.huashitu.service.validateCode.IValidateCodeService;
import com.huashitu.util.LBSUtil.Geohash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Wellloi on 2016/10/9.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager" , defaultRollback = true)
public class UnitTest {
    @Autowired
    private IUserService userService;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IValidateCodeService validateCodeService;
    @Autowired
    private IPicService picService;
    @Autowired
    private IFansAttentionService fansAttentionService;
    @Autowired
    private ILiveService liveService;


    @Test
    public void selectTest(){
    User user = new User();
//        user.setId(84L);
        user.setAccount("aaaaaaaaaaaaaaaa");
        user.setLevel(0);
        user.setIncome(123);
        userService.insertUser(user);
        userService.insert(user);
        System.out.println(userService.selectOne(user));

        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void test2(){
        PageHelper.startPage(1,1);
        List<User> users = this.fansAttentionService.selectIdolByUserId(110L);
        System.out.println(users);
    }

    @Test
    public void test3(){
        Geohash geohash = new Geohash();
        Live live = new Live();
//        live.setId(1L);
//        live.setAnchorId(110L);
//        live.setTitle("测试直播");
//        live.setLon(34.123456);
//        live.setLat(130.123456);
//        live.setGeoLocation(geohash.encode(34.123456,130.123456));
//        live.setBeginTime(new java.util.Date());
//        liveService.insert(live);
        List<Live> select = liveService.selectOnlineLive(1);



    }

}
