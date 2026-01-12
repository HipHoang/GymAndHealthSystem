/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.Feedback;
import com.hmh.pojo.User;
import com.hmh.services.FeedbackService;
import com.hmh.services.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @PostMapping("/secure/feedbacks")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback, Principal principal) {
        String username = principal.getName();
        User member = userService.getUserByUsername(username);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy người dùng");
        }

        feedback.setMemberId(member);
        feedback.setCreatedAt(new Date());
        Feedback saved = feedbackService.addFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/secure/feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbacks(
            @RequestParam(required = false) Integer memberId,
            @RequestParam(required = false) Integer targetId) {

        Map<String, String> params = new HashMap<>();
        if (memberId != null)
            params.put("memberId", memberId.toString());
        if (targetId != null)
            params.put("targetId", targetId.toString());

        return ResponseEntity.ok(feedbackService.getFeedbacks(params));
    }

    @GetMapping("/secure/feedbacks/average-rating/{targetId}")
    public ResponseEntity<?> getAverageRating(@PathVariable("targetId") int targetId) {
        Double avg = feedbackService.getAverageRatingByTarget(targetId);
        return ResponseEntity.ok(Map.of("averageRating", avg));
    }
}
