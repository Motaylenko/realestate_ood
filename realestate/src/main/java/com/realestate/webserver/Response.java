package com.realestate.webserver;

/**
 * Абстракція HTTP-відповіді для незалежності від фреймворку.
 */
public interface Response {
    Response status(int code);

    Response header(String name, String value);

    Response json(Object obj);

    Response result(String content);

    Response html(String content);

    Response redirect(String location);
}
