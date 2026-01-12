/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import com.hmh.pojo.BookingSchedule;
import com.hmh.pojo.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hieph
 */
public interface BookingScheduleService {
    BookingSchedule getBookingById(int id);
    List<BookingSchedule> getAllBookings();
    List<BookingSchedule> getBookingsByMember(User member);
    List<BookingSchedule> getBookingsByPT(User pt);
    List<BookingSchedule> getBookingsByDate(Date date);
    BookingSchedule createBooking(Map<String, String> params);
    BookingSchedule updateBookingStatus(int id, String status);
    boolean deleteBooking(int id);
    List<BookingSchedule> getBookingSchedules(Map<String, String> params);
}
