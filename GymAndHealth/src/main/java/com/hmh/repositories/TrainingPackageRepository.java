/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories;

import com.hmh.pojo.TrainingPackage;
import java.util.List;
import java.util.Map;

public interface TrainingPackageRepository {
    TrainingPackage getTrainingPackageById(int id);
    TrainingPackage addOrUpdateTrainingPackage(TrainingPackage p);
    List<TrainingPackage> getTrainingPackages(Map<String, String> params);
    boolean deleteTrainingPackage(int id);
}
