/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.BookingSchedule;
import com.hmh.services.BookingScheduleService;
import com.hmh.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */

@Controller
public class BookingScheduleController {

    @Autowired
    private BookingScheduleService bookingScheduleService;

    @Autowired
    private UserService userService;

    @GetMapping("/booking-add-view")
    public String addBookingView(Model model) {
        model.addAttribute("booking", new BookingSchedule());
        model.addAttribute("users", userService.getAllUsers());
        return "addbookingschedule";
    }

    @PostMapping("/bookings/add")
    public String createBooking(@RequestParam Map<String, String> params, Model model) {
        try {
            bookingScheduleService.createBooking(params);
            return "redirect:/bookings";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("users", userService.getAllUsers());
            return "addbookingschedule";
        }
    }

    @PostMapping("/bookings/{id}/update-status")
    public String updateStatus(@PathVariable("id") int id, @RequestParam("status") String status) {
        bookingScheduleService.updateBookingStatus(id, status);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        bookingScheduleService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
