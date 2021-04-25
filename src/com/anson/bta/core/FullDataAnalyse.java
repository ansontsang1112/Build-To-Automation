package com.anson.bta.core;

import com.anson.bta.manager.CategoryManager;
import com.anson.bta.manager.DateManager;
import com.anson.bta.manager.ExcelManager;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullDataAnalyse {
    public static ArrayList dataList(String path, ArrayList<String> fileList) throws IOException {
        ArrayList<ArrayList> list = new ArrayList<>();
        for(String file : fileList) {
            //Init Sheets
            HSSFSheet sheet = ExcelManager.sheetManager(path, file);
            FormulaEvaluator formulaEvaluator = ExcelManager.formulaEvaluator(path, file);

            //Init Food Map
            Map<String, Double> foodsMap = new HashMap<>();

            //Get All Food category
            ArrayList<String> foods = CategoryManager.foodCategory;

            //Filter All Types of Food and Get the Result
            ArrayList<Map> resultPerDay = new ArrayList<>();

            //Get Date & Weeks (Sheet)
            resultPerDay.add(DateManager.recordFileDate(sheet));
            resultPerDay.add(DateManager.recordFileWeek(sheet));

            for(String filter : foods) {
                foodsMap.put(filter, ExcelManager.resultManager(formulaEvaluator, sheet, filter));
            }

            resultPerDay.add(foodsMap);
            list.add(resultPerDay);
        }
        
        return list;
    }
}
