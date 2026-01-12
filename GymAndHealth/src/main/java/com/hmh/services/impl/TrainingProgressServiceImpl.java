/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.pojo.TrainingProgress;
import com.hmh.repositories.TrainingProgressRepository;
import com.hmh.services.TrainingProgressService;
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
public class TrainingProgressServiceImpl implements TrainingProgressService {

    @Autowired
    private TrainingProgressRepository trainingProgressRepository;

    @Override
    public TrainingProgress addProgress(TrainingProgress p) {
        return this.trainingProgressRepository.addProgress(p);
    }

    @Override
    public List<TrainingProgress> getProgress(Map<String, String> params) {
        return this.trainingProgressRepository.getProgress(params);
    }
}
