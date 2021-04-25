package com.anson.bta.manager;

import com.anson.bta.core.WeeksUsage;
import com.anson.bta.main.Global;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.util.ArrayList;

public class ExcelLayoutManager {
    public static void coreLayout() throws IOException, InvalidFormatException {
        //Header McCafe 櫃檯及冷藏櫃存量及用量指引
        ExcelGenerationManager.createCell(1, 4, "McCafe 櫃檯及冷藏櫃存量及用量指引");
        ExcelGenerationManager.setBoldStyle(1,4);
        ExcelGenerationManager.setFontSize(1,4,14);

        int foodRowCounter = 6;
        //Food Types
        for(String food : Global.defaultFoodListinChinese.toString().split("\n")) {
            ExcelGenerationManager.createCell(foodRowCounter, 1, food);
            ExcelGenerationManager.setFontSize(foodRowCounter,1,10);
            foodRowCounter++;
        }

        //Set WeekDay(Day,Night, Whole Day), Weekend(Day,Night, Whole Day)
        for(int i = 4; i < 7; i++) {
            ExcelGenerationManager.createCell(3,i, "平日");
            ExcelGenerationManager.setAlignCenter(3, i);
        }

        for(int i = 7; i < 10; i++) {
            ExcelGenerationManager.createCell(3,i, "假日");
            ExcelGenerationManager.setAlignCenter(3, i);
        }

        for(int i = 4; i < 10; i++) {
            if(i==4 || i==7) {
                ExcelGenerationManager.createCell(4,i, "日班");
                ExcelGenerationManager.setAlignCenter(4, i);
            } else if(i==5 || i==8) {
                ExcelGenerationManager.createCell(4,i, "夜班");
                ExcelGenerationManager.setAlignCenter(4, i);
            } else {
                ExcelGenerationManager.createCell(4,i, "全日");
                ExcelGenerationManager.setAlignCenter(4, i);
            }
        }
    }

    public static void notApplicableManager() throws IOException, InvalidFormatException {
        int foodRowCounter = 6;
        //Food Types
        for(String food : Global.defaultFoodListinChinese.split("\n")) {
            if(food.equalsIgnoreCase( "小龍蝦蛋沙律意式飽") || food.equalsIgnoreCase("雞肉菠蘿意式飽") || food.equalsIgnoreCase("新餐肉芝味多士") ||
                    food.equalsIgnoreCase("火腿扒碎蛋芝味多士") || food.equalsIgnoreCase("蘑菇粟米芝味多士") || food.equalsIgnoreCase("新餐肉蛋沙律意式飽")) {
                ExcelGenerationManager.createCell(foodRowCounter, 6, "不適用");
                ExcelGenerationManager.createCell(foodRowCounter, 9, "不適用");
                ExcelGenerationManager.setFontSize(foodRowCounter,6,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,9,10);
            } else {
                ExcelGenerationManager.createCell(foodRowCounter, 4, "不適用");
                ExcelGenerationManager.createCell(foodRowCounter, 5, "不適用");
                ExcelGenerationManager.createCell(foodRowCounter, 7, "不適用");
                ExcelGenerationManager.createCell(foodRowCounter, 8, "不適用");

                ExcelGenerationManager.setFontSize(foodRowCounter,4,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,5,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,7,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,8,10);

            }
            foodRowCounter++;
        }
    }

    public static void dataManager(ArrayList<ArrayList> data) throws IOException, InvalidFormatException {
        int foodRowCounter = 6;
        //Food Types
        for(String food : Global.defaultFoodList.split("\n")) {
            if(food.equalsIgnoreCase( "CRAYFISH & EGG") || food.equalsIgnoreCase("CHICKEN & PINEAPPLE") || food.equalsIgnoreCase("OMNIPORK LUNCHEON & EGG CHEESY T") ||
                    food.equalsIgnoreCase("HAM & EGG CHEESY TOASTIE") || food.equalsIgnoreCase("CORN AND CHEESY CHAMPIGNON TOAST") ||
                    food.equalsIgnoreCase("OMNIPORK LUNCHEON & EGG MAYO C")) {

                ExcelGenerationManager.createCell(foodRowCounter, 4, Math.round((Double) (WeeksUsage.averageItemUsagePerWeekDay(CategoryManager.foodCategory, data).get(food))*0.7));
                ExcelGenerationManager.createCell(foodRowCounter, 5, Math.round((Double) (WeeksUsage.averageItemUsagePerWeekDay(CategoryManager.foodCategory, data).get(food))*0.3));
                ExcelGenerationManager.createCell(foodRowCounter, 7, Math.round((Double) (WeeksUsage.averageItemUsagePerWeekEnd(CategoryManager.foodCategory, data).get(food))*0.7));
                ExcelGenerationManager.createCell(foodRowCounter, 8, Math.round((Double) (WeeksUsage.averageItemUsagePerWeekEnd(CategoryManager.foodCategory, data).get(food))*0.3));

                ExcelGenerationManager.setFontSize(foodRowCounter,4,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,5,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,7,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,8,10);


            } else {
                if(food.equalsIgnoreCase("BROWN SUGAR LOAF")) {
                    ExcelGenerationManager.createCell(foodRowCounter, 6, Math.round((Double) WeeksUsage.averageItemUsagePerWeekDay(CategoryManager.foodCategory, data).get(food) * 2));
                    ExcelGenerationManager.createCell(foodRowCounter, 9, Math.round((Double) WeeksUsage.averageItemUsagePerWeekEnd(CategoryManager.foodCategory, data).get(food) * 2));
                } else {
                    ExcelGenerationManager.createCell(foodRowCounter, 6, Math.round((Double) WeeksUsage.averageItemUsagePerWeekDay(CategoryManager.foodCategory, data).get(food)));
                    ExcelGenerationManager.createCell(foodRowCounter, 9, Math.round((Double) WeeksUsage.averageItemUsagePerWeekEnd(CategoryManager.foodCategory, data).get(food)));
                }
                ExcelGenerationManager.setFontSize(foodRowCounter,6,10);
                ExcelGenerationManager.setFontSize(foodRowCounter,9,10);
            }
            foodRowCounter++;
        }
    }
}
