package com.nortic.posdash.ms.usersettings.utils;

import com.nortic.posdash.models.enums.StatusEnum;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseBody<T> {

    private Map<String, Object> body = new LinkedHashMap<>();

    public ResponseBody(T data, StatusEnum status) {
        this.body.put("status", status.getMessage());
        this.body.put("timestamp", LocalDate.now());
        this.body.put("data", data);
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
