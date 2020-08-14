package com.tony.church.entity;

import org.springframework.stereotype.Component;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity
@Table(name="service")
public class ChurchEvent extends AbstractEntity {

    @Transient
    public List<String> eventTypes;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    @NotNull(message = "type of service must be specified")
    private EventType eventType;

    @Column(name="attendance")
    //@NotNull(message = "Provide the number of attendees")
    private Integer attendance;

    @Column(name="service_date")
    @JsonbDateFormat(value="yyyy-MM-dd")
    //@PastOrPresent(message = "The service cannot have a future date")
    private Date serviceDate;

    @Column(name="offering_amount")
    //@NotNull(message = "Offering data must be entered")
    private BigDecimal offering;

    @Column(name="location")
    @NotNull(message = "Enter the location of the service")
    private String location;

    @Column(name="description")
    private String description;

    public ChurchEvent(){
        eventTypes = Stream.of(EventType.values())
                .map(EventType::name)
                .collect(Collectors.toList());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public BigDecimal getOffering() {
        return offering;
    }

    public void setOffering(BigDecimal offering) {
        this.offering = offering;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ChurchEvent{" +
                "eventTypes=" + eventTypes +
                ", eventType=" + eventType +
                ", attendance=" + attendance +
                ", serviceDate=" + serviceDate +
                ", offering=" + offering +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
