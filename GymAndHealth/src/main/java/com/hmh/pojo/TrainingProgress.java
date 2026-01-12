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
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author hieph
 */
@Entity
@Table(name = "training_progress")
@NamedQueries({
    @NamedQuery(name = "TrainingProgress.findAll", query = "SELECT t FROM TrainingProgress t"),
    @NamedQuery(name = "TrainingProgress.findById", query = "SELECT t FROM TrainingProgress t WHERE t.id = :id"),
    @NamedQuery(name = "TrainingProgress.findByDate", query = "SELECT t FROM TrainingProgress t WHERE t.date = :date"),
    @NamedQuery(name = "TrainingProgress.findByMember", query = "SELECT t FROM TrainingProgress t WHERE t.memberId = :memberId")
})
public class TrainingProgress implements Serializable {

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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "bmi")
    private BigDecimal bmi;

    @Column(name = "muscle_mass")
    private BigDecimal muscleMass;

    @Lob
    @Column(name = "notes")
    private String notes;

    public TrainingProgress() {
    }

    public TrainingProgress(Integer id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public void setBmi(BigDecimal bmi) {
        this.bmi = bmi;
    }

    public BigDecimal getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(BigDecimal muscleMass) {
        this.muscleMass = muscleMass;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TrainingProgress)) {
            return false;
        }
        TrainingProgress other = (TrainingProgress) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.TrainingProgress[ id=" + id + " ]";
    }
}
