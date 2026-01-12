/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.TrainingProgress;
import com.hmh.repositories.TrainingProgressRepository;
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
public class TrainingProgressRepositoryImpl implements TrainingProgressRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public TrainingProgress addProgress(TrainingProgress p) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(p);
        return p;
    }

    @Override
    public List<TrainingProgress> getProgress(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<TrainingProgress> q = b.createQuery(TrainingProgress.class);
        Root<TrainingProgress> root = q.from(TrainingProgress.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("memberId")) {
                int memberId = Integer.parseInt(params.get("memberId"));
                predicates.add(b.equal(root.get("memberId").get("id"), memberId));
            }

            if (params.containsKey("ptId")) {
                int ptId = Integer.parseInt(params.get("ptId"));
                predicates.add(b.equal(root.get("ptId").get("id"), ptId));
            }

            if (params.containsKey("date")) {
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("date"));
                    predicates.add(b.equal(root.get("date"), date));
                } catch (Exception e) {
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("date"))); // sắp xếp mới nhất trước

        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
