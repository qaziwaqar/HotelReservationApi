package com.waqar.reservation.data.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "number")
    private String number;
    @Column(name = "total_beds")
    private int totalBeds;
    @Column(name = "remarks")
    private String remarks;

    public Room() {
    }

    public Room(String number, int totalBeds, String remarks) {
        this.number = number;
        this.totalBeds = totalBeds;
        this.remarks = remarks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("number='" + number + "'")
                .add("totalBeds=" + totalBeds)
                .add("remarks='" + remarks + "'")
                .toString();
    }
}