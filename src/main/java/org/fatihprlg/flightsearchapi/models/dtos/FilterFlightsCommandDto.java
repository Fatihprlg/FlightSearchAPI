package org.fatihprlg.flightsearchapi.models.dtos;

import java.util.Date;

public record FilterFlightsCommandDto(Integer id, Integer departureAirportId, Integer landingAirportId, Date departureTime, Date landingTime, Float price) {
}
