package io.github.trident.api.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiBaseController {
    @Autowired
    protected Gson gson;

    protected ResponseEntity<?> toSuccessResponseEntity(String requestId) {
        return new ResponseEntity<>(requestId, HttpStatus.OK);
    }
}
