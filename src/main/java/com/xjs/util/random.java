package com.xjs.util;

import java.text.DecimalFormat;
import java.util.Random;

public class random {
    private static final Random random = new Random();
    private static final DecimalFormat sixdf = new DecimalFormat("000000");
    private static final DecimalFormat eight = new DecimalFormat("00000000");

    //生成6位随机数
    public static String getSixRandom() {
        return sixdf.format(random.nextInt(1000000));
    }
    //生成8位随机数
    public static String getEightRandom() {
        return eight.format(random.nextInt(100000000));
    }
}
