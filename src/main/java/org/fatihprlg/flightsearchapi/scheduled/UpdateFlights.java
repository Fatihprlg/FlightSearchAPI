package org.fatihprlg.flightsearchapi.scheduled;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.fatihprlg.flightsearchapi.services.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

@Component
public class UpdateFlights {
    private static final Logger log = LoggerFactory.getLogger(UpdateFlights.class);
    private static final String localhost = "http://localhost:8080";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Random random = new Random();
    private final FlightService flightService;

    public UpdateFlights(FlightService flightService) {
        this.flightService = flightService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateFlightsScheduled() {
        RestTemplate restTemplate = new RestTemplate();
        int count = random.nextInt(15) + 1;
        String requestUrl = localhost + "/api/v1/utils/random/get_flights/" + count;
        // Temel Kimlik Doğrulama Başlığı Oluşturma
        String authHeader = createBasicAuthHeader("user", "awesomePassword");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<AddFlightCommandDto>> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            List<AddFlightCommandDto> flightsToAdd = response.getBody();
            assert flightsToAdd != null;
            flightsToAdd.forEach(flightService::addFlight);
            log.info("Flights updated at {}. Added new {} flights.", dateFormat.format(new Date()), flightsToAdd.size());
        } else {
            log.error("Failed to retrieve flights from the external API.");
            throw new RuntimeException("Failed to retrieve flights from the external API.");
        }
    }

    private String createBasicAuthHeader(String username, String password) {
        String authString = username + ":" + password;
        byte[] authBytes = authString.getBytes(StandardCharsets.UTF_8);
        String encodedAuthString = Base64.getEncoder().encodeToString(authBytes);
        return "Basic " + encodedAuthString;
    }
}
