package com.waqar.reservation.business.domain;

import java.util.Date;

public class ReservationDTO {
    private long id;
    private Date reservationDate;

    private long roomId;
    private String number;
    private int totalBeds;
    private String remarks;

    private long guestId;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String country;
    private String phoneNumber;

    public ReservationDTO() {
    }

    public ReservationDTO(long id, Date reservationDate, long roomId, long guestId) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.roomId = roomId;
        this.guestId = guestId;
    }

    public ReservationDTO(long id, Date reservationDate, long roomId, String number, int totalBeds, String remarks, long guestId, String firstName, String lastName, int age, String email, String country, String phoneNumber) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.roomId = roomId;
        this.number = number;
        this.totalBeds = totalBeds;
        this.remarks = remarks;
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
