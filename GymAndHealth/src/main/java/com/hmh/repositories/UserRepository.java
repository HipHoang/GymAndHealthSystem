/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories;

import com.hmh.pojo.User;
import java.util.List;

/**
 *
 * @author hieph
 */
public interface UserRepository {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> searchUsersByUsername(String username);
    User addUser(User user);
    User updateUser(User user);
    boolean deleteUser(int id);
    boolean authenticate(String username, String password);
    List<User> findByRole(String role);
}
