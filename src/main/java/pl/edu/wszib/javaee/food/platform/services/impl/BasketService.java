package pl.edu.wszib.javaee.food.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.javaee.food.platform.dao.IDishDAO;
import pl.edu.wszib.javaee.food.platform.exception.DishNotFoundExeption;
import pl.edu.wszib.javaee.food.platform.model.Dish;
import pl.edu.wszib.javaee.food.platform.services.IBasketService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Service
public class BasketService implements IBasketService {

    @Autowired
    IDishDAO dishDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public Double calculateTotal() {
        Double sum = 0.0;
        for(Dish dish : this.sessionObject.getBasked()){
            sum = sum + dish.getPrice() * dish.getAmount();
        }
        return sum;
    }

    @Override
    public void addDishByIdToOrder(Long id) {
        Dish dish = this.dishDAO.findDishById(id).orElseThrow(() -> new DishNotFoundExeption("Dish not found!!!"));
        for (Dish dishFromBasket : this.sessionObject.getBasked()) {
            if (dishFromBasket.getId() == dish.getId()){
                dishFromBasket.setAmount(dishFromBasket.getAmount() + 1);
                return;
            }
        }

        dish.setAmount(1);
        this.sessionObject.getBasked().add(dish);
    }
}
