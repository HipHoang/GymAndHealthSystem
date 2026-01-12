/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.pojo.TrainingPackage;
import com.hmh.repositories.TrainingPackageRepository;
import com.hmh.services.TrainingPackageService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author hieph
 */
@Service
public class TrainingPackageServiceImpl implements TrainingPackageService {

    @Autowired
    private TrainingPackageRepository trainingPackageRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public TrainingPackage getPackageById(int id) {
        return this.trainingPackageRepository.getTrainingPackageById(id);
    }

    @Override
    public List<TrainingPackage> getTrainingPackages(Map<String, String> params) {
        return this.trainingPackageRepository.getTrainingPackages(params);
    }

    @Override
    public TrainingPackage addOrUpdateTrainingPackage(Map<String, String> params, MultipartFile image) {
        try {
            TrainingPackage pkg = new TrainingPackage();

            if (params.containsKey("id"))
                pkg.setId(Integer.parseInt(params.get("id")));

            pkg.setName(params.get("name"));
            pkg.setDuration(Integer.parseInt(params.get("duration")));
            pkg.setPrice(new BigDecimal(params.get("price")));
            pkg.setBenefits(params.get("benefits"));
            pkg.setActive(Boolean.parseBoolean(params.getOrDefault("active", "true")));

            if (image != null && !image.isEmpty()) {
                Map res = this.cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                pkg.setImage(res.get("secure_url").toString());
            }

            return this.trainingPackageRepository.addOrUpdateTrainingPackage(pkg);

        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(TrainingPackageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean deleteTrainingPackage(int id) {
        return this.trainingPackageRepository.deleteTrainingPackage(id);
    }
}