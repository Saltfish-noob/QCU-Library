package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.IBorrowLogDao;
import edu.qcu.dao.IPermissionDao;
import edu.qcu.dao.IUserDao;
import edu.qcu.domain.Permission;
import edu.qcu.domain.Role;
import edu.qcu.domain.UserInfo;
import edu.qcu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao userDao){
        this.userDao=userDao;
    }

    private IBorrowLogDao borrowLogDao;

    @Autowired
    public void setBorrowLogDao(IBorrowLogDao borrowLogDao) {
        this.borrowLogDao = borrowLogDao;
    }

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    private IPermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(IPermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo;
        User user=null;
        try {
            userInfo = userDao.findByUsername(username);
            List<String> logIdByUserId = borrowLogDao.findLogIdByUserId(userInfo.getId());
            session.setAttribute("nickname",userInfo.getNickname());
            session.setAttribute("haveBorrow",logIdByUserId.size());
            session.setAttribute("LogId",logIdByUserId);
            session.setAttribute("userId",userInfo.getId());
            List<Permission> permissionList = new ArrayList<>();
            for (Role role : userInfo.getRoles()) {
                permissionList.addAll(permissionDao.findPermissionByRoleId(role.getId()));
            }
            user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()!= 0,true,true,true,getAuthority(permissionList));
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Permission> permissions) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Permission permission:permissions){
            list.add(new SimpleGrantedAuthority("Permission_"+permission.getPermissionName()));
        }
        list = new ArrayList<>(new HashSet<>(list));
        return list;
    }

    @Override
    public List<UserInfo> findAll(Integer page, Integer pageSize) throws Exception{
        PageHelper.startPage(page,pageSize);
        return userDao.findAll();
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userDao.save(userInfo);
    }

    @Override
    public List<Role> findOtherRoles(String id) throws Exception {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception{
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) throws Exception {
        userDao.updateUserInfo(userInfo);
    }

    @Override
    public Integer findInfoByUserId(String userId) throws Exception {
        return borrowLogDao.findLogCountByUserId(userId);
    }

    @Override
    public void delUserById(String userId) throws Exception {
        userDao.delUserById(userId);
    }

    @Override
    public Integer findUserByUserName(String username) throws Exception {
        return userDao.findUserByUserName(username);
    }

    @Override
    public void alterPassWordBySelf(String passwd, String userId) throws Exception {
        String password = new BCryptPasswordEncoder().encode(passwd);
        userDao.alterPassWordBySelf(password,userId);
    }
}
