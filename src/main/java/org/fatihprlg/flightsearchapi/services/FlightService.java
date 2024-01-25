package org.fatihprlg.flightsearchapi.services;

import org.fatihprlg.flightsearchapi.database.FlightRepository;
import org.fatihprlg.flightsearchapi.models.Flight;
import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FlightDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterFlightsCommandDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private static final Logger log = LoggerFactory.getLogger(AirportService.class);

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightService(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    public FlightDto addFlight(AddFlightCommandDto flightData){
        try {
            Flight flightSavedToDb = flightRepository.saveAndFlush(modelMapper.map(flightData, Flight.class));
            log.info("Added {} flight successfully.", flightData);
            return modelMapper.map(flightSavedToDb, FlightDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    public void removeFlight(int id){
        try {
            flightRepository.deleteById(id);
            log.info("Deleted flight with id {} successfully.", id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    public FlightDto updateFlight(FlightDto updateData){
        try {
            Flight updatedFlight = flightRepository.saveAndFlush(modelMapper.map(updateData, Flight.class));
            log.info("Updated flight {} successfully.", updatedFlight);
            return modelMapper.map(updatedFlight, FlightDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
    public FlightDto getFlightById(Integer id){
        try {
            Optional<Flight> foundedFlight = flightRepository.findById(id);
            log.info("Founded flight with id {} is {}.", id, foundedFlight);
            return modelMapper.map(foundedFlight, FlightDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
    public List<FlightDto> getAllFlights(){
        try {
            List<FlightDto> allFlights = flightRepository.findAll().stream()
                    .map(entity -> modelMapper.map(entity, FlightDto.class))
                    .collect(Collectors.toList());
            log.info("Operation 'getAllFlights' completed successfully.");
            return allFlights;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
    public List<FlightDto> getFilteredFlights(FilterFlightsCommandDto flightQuery){
        try {

            List<Flight> allFlights = flightRepository.findAll();
            List<Flight> filteredFlights = allFlights.stream()
                    .filter(flight -> (flightQuery.getId() == null || flight.getId().equals(flightQuery.getId())))
                    .filter(flight -> (flightQuery.getDepartureAirportId() == null || flight.getDepartureAirportId().equals(flightQuery.getDepartureAirportId())))
                    .filter(flight -> (flightQuery.getLandingAirportId() == null || flight.getLandingAirportId().equals(flightQuery.getLandingAirportId())))
                    .filter(flight -> (flightQuery.getDepartureTime() == null || flight.getDepartureTime().equals(flightQuery.getDepartureTime())))
                    .filter(flight -> (flightQuery.getReturnTime() == null || flight.getReturnTime().equals(flightQuery.getReturnTime())))
                    .filter(flight -> (flightQuery.getPrice() == null || flight.getPrice().equals(flightQuery.getPrice())))
                    .toList();
            log.info("Founded airports with filter {} is {}", flightQuery, filteredFlights);
            return filteredFlights.stream()
                    .map(entity -> modelMapper.map(entity, FlightDto.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
}
