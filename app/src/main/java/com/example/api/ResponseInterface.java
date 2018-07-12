package com.example.api;

/**
 * Created by Rajat on 6/6/2016.
 */
public interface ResponseInterface {
    Void parseResponse(String response);
    Void ErrorResponse(Exception e);
}
