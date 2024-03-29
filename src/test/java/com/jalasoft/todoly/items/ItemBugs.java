package com.jalasoft.todoly.items;

import api.APIManager;
import api.methods.APIItemMethods;
import entities.Item;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * The ItemBugs class implements all the tests for automating the bugs related to the Item's API.
 * @author TestNG group: <a href="mailto:jimy.tastaca@fundacion-jala.org">Jimy Tastaca</a>
 * @version 1.0
 */
public class ItemBugs {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Item> items = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
        items.add(APIItemMethods.createItem("ItemById Test Item", null, 4000240, false));
        items.add(APIItemMethods.createItem("ItemById Delete Test Item", null, 4000240, false));
        if ((items.get(0)) == null || (items.get(1)) == null) {
            Assert.fail("Items were not created");
        }
    }

    @Test
    public void getItemByIdWithNonExistentId() {    
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(), 555555);
        Response response = apiManager.get(itemByIdEndpoint);
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorCode").contains("301"), "Correct ErrorCode is returned");
    }

    @Test
    public void getItemByIdWithNegativeId() {
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(), -123456);
        Response response = apiManager.get(itemByIdEndpoint);
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorCode").contains("301"), "Correct ErrorCode is returned");
    }

    @AfterClass
    public void tearDown() {
        for (Item item: items) {
            boolean isItemDeleted = APIItemMethods.deleteItem(item.getId());
            Assert.assertTrue(isItemDeleted,"Item was not deleted");
        }
    }
}
