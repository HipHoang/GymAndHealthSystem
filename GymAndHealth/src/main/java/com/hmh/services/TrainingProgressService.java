/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import com.hmh.pojo.TrainingProgress;
import java.util.List;
import java.util.Map;


/**
 *
 * @author hieph
 */

public interface TrainingProgressService {
    TrainingProgress addProgress(TrainingProgress p);
    List<TrainingProgress> getProgress(Map<String, String> params);
}
