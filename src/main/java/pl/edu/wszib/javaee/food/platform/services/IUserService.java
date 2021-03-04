package pl.edu.wszib.javaee.food.platform.services;

import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
public interface IUserService {

    void authenticate(AppUser user);

    void logout();

    boolean register (RegistrationModel registrationModel);

}
