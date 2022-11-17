package com.javafx.pos.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersistenceTest {

    private SessionFactory factory = null;

    @BeforeEach
    void setUp() {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public User saveUser(String firstname, String lastname,
                         String username, String password, String type) {

        User user = new User(firstname,lastname,username,password,type);
        try (Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
        return user;
    }

    @Test
    public void readUser() {
        User savedUser = saveUser("Erich","Gutierrez"
                ,"lusanex","12345","cashier");
        List<User> list;
        try (Session session = factory.openSession())
        {
            list = session
                    .createQuery("from users",User.class)
                    .list();
        }
        assertEquals(list.size(),1);
        for (User u : list){
            System.out.println(u);
        }
        assertEquals(list.get(0),savedUser);
    }

}
