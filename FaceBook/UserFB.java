package com.company.FaceBook;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
@Entity
public class UserFB {
    @Id
    private int userId;
    private String name;
    private String email;
    private String password;
    private String followers2;
    private String post2;

    @Transient
    private List followers;

    @Transient
    private List post;


    public UserFB() {
    }

    public UserFB(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPost2() {
        return post2;
    }

    public void setPost2(String post2) {
        this.post2 = post2;
    }

    public List getPost() {
        return post;
    }

    public void setPost(List post) {
        this.post = post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFollowers2() {
        return followers2;
    }

    public void setFollowers2(String followers2) {
        this.followers2 = followers2;
    }

    public List getFollowers() {
        return followers;
    }

    public void setFollowers(List followers) {
        this.followers = followers;
    }
}
