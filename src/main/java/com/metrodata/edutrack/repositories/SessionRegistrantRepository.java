package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.SessionRegistrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRegistrantRepository extends JpaRepository<SessionRegistrant, Long> {
//    Query method
    SessionRegistrant findSessionRegistrantById(Long id);
    List<SessionRegistrant> findSessionRegistrantsByIsReminderSent(Boolean status);

//    Custom query JPQL
    @Query("SELECT s FROM SessionRegistrant s WHERE s.id = ?1")
    SessionRegistrant getSessionRegistrantById(Long id);

    @Query("SELECT s FROM SessionRegistrant s WHERE s.isReminderSent = ?1")
    List<SessionRegistrant> getSessionRegistrantsByIsReminderSent(Boolean status);

//    Custom query SQL
    @Query(value = "SELECT * FROM tb_tr_session_registrants WHERE id = ?1", nativeQuery = true)
    SessionRegistrant getSessionRegistrantByIdNative(Long id);

    @Query(value = "SELECT * FROM tb_tr_session_registrants WHERE is_reminder_sent = ?1", nativeQuery = true)
    List<SessionRegistrant> getSessionRegistrantsByIsReminderSentNative(Boolean status);
}
