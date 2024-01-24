package org.fatihprlg.flightsearchapi.database;

import org.fatihprlg.flightsearchapi.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
