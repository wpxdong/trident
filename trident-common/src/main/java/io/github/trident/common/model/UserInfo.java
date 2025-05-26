package io.github.trident.common.model;

/**
 * @projectName: trident
 * @package: io.github.trident.common.model.vo
 * @className: UserInfo
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 11:53
 * @version: 1.0
 */
import java.util.Date;
import java.util.Set;
public class UserInfo {
    /**
     * user name.
     */
    private String userName;

    /**
     * user id.
     */
    private String userId;

    /**
     * Gets the value of userName.
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName.
     *
     * @param userName userName
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets the value of userId.
     *
     * @return the value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the userId.
     *
     * @param userId userId
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * builder method.
     *
     * @return builder object.
     */
    public static UserInfo.UserInfoBuilder builder() {
        return new UserInfo.UserInfoBuilder();
    }

    public static final class UserInfoBuilder {

        private String userName;

        private String userId;

        private UserInfoBuilder() {
        }

        /**
         * userName.
         *
         * @param userName the userName.
         * @return UserInfoBuilder.
         */
        public UserInfoBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * userId.
         *
         * @param userId the userId.
         * @return UserInfoBuilder.
         */
        public UserInfoBuilder userId(final String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * build method.
         *
         * @return build object.
         */
        public UserInfo build() {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setUserId(userId);
            return userInfo;
        }
    }
}
