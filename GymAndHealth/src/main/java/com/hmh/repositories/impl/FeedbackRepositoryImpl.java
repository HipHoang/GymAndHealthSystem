/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.Feedback;
import com.hmh.repositories.FeedbackRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
public class FeedbackRepositoryImpl implements FeedbackRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Feedback addFeedback(Feedback f) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(f);
        return f;
    }

    @Override
    public List<Feedback> getFeedbacks(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Feedback> q = b.createQuery(Feedback.class);
        Root<Feedback> root = q.from(Feedback.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("memberId")) {
                int memberId = Integer.parseInt(params.get("memberId"));
                predicates.add(b.equal(root.get("memberId").get("id"), memberId));
            }

            if (params.containsKey("targetId")) {
                int targetId = Integer.parseInt(params.get("targetId"));
                predicates.add(b.equal(root.get("targetId").get("id"), targetId));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("createdAt")));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Double getAverageRatingByTarget(int targetId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT AVG(f.rating) FROM Feedback f WHERE f.targetId.id = :id");
        q.setParameter("id", targetId);

        Object result = q.getSingleResult();
        return result != null ? (Double) result : 0.0;
    }
}
