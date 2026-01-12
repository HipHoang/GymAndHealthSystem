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
@Table(name = "payment_transaction")
@NamedQueries({
    @NamedQuery(name = "PaymentTransaction.findAll", query = "SELECT p FROM PaymentTransaction p"),
    @NamedQuery(name = "PaymentTransaction.findById", query = "SELECT p FROM PaymentTransaction p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentTransaction.findByStatus", query = "SELECT p FROM PaymentTransaction p WHERE p.status = :status"),
    @NamedQuery(name = "PaymentTransaction.findByMethod", query = "SELECT p FROM PaymentTransaction p WHERE p.method = :method"),
    @NamedQuery(name = "PaymentTransaction.findByCreatedAt", query = "SELECT p FROM PaymentTransaction p WHERE p.createdAt = :createdAt")
})
public class PaymentTransaction implements Serializable {

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

    @Basic(optional = false)
    @Column(name = "amount")
    private BigDecimal amount;

    @Basic(optional = false)
    @Column(name = "method")
    private String method;

    @Column(name = "status")
    private String status = "pending";

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public PaymentTransaction() {
    }

    public PaymentTransaction(Integer id) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PaymentTransaction)) {
            return false;
        }
        PaymentTransaction other = (PaymentTransaction) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.PaymentTransaction[ id=" + id + " ]";
    }
}
