package org.fatihprlg.flightsearchapi.database;

import org.fatihprlg.flightsearchapi.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirportId = :#{#filter.departureAirportId} AND f.landingAirportId = :#{#filter.landingAirportId}")
    List<Flight> filter(@Param("filter")Flight filter);
}
