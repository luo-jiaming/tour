package cn.edu.hlju.tour.common.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sole on 2017/3/24.
 */
public class RandomUtils {

    //不重复
    public static Set<Long> randomSet(Long total, Long length) {
        Set<Long> set = new HashSet<>();
        while (set.size() < length) {
            Long temp = (long)(Math.random()*total);
            set.add(temp);
        }
        return set;
    }

}
