package com.example.api;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.example.config.Config.getProperty;
import static io.restassured.filter.log.LogDetail.ALL;

public abstract class BaseController<SELF extends BaseController<SELF>>  {

    private final RequestSpecBuilder reqSpecBuilder;

    protected BaseController(String basePath) {
        this.reqSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setBasePath(basePath)
                .addHeader("Content-Type", "application/json")
                .log(ALL);
    }

    protected String getBaseUrl() {
        return getProperty("base.url");
    }

    public SELF withPathParams(Map<String, ?> pathParams) {
        this.reqSpecBuilder.addPathParams(pathParams);
        return getSelf();
    }

    public SELF withQueryParams(Map<String, ?> queryParams) {
        this.reqSpecBuilder.addQueryParams(queryParams);
        return getSelf();
    }

    public SELF withBody(Object body) {
        this.reqSpecBuilder.setBody(body);
        return getSelf();
    }

    private RequestSpecification prepareRequestSpecification() {
        return RestAssured.given().spec(reqSpecBuilder.build());
    }

    protected Response executeRequest(Method method, String endpoint) {
        return prepareRequestSpecification().request(method, endpoint)
                .then().log().all()
                .extract().response();
    }

    public Response get(String endpoint) {
        return executeRequest(Method.GET, endpoint);
    }

    public Response post(String endpoint) {
        return executeRequest(Method.POST, endpoint);
    }

    public Response patch(String endpoint) {
        return executeRequest(Method.PATCH, endpoint);
    }


    public Response delete(String endpoint) {
        return executeRequest(Method.DELETE, endpoint);
    }

    protected abstract SELF getSelf();
}