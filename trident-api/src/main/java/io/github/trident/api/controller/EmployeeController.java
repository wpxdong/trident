package io.github.trident.api.controller;

import com.google.gson.JsonObject;
import io.github.trident.base.organization.IEmployeeService;
import io.github.trident.common.domain.organization.Employee;
import io.github.trident.common.model.UserInfo;
import org.apache.dubbo.common.logger.FluentLogger;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/1/employee")
public class EmployeeController extends BaseController {
    @DubboReference(lazy = true, timeout = 300000)
    IEmployeeService employeeService;

    /**
     * curl "http://localhost:8078/api/1/employee/create" -XPOST -H "X-Access-Token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiY2xpZW50SWQiOiI4MDAwIiwiZXhwIjoxNzUxMy MjM2fQ.axg6CUD7OJ2uirHt_dfX6FNggrp0hvFQE6pS3PUfyM8" -d "{'emp_code':'100','emp_name':'杨志远','gender':1,'birthdate':'1980-01-01','status':1,'in_date':'2018-01-01','office_address':' 北
     * 京分公司','mobile_no':'1111111','home_address':'北京','person_email':'yuan.zh@126.com','position':'主管','office_tel':'100','office_zip':'22222','home_tel':'122222','home_address'
     * :'xxxx','work_exp':'3年','remark':'北京分公司','out_date':'1980-01-01'}" -H "Content-Type:application/json"
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Employee employee,
                                    @RequestParam(value = "region", required = false) String region,
                                    @RequestParam(value = "lang", required = false) String lang) {
        String requestId = UUID.randomUUID().toString();
        UserInfo userInfo = ((UserInfo) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
        employee.setCreateUser(userInfo.getUserName());
        String responseSvc =  employeeService.persist(employee, requestId, Locale.CHINA);
        JsonObject svcJson = gson.fromJson(responseSvc, JsonObject.class);
        if (svcJson.has("code") && svcJson.get("code").getAsInt() == 0) {
            return toSuccessResponseEntity(requestId);
        } else {
            return toSuccessResponseEntity(requestId, svcJson.get("message").getAsString());
        }
    }

    /**
     * curl "http://localhost:8078/api/1/employee/change/5" -XPOST -H "X-Access-Token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiY2xpZW50SWQiOiI4MDAwIiwiZXhwIjoxNzUxMjYyMjM2fQ.axg6CUD7OJ2uirHt_dfX6FNggrp0hvFQE6pS3PUfyM8" -d "{'emp_code':'1002'}" -H "Content-Type:application/json"
     *
     * @param employee
     * @param id
     * @param region
     * @param lang
     * @return
     */
    @PostMapping("/change/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable(value = "id") Long id,
                                    @RequestParam(value = "region", required = false) String region,
                                    @RequestParam(value = "lang", required = false) String lang) {
        String requestId = UUID.randomUUID().toString();
        if (Objects.isNull(employee)) {
            return toSuccessResponseEntity(requestId, "id is null");
        }
        employee.setId(id);
        UserInfo userInfo = ((UserInfo) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
        employee.setModifyUser(userInfo.getUserName());
        String responseSvc =  employeeService.persist(employee, requestId, Locale.CHINA);
        JsonObject svcJson = gson.fromJson(responseSvc, JsonObject.class);
        if (svcJson.has("code") && svcJson.get("code").getAsInt() == 0) {
            return toSuccessResponseEntity(requestId);
        } else {
            return toSuccessResponseEntity(requestId, svcJson.get("message").getAsString());
        }
    }
}
