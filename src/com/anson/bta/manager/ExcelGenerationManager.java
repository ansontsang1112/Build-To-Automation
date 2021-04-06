package com.anson.bta.manager;

import com.anson.bta.main.Global;
import com.anson.bta.main.Utils;
import com.anson.bta.ui.diagUI;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ExcelGenerationManager {
    private static FileInputStream fileInputStream() throws FileNotFoundException {
        return new FileInputStream(Global.folderLocation+"\\"+Global.resultWorkBookName+".xls");
    }

    private static FileOutputStream fileOutStream() throws FileNotFoundException {
        return new FileOutputStream(Global.folderLocation+"\\"+Global.resultWorkBookName+".xls");
    }

    public static void createWorkBook() {
        Workbook wb = new HSSFWorkbook();
        try(OutputStream fOut = new FileOutputStream(Global.folderLocation+"\\"+Global.resultWorkBookName+".xls")) {
            wb.write(fOut);
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
        } catch (IOException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
        }
    }

    public static boolean createSheet(String sheetName) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);

        if(!Utils.isFileExistInDirectory(Global.folderLocation.toString(), Global.resultWorkBookName+".xls")) {
            createWorkBook();
        }

        try (OutputStream fileOut = fileOutStream()) {
            wb.write(fileOut);
            wb.close();
            return true;
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (IOException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        }
    }

    public static boolean createCell(int vRow, int vCell, String text) throws IOException, InvalidFormatException {
        InputStream inputStream = fileInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);

        if(Utils.isRowEmpty(wb.getSheetAt(0).getRow(vRow))) {
            wb.getSheetAt(0).createRow(vRow).createCell(vCell).setCellValue(text);
        } else {
            wb.getSheetAt(0).getRow(vRow).createCell(vCell).setCellValue(text);
        }

        OutputStream fileOut = fileOutStream();
        wb.write(fileOut);
        wb.close();
        inputStream.close();
        fileOut.close();
        return true;
    }

    public static boolean createCell(int vRow, int vCell, double data) throws IOException, InvalidFormatException {
        InputStream inputStream = fileInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);

        if(Utils.isRowEmpty(wb.getSheetAt(0).getRow(vRow))) {
            wb.getSheetAt(0).createRow(vRow).createCell(vCell).setCellValue(data);
        } else {
            wb.getSheetAt(0).getRow(vRow).createCell(vCell).setCellValue(data);
        }

        OutputStream fileOut = fileOutStream();
        wb.write(fileOut);
        wb.close();
        inputStream.close();
        fileOut.close();
        return true;
    }

    public static boolean setBoldStyle(int vRow, int vCell) {
        try(InputStream inputStream = fileInputStream()) {
            Workbook wb = WorkbookFactory.create(inputStream);

            //Bold Font
            Font font = wb.createFont();
            font.setBold(true);

            //Font Style Applies
            CellStyle style = wb.createCellStyle();
            style.setFont(font);

            wb.getSheetAt(0).getRow(vRow).getCell(vCell).setCellStyle(style);

            try(OutputStream fileOut = fileOutStream()) {
                wb.write(fileOut);

                wb.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (IOException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (InvalidFormatException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        }
    }

    public static boolean setFontSize(int vRow, int vCell, int size) {
        try (InputStream inputStream = fileInputStream()) {
            Workbook wb = WorkbookFactory.create(inputStream);

            //Bold Font
            Font font = wb.createFont();
            font.setFontName("微軟正黑體");
            font.setFontHeight((short) (size*20));

            //Font Style Applies
            CellStyle style = wb.createCellStyle();
            style.setFont(font);

            wb.getSheetAt(0).getRow(vRow).getCell(vCell).setCellStyle(style);

            try (OutputStream fileOut = fileOutStream()) {
                wb.write(fileOut);
                wb.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (IOException | InvalidFormatException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        }
    }

    public static boolean setAlignCenter(int vRow, int vCell) {
        try (InputStream inputStream = fileInputStream()) {
            Workbook wb = WorkbookFactory.create(inputStream);

            //Bold Font
            CellStyle cellStyle = wb.getSheetAt(0).getRow(vRow).getSheet().getWorkbook().createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            wb.getSheetAt(0).getRow(vRow).getCell(vCell).setCellStyle(cellStyle);

            try (OutputStream fileOut = fileOutStream()) {
                wb.write(fileOut);
                wb.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (IOException | InvalidFormatException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        }
    }

    public static boolean setBackgroundColor(int vRow, int vCell, IndexedColors colors) {
        try (InputStream inputStream = fileInputStream()) {
            Workbook wb = WorkbookFactory.create(inputStream);

            //Color Setting
            CellStyle style = wb.createCellStyle();
            style.setFillBackgroundColor(colors.getIndex());
            style.setFillPattern(FillPatternType.BIG_SPOTS);

            wb.getSheetAt(0).getRow(vRow).getCell(vCell).setCellStyle(style);

            try (OutputStream fileOut = fileOutStream()) {
                wb.write(fileOut);
                wb.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        } catch (IOException | InvalidFormatException e) {
            diagUI.errorBox(e.getMessage(), "系統故障");
            return false;
        }
    }
}
