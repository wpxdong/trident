package io.github.trident.common.domain.authorization;

import io.github.trident.common.domain.BaseEntity;
import io.github.trident.common.domain.organization.Employee;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @projectName: trident
 * @package: io.github.trident.common.domain.authorization
 * @className: LoginUser
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:26
 * @version: 1.0
 */
public class AuthUser extends BaseEntity {
    //职员对象
    private Employee empCode;

    //用户登录名
    private String loginName;

    //用户登录密码
    private String password;

    //用户最后登录时间
    private Date lastLogin;

    //用户状态(0:未启用 1：启用)
    private Byte status;

    //用户启用时间
    private Date invalidDate;

    //用户禁用时间
    private Date validDate;

    //用户所分配的角色信息
    @SuppressWarnings("unused")
    private List<Role> roles;

    /**
     * clientId.
     */
    private String clientId;

    //用户所拥有的角色信息ID集合
    private Set<String> roleids;

    //用户所拥有的部门信息ID集合
    private Set<String> deptids;

    private Set<String> accessUris;
    private Date lastErrTime;
    private Integer errorTimes;

    public Set<String> getAccessUris() {
        return accessUris;
    }

    public void setAccessUris(Set<String> accessUris) {
        this.accessUris = accessUris;
    }

    public Employee getEmpCode() {
        return this.empCode;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<String> getRoleids() {
        return roleids;
    }

    @Deprecated
    public List<Role> getRoles() {
        return null;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setEmpCode(Employee empCode) {
        this.empCode = empCode;
    }


    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleids(Set<String> roleids) {
        this.roleids = roleids;
    }

    @Deprecated
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<String> getDeptids() {
        return deptids;
    }

    public void setDeptids(Set<String> deptids) {
        this.deptids = deptids;
    }

    public Set<String> queryAccessUris() {
        return accessUris;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getLastErrTime() {
        return lastErrTime;
    }

    public void setLastErrTime(Date lastErrTime) {
        this.lastErrTime = lastErrTime;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }
}
