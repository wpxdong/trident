package io.github.trident.common.model.vo;

import java.io.Serializable;

/**
 * @projectName: trident
 * @package: io.github.trident.common.model.vo
 * @className: UserVO
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 11:48
 * @version: 1.0
 */
public class UserVO implements Serializable {
    private String id;
    private String userName;
    private String password;
    private Integer role;
    private boolean enabled;
    private String clientId;
    private String dateCreated;
    private String dateUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public UserVO(){}
    public UserVO(String id, String userName, String password, Integer role, boolean enabled, String clientId, String dateCreated, String dateUpdated) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.clientId = clientId;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }
}
