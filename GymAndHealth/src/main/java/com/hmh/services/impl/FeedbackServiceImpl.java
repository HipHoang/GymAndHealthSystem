/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.pojo.Feedback;
import com.hmh.repositories.FeedbackRepository;
import com.hmh.services.FeedbackService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author hieph
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return this.feedbackRepository.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getFeedbacks(Map<String, String> params) {
        return this.feedbackRepository.getFeedbacks(params);
    }

    @Override
    public Double getAverageRatingByTarget(int targetId) {
        return this.feedbackRepository.getAverageRatingByTarget(targetId);
    }
}
