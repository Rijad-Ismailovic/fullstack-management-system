package com.example.diplomski.service.impl;

import com.example.diplomski.common.exceptions.ResourceNotFoundException;
import com.example.diplomski.dto.TripDto;
import com.example.diplomski.entity.Driver;
import com.example.diplomski.entity.Location;
import com.example.diplomski.entity.Trip;
import com.example.diplomski.entity.Vehicle;
import com.example.diplomski.mapper.TripMapper;
import com.example.diplomski.repository.DriverRepository;
import com.example.diplomski.repository.LocationRepository;
import com.example.diplomski.repository.TripRepository;
import com.example.diplomski.repository.VehicleRepository;
import com.example.diplomski.service.LocationService;
import com.example.diplomski.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;
    private DriverRepository driverRepository;
    private VehicleRepository vehicleRepository;
    private LocationRepository locationRepository;

    @Override
    public TripDto getTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip with given ID doesnt exist: " + id));
        return TripMapper.mapToTripDto(trip);
    }

    @Override
    public List<TripDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map((trip) -> TripMapper.mapToTripDto(trip)).collect(Collectors.toList());
    }

    @Override
    public TripDto createTrip(TripDto tripDto) {
        Driver driver = driverRepository.findById(tripDto.getDriverId()).orElseThrow(() -> new ResourceNotFoundException("Driver with given ID doesnt exist: " + tripDto.getDriverId()));
        Vehicle vehicle = vehicleRepository.findById(tripDto.getVehicleId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle with given ID doesnt exist: " + tripDto.getVehicleId()));

        Trip trip = TripMapper.mapToTrip(tripDto, vehicle, driver);
        Trip savedTrip = tripRepository.save(trip);
        return TripMapper.mapToTripDto(savedTrip);
    }

    @Override
    public TripDto updateTrip(Long id, TripDto tripDto) {
        Trip existingTrip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip with given ID doesnt exist: " + id));

        BeanUtils.copyProperties(tripDto, existingTrip, "id");

        Trip updatedTrip = tripRepository.save(existingTrip);
        return TripMapper.mapToTripDto(updatedTrip);
    }

    @Override
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public List<TripDto> search(String departure, String arrival, LocalDate arrivalDate, LocalDate returnDate, Boolean wifi, Boolean restroom, Boolean ac, Boolean outlet, Boolean reclining, Integer maxPrice, Integer maxDuration) {
        Location departureLocation;
        Location arrivalLocation;

        if (departure != null && departure.trim().isEmpty()){
            departureLocation = null;
        } else {
            departureLocation = locationRepository.findByName(departure);
        }
        if (arrival != null && arrival.trim().isEmpty()){
            arrivalLocation = null;
        } else {
            arrivalLocation = locationRepository.findByName(arrival);
        }

        List<Trip> trips = tripRepository.search(departureLocation, arrivalLocation, arrivalDate, returnDate, wifi, restroom, ac, outlet, reclining, maxPrice, maxDuration);
        return trips.stream().map((trip) -> TripMapper.mapToTripDto(trip)).collect(Collectors.toList());

    }
}
