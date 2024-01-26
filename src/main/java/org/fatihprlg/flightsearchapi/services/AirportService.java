package org.fatihprlg.flightsearchapi.services;

import io.micrometer.common.util.StringUtils;
import org.fatihprlg.flightsearchapi.database.AirportRepository;
import org.fatihprlg.flightsearchapi.models.Airport;
import org.fatihprlg.flightsearchapi.models.dtos.AddAirportCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.AirportDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterAirportsCommandDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private static final Logger log = LoggerFactory.getLogger(AirportService.class);

    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public AirportService(AirportRepository airportRepository, ModelMapper modelMapper) {
        this.airportRepository = airportRepository;
        this.modelMapper = modelMapper;
    }


    public void deleteAirport(Integer id) {
        try {
            airportRepository.deleteById(id);
            log.info("Airport with id {} deleted successfully.", id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public AirportDto updateAirport(AirportDto airport) {
        try {
            Airport updatedAirport = airportRepository.saveAndFlush(modelMapper.map(airport, Airport.class));
            log.info("Airport {} updated successfully.", airport);
            return modelMapper.map(updatedAirport, AirportDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public AirportDto addAirport(AddAirportCommandDto airport) {
        try {
            Airport addedAirport = airportRepository.saveAndFlush(modelMapper.map(airport, Airport.class));
            log.info("Airport {} added successfully.", addedAirport);
            return modelMapper.map(addedAirport, AirportDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public AirportDto getAirportById(Integer id) {
        try {
            Optional<Airport> airport = airportRepository.findById(id);
            log.info("Founded airport with id {} is {}", id, airport);
            return modelMapper.map(airport, AirportDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public List<AirportDto> getAllAirports() {
        try {
            List<AirportDto> allAirports = airportRepository.findAll().stream()
                    .map(entity -> modelMapper.map(entity, AirportDto.class))
                    .collect(Collectors.toList());
            log.info("Operation 'getAllAirports' completed successfully.");
            return allAirports;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public List<AirportDto> getFilteredAirports(FilterAirportsCommandDto filterQuery) {
        try {
            List<Airport> allAirports = airportRepository.findAll();
            List<Airport> filteredAirports = allAirports.stream()
                    .filter(airport -> (filterQuery.getId() == null || airport.getId().equals(filterQuery.getId())))
                    .filter(airport -> (filterQuery.getCity() == null || StringUtils.isEmpty(filterQuery.getCity()) || airport.getCity().equals(filterQuery.getCity())))
                    .toList();
            log.info("Founded airports with filter {} is {}", filterQuery, filteredAirports);
            return filteredAirports.stream()
                    .map(entity -> modelMapper.map(entity, AirportDto.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }
}
