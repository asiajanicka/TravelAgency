package exceptions;

public class NoPlacementException extends Exception{

    public NoPlacementException(){
    }

    public NoPlacementException(String message){
        super(message);
    }
}
