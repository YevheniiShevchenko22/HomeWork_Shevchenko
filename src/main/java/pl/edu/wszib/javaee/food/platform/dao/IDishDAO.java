package pl.edu.wszib.javaee.food.platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;
import pl.edu.wszib.javaee.food.platform.model.Dish;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
public interface IDishDAO extends JpaRepository<Dish, Long> {
    Optional<Dish> findDishById(Long id);

}
