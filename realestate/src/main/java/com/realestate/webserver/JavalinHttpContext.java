package com.realestate.webserver;

import io.javalin.http.Context;

/**
 * Адаптер Javalin Context до абстракції HttpContext.
 */
public class JavalinHttpContext implements HttpContext {
    private final Context ctx;

    public JavalinHttpContext(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Response status(int code) {
        ctx.status(code);
        return this;
    }

    @Override
    public Response header(String name, String value) {
        ctx.header(name, value);
        return this;
    }

    @Override
    public Response json(Object obj) {
        ctx.json(obj);
        return this;
    }

    @Override
    public Response result(String content) {
        ctx.result(content);
        return this;
    }

    @Override
    public Response html(String content) {
        ctx.html(content);
        return this;
    }

    @Override
    public Response redirect(String location) {
        ctx.redirect(location);
        return this;
    }

    @Override
    public String formParam(String key) {
        return ctx.formParam(key);
    }

    @Override
    public String queryParam(String key) {
        return ctx.queryParam(key);
    }

    @Override
    public String pathParam(String key) {
        return ctx.pathParam(key);
    }
}
