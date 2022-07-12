package com.jalasoft.todoly.keywords;


import entities.Item;
import framework.Environment;
import io.restassured.response.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.LoggerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {
    private static final Environment environment = Environment.getInstance();
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final KeywordDrivenAction kda = new KeywordDrivenAction();
    private Response response;

    public ArrayList excelDataReader(int colNum) throws IOException {

        ArrayList excelActions = new ArrayList();
        try {

            FileInputStream file = new FileInputStream("C:\\Users\\Usuario\\Documents\\Jalasoft\\API Testing\\Demo\\todo.ly\\src\\main\\java\\utils\\ExcelSheet.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("testCases");
            Iterator row = sheet.rowIterator();
            row.next();
            while (row.hasNext()) {
                Row nextRow = (Row) row.next();
                Cell cell = nextRow.getCell(colNum);
                String data = cell.getStringCellValue();
                excelActions.add(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return excelActions;
    }
}
