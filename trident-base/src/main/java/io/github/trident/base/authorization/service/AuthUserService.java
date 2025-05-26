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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Value("${thirth_mins:30}")
    int thirthMins;
    @Autowired
    Gson gson;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
            response.addProperty("err_code",1);
            response.addProperty("message","UserNameIsNull");
            return gson.toJson(response);
        }
        if (Objects.isNull(password)) {
            response.addProperty("err_code",1);
            response.addProperty("message","LoginPasswordIsNull");
            return gson.toJson(response);
        }
//        AesUtils.cbcDecrypt(secretProperties.getKey(), secretProperties.getIv(), password);

        AuthUser authUser = authUserMapper.selectByUserNameAndPassword(userName, DigestUtils.sha512Hex(password));
        if (Objects.nonNull(authUser)) {
            // 用户已经被禁用
            if (Objects.isNull(authUser.getStatus()) || authUser.getStatus() == 0) {
                response.addProperty("err_code","1");
                response.addProperty("message","UserIsDisable");
                return gson.toJson(response);
//                throw new LoginException(LoginExceptionType.UserIsDisable);
            }
            // 账号锁定功能
            if (isThirthMins(authUser.getLoginName())) {
                // 锁定时间到期，解除锁定
                if (Objects.nonNull(authUserMapper.queryUserLastErrTime(authUser.getLoginName()))) {
                    cleanUserError(authUser.getLoginName());
                }
            } else {
                response.addProperty("err_code","1");
                response.addProperty("message","CurrentAccountLocked");
                return gson.toJson(response);
            }

            cleanUserError(authUser.getLoginName());
            response.addProperty("err_code","0");
            response.addProperty("token",JwtUtils.generateToken(authUser.getLoginName(), authUser.getPassword(), clientId, 10L));
            response.addProperty("expired_time",expiredSeconds);
            authUser.setLastLogin(new Date());
            authUserMapper.updateUserLastLoginDate(authUser.getId());
        } else {
            response.addProperty("err_code",1);
            response.addProperty("message","UserIsNull");
            return gson.toJson(response);
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
    private boolean isThirthMins(@NotNull final String loginName) {
        Date lastLoginErrTime = authUserMapper.queryUserLastErrTime(loginName);
        if (Objects.isNull(lastLoginErrTime)) {
            return true;
        }
        long currentTime = System.currentTimeMillis();
        long diffMin = (currentTime - lastLoginErrTime.getTime()) / (1000 * 60);
        return diffMin >= thirthMins;
    }

    private void cleanUserError(@NotNull final String loginName) {
        authUserMapper.cleanUserLastErrTime(loginName);
        authUserMapper.cleanUserErrorTimes(loginName);
    }
}
