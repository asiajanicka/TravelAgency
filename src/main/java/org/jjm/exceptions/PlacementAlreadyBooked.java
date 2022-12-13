package org.jjm.exceptions;

public class PlacementAlreadyBooked extends Exception{

    public PlacementAlreadyBooked(){
    }

    public PlacementAlreadyBooked(String message){
        super(message);
    }
}
