/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.BookingSchedule;
import com.hmh.pojo.User;
import com.hmh.services.BookingScheduleService;
import com.hmh.services.UserService;
import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBookingScheduleController {

    @Autowired
    private BookingScheduleService bookingScheduleService;

    @Autowired
    private UserService userService;

    @GetMapping("/secure/bookings/member")
    public ResponseEntity<?> getBookingsByMember(Principal principal) {
        String username = principal.getName();
        User member = userService.getUserByUsername(username);

        if (member == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy hội viên!");

        return ResponseEntity.ok(bookingScheduleService.getBookingsByMember(member));
    }

    @GetMapping("/secure/bookings/pt")
    public ResponseEntity<?> getBookingsByPT(Principal principal) {
        String username = principal.getName();
        User pt = userService.getUserByUsername(username);

        if (pt == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy PT!");

        return ResponseEntity.ok(bookingScheduleService.getBookingsByPT(pt));
    }

    @GetMapping("/secure/bookings/date")
    public ResponseEntity<?> getBookingsByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<BookingSchedule> bookings = bookingScheduleService.getBookingsByDate(date);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/secure/bookings")
    public ResponseEntity<?> createBooking(@RequestParam Map<String, String> params) {
        try {
            BookingSchedule booking = bookingScheduleService.createBooking(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/secure/bookings/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") int id,
                                          @RequestParam("status") String status) {
        BookingSchedule updated = bookingScheduleService.updateBookingStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/secure/bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") int id) {
        if (bookingScheduleService.deleteBooking(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy booking");
    }
}