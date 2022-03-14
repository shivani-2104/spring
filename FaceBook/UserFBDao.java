package com.company.FaceBook;

import java.util.List;

public interface UserFBDao {
    List<UserFB> readAll();
    void create(UserFB userFB);
    void post(PostFB post);
    List readByEmail(String email,String password);
//    void update(User user);
public boolean delete(String email,String password);
//List getUsersDetails();
    void follow(String email);
    void block(String email);
    List allFollwers();
}
