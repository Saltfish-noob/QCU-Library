package edu.qcu.utils;

import com.alibaba.druid.sql.visitor.functions.If;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RecommendUtils {


    //两两计算欧几里得距离
    public static Double getEuclideanMetric(Map<String, Double> x, Map<String, Double> y) {

        if (x.isEmpty() || y.isEmpty()) {
            return 0d;
        }
        //用于存放两者都有的数据
        List<Double> USER1ToUSER2 = new ArrayList<>();
        boolean haveSameBook = false;
        for (Map.Entry<String, Double> entry : x.entrySet()) {
            for (Map.Entry<String, Double> entry2 : y.entrySet()) {
                if (Objects.equals(entry2.getKey(), entry.getKey())) {
                    haveSameBook = true;
                    //先计算单独每项的差的成绩
                    Double a = entry2.getValue();
                    Double b = entry.getValue();
                    if (entry2.getValue() != null && entry.getValue() != null)
                        USER1ToUSER2.add(pow(entry2.getValue() - entry.getValue(), 2));
                }
            }
        }
        //如果两者没有相同的借阅记录则视为无关联
        if (!haveSameBook)
            return 0d;
        //存储根号下的结果
        Double sum = 0d;
        for (Double aDouble : USER1ToUSER2) {
            sum += aDouble;
        }
        //开根取倒数（加一是为了防止0作分母）
        double result = 1 / (1 + sqrt(sum));
        //由于样本较少所以强行降低权重
        if (USER1ToUSER2.size() < 2)
            result = result / 10d;
        return result;
    }
}
