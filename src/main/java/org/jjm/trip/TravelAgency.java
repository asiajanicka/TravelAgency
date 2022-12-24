package org.jjm.trip;

import org.jjm.destination.Destination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.utils.DataInitializer;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    private List<Destination> destinations;
    private static final Logger logger = LogManager.getLogger(TravelAgency.class);

    public TravelAgency() {
        this.destinations = new ArrayList<>();
        addDestination(DataInitializer.initMalaga());
        logger.info("Travel Agency - initialized with destination: Malaga ES");
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void printDestinations() {
        System.out.println("TRAVEL AGENCY - Available Destinations:");
        destinations.forEach(Destination::printSummary);
        System.out.println();
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return String.format("Travel Agency Destinations: %s", destinations);
    }
}
