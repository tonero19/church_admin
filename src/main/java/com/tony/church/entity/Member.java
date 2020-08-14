package com.tony.church.entity;

import com.fasterxml.jackson.annotation.*;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Member extends AbstractEntity{

    @Embedded
    private Address address;

    @NotNull(message = "first name must exist")
    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "member", orphanRemoval=true) // //cascade = { CascadeType.ALL
    @JsonBackReference
    private List<TitheDetail> titheDetails = new ArrayList<>();

//    @OneToMany(mappedBy = "member")
//    // @OrderBy("fullName ASC, dateOfBirth desc ")
//    private List<TitheDetail> titheDetails = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "members_departments",
            joinColumns = { @JoinColumn(name = "member_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") })
    @JsonIgnoreProperties("members")
    private Set<Department> departments = new HashSet<>();


    @Column(name="date_of_birth")
    @JsonbDateFormat(value="yyyy-MM-dd")
    //@Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="join_date")
    private Date joinDate;


    public void addDepartment(Department department){
        departments.add(department);
        department.getMembers().add(this);
    }

    public void removeDepartment(Department department){
        departments.remove(department);
        department.getMembers().remove(this);
    }

    public void removeDepartment(TitheDetail titheDetail){
        titheDetails.remove(titheDetail);
        titheDetail.setMember(null);
    }

    public Address getAddress() {
        return address;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<TitheDetail> getTitheDetails() {
        return titheDetails;
    }

    public void setTitheDetails(List<TitheDetail> titheDetails) {
        this.titheDetails = titheDetails;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        return getId() != null && getId().equals(((Member) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Member{" +
                address.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", date=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
