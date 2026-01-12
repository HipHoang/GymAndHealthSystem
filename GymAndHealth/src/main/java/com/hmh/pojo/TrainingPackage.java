/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author hieph
 */
@Entity
@Table(name = "training_package")
@NamedQueries({
    @NamedQuery(name = "TrainingPackage.findAll", query = "SELECT t FROM TrainingPackage t"),
    @NamedQuery(name = "TrainingPackage.findById", query = "SELECT t FROM TrainingPackage t WHERE t.id = :id"),
    @NamedQuery(name = "TrainingPackage.findByName", query = "SELECT t FROM TrainingPackage t WHERE t.name = :name"),
    @NamedQuery(name = "TrainingPackage.findByDuration", query = "SELECT t FROM TrainingPackage t WHERE t.duration = :duration"),
    @NamedQuery(name = "TrainingPackage.findByPrice", query = "SELECT t FROM TrainingPackage t WHERE t.price = :price"),
    @NamedQuery(name = "TrainingPackage.findByActive", query = "SELECT t FROM TrainingPackage t WHERE t.active = :active")
})
public class TrainingPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "duration")
    private Integer duration;

    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    @Lob
    @Size(max = 65535)
    @Column(name = "benefits")
    private String benefits;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
    @JsonIgnore
    private Set<UserPackage> userPackageSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
    @JsonIgnore
    private Set<PaymentTransaction> paymentTransactionSet;

    public TrainingPackage() {
    }

    public TrainingPackage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<UserPackage> getUserPackageSet() {
        return userPackageSet;
    }

    public void setUserPackageSet(Set<UserPackage> userPackageSet) {
        this.userPackageSet = userPackageSet;
    }

    public Set<PaymentTransaction> getPaymentTransactionSet() {
        return paymentTransactionSet;
    }

    public void setPaymentTransactionSet(Set<PaymentTransaction> paymentTransactionSet) {
        this.paymentTransactionSet = paymentTransactionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TrainingPackage)) {
            return false;
        }
        TrainingPackage other = (TrainingPackage) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.TrainingPackage[ id=" + id + " ]";
    }
}
