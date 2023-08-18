package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.SessionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDetailRepository extends JpaRepository<SessionDetail, Long> {
//    Query method
    SessionDetail findSessionDetailById(Long id);

//    Custom query JPQL
    @Query("SELECT s FROM SessionDetail s WHERE s.id = ?1")
    SessionDetail getSessionDetailById(Long id);

//    Custom query SQL
    @Query(value = "SELECT * FROM tb_m_session_details WHERE id = ?1", nativeQuery = true)
    SessionDetail getSessionDetailByIdNative(Long id);
}
