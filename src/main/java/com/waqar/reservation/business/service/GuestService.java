package com.waqar.reservation.business.service;

import com.waqar.reservation.data.entity.Guest;
import com.waqar.reservation.data.repositories.GuestRepository;
import com.waqar.reservation.data.repositories.ReservationRepository;
import com.waqar.reservation.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public GuestService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Guest> getAllGuests() {
        List<Guest> guestList = new ArrayList<>();
        this.guestRepository.findAll().forEach(guestList::add);
        return guestList;
    }

    public Guest addGuest(Guest guest) {
        return this.guestRepository.save(guest);
    }

    public Optional<Guest> findGuestById(Long id) {
        return this.guestRepository.findById(id);
    }
}
