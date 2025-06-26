package io.github.trident.base.service;

import com.google.gson.Gson;
import io.github.trident.base.mapper.DepartmentMapper;
import io.github.trident.base.organization.IDepartmentService;
import io.github.trident.base.util.Constants;
import io.github.trident.common.domain.organization.Department;
import io.github.trident.common.utils.ResponseUtils;
import jakarta.validation.constraints.NotNull;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Service
@DubboService
public class DepartmentService implements IDepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    Gson gson;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = org.springframework.transaction.annotation.Isolation.DEFAULT)
    public String persist(@NotNull Department department, String requestId, Locale locale) {
        try {
            if (Objects.isNull(department.getId())) {
                department.setCreateDate(new Date());
                departmentMapper.insertDepartment(department);
            } else {
                department.setModifyDate(new Date());
            }

        }catch (Exception ex){
            return gson.toJson(ResponseUtils.setErrResponse(1, Constants.DB_ERROR, ex.getMessage()));
        }
        return gson.toJson(ResponseUtils.setSuccessResponse());
    }
}
