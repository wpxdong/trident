package io.github.trident.common.domain.organization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.trident.common.domain.BaseEntity;

import java.util.Date;

/**
 * @projectName: trident
 * @package: io.github.trident.common.domain.organization
 * @className: Employee
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:26
 * @version: 1.0
 */
public class Employee extends BaseEntity {
    //部门实体
    private Department deptId;
    //职员编号
    @SerializedName("emp_code")
    private String empCode;
    //人员姓名
    @SerializedName("emp_name")
    private String empName;
    //性别
    @SerializedName("gender")
    private Integer gender;
    //生日
    private Date birthdate;
    //状态（在职/离职）
    private Integer status;
    //入职日期
    @SerializedName("in_date")
    private Date inDate;
    //离职日期
    @SerializedName("out_date")
    private Date outDate;
    //办公电话
    @SerializedName("office_tel")
    private String officeTel;
    //办公地址
    @SerializedName("office_address")
    private String officeAddress;
    //办公邮编
    @SerializedName("office_zip")
    private String officeEmail;
    //手机号码
    @SerializedName("mobile_no")
    private String mobileNo;
    //家庭电话
    @SerializedName("home_tel")
    private String homeTel;
    //家庭地址
    @SerializedName("home_address")
    private String homeAddress;
    //私人邮箱
    @SerializedName("person_email")
    private String personEmail;
    //工作描述
    @SerializedName("work_exp")
    private String workExp;
    //备注
    @SerializedName("remark")
    private String remark;
    //职位
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Department deptId) {
        this.deptId = deptId;
    }

    public String getEmpCode() {
        return this.empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInDate() {
        return this.inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return this.outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getHomeTel() {
        return this.homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getHomeAddress() {
        return this.homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }


    public String getPersonEmail() {
        return this.personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getWorkExp() {
        return this.workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
