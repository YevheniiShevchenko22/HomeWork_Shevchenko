package pl.edu.wszib.javaee.food.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.javaee.food.platform.dao.IUserDAO;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;
import pl.edu.wszib.javaee.food.platform.services.IUserService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(AppUser user) {
        AppUser userFromDB = this.userDAO.findAppUserByName(user.getName()).orElse(null);

        if (userFromDB == null){
            return;
        }

        if (user.getPass().equals(userFromDB.getPass())){
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
        this.sessionObject.clearBasket();
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {

        if (this.userDAO.findAppUserByName(registrationModel.getName()).orElse(null) != null){
            return false;
        }else {
            AppUser newUser = registrationModel.toUser(registrationModel);
            this.userDAO.save(newUser);
            return true;
        }
    }
}
