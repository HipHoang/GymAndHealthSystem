/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.pojo.User;
import com.hmh.repositories.UserRepository;
import com.hmh.services.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hieph
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> searchUsersByUsername(String username) {
        return userRepository.searchUsersByUsername(username);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        String username = params.get("username");
        String email = params.get("email");

        if (userRepository.getUserByUsername(username) != null) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (userRepository.getUserByEmail(email) != null) {
            throw new RuntimeException("Email đã tồn tại, vui lòng dùng email khác.");
        }

        User u = new User();
        u.setUsername(username);
        u.setFirstName(params.get("first_name"));
        u.setLastName(params.get("last_name"));
        u.setEmail(email);
        u.setPhone(params.get("phone"));
        u.setUserRole(params.get("user_role"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setActive(true);

        try {
            String dob = params.get("date_of_birth");
            if (dob != null && !dob.isEmpty()) {
                LocalDate date = LocalDate.parse(dob);
                u.setDateOfBirth(java.sql.Date.valueOf(date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "avatar"
                ));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            u.setAvatar("https://res.cloudinary.com/dy9g3l14t/image/upload/v1754592125/hinh-nen-gym-cho-dien-thoai-chat-ngau-5-08-11-18-40_nomntf.jpg");
        }

        return this.userRepository.addUser(u);
    }

    @Override
    public User updateUser(Map<String, String> params, MultipartFile avatar, int userId) {
        User u = userRepository.getUserById(userId);
        if (u == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }

        String newUsername = params.get("username");
        String newEmail = params.get("email");

        User existing = userRepository.getUserByUsername(newUsername);
        if (existing != null && !existing.getId().equals(userId)) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        u.setUsername(newUsername);
        u.setFirstName(params.get("first_name"));
        u.setLastName(params.get("last_name"));
        u.setEmail(newEmail);
        u.setPhone(params.get("phone"));
        String role = params.get("user_role");
        if (role != null && !role.isEmpty()) {
            u.setUserRole(role);
        }

        String password = params.get("password");
        if (password != null && !password.isEmpty()) {
            u.setPassword(passwordEncoder.encode(password));
        }

        try {
            String dob = params.get("date_of_birth");
            if (dob != null && !dob.isEmpty()) {
                LocalDate date = LocalDate.parse(dob);
                u.setDateOfBirth(java.sql.Date.valueOf(date));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Sai định dạng ngày sinh");
        }

        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                throw new RuntimeException("Không thể upload avatar");
            }
        }

        return userRepository.updateUser(u);
    }

    @Override
    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(u.getUserRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), roles);
    }
}
