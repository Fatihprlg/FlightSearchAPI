package org.fatihprlg.flightsearchapi.controllers;

import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterFlightsCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FlightDto;
import org.fatihprlg.flightsearchapi.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add_flight")
    public ResponseEntity<FlightDto> addFlight(@RequestBody AddFlightCommandDto flightDto) throws Exception{
        FlightDto flight = flightService.addFlight(flightDto);
        return ResponseEntity.ok(flight);
    }

    @PostMapping("/update_flight")
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto){
        FlightDto flight = flightService.updateFlight(flightDto);
        return ResponseEntity.ok(flight);
    }

    @PostMapping("/delete_flight/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("id") Integer id){
        flightService.removeFlight(id);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @GetMapping("get_flight_by_id/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/get_all_flights")
    public ResponseEntity<List<FlightDto>> getAllFlights(){
        List<FlightDto> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/get_filtered_flights")
    public ResponseEntity<List<FlightDto>> getFilteredFlights(FilterFlightsCommandDto filter){
        List<FlightDto> filteredFlights = flightService.getFilteredFlights(filter);
        return ResponseEntity.ok(filteredFlights);
    }
}
