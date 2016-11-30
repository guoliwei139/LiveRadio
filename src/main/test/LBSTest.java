import com.huashitu.util.LBSUtil.Distance2Points;
import com.huashitu.util.LBSUtil.Geohash;
import org.junit.Test;

/**
 * Created by levy on 2016/11/22.
 */
public class LBSTest {


    Distance2Points distance2Points = new Distance2Points();

    @Test
    public  void test1() {

        double lon1 = 109.0145193757;
        double lat1 = 34.236080797698;
        double lon2 = 108.9644583556;
        double lat2 = 34.286439088548;
        double dist;
        String geocode;

        dist = Distance2Points.GetDistance(lon1, lat1, lon2, lat2);
        System.out.println("两点相距：" + dist + " 米");

        Geohash geohash = new Geohash();

        geocode = geohash.encode(lat1, lon1);
        System.out.println("当前位置编码：" + geocode);

        geocode = geohash.encode(lat2, lon2);
        System.out.println("远方位置编码：" + geocode);

        double[] test = geohash.decode("s6q8mc6nbupd");
        for (int i = 0; i <test.length ; i++) {
            System.out.println(test[i]);
        }


    }
}
