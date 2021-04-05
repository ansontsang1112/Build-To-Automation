package com.anson.bta.manager;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.*;

public class DateManager {
    public static Map recordFileDate(HSSFSheet sheet) {
        int i = 0;
        String date = null;
        Map<String,String> map = new HashMap<>();
        String result = ExcelManager.readSpecificStringCell(sheet, 5, 1);

        for(String stringBuilder : result.split(" ")) {
            if(i == 2) {
                date = stringBuilder;
            }
            i++;
        }

        map.put("Date", date);
        return map;
    }

    public static Map recordFileWeek(HSSFSheet sheet) {
        Map<String, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance(Locale.TRADITIONAL_CHINESE);
        String rawDate = recordFileDate(sheet).get("Date").toString();
        ArrayList<Integer> list = new ArrayList<>();
        for(String value : rawDate.split("-")) {
            list.add(Integer.parseInt(value));
        }
        calendar.set(list.get(0),list.get(1),list.get(2));
        map.put("Week", calendar.get(Calendar.DAY_OF_WEEK));
        return map;
    }
}
