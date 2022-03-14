package com.company.FaceBook;

import com.company.Day18Part2.Tweet;
import com.company.Day18Part2.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    Session getSession()
    {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(UserFB.class);
        configuration.addAnnotatedClass(PostFB.class);
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
