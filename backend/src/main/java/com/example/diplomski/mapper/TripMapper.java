package com.example.diplomski.mapper;

import com.example.diplomski.dto.TripDto;
import com.example.diplomski.entity.Driver;
import com.example.diplomski.entity.Trip;
import com.example.diplomski.entity.Vehicle;

public class TripMapper {
    public static TripDto mapToTripDto(Trip trip){
        return new TripDto(
              trip.getId(),
              trip.getVehicle() != null ? trip.getVehicle().getId() : null,
              trip.getDriver() != null ? trip.getDriver().getId() : null,
              trip.getDepartureLocation(),
              trip.getDepartureTime(),
              trip.getDepartureDate(),
              trip.getArrivalLocation(),
              trip.getArrivalDate(),
              trip.getArrivalTime(),
              trip.getDistanceKm(),
              trip.getDurationMinutes(),
              trip.getTripStatus(),
              trip.getNumOfSeats(),
              trip.getNumOfAvailableSeats(),
              trip.getPrice(),
              trip.getNotes()
        );
    }

    public static Trip mapToTrip(TripDto tripDto, Vehicle vehicle, Driver driver){
        return new Trip(
                tripDto.getId(),
                vehicle,
                driver,
                tripDto.getDepartureLocation(),
                tripDto.getDepartureTime(),
                tripDto.getDepartureDate(),
                tripDto.getArrivalLocation(),
                tripDto.getArrivalDate(),
                tripDto.getArrivalTime(),
                tripDto.getDistanceKm(),
                tripDto.getDurationMinutes(),
                tripDto.getTripStatus(),
                tripDto.getNumOfSeats(),
                tripDto.getNumOfAvailableSeats(),
                tripDto.getPrice(),
                tripDto.getNotes()
        );
    }
}
