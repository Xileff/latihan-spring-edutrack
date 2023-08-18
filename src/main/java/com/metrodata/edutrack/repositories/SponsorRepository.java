package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
//    Query method
    Sponsor findSponsorById(Long id);

//    JPQL
    @Query("SELECT s FROM Sponsor s WHERE s.id = ?1")
    Sponsor getSponsorById(Long id);

//    SQL
    @Query(value = "SELECT * FROM tb_m_sponsors WHERE id = ?1", nativeQuery = true)
    Sponsor getSponsorByIdNative(Long id);
}
