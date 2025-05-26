package io.github.trident.base.login;

import io.github.trident.common.domain.authorization.AuthUser;
import io.github.trident.common.model.dto.UserModifyPasswordDTO;

public interface AuthUserService {
    int create(final AuthUser loginUser);

    AuthUser findByLoginName(String username);

    String login(final String userName, final String password, final String clientId);

    int modifyPassword(final UserModifyPasswordDTO userModifyPasswordDTO);

    boolean checkUserPassword(final String userId);
}
