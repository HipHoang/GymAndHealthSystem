/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hieph
 */
@Entity
@Table(name = "booking_schedule")
@NamedQueries({
    @NamedQuery(name = "BookingSchedule.findAll", query = "SELECT b FROM BookingSchedule b"),
    @NamedQuery(name = "BookingSchedule.findById", query = "SELECT b FROM BookingSchedule b WHERE b.id = :id"),
    @NamedQuery(name = "BookingSchedule.findByType", query = "SELECT b FROM BookingSchedule b WHERE b.type = :type"),
    @NamedQuery(name = "BookingSchedule.findByStatus", query = "SELECT b FROM BookingSchedule b WHERE b.status = :status"),
    @NamedQuery(name = "BookingSchedule.findByScheduleTime", query = "SELECT b FROM BookingSchedule b WHERE b.scheduleTime = :scheduleTime")
})
public class BookingSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private User memberId;

    @ManyToOne
    @JoinColumn(name = "pt_id", referencedColumnName = "id")
    private User ptId;

    @Basic(optional = false)
    @Column(name = "schedule_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleTime;

    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "note")
    private String note;

    public BookingSchedule() {
    }

    public BookingSchedule(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMemberId() {
        return memberId;
    }

    public void setMemberId(User memberId) {
        this.memberId = memberId;
    }

    public User getPtId() {
        return ptId;
    }

    public void setPtId(User ptId) {
        this.ptId = ptId;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BookingSchedule)) {
            return false;
        }
        BookingSchedule other = (BookingSchedule) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.BookingSchedule[ id=" + id + " ]";
    }
}
