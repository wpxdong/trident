package io.github.trident.api.service.shiro;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.trident.api.service.PermissionService;
import io.github.trident.base.login.IAuthUserService;
import io.github.trident.common.model.UserInfo;
import io.github.trident.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName: trident
 * @package: io.github.trident.api.service.shiro
 * @className: ShiroRealm
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/1 14:43
 * @version: 1.0
 */
@Service("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    PermissionService permissionService;
    @Autowired
    IAuthUserService userService;
    @Autowired
    Gson gson;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BearerToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
//        Set<String> permissions = permissionService.getAuthPermByUserName(userInfo.getUserName());
//        if (CollectionUtils.isEmpty(permissions)) {
//            return null;
//        }


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
//        simpleAuthorizationInfo.setRoles(permissionService.getRolesByUserId("1"));
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if (Objects.nonNull(userInfo) && "admin".equals(userInfo.getUserName())) {
            return true;
        }
        return super.isPermitted(permission, info);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String userName = JwtUtils.getIssuer(token);
        if (StringUtils.isEmpty(userName)) {
            throw new AuthenticationException("userName is null");
        }
        String userVO = userService.findByUserName(userName);
        if (StringUtils.isEmpty(userVO)) {
            throw new AuthenticationException(String.format("userName(%s) can not be found.", userName));
        }
        JsonObject userInfo = gson.fromJson(userVO, JsonObject.class);
        String clientIdFromToken = JwtUtils.getClientId(token);
        if (StringUtils.isNotEmpty(clientIdFromToken)
                && userInfo.has("client_id")
                && !StringUtils.equals(userInfo.get("client_id").getAsString(), clientIdFromToken)) {
            throw new AuthenticationException("clientId is invalid or does not match");
        }
        if(!JwtUtils.verifyToken(token, userInfo.get("password").getAsString())) {
            throw new AuthenticationException("token is error");
        }
        return new SimpleAuthenticationInfo(UserInfo.builder()
                .userName(userName)
                .userId(userInfo.get("id").getAsString())
                .build(), token, this.getName());
    }
}
