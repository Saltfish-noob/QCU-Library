package edu.qcu.dao;

import edu.qcu.domain.Permission;
import edu.qcu.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    //根据用户id查询所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "edu.qcu.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Delete("delete from users_role where userId=#{userId}")
    void delRoleToUser(String userId);

    @Select("delete from role_permission where roleId=#{roleId}")
    Role delPermissionByRoleId(String roleId);

    @Select("select * from Permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissionList(String roleId);

    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermission(@Param("roleId") String roleId,@Param("permissionId") String permissionId);

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Select("select COUNT(*) from users_role where roleId=#{roleId}")
    Integer findUserNumByRolesId(String roleId)throws Exception;

    @Delete("delete from role where id=#{roleId}")
    void delRoleById(String roleId)throws Exception;

    @Select("select COUNT(*) from role where roleName=#{roleName}")
    Integer findRoleByRoleName(String roleName);
}
