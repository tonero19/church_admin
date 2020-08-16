package com.tony.church.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="tithe_detail")
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class TitheDetail extends AbstractEntity {

    @ManyToOne()
    @JoinColumn(name="member_id")
   // @JsonManagedReference
    private Member member;

    @Column(name="td_date")
    @JsonbDateFormat(value="yyyy-MM-dd")
   // @PastOrPresent(message = "A date for tithe payment must be provided")
    private Date date;

    @Column(name="amount")
    @NotNull(message = "An amount must be entered")
    private BigDecimal amount;


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitheDetail)) return false;
        return getId() != null && getId().equals(((TitheDetail) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TitheDetail{" +
                "Id= " + getId() +
                ", member= " + member +
                ", date= " + date +
                ", amount= " + amount +
                '}';
    }
}
