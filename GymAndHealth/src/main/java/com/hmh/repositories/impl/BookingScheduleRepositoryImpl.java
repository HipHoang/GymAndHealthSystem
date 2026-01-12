/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories.impl;

import com.hmh.pojo.BookingSchedule;
import com.hmh.repositories.BookingScheduleRepository;
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

@Repository
@Transactional
public class BookingScheduleRepositoryImpl implements BookingScheduleRepository {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public BookingSchedule getBookingScheduleById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(BookingSchedule.class, id);
    }

    @Override
    public BookingSchedule addBookingSchedule(BookingSchedule s) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(s);
        return s;
    }

    @Override
    public boolean deleteBookingSchedule(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        BookingSchedule s = session.get(BookingSchedule.class, id);
        if (s != null) {
            session.remove(s);
            return true;
        }
        return false;
    }

    @Override
    public List<BookingSchedule> getBookingSchedules(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BookingSchedule> cq = builder.createQuery(BookingSchedule.class);
        Root<BookingSchedule> root = cq.from(BookingSchedule.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("ptId")) {
                int ptId = Integer.parseInt(params.get("ptId"));
                predicates.add(builder.equal(root.get("ptId").get("id"), ptId));
            }

            if (params.containsKey("memberId")) {
                int memberId = Integer.parseInt(params.get("memberId"));
                predicates.add(builder.equal(root.get("memberId").get("id"), memberId));
            }

            if (params.containsKey("status")) {
                predicates.add(builder.equal(root.get("status"), params.get("status")));
            }

            cq.where(predicates.toArray(Predicate[]::new));
        }

        Query q = session.createQuery(cq);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            q.setMaxResults(PAGE_SIZE);
            q.setFirstResult((page - 1) * PAGE_SIZE);
        }

        return q.getResultList();
    }
}
