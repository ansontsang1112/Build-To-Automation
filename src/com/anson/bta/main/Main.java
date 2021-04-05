package com.anson.bta.main;

import com.anson.bta.core.WeeksUsage;
import com.anson.bta.manager.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static ArrayList<ArrayList> fullDataList = new ArrayList<>();

    public static void main(String[] args) throws IOException {



        //System Init
        InitManager.run();

        //Get all XLS from the Directory
        String path = Global.folderLocation+"\\Data";
        ArrayList<String> fileList = FileManager.fileNameIndicator(Global.folderLocation+"\\Data");

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
            fullDataList.add(resultPerDay);
        }

        System.out.println(WeeksUsage.averageItemUsagePerWeekDay(CategoryManager.foodCategory, fullDataList));
        System.out.println(WeeksUsage.averageItemUsagePerWeekEnd(CategoryManager.foodCategory, fullDataList));
        //System.out.println(fullDataList);
    }
}
