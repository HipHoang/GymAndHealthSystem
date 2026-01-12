/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
@Table(name = "user_package")
@NamedQueries({
    @NamedQuery(name = "UserPackage.findAll", query = "SELECT u FROM UserPackage u"),
    @NamedQuery(name = "UserPackage.findById", query = "SELECT u FROM UserPackage u WHERE u.id = :id"),
    @NamedQuery(name = "UserPackage.findByStatus", query = "SELECT u FROM UserPackage u WHERE u.status = :status"),
    @NamedQuery(name = "UserPackage.findByStartDate", query = "SELECT u FROM UserPackage u WHERE u.startDate = :startDate"),
    @NamedQuery(name = "UserPackage.findByEndDate", query = "SELECT u FROM UserPackage u WHERE u.endDate = :endDate")
})
public class UserPackage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private TrainingPackage packageId;

    @Column(name = "status")
    private String status;

    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public UserPackage() {
    }

    public UserPackage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public TrainingPackage getPackageId() {
        return packageId;
    }

    public void setPackageId(TrainingPackage packageId) {
        this.packageId = packageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserPackage)) {
            return false;
        }
        UserPackage other = (UserPackage) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.UserPackage[ id=" + id + " ]";
    }

}
