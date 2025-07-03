package org.graded;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


// Main class
public class StudentDataLoader {

    private final LinkedHashMap<String, Student> studentTreeMap = new LinkedHashMap<>(20);

    public LinkedHashMap<String, Student> getStudentTreeMap() {
        return studentTreeMap;
    }

    public List<Student> getStudentStream() {
        List<Student> list = new ArrayList<>();
        for (Student student : studentTreeMap.values()) {
            list.add(student);
        }
        list.sort(Comparator.comparing(Student::points, Comparator.reverseOrder()));
        System.out.println(list);
        return list;
    }

    StudentDataLoader() {
        String filename = "";
        FileInputStream file;
        try {
            filename = "G:/My Drive/time_table_leader.xlsx";

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
        for (int rowNum = 1; rowNum <=sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null ) {
                var stu = new Student(row.getCell(0).getStringCellValue(),
                        getAsRequired(row.getCell(1)), getAsRequired(row.getCell(2)),
                        row.getCell(3).getNumericCellValue());
                studentTreeMap.put(row.getCell(0).getStringCellValue(), stu);
            }
        }

       // workbook.getSheetAt(8).getRow(2).getCell(2).setCellValue("VII");
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

    String getAsRequired(Cell c) {
        try {

                int x = (int) c.getNumericCellValue();
                return "" + x;

        } catch (IllegalStateException e) {
            return c.getStringCellValue();
        }
    }

}
