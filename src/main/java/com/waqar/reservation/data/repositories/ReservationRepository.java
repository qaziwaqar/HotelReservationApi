package com.waqar.reservation.data.repositories;

import com.waqar.reservation.business.domain.ReservationDTO;
import com.waqar.reservation.data.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT new com.waqar.reservation.business.domain.ReservationDTO(r.id,r.reservationDate,rm.id,rm.number,rm.totalBeds,rm.remarks," +
            "g.id,g.firstName,g.lastName,g.age, g.email, g.country, g.phoneNumber) " +
            "FROM Guest g, Room rm, Reservation r " +
            "WHERE r.roomId = rm.id AND " +
            "r.guestId = g.id AND " +
            "r.reservationDate = :reservationDate")
    List<ReservationDTO> findByReservationDate(@Param("reservationDate") Date reservationDate);
}
