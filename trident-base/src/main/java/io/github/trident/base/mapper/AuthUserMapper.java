package io.github.trident.base.mapper;

import io.github.trident.common.domain.authorization.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface AuthUserMapper {
    int insertUser(AuthUser authUser);

    AuthUser selectByUserNameAndPassword(@Param("loginName") String userName, @Param("password") String password);

    Date queryUserLastErrTime(@Param("loginName") String userName);

    void cleanUserLastErrTime(@Param("loginName") String userName);

    void cleanUserErrorTimes(@Param("loginName") String userName);

    void updateUserLastLoginDate(@Param("id") Long id);

    AuthUser selectByUserName(@Param("loginName") String loginName);

    boolean updateErrorTimes(@Param("loginName") String loginName);

    Integer queryErrorTimes(@Param("loginName") String loginName);

    public boolean insertUserLastErrTime(@Param("loginName") String loginName);
}
