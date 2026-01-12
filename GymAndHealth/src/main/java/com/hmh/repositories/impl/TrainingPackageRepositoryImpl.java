/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.TrainingPackage;
import com.hmh.repositories.TrainingPackageRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hieph
 */
@Repository
@Transactional
public class TrainingPackageRepositoryImpl implements TrainingPackageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private static final int PAGE_SIZE = 10;

    @Override
    public TrainingPackage getTrainingPackageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(TrainingPackage.class, id);
    }

    @Override
    public TrainingPackage addOrUpdateTrainingPackage(TrainingPackage p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p.getId() == null)
            s.persist(p);
        else
            s.merge(p);
        return p;
    }

    @Override
    public boolean deleteTrainingPackage(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        TrainingPackage p = this.getTrainingPackageById(id);
        if (p != null) {
            s.remove(p);
            return true;
        }
        return false;
    }

    @Override
    public List<TrainingPackage> getTrainingPackages(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<TrainingPackage> q = b.createQuery(TrainingPackage.class);
        Root<TrainingPackage> root = q.from(TrainingPackage.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("kw")) {
                String kw = params.get("kw");
                predicates.add(b.like(root.get("name"), "%" + kw + "%"));
            }

            if (params.containsKey("fromPrice")) {
                BigDecimal from = new BigDecimal(params.get("fromPrice"));
                predicates.add(b.ge(root.get("price"), from));
            }

            if (params.containsKey("toPrice")) {
                BigDecimal to = new BigDecimal(params.get("toPrice"));
                predicates.add(b.le(root.get("price"), to));
            }

            if (params.containsKey("active")) {
                boolean isActive = Boolean.parseBoolean(params.get("active"));
                predicates.add(b.equal(root.get("active"), isActive));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.asc(root.get("price")));

        Query query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult((page - 1) * PAGE_SIZE);
        }

        return query.getResultList();
    }
}
