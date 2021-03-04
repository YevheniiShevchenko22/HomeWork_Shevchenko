package pl.edu.wszib.javaee.food.platform.services;

import pl.edu.wszib.javaee.food.platform.exception.ValidationException;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
public interface IAdminService {

    void deleteCustomerById(Long id);

    List<AppUser> getAllCustomers();

    boolean registerCustomer(RegistrationModel registrationModel);

    AppUser saveCustomer(RegistrationModel registrationModel) throws ValidationException;

    Optional<AppUser> findByLogin(String login);
}
