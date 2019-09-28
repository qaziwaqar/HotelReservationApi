package com.waqar.reservation.business.service;

import com.waqar.reservation.data.entity.Reservation;
import com.waqar.reservation.data.entity.Room;
import com.waqar.reservation.data.repositories.GuestRepository;
import com.waqar.reservation.data.repositories.ReservationRepository;
import com.waqar.reservation.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Room> getAllAvailableRooms() {
        List<Room> roomList = new ArrayList<>();
        this.roomRepository.findAll().forEach(roomList::add);

        List<Reservation> reservationList = new ArrayList<>();
        this.reservationRepository.findAll().forEach(reservationList::add);
        Set<Long> reservedRoomIdSet = reservationList.stream().map(Reservation::getRoomId).collect(Collectors.toSet());

        List<Room> roomResultList = roomList.stream().filter(room -> !reservedRoomIdSet.contains(room.getId())).collect(Collectors.toList());
        return roomResultList;
    }
}
