package com.travel.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ApiErrorResponseBuilder {

    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    private static final String TYPE = "type";

    public static HashMap<String, Object> build(int code, String type, String message) {
        HashMap<String, Object> res = new LinkedHashMap<>();
        res.put(CODE, code);
        res.put(TYPE, type);
        res.put(MESSAGE, message);
        return res;
    }
}
