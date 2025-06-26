package io.github.trident.common.domain.organization;

import com.google.gson.annotations.SerializedName;
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
    private String code;
    //部门名称
    private String name;
    //部门负责人
    private String principal;
    //部门负责人名称
    @SerializedName("principal_name")
    private String principalName;
    //联系电话
    private String phone;
    //传真
    private String fax;
    //部门地址
    private String address;
    //状态
    private Boolean status;
    //启用日期
    @SerializedName("valid_date")
    private Date validDate;
    //作废日期
    @SerializedName("invalid_date")
    private Date invalidDate;
    //显示顺序
    @SerializedName("display_order")
    private Integer displayOrder;
    //部门层级
    private Integer level;
    //是否叶子节点
    private Boolean leaf;
    //部门描述
    private String desc;
    //部门序列
    private String seq;
    //部门标杆编码,用于各系统间交互
    @SerializedName("standard_code")
    private String standardCode;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("parent_code")
    private String parentCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
