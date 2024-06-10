package com.nortic.posdash.es.users.utils;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nortic.posdash.us.models.enums.*;

public class ResponseBody<T> {

    private Map<String,Object> body = new LinkedHashMap<>();

    public ResponseBody(T data, StatusEnum status) {
        this.body.put("status", status.getMessage());
        this.body.put("timestamp", LocalDate.now());
        this.body.put("data", data);
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
