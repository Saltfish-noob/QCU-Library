package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.IPermissionDao;
import edu.qcu.domain.Permission;
import edu.qcu.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permission")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    private final IPermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(IPermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void updatePermission(Permission permission) throws Exception {
        permissionDao.updatePermission(permission);
    }

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void delPermission(String id) throws Exception {
        permissionDao.delPermission(id);
    }

    @Override
    public Integer findRoleCountByPermission(String userId) throws Exception {
        return permissionDao.findRoleByPermissionId(userId);
    }
}
