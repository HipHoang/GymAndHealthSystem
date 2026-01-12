/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import com.hmh.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author hieph
 */
public interface UserService extends UserDetailsService {
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    List<User> searchUsersByUsername(String username);
    User addUser(Map<String, String> params, MultipartFile avatar);
    User updateUser(Map<String, String> params, MultipartFile avatar, int userId);
    boolean deleteUser(int id);
    boolean authenticate(String username, String password);
    List<User> findByRole(String role);
}
