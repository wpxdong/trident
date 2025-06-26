package io.github.trident.api.controller;

import com.google.gson.JsonObject;
import io.github.trident.base.organization.IDepartmentService;
import io.github.trident.common.domain.organization.Department;
import io.github.trident.common.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/1/department")
public class DepartmentController extends BaseController {
    @DubboReference(lazy = true, timeout = 300000)
    IDepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Department department,
                                    @RequestParam(value = "region", required = false) String region,
                                    @RequestParam(value = "lang", required = false) String lang) {
        String requestId = UUID.randomUUID().toString();
        UserInfo userInfo = ((UserInfo) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
        department.setCreateUser(userInfo.getUserName());
        String responseSvc = departmentService.persist(department, requestId, Locale.CHINA);
        JsonObject svcJson = gson.fromJson(responseSvc, JsonObject.class);
        if (svcJson.has("err_code") && svcJson.get("err_code").getAsInt() == 0) {
            return toSuccessResponseEntity(requestId);
        } else {
            return toResponseEntity(requestId,"unknown_err", svcJson.get("message").getAsString());
        }
    }
}
