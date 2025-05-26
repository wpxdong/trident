package io.github.trident.common.domain.authorization;

import io.github.trident.common.domain.BaseEntity;

import java.util.Collection;

/**
 * @projectName: trident
 * @package: io.github.trident.common.domain.authorization
 * @className: Role
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:29
 * @version: 1.0
 */
public class Role  extends BaseEntity {
    //角色名称
    private String roleName;

    //角色描述
    private String roleDesc;

    //功能对象ID
    private Collection<String> functionIds;

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Collection<String> getFunctionIds() {
        return this.functionIds;
    }

    public void setFunctionIds(Collection<String> functionIds) {
        this.functionIds = functionIds;
    }
}
