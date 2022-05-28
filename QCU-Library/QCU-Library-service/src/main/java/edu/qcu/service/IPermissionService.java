package edu.qcu.service;

import edu.qcu.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    Permission findById(String permissionId)throws Exception;

    void updatePermission(Permission permission)throws Exception;

    List<Permission> findAll(Integer page, Integer pageSize)throws Exception;

    void save(Permission permission)throws Exception;

    void delPermission(String id)throws Exception;

    Integer findRoleCountByPermission(String userId)throws Exception;
}
