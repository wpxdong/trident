package io.github.trident.common.domain.organization;

import io.github.trident.common.domain.BaseEntity;

import java.util.Date;

/**
 * @projectName: trident
 * @package: io.github.trident.common.domain.organization
 * @className: Department
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:27
 * @version: 1.0
 */
public class Department extends BaseEntity {
    //部门编号
    private String deptCode;
    //部门名称
    private String deptName;
    //部门负责人
    private String principal;
    //部门负责人名称
    private String principalName;
    //联系电话
    private String phone;
    //传真
    private String fax;
    //上级部门实体
    private Department parentCode;
    //所属子公司
    private String companyName;
    //邮编号码
    private String zipCode;
    //部门地址
    private String address;
    //状态
    private Boolean status;
    //启用日期
    private Date validDate;
    //作废日期
    private Date invalidDate;
    //显示顺序
    private Integer displayOrder;
    //部门层级
    private Byte deptLevel;
    //是否叶子节点
    private Boolean leaf;
    //部门描述
    private String deptDesc;
    //部门序列
    private String deptSeq;
    //部门标杆编码,用于各系统间交互
    private String standardCode;

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Department getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(Department parentCode) {
        this.parentCode = parentCode;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getInvalidDate() {
        return this.invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Byte getDeptLevel() {
        return this.deptLevel;
    }

    public void setDeptLevel(Byte deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptDesc() {
        return this.deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptSeq() {
        return this.deptSeq;
    }

    public void setDeptSeq(String deptSeq) {
        this.deptSeq = deptSeq;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return the principalName
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName the principalName to set
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

}
