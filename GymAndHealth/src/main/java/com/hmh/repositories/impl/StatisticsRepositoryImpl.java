package com.hmh.repositories.impl;

import com.hmh.pojo.TrainingProgress;
import com.hmh.pojo.User;
import com.hmh.repositories.StatisticsRepository;
import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatisticsRepositoryImpl implements StatisticsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsByWeek(int userId, LocalDate startDate, LocalDate endDate) {
        Session session = factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<TrainingProgress> tpRoot = cq.from(TrainingProgress.class);
        Join<TrainingProgress, User> userJoin = tpRoot.join("memberId");

        Expression<Integer> weekExp = cb.function("WEEK", Integer.class, tpRoot.get("date"));

        Predicate userPredicate = cb.equal(userJoin.get("id"), userId);
        Predicate startPredicate = cb.greaterThanOrEqualTo(tpRoot.get("date"), java.sql.Date.valueOf(startDate));
        Predicate endPredicate = cb.lessThanOrEqualTo(tpRoot.get("date"), java.sql.Date.valueOf(endDate));

        cq.multiselect(
                weekExp,
                cb.count(tpRoot.get("id")),
                cb.avg(tpRoot.get("bmi"))
        );

        cq.where(cb.and(userPredicate, startPredicate, endPredicate));
        cq.groupBy(weekExp);
        cq.orderBy(cb.asc(weekExp));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public List<Object[]> statsByMonth(int userId, LocalDate startDate, LocalDate endDate) {
        Session session = factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<TrainingProgress> tpRoot = cq.from(TrainingProgress.class);
        Join<TrainingProgress, User> userJoin = tpRoot.join("memberId");

        Expression<Integer> monthExp = cb.function("MONTH", Integer.class, tpRoot.get("date"));

        Predicate userPredicate = cb.equal(userJoin.get("id"), userId);
        Predicate startPredicate = cb.greaterThanOrEqualTo(tpRoot.get("date"), java.sql.Date.valueOf(startDate));
        Predicate endPredicate = cb.lessThanOrEqualTo(tpRoot.get("date"), java.sql.Date.valueOf(endDate));

        cq.multiselect(
                monthExp,
                cb.count(tpRoot.get("id")),
                cb.avg(tpRoot.get("bmi"))
        );

        cq.where(cb.and(userPredicate, startPredicate, endPredicate));
        cq.groupBy(monthExp);
        cq.orderBy(cb.asc(monthExp));

        return session.createQuery(cq).getResultList();
    }

}
