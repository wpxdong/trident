package io.github.trident.common.model.vo;

/**
 * @projectName: trident
 * @package: io.github.trident.common.model.vo
 * @className: LoginUserVO
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 13:38
 * @version: 1.0
 */
public class LoginUserVO extends UserVO {
    /**
     * token.
     */
    private String token;

    /**
     * expired time(milliSeconds).
     */
    private Long expiredTime;

    public LoginUserVO() {
    }

    /**
     * Gets the value of token.
     *
     * @return the value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token token
     * @return {@link LoginUserVO}
     */
    public LoginUserVO setToken(final String token) {
        this.token = token;
        return this;
    }

    /**
     * Gets the value of expiredTime.
     *
     * @return the value of expiredTime
     */
    public Long getExpiredTime() {
        return expiredTime;
    }

    /**
     * Sets the expiredTime.
     *
     * @param expiredTime expiredTime
     * @return {@link LoginUserVO}
     */
    public LoginUserVO setExpiredTime(final Long expiredTime) {
        this.expiredTime = expiredTime;
        return this;
    }


}
