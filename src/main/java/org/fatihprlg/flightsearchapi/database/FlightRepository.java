package org.fatihprlg.flightsearchapi.database;

import org.fatihprlg.flightsearchapi.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
