package io.github.trident.base.authorization.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.trident.base.authorization.mapper.AuthUserMapper;
import io.github.trident.base.config.properties.SecretProperties;
import io.github.trident.common.domain.authorization.AuthUser;
import io.github.trident.common.model.dto.UserModifyPasswordDTO;
import io.github.trident.common.utils.DigestUtils;
import io.github.trident.common.utils.JwtUtils;
import io.github.trident.common.utils.SnowFlake;
import jakarta.validation.constraints.NotNull;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.protocol.tri.rest.openapi.model.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.Objects;

/**
 * @projectName: trident
 * @package: io.github.trident.base.login
 * @className: LoginService
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 22:18
 * @version: 1.0
 */

@DubboService
@Service
public class AuthUserService implements io.github.trident.base.login.AuthUserService {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserService.class);
    @Autowired
    SecretProperties secretProperties;
    @Autowired
    AuthUserMapper authUserMapper;
    @Value("${expired_seconds:6000}")
    Long expiredSeconds;
    @Value("${third_min:30}")
    int thirdMin;
    @Autowired
    Gson gson;
    @Autowired
    TransactionTemplate transactionTemplate;
    @Value("${remaining_times:5}")
    Integer RemainingTimes;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public int create(AuthUser loginUser) {
        if (Objects.nonNull(authUserMapper.selectByUserName(loginUser.getLoginName()))) {
            throw new IllegalArgumentException("loginName is exist");
        }
        loginUser.setId(SnowFlake.getInstance().nextId());
        loginUser.setPassword(DigestUtils.sha512Hex(loginUser.getPassword()));
        int result = authUserMapper.insertUser(loginUser);
        return 0;
    }

    @Override
    public AuthUser findByLoginName(String username) {
        return null;
    }

    @Override
    public String login(String userName, String password, String clientId) {
        JsonObject response = new JsonObject();
        if (Objects.isNull(userName)) {
            response.addProperty("err_code", 1);
            response.addProperty("message", "UserNameIsNull");
            return gson.toJson(response);
        }
        if (Objects.isNull(password)) {
            response.addProperty("err_code", 1);
            response.addProperty("message", "LoginPasswordIsNull");
            return gson.toJson(response);
        }
        //前端加密上传密码
//        AesUtils.cbcDecrypt(secretProperties.getKey(), secretProperties.getIv(), password);

        AuthUser authUser = authUserMapper.selectByUserName(userName);
        if (Objects.nonNull(authUser)) {
            String pwdSha512 = DigestUtils.sha512Hex(password);
            // 用户已经被禁用
            if (Objects.isNull(authUser.getStatus()) || authUser.getStatus() == 0) {
                setErrResponse(1, "UserIsDisable");
                return gson.toJson(response);
            }
            // 账号锁定功能
            if (isThirdMin(authUser.getLoginName())) {
                // 锁定时间到期，解除锁定
                if (Objects.nonNull(authUserMapper.queryUserLastErrTime(authUser.getLoginName()))) {
                    cleanUserError(authUser.getLoginName());
                }
            } else {
                return gson.toJson( setErrResponse(1, "CurrentAccountLocked"));
            }
            if (!pwdSha512.equals(authUser.getPassword())) {
                if (validateTimes(authUser.getLoginName())) {
                    return gson.toJson(setErrResponse(1, "TryMoreThanTimes"));
                }
                response = setErrResponse(1, String.format("LoginPasswordIsError, you have %d times to try  times",
                        RemainingTimes - authUserMapper.queryErrorTimes(authUser.getLoginName())));
                return gson.toJson(response);
            }

            cleanUserError(authUser.getLoginName());
            response.addProperty("err_code", "0");
            response.addProperty("token", JwtUtils.generateToken(authUser.getLoginName(), authUser.getPassword(), clientId, 10L));
            response.addProperty("expired_time", expiredSeconds);
            authUser.setLastLogin(new Date());
            authUserMapper.updateUserLastLoginDate(authUser.getId());
        } else {
            return gson.toJson(setErrResponse(1,"UserIsNull"));
        }
        return gson.toJson(response);
    }

    @Override
    public int modifyPassword(UserModifyPasswordDTO userModifyPasswordDTO) {
        return 0;
    }

    @Override
    public boolean checkUserPassword(String userId) {
        return false;
    }

    /**
     * 判断最后一次尝试登陆时间与当前时间是否相差超过30分钟
     *
     * @param loginName
     * @return
     */
    private boolean isThirdMin(@NotNull final String loginName) {
        Date lastLoginErrTime = authUserMapper.queryUserLastErrTime(loginName);
        if (Objects.isNull(lastLoginErrTime)) {
            return true;
        }
        long currentTime = System.currentTimeMillis();
        long diffMin = (currentTime - lastLoginErrTime.getTime()) / (1000 * 60);
        return diffMin >= thirdMin;
    }

    private void cleanUserError(@NotNull final String loginName) {
        authUserMapper.cleanUserLastErrTime(loginName);
        authUserMapper.cleanUserErrorTimes(loginName);
    }

    private boolean validateTimes(final String loginUserName) {
        boolean isToLockUser = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            return isToLockUser(loginUserName);
        }));
        if (isToLockUser) {
            authUserMapper.insertUserLastErrTime(loginUserName);
            return true;
        } else {
            return false;
        }
    }

    private boolean isToLockUser(String loginUserName) {
        authUserMapper.updateErrorTimes(loginUserName);
        int loginErrTimes = authUserMapper.queryErrorTimes(loginUserName);
        return loginErrTimes >= RemainingTimes;
    }

    private JsonObject setErrResponse(Integer errCode, String message) {
        JsonObject response = new JsonObject();
        response.addProperty("err_code", errCode);
        response.addProperty("message", "UserIsNull");
        return response;
    }
}
