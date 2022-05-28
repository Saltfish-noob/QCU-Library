package edu.qcu.dao;

import edu.qcu.domain.Role;
import edu.qcu.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "status", column = "status"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "edu.qcu.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "status", column = "status"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "edu.qcu.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id)throws Exception;

    @Insert("insert into users(username,password,email,status,nickname,sex,birthday,phoneNum) " +
            "values(#{username},#{password},#{email},#{status},#{nickname},#{sex},#{birthday},#{phoneNum})")
    void save(UserInfo userInfo);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{id} )")
    List<Role> findOtherRoles(String id)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId)throws Exception;

    @Update("update users set username=#{username},password=#{password},email=#{email},status=#{status},nickname=#{nickname},sex=#{sex},birthday=#{birthday},phoneNum=#{phoneNum} where id=#{id}")
    void updateUserInfo(UserInfo userInfo)throws Exception;

    @Delete("delete from users where id=#{userId}")
    void delUserById(String userId);

    @Select("select COUNT(*) from users where username=#{username}")
    Integer findUserByUserName(String username);

    @Update("update users set password=#{password} where id=#{userId}")
    void alterPassWordBySelf(@Param("password")String password,@Param("userId") String userId);
}
