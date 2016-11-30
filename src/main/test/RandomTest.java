import com.huashitu.util.StringUtil;
import com.huashitu.util.ValueComparator;
import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by levy on 2016/11/18.
 */
public class RandomTest {


    @Test
    public void test1(){
        System.out.println(StringUtil.getNumByCurrTime(9));
    }

    @Test
    public void test2(){
        HashMap<String,Double> map = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);

        map.put("A",99.5);
        map.put("B",66.4);
        map.put("C",102.4);
        map.put("D",80.3);

        System.out.println("unsorted map: "+map);

        sorted_map.putAll(map);

        System.out.println("results: "+sorted_map);
    }
}
