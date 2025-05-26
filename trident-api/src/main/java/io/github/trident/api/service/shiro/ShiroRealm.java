package io.github.trident.api.service.shiro;

import io.github.trident.api.service.PermissionService;
import io.github.trident.api.service.UserService;
import io.github.trident.common.model.UserInfo;
import io.github.trident.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BearerToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        return null;
    }

    @Override
    protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
//        return super.isPermitted(permission, info);
        return true;
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
//        DashboardUserVO dashboardUserVO = dashboardUserService.findByUserName(userName);
//        if (Objects.isNull(dashboardUserVO)) {
//            throw new AuthenticationException(String.format("userName(%s) can not be found.", userName));
//        }
        String clientIdFromToken = JwtUtils.getClientId(token);
//        if (StringUtils.isNotEmpty(clientIdFromToken)
//                && StringUtils.isNotEmpty(dashboardUserVO.getClientId())
//                && !StringUtils.equals(dashboardUserVO.getClientId(), clientIdFromToken)) {
//            throw new AuthenticationException("clientId is invalid or does not match");
//        }
//        if (!JwtUtils.verifyToken(token, dashboardUserVO.getPassword())) {
        if(!JwtUtils.verifyToken(token, "jwt-token")) {
            throw new AuthenticationException("token is error");
        }
        return new SimpleAuthenticationInfo(UserInfo.builder()
                .userName(userName)
                .userId("11111")
                .build(), token, this.getName());
    }
}
