package it.develhope.apicustomqueries02.entities;

import java.util.Random;

public enum FlightStatus {
    ON_TIME,
    DELAYED,
    CANCELLED;

    private static final Random PRNG = new Random();
    public static FlightStatus randomStatus()  {
        FlightStatus[] flightStatus = values();
        return flightStatus[PRNG.nextInt(flightStatus.length)];
    }



}
