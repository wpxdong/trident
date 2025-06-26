package io.github.trident.base.organization;

import io.github.trident.common.domain.organization.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends IDbService<Employee> {
    /**
     * 查找符合条件的所有员工信息
     *
     * @param deptName
     * @param employee
     * @param page
     * @param start
     * @return
     */
    List<Employee> getAll(String deptName, Employee employee, int page, int start);

    Long countAll(String deptName, Employee employee);

    /**
     *
     * @return
     */
    Optional<List<Employee>> getAll();
}
