/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.pojo.BookingSchedule;
import com.hmh.pojo.User;
import com.hmh.repositories.BookingScheduleRepository;
import com.hmh.repositories.UserRepository;
import com.hmh.services.BookingScheduleService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hieph
 */
@Service
public class BookingScheduleServiceImpl implements BookingScheduleService {

    @Autowired
    private BookingScheduleRepository bookingScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BookingSchedule getBookingById(int id) {
        return this.bookingScheduleRepository.getBookingScheduleById(id);
    }

    @Override
    public List<BookingSchedule> getAllBookings() {
        return this.bookingScheduleRepository.getBookingSchedules(null);
    }

    @Override
    public List<BookingSchedule> getBookingsByMember(User member) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", member.getId().toString());
        return this.bookingScheduleRepository.getBookingSchedules(params);
    }

    @Override
    public List<BookingSchedule> getBookingsByPT(User pt) {
        Map<String, String> params = new HashMap<>();
        params.put("ptId", pt.getId().toString());
        return this.bookingScheduleRepository.getBookingSchedules(params);
    }

    @Override
    public List<BookingSchedule> getBookingsByDate(Date date) {
        return this.getAllBookings(); 
    }

    @Override
    public BookingSchedule createBooking(Map<String, String> params) {
        try {
            BookingSchedule booking = new BookingSchedule();

            int memberId = Integer.parseInt(params.get("member_id"));
            User member = userRepository.getUserById(memberId);
            booking.setMemberId(member);

            if (params.containsKey("pt_id") && !params.get("pt_id").isEmpty()) {
                int ptId = Integer.parseInt(params.get("pt_id"));
                User pt = userRepository.getUserById(ptId);
                booking.setPtId(pt);
            }

            String scheduleTimeStr = params.get("schedule_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date scheduleTime = sdf.parse(scheduleTimeStr);
            booking.setScheduleTime(scheduleTime);

            booking.setType(params.get("type"));
            booking.setStatus("pending");
            booking.setNote(params.getOrDefault("note", ""));

            return this.bookingScheduleRepository.addBookingSchedule(booking);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public BookingSchedule updateBookingStatus(int id, String status) {
        BookingSchedule booking = this.bookingScheduleRepository.getBookingScheduleById(id);
        if (booking != null) {
            booking.setStatus(status);
            return this.bookingScheduleRepository.addBookingSchedule(booking);
        }
        return null;
    }

    @Override
    public boolean deleteBooking(int id) {
        return this.bookingScheduleRepository.deleteBookingSchedule(id);
    }
    
    @Override
    public List<BookingSchedule> getBookingSchedules(Map<String, String> params) {
        return bookingScheduleRepository.getBookingSchedules(params);
    }
}