package io.github.trident.common.utils;

import com.google.gson.JsonObject;

public class ResponseUtils {
    public static JsonObject setErrResponse(Integer errCode, String message) {
        JsonObject response = new JsonObject();
        response.addProperty("err_code", errCode);
        response.addProperty("message", message);
        return response;
    }
    public static JsonObject setSuccessResponse() {
        JsonObject response = new JsonObject();
        response.addProperty("err_code", 0);
        return response;
    }
}
