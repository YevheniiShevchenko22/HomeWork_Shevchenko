package pl.edu.wszib.javaee.food.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.javaee.food.platform.dao.IUserDAO;
import pl.edu.wszib.javaee.food.platform.exception.ValidationException;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;
import pl.edu.wszib.javaee.food.platform.services.IAdminService;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public void deleteCustomerById(Long id) {
        this.userDAO.deleteById(id);
    }

    @Override
    public List<AppUser> getAllCustomers() {
        //return this.userDAO.findAll();
        return this.userDAO.findAppUserByRole(AppUser.Role.CUSTOMER);
    }

    @Override
    public boolean registerCustomer(RegistrationModel registrationModel) {
        if (this.userDAO.findAppUserByName(registrationModel.getName()).orElse(null) != null){
            return false;
        }else {
            AppUser newUser = registrationModel.toCustomer(registrationModel);
            this.userDAO.save(newUser);
            return true;
        }
    }

    @Override
    public AppUser saveCustomer(RegistrationModel model) throws ValidationException {
        validateAppUser(model);
        AppUser savedUser = userDAO.save(model.toCustomer(model));
        return savedUser;
    }

    @Override
    public Optional<AppUser> findByLogin(String login) {
        return userDAO.findAppUserByName(login);
    }

    private void validateAppUser(RegistrationModel registrationModel) throws ValidationException {
        if (isNull(registrationModel)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(registrationModel.getName()) || registrationModel.getName().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }
}
