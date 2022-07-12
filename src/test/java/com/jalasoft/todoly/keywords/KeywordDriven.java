package com.jalasoft.todoly.keywords;

import entities.Item;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoggerManager;

import java.io.IOException;
import java.util.ArrayList;


public class KeywordDriven {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final KeywordDrivenAction kda = new KeywordDrivenAction();
    private static ExcelReader readSheet = new ExcelReader();
    Response response;
    @Test
    public void getAllItemsKDFTest() throws IOException {
        log.info("Listing all items Test using keyword driven framework");
        ArrayList readActions = readSheet.excelDataReader(4);
        String step;
        for(int ind = 0; ind < readActions.size(); ind++){
            step = (String) readActions.get(ind);
            switch (step) {
                case "authenticate": kda.authenticate();
                case "getAllItems": response = kda.getAllItems();
                default: log.error("Non existing actions " + step);
            }
        }
        Assert.assertEquals(response.getStatusCode(), 200, "incorrect status code was returned");
        log.info(response.getBody().prettyPrint());
    }
}
