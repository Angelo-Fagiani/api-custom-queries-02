package it.develhope.apicustomqueries02.repositories;

import it.develhope.apicustomqueries02.entities.Flight;
import it.develhope.apicustomqueries02.entities.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Page<Flight> findAllByStatus(FlightStatus status, PageRequest of);

    List<Flight> getCustomFlight(FlightStatus p1, FlightStatus p2);
}
