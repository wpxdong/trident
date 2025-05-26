package io.github.trident.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.trident.api.Constants;
import io.github.trident.base.login.AuthUserService;
import io.github.trident.common.domain.authorization.AuthUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @projectName: trident
 * @package: io.github.trident.api.controller
 * @className: AuthUserController
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/5/16 10:35
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/1/user")
public class AuthUserController extends ApiBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserController.class);
    @DubboReference(lazy = true, timeout = 300000)
    private AuthUserService authUserService;


    /**
     * curl "http://localhost:8078/api/1/user/create" -XPOST
     *
     * @param user
     * @return
     */
    @PostMapping(path = {"/create"})
    public ResponseEntity<?> create(JsonObject user) {
        String requestId = UUID.randomUUID().toString();
        LOGGER.info("user:{}", user);
        AuthUser authUser = new AuthUser();
        authUser.setLoginName("admin");
        authUser.setPassword("123456");
        authUser.setLastLogin(new Date());
        authUserService.create(authUser);
        JsonObject response = new JsonObject();
        response.addProperty("result_code", "success");
        return toSuccessResponseEntity(requestId);
    }

    /**
     * curl "http://localhost:8078/api/1/user/login" -XPOST -d "login_name=admin&password=123456&client_id=8000"
     *
     * @param userName
     * @param password
     * @param clientId
     * @return
     */
    @PostMapping(path = {"/login"}, produces = "application/json")
    public ResponseEntity<?> login(@RequestParam(value = "login_name") String userName,
                                   @RequestParam("password") String password,
                                   @RequestParam("client_id") String clientId) {
        String requestId = UUID.randomUUID().toString();
        try {
            String loginUserStr = authUserService.login(userName, password, clientId);
            if (Objects.nonNull(loginUserStr)) {
                JsonObject loginUserJson = gson.fromJson(loginUserStr, JsonObject.class);
                if (loginUserJson.has("err_code") && loginUserJson.get("err_code").getAsInt() == 0) {
                    loginUserJson.remove("err_code");
                    return toSuccessResponseEntity(requestId, loginUserJson);
                } else {
                    return toResponseEntity(requestId, "login_fail", loginUserJson.get("message").getAsString());
                }
            }
            return toResponseEntity(requestId, Constants.NOT_FOUND, String.format("userName:%s is not exist", userName));
        } catch (Exception ex) {
            return toResponseEntity(requestId, Constants.UNKNOWN_ERROR, ex.getMessage());
        }
    }

}
