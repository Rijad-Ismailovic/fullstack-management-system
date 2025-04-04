package com.example.diplomski.entity;

import com.example.diplomski.common.enums.TripStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_location")
    private String arrivalLocation;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "return_time")
    private LocalTime returnTime;

    @Column(name = "distance_km")
    private int distanceKm;

    @Column(name = "duration_minutes")
    private int durationMinutes;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    @Column(name = "num_of_seats")
    private int numOfSeats;

    @Column(name = "num_of_available_seats")
    private int numOfAvailableSeats;

    @Column(name = "price")
    private double price;

    @Column(name = "notes")
    private String notes;
}
