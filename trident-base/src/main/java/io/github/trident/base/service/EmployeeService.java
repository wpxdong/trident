package io.github.trident.base.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.trident.base.exception.EmployeeException;
import io.github.trident.base.organization.IEmployeeService;
import io.github.trident.base.mapper.EmployeeMapper;
import io.github.trident.common.domain.organization.Employee;
import io.github.trident.common.utils.ResponseUtils;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @projectName: trident
 * @package: io.github.trident.base.organization.service
 * @className: EmployeeService
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/30 14:03
 * @version: 1.0
 */
@Service
@DubboService
public class EmployeeService implements IEmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    Gson gson;
    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = org.springframework.transaction.annotation.Isolation.DEFAULT)
    public String persist(@NotNull Employee employee, String requestId, Locale locale) {
        JsonObject result = new JsonObject();
        if (Objects.isNull(employee)) {
            return gson.toJson(ResponseUtils.setErrResponse(1, getLocalizedMessage(EmployeeException.IS_NULL_ERROR_CODE, locale)));
        }
        try {
            if (Objects.nonNull(employee.getId())) {
                employee.setModifyDate(new Date());
                employeeMapper.updateEmployee(employee);
            } else {
                if (StringUtils.isNoneEmpty(employee.getEmpCode())) {
                    Employee employeeOld = employeeMapper.selectEmployeeByEmpCode(employee.getEmpCode());
                    if (Objects.nonNull(employeeOld) && Objects.nonNull(employeeOld.getEmpCode())) {
                        return gson.toJson(ResponseUtils.setErrResponse(1, EmployeeException.UNkNOWN_EXCEPTION));
                    }
                }
                employee.setCreateDate(new Date());
                employeeMapper.insertEmployee(employee);
            }
            return gson.toJson(ResponseUtils.setSuccessResponse());
        } catch (Exception ex) {
            return gson.toJson(ResponseUtils.setErrResponse(1, getLocalizedMessage(EmployeeException.IS_NULL_ERROR_CODE, locale)));
        }
    }

    @Override
    public List<Employee> getAll(String deptName, Employee employee, int page, int start) {
        return List.of();
    }

    @Override
    public Long countAll(String deptName, Employee employee) {
        return 0l;
    }

    @Override
    public Optional<List<Employee>> getAll() {
        return Optional.empty();
    }

    public String getLocalizedMessage(String messageId, Locale locale) {
        return messageSource.getMessage(messageId, null, locale);
    }
}
