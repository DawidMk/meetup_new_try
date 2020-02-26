package pl.dma.appka.exceptions;

public class EventDoesntExistException extends RuntimeException {

    public EventDoesntExistException(String message) {
        super(message);
    }
}
