package com.ustc.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DELL on 2018/3/4.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {

    @Id
    private long userId = 0;
    private String name = null;
    private String email = null;
    private String token = null;
    @Generated(hash = 2092852420)
    public UserProfile(long userId, String name, String email, String token) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.token = token;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
