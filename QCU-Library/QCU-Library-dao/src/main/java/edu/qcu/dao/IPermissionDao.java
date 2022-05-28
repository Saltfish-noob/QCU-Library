package edu.qcu.dao;

import edu.qcu.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Insert("insert into permission(permissionName,url,description) values(#{permissionName},#{url},#{description})")
    void save(Permission permission)throws Exception;

    @Select("select * from permission where id=#{permissionId}")
    Permission findById(String permissionId)throws Exception;

    @Update("update permission set permissionName=#{permissionName},url=#{url},description=#{description} where id=#{id}")
    void updatePermission(Permission permission)throws Exception;

    @Delete("delete from permission where id=#{id}")
    void delPermission(String id)throws Exception;

    @Select("select COUNT(*) from role_permission where permissionId=#{id}")
    Integer findRoleByPermissionId(String id);
}
