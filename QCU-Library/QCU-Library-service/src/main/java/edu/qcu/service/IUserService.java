package edu.qcu.service;

import edu.qcu.domain.Role;
import edu.qcu.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService  extends UserDetailsService {
    List<UserInfo> findAll(Integer page,Integer pageSize) throws Exception;

    UserInfo findById(String id)throws Exception;

    void save(UserInfo userInfo)throws Exception;

    List<Role> findOtherRoles(String id)throws Exception;

    void addRoleToUser(String userId, String[] roleIds)throws Exception;

    void updateUserInfo(UserInfo userInfo)throws Exception;

    Integer findInfoByUserId(String userId)throws Exception;

    void delUserById(String userId)throws Exception;

    Integer findUserByUserName(String username)throws Exception;

    void alterPassWordBySelf(String passwd, String userId)throws Exception;
}
