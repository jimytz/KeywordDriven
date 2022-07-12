package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList actions = new ArrayList();
        try {
            //abrimos el XSSFWorkbook
            FileInputStream file = new FileInputStream("C:\\Users\\Usuario\\Documents\\Jalasoft\\API Testing\\Demo\\todo.ly\\src\\main\\java\\utils\\ExcelSheet.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("testCases");
            Iterator row = sheet.rowIterator();
            row.next();
            while (row.hasNext()) {
                Row nextRow = (Row) row.next();
                Cell cell = nextRow.getCell(4);
                String data = cell.getStringCellValue();
                actions.add(data);
                System.out.println(data);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
