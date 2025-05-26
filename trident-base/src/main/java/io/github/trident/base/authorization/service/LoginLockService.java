package io.github.trident.base.authorization.service;

import io.github.trident.common.domain.authorization.AuthUser;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginLockService {
    public Date queryLastLoginErrTime(AuthUser user) {
        return new Date();
    }
}
