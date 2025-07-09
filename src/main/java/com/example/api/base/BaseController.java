package com.example.api.base;

import java.util.Map;

import com.example.config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public abstract class BaseController<SELF extends BaseController<SELF>>  {

    private final RequestSpecBuilder reqSpecBuilder;
    private RequestSpecification builtSpec;

    public BaseController(String basePath) {
        this.reqSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(Config.getBaseUrl())
                .setBasePath(basePath)
                .addHeader("Content-Type", "application/json")
                .log(ALL);
    }

    public SELF withPathParams(Map<String, ?> pathParams) {
        reqSpecBuilder.addPathParams(pathParams);
        return getSelf();
    }

    public SELF withQueryParams(Map<String, ?> queryParams) {
        reqSpecBuilder.addQueryParams(queryParams);
        return getSelf();
    }

    public SELF withBody(Object body) {
        reqSpecBuilder.setBody(body);
        return getSelf();
    }

    private RequestSpecification prepareRequestSpecification() {
        if (builtSpec == null) {
            builtSpec = reqSpecBuilder.build();
        }
        return RestAssured.given().spec(builtSpec);
    }

    protected Response executeRequest(Method method, String endpoint) {
        return prepareRequestSpecification().request(method, endpoint);
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