package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
//    Query method
    Participant findParticipantByName(String name);
    List<Participant> findParticipantsByUniversity(String university);

//    Custom Query JPQL
    @Query("SELECT p FROM Participant p WHERE p.name = ?1")
    Participant getParticipantByName(String name);

    @Query("SELECT p FROM Participant p WHERE p.university LIKE '%?1%'")
    List<Participant> getParticipantsByUniversity(String university);

//    Custom Query SQL
    @Query(value = "SELECT * FROM tb_m_participants WHERE name = ?1", nativeQuery = true)
    Participant getParticipantByNameNative(String name);

    @Query(value = "SELECT * FROM tb_m_participants WHERE university = ?1", nativeQuery = true)
    List<Participant> getParticipantsByUniversityNative(String university);
}
