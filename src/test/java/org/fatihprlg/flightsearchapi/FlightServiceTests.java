package org.fatihprlg.flightsearchapi;


import org.fatihprlg.flightsearchapi.database.FlightRepository;
import org.fatihprlg.flightsearchapi.models.Flight;
import org.fatihprlg.flightsearchapi.models.dtos.AddFlightCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.FlightDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterFlightsCommandDto;
import org.fatihprlg.flightsearchapi.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FlightServiceTests {
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private FlightService flightService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAddFlight_Success() {
        AddFlightCommandDto commandDto = AddFlightCommandDto.builder()
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        Flight flight = Flight.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        FlightDto flightDto = FlightDto.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        when(modelMapper.map(eq(commandDto), eq(Flight.class))).thenReturn(flight);
        when(modelMapper.map(eq(flight), eq(FlightDto.class))).thenReturn(flightDto);
        when(flightRepository.saveAndFlush(any(Flight.class))).thenReturn(flight);
        FlightDto result = flightService.addFlight(commandDto);

        verify(flightRepository).saveAndFlush(flight);
        verify(modelMapper).map(commandDto, Flight.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getDepartureAirportId());
        assertEquals(2, result.getLandingAirportId());
        assertEquals(commandDto.getPrice(), result.getPrice());
    }

    @Test
    void testAddFlight_Success_WithNullReturnTime() {
        AddFlightCommandDto commandDto = AddFlightCommandDto.builder()
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(null)
                .price(123f)
                .build();

        Flight flight = Flight.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(null)
                .price(123f)
                .build();

        FlightDto flightDto = FlightDto.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(null)
                .price(123f)
                .build();

        when(modelMapper.map(eq(commandDto), eq(Flight.class))).thenReturn(flight);
        when(modelMapper.map(eq(flight), eq(FlightDto.class))).thenReturn(flightDto);
        when(flightRepository.saveAndFlush(any(Flight.class))).thenReturn(flight);
        FlightDto result = flightService.addFlight(commandDto);

        verify(flightRepository).saveAndFlush(flight);
        verify(modelMapper).map(commandDto, Flight.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getDepartureAirportId());
        assertEquals(2, result.getLandingAirportId());
        assertEquals(commandDto.getReturnTime(), result.getReturnTime());
        assertEquals(commandDto.getPrice(), result.getPrice());
    }

    @Test
    void testUpdateFlight_Success() {
        FlightDto flightDto = FlightDto.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        Flight flight = Flight.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        when(modelMapper.map(flightDto, Flight.class)).thenReturn(flight);
        when(modelMapper.map(flight, FlightDto.class)).thenReturn(flightDto);
        when(flightRepository.saveAndFlush(any(Flight.class))).thenReturn(flight);

        FlightDto result = flightService.updateFlight(flightDto);

        verify(flightRepository).saveAndFlush(flight);
        verify(modelMapper).map(flightDto, Flight.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getDepartureAirportId());
        assertEquals(2, result.getLandingAirportId());
        assertEquals(flightDto.getPrice(), result.getPrice());
    }

    @Test
    public void testDeleteFlight_Success() {
        int flightId = 1;

        flightService.removeFlight(flightId);

        verify(flightRepository, times(1)).deleteById(flightId);
    }

    @Test
    public void testGetFlightById_Success() {
        Integer flightId = 1;
        Flight flight = Flight.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();
        FlightDto flightDto = FlightDto.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        Optional<Flight> optionalFlight = Optional.of(flight);
        when(modelMapper.map(optionalFlight, FlightDto.class)).thenReturn(flightDto);
        when(flightRepository.findById(flightId)).thenReturn(optionalFlight);

        FlightDto result = flightService.getFlightById(flightId);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getDepartureAirportId());
        assertEquals(2, result.getLandingAirportId());
        assertEquals(flightDto.getDepartureTime(), result.getDepartureTime());
        assertEquals(flightDto.getReturnTime(), result.getReturnTime());
        assertEquals(flightDto.getPrice(), result.getPrice());
    }

    @Test
    public void testGetAllFlights_Success() {
        List<Flight> flightList = new ArrayList<>();
        flightList.add(Flight.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        flightList.add(Flight.builder().id(2).departureAirportId(3)
                .landingAirportId(4)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());

        List<FlightDto> flightDtoList = new ArrayList<>();
        flightDtoList.add(FlightDto.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        flightDtoList.add(FlightDto.builder().id(2).departureAirportId(3)
                .landingAirportId(4)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());

        when(flightRepository.findAll()).thenReturn(flightList);
        when(modelMapper.map(any(Flight.class), eq(FlightDto.class))).thenReturn(flightDtoList.get(0), flightDtoList.get(1));

        List<FlightDto> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(flightDtoList, result);
    }

    @Test
    public void testGetFilteredFlights_Success() {
        FilterFlightsCommandDto filterQuery = FilterFlightsCommandDto.builder()
                .id(1)
                .departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build();

        List<Flight> allFlights = new ArrayList<>();

        allFlights.add(Flight.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        allFlights.add(Flight.builder().id(2).departureAirportId(3)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());

        List<FlightDto> filteredFlightDtos = new ArrayList<>();
        filteredFlightDtos.add(FlightDto.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());

        when(flightRepository.findAll()).thenReturn(allFlights);
        when(modelMapper.map(allFlights.getFirst(), FlightDto.class)).thenReturn(filteredFlightDtos.getFirst());

        List<FlightDto> result = flightService.getFilteredFlights(filterQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filteredFlightDtos, result);
    }

    @Test
    public void testGetFilteredFlights_Success_JustId() {
        FilterFlightsCommandDto filterQuery = FilterFlightsCommandDto.builder()
                .id(1)
                .build();

        List<Flight> allFlights = new ArrayList<>();

        allFlights.add(Flight.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        allFlights.add(Flight.builder().id(2).departureAirportId(3)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());

        List<FlightDto> filteredFlightDtos = new ArrayList<>();
        filteredFlightDtos.add(FlightDto.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        when(flightRepository.findAll()).thenReturn(allFlights);
        when(modelMapper.map(allFlights.getFirst(), FlightDto.class)).thenReturn(filteredFlightDtos.getFirst());

        List<FlightDto> result = flightService.getFilteredFlights(filterQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filteredFlightDtos, result);
    }

    @Test
    public void testGetFilteredFlights_Success_JustLandingId() {
        FilterFlightsCommandDto filterQuery = FilterFlightsCommandDto.builder()
                .landingAirportId(2)
                .build();

        List<Flight> allFlights = new ArrayList<>();

        allFlights.add(Flight.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        allFlights.add(Flight.builder().id(2).departureAirportId(3)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());


        List<FlightDto> filteredFlightDtos = new ArrayList<>();
        filteredFlightDtos.add(FlightDto.builder().id(1).departureAirportId(1)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(123f)
                .build());
        filteredFlightDtos.add(FlightDto.builder().id(2).departureAirportId(3)
                .landingAirportId(2)
                .departureTime(new Timestamp(new Date().getTime()))
                .returnTime(new Timestamp(new Date().getTime() + 500))
                .price(1233f)
                .build());

        when(flightRepository.findAll()).thenReturn(allFlights);
        when(modelMapper.map(any(Flight.class), eq(FlightDto.class))).thenReturn(filteredFlightDtos.get(0), filteredFlightDtos.get(1));

        List<FlightDto> result = flightService.getFilteredFlights(filterQuery);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(filteredFlightDtos, result);
    }
}
