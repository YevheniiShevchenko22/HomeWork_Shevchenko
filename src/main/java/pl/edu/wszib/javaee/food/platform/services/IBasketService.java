package pl.edu.wszib.javaee.food.platform.services;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
public interface IBasketService {

    Double calculateTotal();

    void addDishByIdToOrder(Long id);
}
