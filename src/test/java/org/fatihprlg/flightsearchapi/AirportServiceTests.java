package org.fatihprlg.flightsearchapi;

import org.fatihprlg.flightsearchapi.database.AirportRepository;
import org.fatihprlg.flightsearchapi.models.Airport;
import org.fatihprlg.flightsearchapi.models.dtos.AddAirportCommandDto;
import org.fatihprlg.flightsearchapi.models.dtos.AirportDto;
import org.fatihprlg.flightsearchapi.models.dtos.FilterAirportsCommandDto;
import org.fatihprlg.flightsearchapi.services.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AirportServiceTests {
    @Mock
    private AirportRepository airportRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddAirport_Success() {
        AddAirportCommandDto commandDto = AddAirportCommandDto.builder()
                .city("Sample City")
                .build();

        Airport airport = Airport.builder()
                .id(1)
                .city("Sample City")
                .build();

        AirportDto airportDto = AirportDto.builder()
                .id(1)
                .city("Sample City")
                .build();

        when(airportRepository.saveAndFlush(any(Airport.class))).thenReturn(airport);
        when(modelMapper.map(commandDto, Airport.class)).thenReturn(airport);
        when(modelMapper.map(airport, AirportDto.class)).thenReturn(airportDto);
        AirportDto result = airportService.addAirport(commandDto);

        verify(airportRepository).saveAndFlush(airport);
        verify(modelMapper).map(commandDto, Airport.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Sample City", result.getCity());
    }

    @Test
    void testUpdateAirport_Success() {
        AirportDto airportDto = new AirportDto();
        airportDto.setId(1);
        airportDto.setCity("Sample City");

        Airport airport = new Airport();
        airport.setId(1);
        airport.setCity("Sample City");

        when(airportRepository.saveAndFlush(any(Airport.class))).thenReturn(airport);
        when(modelMapper.map(airportDto, Airport.class)).thenReturn(airport);
        when(modelMapper.map(airport, AirportDto.class)).thenReturn(airportDto);

        AirportDto result = airportService.updateAirport(airportDto);

        verify(airportRepository).saveAndFlush(airport);
        verify(modelMapper).map(airportDto, Airport.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Sample City", result.getCity());
    }

    @Test
    public void testDeleteAirport_Success() {
        Integer airportId = 1;

        airportService.deleteAirport(airportId);

        verify(airportRepository, times(1)).deleteById(airportId);
    }

    @Test
    public void testGetAirportById_Success() {
        Integer airportId = 1;
        Airport airport = Airport.builder()
                .id(airportId)
                .city("London")
                .build();
        AirportDto airportDto = AirportDto.builder()
                .id(airportId)
                .city("London")
                .build();

        Optional<Airport> optionalAirport = Optional.of(airport);
        when(modelMapper.map(optionalAirport, AirportDto.class)).thenReturn(airportDto);
        when(airportRepository.findById(airportId)).thenReturn(optionalAirport);

        AirportDto result = airportService.getAirportById(airportId);

        assertNotNull(result);
        assertEquals(airportId, result.getId());
        assertEquals(airport.getCity(), result.getCity());
    }

    @Test
    public void testGetAllAirports_Success() {
        List<Airport> airportList = new ArrayList<>();
        airportList.add(Airport.builder().id(1).city("London").build());
        airportList.add(Airport.builder().id(2).city("Paris").build());

        List<AirportDto> airportDtoList = new ArrayList<>();
        airportDtoList.add(AirportDto.builder().id(1).city("London").build());
        airportDtoList.add(AirportDto.builder().id(2).city("Paris").build());

        when(airportRepository.findAll()).thenReturn(airportList);
        when(modelMapper.map(any(Airport.class), eq(AirportDto.class))).thenReturn(airportDtoList.get(0), airportDtoList.get(1));

        List<AirportDto> result = airportService.getAllAirports();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(airportDtoList, result);
    }

    @Test
    public void testGetFilteredAirports_Success() {
        Integer filterId = 1;
        String filterCity = "London";

        FilterAirportsCommandDto filterQuery = FilterAirportsCommandDto.builder()
                .id(filterId)
                .city(filterCity)
                .build();

        List<Airport> allAirports = new ArrayList<>();

        allAirports.add(Airport.builder().id(1).city("London").build());
        allAirports.add(Airport.builder().id(2).city("Paris").build());

        List<AirportDto> filteredAirportDtos = new ArrayList<>();
        filteredAirportDtos.add(AirportDto.builder().id(1).city("London").build());

        when(airportRepository.findAll()).thenReturn(allAirports);
        when(modelMapper.map(any(Airport.class), eq(AirportDto.class))).thenReturn(filteredAirportDtos.getFirst());

        List<AirportDto> result = airportService.getFilteredAirports(filterQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filteredAirportDtos, result);
    }

    @Test
    public void testGetFilteredAirports_Success_JustId() {
        Integer filterId = 1;

        FilterAirportsCommandDto filterQuery = FilterAirportsCommandDto.builder()
                .id(filterId)
                .build();

        List<Airport> allAirports = new ArrayList<>();

        allAirports.add(Airport.builder().id(1).city("London").build());
        allAirports.add(Airport.builder().id(2).city("Paris").build());

        List<AirportDto> filteredAirportDtos = new ArrayList<>();
        filteredAirportDtos.add(AirportDto.builder().id(1).city("London").build());

        when(airportRepository.findAll()).thenReturn(allAirports);
        when(modelMapper.map(any(Airport.class), eq(AirportDto.class))).thenReturn(filteredAirportDtos.getFirst());

        List<AirportDto> result = airportService.getFilteredAirports(filterQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filteredAirportDtos, result);
    }

    @Test
    public void testGetFilteredAirports_Success_JustCity() {

        String filterCity = "London";

        FilterAirportsCommandDto filterQuery = FilterAirportsCommandDto.builder()
                .city(filterCity)
                .build();

        List<Airport> allAirports = new ArrayList<>();

        allAirports.add(Airport.builder().id(1).city("London").build());
        allAirports.add(Airport.builder().id(2).city("Paris").build());

        List<AirportDto> filteredAirportDtos = new ArrayList<>();
        filteredAirportDtos.add(AirportDto.builder().id(1).city("London").build());

        when(airportRepository.findAll()).thenReturn(allAirports);
        when(modelMapper.map(any(Airport.class), eq(AirportDto.class))).thenReturn(filteredAirportDtos.getFirst());

        List<AirportDto> result = airportService.getFilteredAirports(filterQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filteredAirportDtos, result);
    }
}