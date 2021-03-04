package pl.edu.wszib.javaee.food.platform.exception;

/**
 * Created by Yevhenii Shevchenko at 3/4/21
 * Project name: food.platform
 **/
public class DishNotFoundExeption extends RuntimeException{

    public DishNotFoundExeption(String msg){
        super(msg);
    }
}
