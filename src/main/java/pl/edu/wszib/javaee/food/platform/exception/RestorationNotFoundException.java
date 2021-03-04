package pl.edu.wszib.javaee.food.platform.exception;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
public class RestorationNotFoundException extends RuntimeException {
    public RestorationNotFoundException(String message) {
        super(message);
    }
}
