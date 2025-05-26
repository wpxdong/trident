package io.github.trident.common.exception;

public enum LoginExceptionType {
    /**
     * 登录密码不能为空
     */
    LoginPasswordIsNull,

    /**
     * 登录密码错误
     */
    LoginPasswordIsError,

    /**
     * 该用户不存在
     */
    UserIsNull,

    /**
     * 当前用户已经被禁用
     */
    UserIsDisable,

    /**
     * 用户名不能为空
     */
    UserNameIsNull,
    /**
     * 密码输入错误不能超过 五次
     */
    TryMoreThanTimes,
    /**
     * 账号已锁定
     */
    CurrentAccountLocked,

    /**
     * 系统时间和电脑时间有出入
     */
    UserLogindateNoSame
}
