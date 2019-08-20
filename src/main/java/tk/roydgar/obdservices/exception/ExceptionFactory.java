package tk.roydgar.obdservices.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionFactory {

    public static ResourceNotFoundException carNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Car with id %d is not present in database", id));
    }

    public static ResourceNotFoundException carLogNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Car log with id %d is not present in database", id));
    }

    public static ResourceNotFoundException carErrorLogNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Car error log with id %d is not present in database", id));
    }

    public static ResourceNotFoundException userNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("User with id %d is not present in database", id));
    }

    public static ResourceNotFoundException workshopNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Workshop with id %d is not present in database", id));
    }

}
