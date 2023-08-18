package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.SessionDetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDetailRoomRepository extends JpaRepository<SessionDetailRoom, Long> {
//    Query method
    SessionDetailRoom findSessionDetailRoomById(Long id);

//    Custom JPQL
    @Query("SELECT s FROM SessionDetailRoom s WHERE s.id = ?1")
    SessionDetailRoom getSessionDetailRoomById(Long id);

//    Custom SQL
    @Query(value = "SELECT * FROM tb_tr_session_detail_rooms WHERE id = ?1", nativeQuery = true)
    SessionDetailRoom getSessionDetailRoomByIdNative(Long id);
}
