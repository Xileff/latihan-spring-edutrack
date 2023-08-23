package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Room;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.RoomData;
import com.metrodata.edutrack.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController (RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomData> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("{id}")
    public RoomData getRoomById(@PathVariable Long id) {
        return roomService.getRoomDTOById(id);
    }

    @PostMapping
    public ResponseData<Room> insertRoom(@RequestBody RoomData roomData) {
        return roomService.insertRoom(roomData);
    }

    @PatchMapping("{id}")
    public ResponseData<Room> updateRoom(@PathVariable Long id, @RequestBody RoomData roomData) {
        return roomService.updateRoom(id, roomData);
    }

    @DeleteMapping("{id}")
    public ResponseData<Room> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }
}
