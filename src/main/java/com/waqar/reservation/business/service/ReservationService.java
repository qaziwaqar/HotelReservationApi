package com.waqar.reservation.business.service;

import com.waqar.reservation.business.domain.ReservationDTO;
import com.waqar.reservation.data.entity.Guest;
import com.waqar.reservation.data.entity.Reservation;
import com.waqar.reservation.data.entity.Room;
import com.waqar.reservation.data.repositories.GuestRepository;
import com.waqar.reservation.data.repositories.ReservationRepository;
import com.waqar.reservation.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservedRooms() {
        List<Reservation> reservationList = new ArrayList<>();
        this.reservationRepository.findAll().forEach(reservationList::add);
        return reservationList;
    }

    public Reservation addReservation(Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }

    public List<ReservationDTO> getAllReservedRoomsByDate(String date) {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        Date reservationDate = getDateFromDateString(date);
        this.reservationRepository.findByReservationDate(reservationDate).forEach(reservationDTOList::add);
        return reservationDTOList;
    }

    public Optional<ReservationDTO> getReservedRoomsById(Long id) {
        ReservationDTO reservationDTO = null;
        Optional<Reservation> reservationOptional = this.reservationRepository.findById(id);
        if(reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            Room room = this.roomRepository.findById(reservation.getRoomId()).get();
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            reservationDTO = new ReservationDTO();
            reservationDTO.setId(reservation.getId());
            reservationDTO.setReservationDate(reservation.getReservationDate());

            reservationDTO.setRoomId(room.getId());
            reservationDTO.setNumber(room.getNumber());
            reservationDTO.setTotalBeds(room.getTotalBeds());
            reservationDTO.setRemarks(room.getRemarks());

            reservationDTO.setFirstName(guest.getFirstName());
            reservationDTO.setLastName(guest.getLastName());
            reservationDTO.setPhoneNumber(guest.getPhoneNumber());
            reservationDTO.setAge(guest.getAge());
            reservationDTO.setEmail(guest.getEmail());
            reservationDTO.setCountry(guest.getCountry());

            return Optional.of(reservationDTO);
        }else {
            return Optional.ofNullable(reservationDTO);
        }
    }

    private Date getDateFromDateString(String date) {
        if(date == null) {
            return new Date();
        }
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException pe) {
            return new Date();
        }
    }

}
