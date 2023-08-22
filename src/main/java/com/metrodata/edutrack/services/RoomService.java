package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.Room;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.RoomData;
import com.metrodata.edutrack.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room with ID : " + id + " not found."));
    }

    public ResponseData<Room> insertRoom(RoomData roomData) {
        try {
            Room room = new Room();
            room.setName(roomData.getName());
            return new ResponseData<>(roomRepository.save(room), "Room inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Room> updateRoom(Long id, RoomData roomData) {
        try {
            Room room = getRoomById(id);
            room.setName(roomData.getName());
            return new ResponseData<>(roomRepository.save(room), "Room with ID : " + id + " updated successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Room> deleteRoom(Long id) {
        try {
            Room room = getRoomById(id);
            roomRepository.delete(room);
            return new ResponseData<>(room, "Room with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
