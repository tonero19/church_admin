package com.tony.church.component;

import java.math.BigDecimal;
import java.sql.Date;

public class Tithe {

    private Date date;
    private BigDecimal amount;
    private Integer titheId;
    private Integer memberId;



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

    public Integer getTitheId() {
        return titheId;
    }

    public void setTitheId(Integer titheId) {
        this.titheId = titheId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Tithe{" +
                "date=" + date +
                ", amount=" + amount +
                ", titheId=" + titheId +
                ", memberId=" + memberId +
                '}';
    }
}
