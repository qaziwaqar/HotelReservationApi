package com.waqar.reservation.web.api;

import com.waqar.reservation.business.service.RoomService;
import com.waqar.reservation.data.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<Room> getAllAvailableRooms() {
        return this.roomService.getAllAvailableRooms();
    }

    @PostMapping("/")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return ResponseEntity.ok(this.roomService.addRoom(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity getRoomById(@PathVariable Long id) {
        Optional<Room> optionalRoom = this.roomService.getRoomById(id);
        if(optionalRoom.isPresent()) {
            return ResponseEntity.ok(optionalRoom.get());
        } else {
            return ResponseEntity.badRequest().body("Room with ID " + id + ", is not found");
        }
    }
}
