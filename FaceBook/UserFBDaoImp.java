package com.company.FaceBook;

import com.company.Day18Part2.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserFBDaoImp implements UserFBDao
{
    @Autowired
Session session;

    public List readAll()
    {
        return session.createQuery("from UserFB", UserFB.class).getResultList();
    }
    public void create(UserFB userFB)
    {
        session.beginTransaction();
        session.persist(userFB);
        session.getTransaction().commit();
    }
    public List readByEmail(String email,String password)
    {
        Query q=session.createQuery(" from UserFB where email=:e and password=:p");
        q.setParameter("e",email);
        q.setParameter("p",password);
        List lists=q.getResultList();
        return lists;
    }
    public boolean delete(String email,String password)
    {
        session.beginTransaction();
        Query q1=session.createQuery("delete from User where email=:e and password=:p");
        q1.setParameter("e",email);
        q1.setParameter("p",password);
        q1.executeUpdate();
        session.getTransaction().commit();
        return true;
    }
    public void post(PostFB post)
    {
        session.beginTransaction();

        Query q=session.createQuery("select userId from UserFB where email=:e and password=:p");
        q.setParameter("e",post.getEmail());
        q.setParameter("p",post.getPassword());

        int x=(Integer)q.getSingleResult();
        System.out.println("x== "+x);
        UserFB data=session.get(UserFB.class,x);
        List list;
        if(data.getPost()==null)
        {
            list=new ArrayList();
        }
        else
        {
            list=data.getPost();
        }
        list.add(post.getPost());
        data.setPost2(""+list);
        session.persist(data);
        session.persist(post);
        session.getTransaction().commit();

    }
//    public List getUsersDetails()
//    {
//        session.createQuery("from UserFB",User.class).getResultList();
//    }
public void follow(String email)
{
    session.beginTransaction();

    Query q=session.createQuery("select userId from UserFB where email=:e");
    q.setParameter("e",email);

    int x=(Integer)q.getSingleResult();
    System.out.println("x== "+x);

    UserFB data=session.get(UserFB.class,x);
    List list;

    Query q5=session.createQuery("select followers2 from UserFB where userId=:u");
    q5.setParameter("u",x);
    list=q5.getResultList();

    if(list==null)
    {
        list=new ArrayList();
    }

    list.add(FacebookAPI.currentEmail);

    data.setFollowers(list);

    data.setFollowers2(""+list);
    session.persist(data);
    session.getTransaction().commit();
}
public List allFollwers()
    {
        List list;
        Query q5=session.createQuery("select followers2 from UserFB where email=:e");
        q5.setParameter("e",FacebookAPI.currentEmail);
        list=q5.getResultList();
        return list;
    }
}
