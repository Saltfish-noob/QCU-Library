package edu.qcu.domain;


import edu.qcu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

public class UserInfo {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private Integer status;
    private String statusStr;
    private String nickname;
    private Integer sex;
    private String sexStr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String birthdayStr;
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status == 0)
            statusStr = "停用";
        else
            statusStr = "可用";
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexStr() {
        if (sex == 0)
            sexStr = "女";
        else
            sexStr = "男";
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayStr() {
        if (birthday!=null){
            birthdayStr = DateUtils.date2String(birthday,"yyyy-MM-dd");
        }
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", sexStr='" + sexStr + '\'' +
                ", birthday=" + birthday +
                ", birthdayStr='" + birthdayStr + '\'' +
                ", roles=" + roles +
                '}';
    }
}
