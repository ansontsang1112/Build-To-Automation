package com.anson.bta.manager;

import com.anson.bta.main.Global;
import com.anson.bta.main.Utils;
import com.anson.bta.ui.diagUI;
import jdk.jshell.execution.Util;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelManager {

    public static FormulaEvaluator formulaEvaluator(String path, String file) throws IOException {
        FileInputStream fis = new FileInputStream(new File(path+"\\"+file));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        return wb.getCreationHelper().createFormulaEvaluator();
    }

    public static HSSFSheet sheetManager(String path, String file) throws IOException {
        try {
            if(!file.contains(".xls")) {
                diagUI.errorBox("請放置 xls 檔案", "檔案類型錯誤");
            }
        } catch (Exception e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
        }

        FileInputStream fis = new FileInputStream(new File(path+"\\"+file));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);

        return sheet;
    }

    public static Double resultManager(FormulaEvaluator formulaEvaluator, HSSFSheet sheet, String filter) {
        ArrayList<Integer> vRow = new ArrayList<>();
        ArrayList<Integer> rowIndex = new ArrayList<>();

        Double data = 0.00;
        for(Row row : sheet) {
            for (Cell cell : row) {
                if(formulaEvaluator.evaluateInCell(cell).getCellType() == cell.CELL_TYPE_STRING) {
                    if(cell.getStringCellValue().contains(filter)) {
                        rowIndex.add(cell.getRowIndex());
                    }
                }
            }
        }

        for(int i = 0; i < rowIndex.size(); i++) {
            Row row = sheet.getRow(rowIndex.get(i));
            vRow.add(row.getRowNum());
        }

        for(double value : vRow) {
            data += readSpecificNumericCell(sheet, (int)value, 5);
        }

        return data;
    }

    protected static Double readSpecificNumericCell(Sheet sheet, int vRow, int vColumn) {
        double value;

        Row row = sheet.getRow(vRow);
        Cell cell = row.getCell(vColumn);
        value = cell.getNumericCellValue();

        return value;
    }

    protected static String readSpecificStringCell(Sheet sheet, int vRow, int vColumn) {
        Row row = sheet.getRow(vRow);
        Cell cell = row.getCell(vColumn);
        String value = cell.getStringCellValue();
        return value;
    }
}
