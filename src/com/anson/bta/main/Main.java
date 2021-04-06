package com.anson.bta.main;

import com.anson.bta.core.FullDataAnalyse;
import com.anson.bta.core.WeeksUsage;
import com.anson.bta.manager.*;
import com.anson.bta.ui.diagUI;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        //Init Vars
        ArrayList<ArrayList> dataList;

        //System Init
        InitManager.run();

        //File Check
        if(Utils.isFileExistInDirectory(Global.folderLocation+"\\Data", "Product Mix by Date.xls")) {
            //Check XLS Path
            String path = Global.folderLocation+"\\Data";

            //Get all XLS from the Directory
            ArrayList<String> fileList = FileManager.fileNameIndicator(Global.folderLocation+"\\Data");
            dataList = FullDataAnalyse.dataList(path, fileList);

            //Create Result Sheet & Workbook
            ExcelGenerationManager.createSheet("ResultSheet");
            ExcelLayoutManager.coreLayout();
            ExcelLayoutManager.notApplicableManager();
            ExcelLayoutManager.dataManager(dataList);

            diagUI.informationBox("資料處理完畢並已經匯出到 " + Global.folderLocation+"\\Build-To-Automation-ResultFile.xls", "系統操作完成");
        } else {
            diagUI.informationBox("請「Product Mix by Date」檔案放到 " + Global.folderLocation+"\\Data", "放置資料");
        }
    }
}
