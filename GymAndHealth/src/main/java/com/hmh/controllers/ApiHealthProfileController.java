package com.hmh.controllers;

import com.hmh.pojo.HealthProfile;
import com.hmh.pojo.User;
import com.hmh.services.HealthProfileService;
import com.hmh.services.UserService;
import java.security.Principal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiHealthProfileController {

    @Autowired
    private HealthProfileService healthProfileService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/health-profiles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") int id) {
        this.healthProfileService.deleteHealthProfile(id);
    }

    @GetMapping("/secure/health-profiles")
    @ResponseBody
    public ResponseEntity<HealthProfile> getMyHealthProfile(Principal principal) {
        String username = principal.getName();

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HealthProfile profile = healthProfileService.getHealthProfileByUserId(user.getId());
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("secure/health-profiles/{id}")
    public ResponseEntity<HealthProfile> getHealthProfileById(@PathVariable(value = "id") int id) {
        HealthProfile healthProfile = healthProfileService.getHealthProfileById(id);

        if (healthProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(healthProfile);
    }

    @PostMapping("/secure/health-profile/add")
    public ResponseEntity<?> addHealthProfile(@RequestBody HealthProfile hp, Principal principal) {
        try {
            String username = principal.getName(); 
            User user = this.userService.getUserByUsername(username); 

            if (user == null) {
                return new ResponseEntity<>("Không tìm thấy người dùng!", HttpStatus.BAD_REQUEST);
            }

            hp.setUserId(user); 

            if (hp.getHeight() > 0 && hp.getWeight() > 0) {
                float heightInMeters = hp.getHeight() / 100f;
                float bmi = hp.getWeight() / (heightInMeters * heightInMeters);
                hp.setBmi(bmi);
            } else {
                hp.setBmi(0);
            }

            hp.setUpdatedAt(new Date()); 

            return new ResponseEntity<>(this.healthProfileService.saveHealthProfile(hp), HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace(); 
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi lưu hồ sơ!");
        }
    }

    @PutMapping("/secure/health-profile/update")
    public ResponseEntity<?> updateHealthProfile(@RequestBody HealthProfile hp, Principal principal) {
        try {       
            String username = principal.getName();
            User user = this.userService.getUserByUsername(username);

            if (user == null) {
                return new ResponseEntity<>("Không tìm thấy người dùng!", HttpStatus.BAD_REQUEST);
            }

            HealthProfile existing = this.healthProfileService.getHealthProfileById(hp.getId());

            if (existing == null) {
                return new ResponseEntity<>("Không tìm thấy hồ sơ sức khỏe!", HttpStatus.NOT_FOUND);
            }

            if (!existing.getUserId().getId().equals(user.getId())) {
                return new ResponseEntity<>("Không có quyền cập nhật hồ sơ sức khỏe của người khác!", HttpStatus.FORBIDDEN);
            }

            hp.setUserId(user);

            if (hp.getHeight() > 0 && hp.getWeight() > 0) {
                float heightInMeters = hp.getHeight() / 100f;
                float bmi = hp.getWeight() / (heightInMeters * heightInMeters);
                hp.setBmi(bmi);
            }

            hp.setUpdatedAt(new Date());

            HealthProfile updated = this.healthProfileService.saveHealthProfile(hp);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
