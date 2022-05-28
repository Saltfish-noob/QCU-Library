package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.IRoleDao;
import edu.qcu.domain.Permission;
import edu.qcu.domain.Role;
import edu.qcu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("role")
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final IRoleDao roleDao;

    @Autowired
    public RoleServiceImpl(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return roleDao.findAll();
    }

    @Override
    public void delRoleToUser(String userId) throws Exception {
        roleDao.delRoleToUser(userId);
    }

    @Override
    public Role delPermissionByRoleId(String roleId) throws Exception {
        return roleDao.delPermissionByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionList(String roleId) throws Exception {
        return roleDao.findOtherPermissionList(roleId);
    }

    @Override
    public void addPermission(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds
        ) {
            roleDao.addPermission(roleId, permissionId);
        }
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Integer findUserNumByRolesId(String roleId) throws Exception {
        return roleDao.findUserNumByRolesId(roleId);
    }

    @Override
    public void delRoleById(String roleId) throws Exception {
        roleDao.delRoleById(roleId);
    }

    @Override
    public void delRole(String roleId) throws Exception {
        roleDao.delPermissionByRoleId(roleId);
        roleDao.delRoleById(roleId);
    }

    @Override
    public Integer findRoleByRoleName(String roleName) throws Exception {
        return roleDao.findRoleByRoleName(roleName);
    }

}
