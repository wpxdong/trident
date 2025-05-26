package io.github.trident.common.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * @projectName: trident
 * @package: io.github.trident.common.model.dto
 * @className: UserModifyPasswordDTO
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:42
 * @version: 1.0
 */
public class UserModifyPasswordDTO implements Serializable {
    static final String PASSWORD_RULE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#.=_+-])[A-Za-z\\d@$!%*?&#.=_+-]{8,}$";
    private String id;
    private String userName;
    @NotNull
    @Pattern(regexp = PASSWORD_RULE, message = "{password.must}")
    private String password;
    private String oldPassword;

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

    public @NotNull @Pattern(regexp = PASSWORD_RULE, message = "{}") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Pattern(regexp = PASSWORD_RULE, message = "{}") String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
