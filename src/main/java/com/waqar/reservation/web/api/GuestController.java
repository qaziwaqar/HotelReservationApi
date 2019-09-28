package com.waqar.reservation.web.api;

import com.waqar.reservation.business.service.GuestService;
import com.waqar.reservation.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
