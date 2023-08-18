package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.SessionSpeaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionSpeakerRepository extends JpaRepository<SessionSpeaker, Long> {
//    Query method
    SessionSpeaker findSessionSpeakerById(Long id);

//    JPQL
    @Query("SELECT s FROM SessionSpeaker s WHERE s.id = ?1")
    SessionSpeaker getSessionSpeakerById(Long id);

//    SQL
    @Query(value = "SELECT * FROM tb_tr_session_speakers WHERE id = ?1", nativeQuery = true)
    SessionSpeaker getSessionSpeakerByIdNative(Long id);
}
