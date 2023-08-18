package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
//    Query method
    Session findSessionById(Long id);
    List<Session> findSessionsByNeedAttendance(Boolean needAttendance);

//    Custom JPQL
    @Query("SELECT s FROM Session s WHERE s.id = ?1")
    Session getSessionById(Long id);

    @Query("SELECT s FROM Session s WHERE s.needAttendance = ?1")
    List<Session> getSessionsByNeedAttendance(Boolean needAttendance);

//    Custom SQL
    @Query(value = "SELECT * FROM tb_m_sessions WHERE id = ?1", nativeQuery = true)
    Session getSessionByIdNative(Long id);

    @Query(value = "SELECT * FROM tb_m_sessions WHERE need_attendance = ?1", nativeQuery = true)
    List<Session> getSessionsByNeedAttendanceNative(Boolean needAttendance);
}
