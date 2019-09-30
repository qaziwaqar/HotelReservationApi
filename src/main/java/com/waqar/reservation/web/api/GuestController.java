package com.waqar.reservation.web.api;

import com.waqar.reservation.business.service.GuestService;
import com.waqar.reservation.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/all")
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @PostMapping("/")
    public ResponseEntity addGuest(@RequestBody Guest guest) {
        return ResponseEntity.ok(this.guestService.addGuest(guest));
    }

    @GetMapping("/{id}")
    public ResponseEntity getGuestById(@PathVariable Long id) {
        Optional<Guest> optionalGuest = this.guestService.findGuestById(id);
        if (optionalGuest.isPresent()) {
            return ResponseEntity.ok(optionalGuest.get());
        } else {
            return ResponseEntity.badRequest().body("Guest ID " + id + ", not found");
        }
    }
}
