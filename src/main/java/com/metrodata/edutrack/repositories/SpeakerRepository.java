package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
//    Query method
    Speaker findSpeakerById(Long id);
    List<Speaker> findSpeakersByJobTitle(String jobTitle);

//    JPQL
    @Query("SELECT s FROM Speaker s WHERE s.id = ?1")
    Speaker getSpeakerById(Long id);

    @Query("SELECT s FROM Speaker s WHERE s.jobTitle = ?1")
    List<Speaker> getSpeakersByJobTitle(String jobTitle);

//    SQL
    @Query(value = "SELECT * FROM tb_m_speakers WHERE id = ?1", nativeQuery = true)
    Speaker getSpeakerByIdNative(Long id);

    @Query(value = "SELECT * FROM tb_m_speakers WHERE job_title = ?1", nativeQuery = true)
    List<Speaker> getSpeakersByJobTitleNative(String jobTitle);
}
