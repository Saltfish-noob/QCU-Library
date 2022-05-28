package edu.qcu.service;

import edu.qcu.domain.Permission;
import edu.qcu.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll()throws Exception;

    List<Role> findAll(Integer page, Integer pageSize)throws Exception;

    void delRoleToUser(String userId)throws Exception;

    Role delPermissionByRoleId(String roleId)throws Exception;

    List<Permission> findOtherPermissionList(String roleId)throws Exception;

    void addPermission(String roleId, String[] permissionId)throws Exception;

    void save(Role role)throws Exception;

    Integer findUserNumByRolesId(String roleId)throws Exception;

    void delRoleById(String roleId)throws Exception;

    void delRole(String roleId)throws Exception;

    Integer findRoleByRoleName(String roleName)throws Exception;

}
