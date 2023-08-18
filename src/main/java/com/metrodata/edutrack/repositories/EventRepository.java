package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    Query Method
    Event findByName(String name);
    List<Event> findAllBySlug(String slug);

//    Custom Query JPQL
    @Query("SELECT e FROM Event e WHERE e.name = ?1")
    Event getEventByName(String name);

    @Query("SELECT e FROM Event e WHERE e.slug LIKE '%?1%'")
    List<Event> getEventsBySlug(String slug);

//    Custom Query SQL
    @Query(value = "SELECT * FROM tb_m_events WHERE name = ?1", nativeQuery = true)
    Event getEventByNameNative(String name);

    @Query(value = "SELECT * FROM tb_m_events WHERE slug LIKE '%?1%'", nativeQuery = true)
    List<Event> getEvensBySlugNative(String slug);
}
