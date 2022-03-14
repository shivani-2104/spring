package com.company.FaceBook;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostFB {
    @Id
    private String email;
    private String password;
    private String post;

    public PostFB() {
    }

    public PostFB(String email, String password, String post) {
        this.email = email;
        this.password = password;
        this.post = post;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "PostFB{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
