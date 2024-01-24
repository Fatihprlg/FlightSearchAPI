package org.fatihprlg.flightsearchapi.services;

import org.fatihprlg.flightsearchapi.database.FlightRepository;
import org.fatihprlg.flightsearchapi.models.Flight;
import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FlightDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterFlightsCommandDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ModelMapper modelMapper;
    public FlightDto addFlight(AddFlightCommandDto flightData){
        Flight flightSavedToDb = flightRepository.saveAndFlush(modelMapper.map(flightData, Flight.class));
        return modelMapper.map(flightSavedToDb, FlightDto.class);
    }

    public boolean removeFlight(int id){
        try {
            flightRepository.deleteById(id);
            return true;
        }
        catch (Exception exception)
        { //TODO add logger
            throw exception;
        }
    }

    public FlightDto getFlights(FilterFlightsCommandDto flightQuery){
        return null;
    }
}
