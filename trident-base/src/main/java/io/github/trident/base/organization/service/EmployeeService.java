package io.github.trident.base.organization.service;

import io.github.trident.common.domain.organization.Employee;
import io.github.trident.common.service.base.IEmpolyeeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public class EmployeeService implements IEmpolyeeService {
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
}
