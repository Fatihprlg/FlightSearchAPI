package org.fatihprlg.flightsearchapi.utils;

import org.fatihprlg.flightsearchapi.database.AirportRepository;
import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/v1/utils/random")
public class RandomFlightsCreator {
    private static final Random random = new Random();
    private static final long SIX_MONTHS_IN_MILLIS = 6 * 30 * 24 * 60 * 60 * 1000L;
    @Autowired
    private AirportRepository airportRepo;

    @GetMapping("/get_flights/{count}")
    public ResponseEntity<List<AddFlightCommandDto>> getRandomFlights(@PathVariable("count") Integer count){
        List<AddFlightCommandDto> generatedFlights = new ArrayList<>();
        for (int i = 0; i < count; i++){
            generatedFlights.add(generateRandomFlightData());
        }
        return ResponseEntity.ok(generatedFlights);
    }

    private AddFlightCommandDto generateRandomFlightData() {
        AddFlightCommandDto flightDto = new AddFlightCommandDto();
        int airportCt = (int)airportRepo.count();
        flightDto.setDepartureAirportId(random.nextInt(airportCt) + 1);
        flightDto.setLandingAirportId(random.nextInt(airportCt) + 1);
        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
        flightDto.setDepartureTime(generateRandomTimestamp(now));
        boolean returnOrNot = random.nextBoolean();
        flightDto.setReturnTime(returnOrNot ? generateRandomTimestamp(flightDto.getDepartureTime().getTime()) : null);
        flightDto.setPrice(generateRandomPrice());

        return flightDto;
    }

    private static Timestamp generateRandomTimestamp(long startDate) {
        long sixMonthsFromNow = startDate + SIX_MONTHS_IN_MILLIS;
        long randomTimestamp = startDate + (long) (Math.random() * (sixMonthsFromNow - startDate));
        return new Timestamp(randomTimestamp);
    }

    private static float generateRandomPrice() {
        return 100 + random.nextFloat() * (1000 - 100);
    }
}
