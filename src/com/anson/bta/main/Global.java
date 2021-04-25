package com.anson.bta.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Global {
    public static Path folderLocation = Paths.get(System.getProperty("user.dir")+"\\Build-To-Automation");

    public static String defaultFoodList = "CINNAMON ROLL\n" +
            "BROWN SUGAR LOAF\n" +
            "CONDENSED MILK AND PEANUT BUTTER\n" +
            "CROISSANT\n" +
            "OMNIPORK LUNCHEON & EGG CHEESY\n" +
            "HAM & EGG CHEESY TOASTIE\n" +
            "CORN AND CHEESY CHAMPIGNON TOAST\n" +
            "CRAYFISH & EGG\n" +
            "CHICKEN & PINEAPPLE\n" +
            "OMNIPORK LUNCHEON & EGG MAYO\n" +
            "NEW YORK CHEESECAKE\n" +
            "COOKIES&CREAM CHEESECAKE\n" +
            "BLUEBERRY CHEESECAKE\n" +
            "MEAT CHEESY PIZZA\n" +
            "CHOCOLATE FUDGE";

    public static String defaultFoodListinChinese = "提子肉桂卷\n" +
            "黑糖全麥飽\n" +
            "厚多士\n" +
            "牛角飽\n" +
            "新餐肉芝味多士\n" +
            "火腿扒碎蛋芝味多士\n" +
            "蘑菇粟米芝味多士\n" +
            "小龍蝦蛋沙律意式飽\n" +
            "雞肉菠蘿意式飽\n" +
            "新餐肉蛋沙律意式飽\n" +
            "紐約芝士蛋糕\n" +
            "曲奇妙趣芝士蛋糕\n" +
            "藍莓芝士蛋糕\n" +
            "新餐肉芝味卷/薄餅\n" +
            "特濃朱古力蛋糕";

    public static Map<String, Integer> antiZeroCounter = new HashMap<>();

    public static String resultWorkBookName = "Build-To-Automation-ResultFile";
}
