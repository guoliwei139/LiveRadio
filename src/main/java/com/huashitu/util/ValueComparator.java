package com.huashitu.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by levy on 2016/11/22.
 */
public class ValueComparator implements Comparator<String> {

    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base){
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if (base.get(a) <= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
