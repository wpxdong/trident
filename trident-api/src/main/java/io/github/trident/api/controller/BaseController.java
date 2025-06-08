package io.github.trident.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.trident.api.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BaseController {
    @Autowired
    protected Gson gson;

    protected ResponseEntity<?> toSuccessResponseEntity(String requestId) {
        JsonObject response = baseResponse(requestId, Constants.SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected ResponseEntity<?> toSuccessResponseEntity(String requestId, Object data) {
        JsonObject response = baseResponse(requestId, Constants.SUCCESS);
        if (Objects.nonNull(data)) {
            response.add("data", gson.toJsonTree(data));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected ResponseEntity<?> toResponseEntity(String requestId, String resultCode, String displayMessage) {
        JsonObject response = baseResponse(requestId, resultCode);
        response.addProperty("display_msg", displayMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private JsonObject baseResponse(String requestId, String resultCode) {
        JsonObject response = new JsonObject();
        response.addProperty(Constants.RESULT_CODE, resultCode);
        response.addProperty(Constants.REQUEST_ID, requestId);
        return response;
    }
}
