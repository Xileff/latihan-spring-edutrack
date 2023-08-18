package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
//    Query method
    Room findRoomById(Long id);

//    Custom query JPQL
    @Query("SELECT r FROM Room r WHERE r.id = ?1")
    Room getRoomById(Long id);

//    Custom query SQL
    @Query(value = "SELECT * FROM tb_m_rooms WHERE id = ?1", nativeQuery = true)
    Room getRoomByIdNative(Long id);
}
