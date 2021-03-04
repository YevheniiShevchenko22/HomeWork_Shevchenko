package pl.edu.wszib.javaee.food.platform.services;

import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;

import java.util.List;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
public interface ICustomerRestorationService {
    void saveRestoration(CustomerRestoration restoration);

    CustomerRestoration getRestorationById(Long id);

    void updateRestorante(CustomerRestoration restoration);

    List<CustomerRestoration> getAllRestoration();
}
