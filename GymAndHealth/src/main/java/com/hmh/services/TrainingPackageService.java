/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import com.hmh.pojo.TrainingPackage;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hieph
 */
public interface TrainingPackageService {
    TrainingPackage getPackageById(int id);
    List<TrainingPackage> getTrainingPackages(Map<String, String> params);
    TrainingPackage addOrUpdateTrainingPackage(Map<String, String> params, MultipartFile image);
    boolean deleteTrainingPackage(int id);
}
