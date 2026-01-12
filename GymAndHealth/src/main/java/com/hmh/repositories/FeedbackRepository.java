/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories;

import com.hmh.pojo.Feedback;
import java.util.List;
import java.util.Map;

public interface FeedbackRepository {
    Feedback addFeedback(Feedback f);
    List<Feedback> getFeedbacks(Map<String, String> params);
    Double getAverageRatingByTarget(int targetId);
}
