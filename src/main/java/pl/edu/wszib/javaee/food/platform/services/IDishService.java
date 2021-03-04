package pl.edu.wszib.javaee.food.platform.services;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.javaee.food.platform.model.Dish;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
public interface IDishService {

    Dish getDishByID(Long id);

    void updateDish(Dish dish);

    void saveDish(Dish dish, MultipartFile multipartFile) throws IOException;

    void deleteDishById(Long id);

    List<Dish> getAllDishes();
}
