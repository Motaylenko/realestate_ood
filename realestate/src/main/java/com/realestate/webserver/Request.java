package com.realestate.webserver;

/**
 * Абстракція HTTP-запиту для незалежності від фреймворку.
 */
public interface Request {
    String formParam(String key);
    String queryParam(String key);
    String pathParam(String key);
}
