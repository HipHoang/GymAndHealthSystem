/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.PaymentTransaction;
import com.hmh.repositories.PaymentTransactionRepository;
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

/**
 *
 * @author hieph
 */
@Repository
@Transactional
public class PaymentTransactionRepositoryImpl implements PaymentTransactionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public PaymentTransaction addTransaction(PaymentTransaction t) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(t);
        return t;
    }

    @Override
    public List<PaymentTransaction> getTransactions(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<PaymentTransaction> q = b.createQuery(PaymentTransaction.class);
        Root<PaymentTransaction> root = q.from(PaymentTransaction.class);
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

            if (params.containsKey("fromDate") && params.containsKey("toDate")) {
                try {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    Date from = f.parse(params.get("fromDate"));
                    Date to = f.parse(params.get("toDate"));
                    predicates.add(b.between(root.get("createdAt"), from, to));
                } catch (Exception e) {
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("createdAt")));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Double getTotalRevenue() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT SUM(p.amount) FROM PaymentTransaction p WHERE p.status = 'success'");
        Object r = q.getSingleResult();
        return r != null ? (Double) r : 0.0;
    }
}
