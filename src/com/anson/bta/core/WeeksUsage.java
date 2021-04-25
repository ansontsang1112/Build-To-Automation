package com.anson.bta.core;

import com.anson.bta.main.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeeksUsage {
    public static Double itemWeekUsage(String filter, ArrayList<ArrayList> rawData, int dayOfWeek) {
        //Map<String, Double> map = new HashMap<>();
        double result = 0;
        for(ArrayList<Map> data : rawData) {
            if(Integer.parseInt(data.get(1).get("Week").toString()) == dayOfWeek) {
                result += Double.parseDouble(data.get(2).get(filter).toString());
            }
        }
        return result;
    }

    public static Map averageItemUsagePerWeekDay(ArrayList<String> foodList, ArrayList<ArrayList> rawData) {
        Map<String, Double> map = new HashMap<>();
        for(String food : foodList) {
            for(int i = 0; i < 6; i++) {
                map.put(food, (double) Math.round(Utils.averageCalculator(5, itemWeekUsage(food, rawData, i))));
            }
        }
        return map;
    }

    public static Map averageItemUsagePerWeekEnd(ArrayList<String> foodList, ArrayList<ArrayList> rawData) {
        Map<String, Double> map = new HashMap<>();
        for(String food : foodList) {
            for(int i = 6; i < 8; i++) {
                if(Utils.averageCalculator(5, itemWeekUsage(food, rawData, i)) <= 1) {
                    map.put(food, Math.ceil(Utils.averageCalculator(5, itemWeekUsage(food, rawData, i))));
                } else {
                    map.put(food, (double) Math.round(Utils.averageCalculator(5, itemWeekUsage(food, rawData, i))));
                }
            }
        }
        return map;
    }
}
