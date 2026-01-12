/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.UserPackage;
import com.hmh.repositories.UserPackageRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserPackageRepositoryImpl implements UserPackageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private static final int PAGE_SIZE = 10;

    @Override
    public UserPackage getUserPackageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(UserPackage.class, id);
    }

    @Override
    public UserPackage addOrUpdateUserPackage(UserPackage up) {
        Session s = this.factory.getObject().getCurrentSession();
        if (up.getId() == null)
            s.persist(up);
        else
            s.merge(up);
        return up;
    }

    @Override
    public boolean deleteUserPackage(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        UserPackage up = this.getUserPackageById(id);
        if (up != null) {
            s.remove(up);
            return true;
        }
        return false;
    }

    @Override
    public List<UserPackage> getUserPackages(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<UserPackage> q = b.createQuery(UserPackage.class);
        Root<UserPackage> root = q.from(UserPackage.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("userId")) {
                int userId = Integer.parseInt(params.get("userId"));
                predicates.add(b.equal(root.get("userId").get("id"), userId));
            }

            if (params.containsKey("packageId")) {
                int pkgId = Integer.parseInt(params.get("packageId"));
                predicates.add(b.equal(root.get("packageId").get("id"), pkgId));
            }

            if (params.containsKey("status")) {
                predicates.add(b.equal(root.get("status"), params.get("status")));
            }

            if (params.containsKey("startDate")) {
                try {
                    Date start = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("startDate"));
                    predicates.add(b.greaterThanOrEqualTo(root.get("startDate"), start));
                } catch (Exception e) {
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("startDate")));

        Query query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult((page - 1) * PAGE_SIZE);
        }

        return query.getResultList();
    }
}
