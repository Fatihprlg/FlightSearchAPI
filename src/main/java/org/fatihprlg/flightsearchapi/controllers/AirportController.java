package org.fatihprlg.flightsearchapi.controllers;

import org.fatihprlg.flightsearchapi.models.dtos.AddAirportCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterAirportsCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.AirportDto;
import org.fatihprlg.flightsearchapi.services.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/add_airport")
    public ResponseEntity<AirportDto> addAirport(@RequestBody AddAirportCommandDto AirportDto){
        AirportDto airport = airportService.addAirport(AirportDto);
        return ResponseEntity.ok(airport);
    }

    @PostMapping("/update_airport")
    public ResponseEntity<AirportDto> updateAirport(@RequestBody AirportDto AirportDto){
        AirportDto airport = airportService.updateAirport(AirportDto);
        return ResponseEntity.ok(airport);
    }

    @PostMapping("/delete_airport/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable("id") Integer id){
        airportService.deleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully");
    }

    @GetMapping("get_airport_by_id/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @GetMapping("/get_all_airports")
    public ResponseEntity<List<AirportDto>> getAllAirports(){
        List<AirportDto> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/get_filtered_airports")
    public ResponseEntity<List<AirportDto>> getFilteredAirports(FilterAirportsCommandDto filter){
        List<AirportDto> filteredAirports = airportService.getFilteredAirports(filter);
        return ResponseEntity.ok(filteredAirports);
    }
}
