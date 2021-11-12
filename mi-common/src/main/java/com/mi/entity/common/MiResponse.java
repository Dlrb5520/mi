package com.mi.entity.common;

import java.util.HashMap;

/**
 * @ClassName MiResponse
 * @Author yangli
 * @Date 2021/7/14 21:47
 * @Description:
 */
public class MiResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public MiResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MiResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MiResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
