package com.jalasoft.todoly.keywords;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;

public class KeywordDrivenAction {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    public void authenticate() {

        apiManager.setCredentials(environment.getUserName(),environment.getPassword());
    }
    public Response getAllItems() {
        return apiManager.get(environment.getItemsEndpoint());
    }
}
