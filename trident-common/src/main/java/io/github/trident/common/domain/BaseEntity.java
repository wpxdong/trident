package io.github.trident.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: trident
 * @package: io.github.trident.common.domain
 * @className: BaseEntity
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/29 11:35
 * @version: 1.0
 */
public class BaseEntity implements Serializable {
    protected Long id;
    protected String createUser;
    protected Date createDate;
    protected String modifyUser;
    protected Date modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
