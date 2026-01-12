/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories;

import com.hmh.pojo.UserPackage;
import java.util.List;
import java.util.Map;

public interface UserPackageRepository {
    UserPackage getUserPackageById(int id);
    UserPackage addOrUpdateUserPackage(UserPackage up);
    List<UserPackage> getUserPackages(Map<String, String> params);
    boolean deleteUserPackage(int id);
}
