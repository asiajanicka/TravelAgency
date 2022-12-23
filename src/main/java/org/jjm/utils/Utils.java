package org.jjm.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.bookings.HotelBooking;
import org.jjm.bookings.TransportBooking;
import org.jjm.destination.Destination;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.transport.Seat;
import org.jjm.trip.CustomizedTrip;
import org.jjm.trip.Person;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class);

    public static <T> Seat<T> findSeatByType(List<Seat<T>> seats, T seatType) throws NoPlacementAvailableException {
        return seats.stream()
                .filter(p -> !p.isBooked() && p.getSeatType().equals(seatType))
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(String.format("There is no free seat of type %s. " +
                        "All seats are booked.", seatType)));
    }

    public static List<String> readDataFromFile(String fileName) throws IOException {
        return FileUtils.readLines(new File(fileName), "UTF-8");
    }

    public static void writeSessionStatisticsToFile(String fileName) throws IOException {
        String text = String.format("Statistics about number of created objects:\n" +
                        "Person: %d\nAvailable destinations: %d\nCustomized trips: %d\n" +
                        "Transport bookings: %d\nHotel bookings: %d",
                Person.getCounter(), Destination.getCounter(), CustomizedTrip.getCounter(),
                TransportBooking.getCounter(), HotelBooking.getCounter());
        FileUtils.write(new File(fileName), text, "UTF-8", false);
    }

    public static <T> T findFirstMatchingInList(List<T> list, Checker<T> tester) {
        for (T element : list) {
            if (tester.check(element))
                return element;
        }
        return null;
    }

    public static <T> List<T> findAllMatchingInList(List<T> list, Checker<T> tester) {
        List<T> tempList = new ArrayList<>();
        for (T element : list) {
            if (tester.check(element))
                tempList.add(element);
        }
        return tempList;
    }

    public static Person getPersonWithReflection(String firstName, String lastName, int age) {
        String className = "org.jjm.trip.Person";
        Class<?> pClass = null;
        try {
            pClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error(String.format("Couldn't find file %s to create person from reflection", className), e);
            System.out.println("Sorry, program terminated as participant couldn't be created");
            System.exit(-1);
        }

        Person p = null;
        try {
            p = (Person) pClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            logger.error(String.format("Person [%s %s age: %d] instance could not be created from reflection",
                    firstName, lastName, age), e);
            System.out.println("Sorry, program terminated as participant couldn't be created");
            System.exit(-1);
        }

        Method[] methods = pClass.getDeclaredMethods();
        try {
            for (Method m : methods) {
                if (m.getName().equals("setFirstName")) {
                    m.invoke(p, firstName);
                }
                if (m.getName().equals("setLastName")) {
                    m.invoke(p, lastName);
                }
                if (m.getName().equals("setAge")) {
                    m.invoke(p, age);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error(String.format("Value for person [%s] couldn't be changed due to problem with reflection" +
                    " method", p), e);
            System.out.println("Sorry, program terminated as participant couldn't be created");
            System.exit(-1);
        }
        return p;
    }

}
