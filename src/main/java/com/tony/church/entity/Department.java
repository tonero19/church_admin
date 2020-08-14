package com.tony.church.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Department extends AbstractEntity {

    @Column(name="name")
   // @NotEmpty(message = "Department name must be set")
    private String departmentName;

    public Department(){}

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "departments")
    @JsonIgnoreProperties("departments")
    private Set<Member> members = new HashSet<>();


/*@OneToMany(mappedBy = "department")
   // @OrderBy("fullName ASC, dateOfBirth desc ")
    private List<Member> members = new ArrayList<>();*/


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof Department) ) return false;

        final Department dept = (Department) other;

        if ( !dept.getDepartmentName().equals( getDepartmentName()) ) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = getDepartmentName().hashCode();
        result = 29 * result + 10;
        return result;
    }
}
