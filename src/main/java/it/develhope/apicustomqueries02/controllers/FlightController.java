package it.develhope.apicustomqueries02.controllers;

import it.develhope.apicustomqueries02.entities.Flight;
import it.develhope.apicustomqueries02.entities.FlightStatus;
import it.develhope.apicustomqueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static it.develhope.apicustomqueries02.entities.FlightStatus.randomStatus;

@RestController
@RequestMapping(value = "flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    public String generateRandomValueForFlight() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @GetMapping
    public List<Flight> getListFlight(@RequestParam(required = false) Integer n) {
        if (n == null) n = 100;
        List<Flight> listFlight = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            listFlight.add(new Flight(i, generateRandomValueForFlight(), generateRandomValueForFlight(), generateRandomValueForFlight(), randomStatus()));
            flightRepository.saveAllAndFlush(listFlight);
        }
        return listFlight;
    }

    @GetMapping("")
    public Page<Flight> getAllFlights(@RequestParam int page, @RequestParam int size){
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    //A cosa serve PageRequest.of??
    @GetMapping("/status/{status}")
    public Page<Flight> getAllFlightsByStatus(@PathVariable FlightStatus status, @RequestParam int page, @RequestParam int size){
        return flightRepository.findAllByStatus(status, (PageRequest.of(page, size)));
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlight(@RequestParam FlightStatus p1, @RequestParam FlightStatus p2){
        return flightRepository.getCustomFlight(p1, p2);
    }

}









