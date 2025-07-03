package org.graded;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String filename = "";
        FileInputStream file;
        try {
            filename = "C:/Users/hilal/OneDrive - MSFT/GRADED_DATA/TIME_LEADER_BOARD/test.xlsx";
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
            //System.out.println("Info :  " + workbook.getProperties().getCoreProperties().getModified());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);
        //sheet.getRow(0).getCell(1).setCellValue("Heee");
        //System.out.println(workbook.getSheetAt(1).getRow(0).getCell(1).getCellFormula());
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            outFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getAsRequired(Cell c) {
        try {
            int x = (int) c.getNumericCellValue();
            return "" + x;
        } catch (IllegalStateException e) {
            return c.getStringCellValue();
        }
    }
}
