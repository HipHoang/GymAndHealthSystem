/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.User;
import com.hmh.repositories.UserRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hieph
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserById(int id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Query q = s.createNamedQuery("User.findByUsername", User.class);
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            Session session = factory.getObject().getCurrentSession();
            Query q = session.createNamedQuery("User.findByEmail", User.class);
            q.setParameter("email", email);
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = factory.getObject().getCurrentSession();
        Query q = session.createNamedQuery("User.findAll", User.class);
        return q.getResultList();
    }

    @Override
    public List<User> searchUsersByUsername(String username
    ) {
        Session session = factory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM User u WHERE u.username LIKE :kw", User.class);
        q.setParameter("kw", "%" + username + "%");
        return q.getResultList();
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(u);
        return u;
    }

    @Override
    public User updateUser(User u) {
        Session session = factory.getObject().getCurrentSession();
        session.merge(u);
        return u;
    }

    @Override
    public boolean deleteUser(int id
    ) {
        Session session = factory.getObject().getCurrentSession();
        User u = session.get(User.class, id);
        if (u != null) {
            session.delete(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean authenticate(String username, String password
    ) {
        User u = this.getUserByUsername(username);
        return this.passwordEncoder.matches(password, u.getPassword());
    }

    @Override
    public List<User> findByRole(String role
    ) {
        Session session = factory.getObject().getCurrentSession();
        Query q = session.createNamedQuery("User.findByUserRole", User.class);
        q.setParameter("userRole", role);
        return q.getResultList();
    }
}
