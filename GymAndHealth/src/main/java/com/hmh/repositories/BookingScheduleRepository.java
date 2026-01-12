/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repositories;

import com.hmh.pojo.BookingSchedule;
import java.util.List;
import java.util.Map;

public interface BookingScheduleRepository {
    BookingSchedule getBookingScheduleById(int id);
    BookingSchedule addBookingSchedule(BookingSchedule s);
    boolean deleteBookingSchedule(int id);
    List<BookingSchedule> getBookingSchedules(Map<String, String> params);
}
