package io.github.trident.base.mapper;

import io.github.trident.common.domain.organization.Department;
import io.github.trident.common.domain.organization.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getAll(String deptName, Employee employee, int page, int start);

    Long countAll(String deptName, Employee employee);

    List<Employee> getAll();

    void update(Employee employee);

    void insert(Employee employee);

    void deleteById(Long id);

    List<Employee> getAll(Employee employee, int limit, int start);

    Long count(Employee employee);

    List<Employee> getAllEmployeesByDeptId(Department deptId);

    List<Employee> getAllEmployeesByDeptId(String deptId);

    Employee getEmpById(String id);

    /**
     * 同步UUMS 数据，进行相应的数据同步，权限分配
     *
     * @param empCode changeType postion orgId
     * @return
     * @author ZhangZW
     * @update 2014-1-6
     */
    int syncEmp(String empCode, String changeType, String postion, String orgId);

    /**
     * <p>
     * 根据部门编号查询有效员工列表
     * <br />
     * </p>
     *
     * @param deptCode
     * @return List<Employee>
     * @author andy
     * @version 0.1 2014-3-19
     */
    List<Employee> getAllEmployeeByDeptCode(String deptCode);
}
