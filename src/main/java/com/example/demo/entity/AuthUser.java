package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息(AuthUser)实体类
 *
 * @author makejava
 * @since 2021-10-15 11:20:57
 */
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 220118574300357221L;
    /**
    * 用户ID
    */
    private Integer id;
    /**
    * 用户代码
    */
    private String code;
    /**
    * 用户名 用于登录
    */
    private String name;
    /**
    * 密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 头像图片地址
    */
    private String avatar;
    /**
    * 个性签名
    */
    private String sign;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 创建时间
    */
    private Date createdTime;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthUser other = (AuthUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    public AuthUser() {

    }
    
    public AuthUser(int userId) {
        this.id = userId;
    }
    public AuthUser(AuthUser user) {
        this.id = user.id;
        this.code = user.code;
        this.name = user.name;
        this.password = user.password;
        this.nickname = user.nickname;
        this.avatar = user.avatar;
        this.sign = user.sign;
        this.phone = user.phone;
        this.email = user.email;
        this.createdTime = user.createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}