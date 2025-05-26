package io.github.trident.common.exception;

import java.io.Serializable;

public class LoginException extends TridentException {
    private static final long serialVersionUID = -4375232641764945199L;

    private static final String USER_NAME_IS_ERROR_CODE = "i18n.login.UserNameIsNullException";

    private static final String LOGIN_PASSWORD_IS_ERROR_CODE = "i18n.login.LoginPasswordIsErrorException";

    private static final String LOGIN_PASSWORD_IS_NULL_ERROR_CODE = "i18n.login.LoginPasswordIsNullException";

    private static final String USER_IS_NULL_ERROR_CODE = "i18n.login.UserIsNullException";

    private static final String USER_IS_DISABLE_ERROR_CODE = "i18n.login.UserIsDisableException";

    private static final String USER_PASSWORD_TRY_TOO_TIMES = "i18n.login.UserPasswordErrTimes";

    private static final String ACCOUNT_IS_LOCKED = "i18n.login.UserAccountIsLocked";

    private static final String USER_LOGINDATE_NOSAME = "i18n.login.UserLoginDateNoSame";

    public LoginException(LoginExceptionType errorType) {
        if (errorType.equals(LoginExceptionType.UserNameIsNull)) {
            this.errCode = USER_NAME_IS_ERROR_CODE;
        }
        if (errorType.equals(LoginExceptionType.LoginPasswordIsError)) {
            this.errCode = LOGIN_PASSWORD_IS_ERROR_CODE;
        }
        if (errorType.equals(LoginExceptionType.LoginPasswordIsNull)) {
            this.errCode = LOGIN_PASSWORD_IS_NULL_ERROR_CODE;
        }
        if (errorType.equals(LoginExceptionType.UserIsNull)) {
            this.errCode = USER_IS_NULL_ERROR_CODE;
        }
        if (errorType.equals(LoginExceptionType.UserIsDisable)) {
            this.errCode = USER_IS_DISABLE_ERROR_CODE;
        }
        if (errorType.equals(LoginExceptionType.TryMoreThanTimes)) {
            this.errCode = USER_PASSWORD_TRY_TOO_TIMES;
        }
        if (errorType.equals(LoginExceptionType.CurrentAccountLocked)) {
            this.errCode = ACCOUNT_IS_LOCKED;
        }
        if (errorType.equals(LoginExceptionType.UserLogindateNoSame)) {
            this.errCode = USER_LOGINDATE_NOSAME;
        }
    }

    public LoginException(LoginExceptionType errorType, Object... args) {
        this(errorType);
//        this.setErrorArguments(args);
    }
}
