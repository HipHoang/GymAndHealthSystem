/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.pojo.UserPackage;
import com.hmh.repositories.UserPackageRepository;
import com.hmh.services.UserPackageService;
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
public class UserPackageServiceImpl implements UserPackageService {

    @Autowired
    private UserPackageRepository userPackageRepository;

    @Override
    public UserPackage getUserPackageById(int id) {
        return this.userPackageRepository.getUserPackageById(id);
    }

    @Override
    public UserPackage addOrUpdateUserPackage(UserPackage up) {
        return this.userPackageRepository.addOrUpdateUserPackage(up);
    }

    @Override
    public List<UserPackage> getUserPackages(Map<String, String> params) {
        return this.userPackageRepository.getUserPackages(params);
    }

    @Override
    public boolean deleteUserPackage(int id) {
        return this.userPackageRepository.deleteUserPackage(id);
    }
}
