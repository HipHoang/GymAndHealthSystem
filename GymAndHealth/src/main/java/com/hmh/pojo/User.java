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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author hieph
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "User.findByDateOfBirth", query = "SELECT u FROM User u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "gender")
    private String gender;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<UserPackage> userPackageSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    @JsonIgnore
    private Set<BookingSchedule> memberScheduleSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ptId")
    @JsonIgnore
    private Set<BookingSchedule> ptScheduleSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    @JsonIgnore
    private Set<TrainingProgress> memberProgressSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ptId")
    @JsonIgnore
    private Set<TrainingProgress> ptProgressSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<PaymentTransaction> paymentTransactionSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    @JsonIgnore
    private Set<ChatMessage> senderChatSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverId")
    @JsonIgnore
    private Set<ChatMessage> receiverChatSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    @JsonIgnore
    private Set<Feedback> feedbackSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "targetId")
    @JsonIgnore
    private Set<Feedback> targetFeedbackSet;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private HealthProfile healthProfile;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, String email, String gender, String phone, String username, String password, String userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public HealthProfile getHealthProfile() {
        return healthProfile;
    }

    public void setHealthProfile(HealthProfile healthProfile) {
        this.healthProfile = healthProfile;
    }

    public Set<UserPackage> getUserPackageSet() {
        return userPackageSet;
    }

    public void setUserPackageSet(Set<UserPackage> userPackageSet) {
        this.userPackageSet = userPackageSet;
    }

    public Set<BookingSchedule> getMemberScheduleSet() {
        return memberScheduleSet;
    }

    public void setMemberScheduleSet(Set<BookingSchedule> memberScheduleSet) {
        this.memberScheduleSet = memberScheduleSet;
    }

    public Set<BookingSchedule> getPtScheduleSet() {
        return ptScheduleSet;
    }

    public void setPtScheduleSet(Set<BookingSchedule> ptScheduleSet) {
        this.ptScheduleSet = ptScheduleSet;
    }

    public Set<TrainingProgress> getMemberProgressSet() {
        return memberProgressSet;
    }

    public void setMemberProgressSet(Set<TrainingProgress> memberProgressSet) {
        this.memberProgressSet = memberProgressSet;
    }

    public Set<TrainingProgress> getPtProgressSet() {
        return ptProgressSet;
    }

    public void setPtProgressSet(Set<TrainingProgress> ptProgressSet) {
        this.ptProgressSet = ptProgressSet;
    }

    public Set<PaymentTransaction> getPaymentTransactionSet() {
        return paymentTransactionSet;
    }

    public void setPaymentTransactionSet(Set<PaymentTransaction> paymentTransactionSet) {
        this.paymentTransactionSet = paymentTransactionSet;
    }

    public Set<Feedback> getFeedbackSet() {
        return feedbackSet;
    }

    public void setFeedbackSet(Set<Feedback> feedbackSet) {
        this.feedbackSet = feedbackSet;
    }

    public Set<Feedback> getTargetFeedbackSet() {
        return targetFeedbackSet;
    }

    public void setTargetFeedbackSet(Set<Feedback> targetFeedbackSet) {
        this.targetFeedbackSet = targetFeedbackSet;
    }

    public Set<ChatMessage> getSenderChatSet() {
        return senderChatSet;
    }

    public void setSenderChatSet(Set<ChatMessage> senderChatSet) {
        this.senderChatSet = senderChatSet;
    }

    public Set<ChatMessage> getReceiverChatSet() {
        return receiverChatSet;
    }

    public void setReceiverChatSet(Set<ChatMessage> receiverChatSet) {
        this.receiverChatSet = receiverChatSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.User[ id=" + id + " ]";
    }
}
