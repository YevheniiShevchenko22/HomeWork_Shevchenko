package pl.edu.wszib.javaee.food.platform.exception;

/**
 * Created by Yevhenii Shevchenko at 2/19/21
 * Project name: food.platform
 **/
public class ValidationException extends Exception {

    private String message;

    public ValidationException(String message) {
    }

    public String getMessage() {
        return message;
    }
}