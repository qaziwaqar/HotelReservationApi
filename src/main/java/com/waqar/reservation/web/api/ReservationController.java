package com.waqar.reservation.web.api;

import com.waqar.reservation.business.domain.ReservationDTO;
import com.waqar.reservation.business.service.ReservationService;
import com.waqar.reservation.data.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservedRooms() {
        return this.reservationService.getAllReservedRooms();
    }

    @PostMapping("/")
    public ResponseEntity addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(this.reservationService.addReservation(reservation));
    }

    // While @RequestParams extract values from the query string, @PathVariables extract values from the URI path:

    @GetMapping
    public List<ReservationDTO> getAllReservedRoomsByDate(@RequestParam(value = "date", required = false) String date) {
        return this.reservationService.getAllReservedRoomsByDate(date);
    }

    @GetMapping("/{id}")
    public ResponseEntity getReservedRoomsById(@PathVariable("id") Long id) {
        Optional<ReservationDTO> optionalReservationDTO = this.reservationService.getReservedRoomsById(id);
        if (optionalReservationDTO.isPresent()) {
            return ResponseEntity.ok(optionalReservationDTO.get());
        } else {
            return ResponseEntity.badRequest().body("Reserved Room ID " + id + ", not found");
        }
    }

}
