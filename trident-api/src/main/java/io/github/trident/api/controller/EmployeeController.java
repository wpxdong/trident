package io.github.trident.api.controller;

import io.github.trident.base.organization.IEmployeeService;
import io.github.trident.common.domain.organization.Employee;
import io.github.trident.common.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/1/employee")
public class EmployeeController extends BaseController {
    @DubboReference(lazy = true, timeout = 300000)
    IEmployeeService employeeService;

    @RequestMapping("/create")
    /**
     * curl "http://localhost:8078/api/1/employee/create" -XPOST -H "X-Access-Token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiY2xpZW50SWQiOiI4MDAwIiwiZXhwIjoxNzUxMjYyMjM2fQ.axg6CUD7OJ2uirHt_dfX6FNggrp0hvFQE6pS3PUfyM8" -d "{'emp_code': 'c0809'}" -H "Content-Type:application/json"
     */
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        String requestId = UUID.randomUUID().toString();
        UserInfo userInfo = ((UserInfo) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
        employee.setCreateUser(userInfo.getUserName());
        employeeService.persist(employee, requestId, Locale.CHINA);
        return toSuccessResponseEntity(requestId);
    }

}
