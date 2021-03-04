package pl.edu.wszib.javaee.food.platform.services.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.javaee.food.platform.cofiguration.AppConfiguration;
import pl.edu.wszib.javaee.food.platform.cofiguration.WebConfig;
import pl.edu.wszib.javaee.food.platform.config.TestAppConfiguration;
import pl.edu.wszib.javaee.food.platform.dao.IDishDAO;
import pl.edu.wszib.javaee.food.platform.dao.IUserDAO;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;
import pl.edu.wszib.javaee.food.platform.services.IUserService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

import java.util.Optional;

/**
 * Created by Yevhenii Shevchenko at 3/4/21
 * Project name: food.platform
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class, WebConfig.class})
class UserServiceImplTest {

    @MockBean
    IDishDAO dishDAO;

    @MockBean
    IUserDAO userDAO;

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @Test
    public void registerTest() {

        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setName("walentin");
        registrationModel.setPass("walentin123");
        registrationModel.setPass2("walentin123");
        registrationModel.setEmail("walentin@mail.com");
        registrationModel.setPhone("12345678");

        Mockito.when(this.userDAO.findAppUserByName("walentin")).thenReturn(null);
        Mockito.when(this.userDAO.save(ArgumentMatchers.any())).thenReturn(true);

        boolean result = userService.register(registrationModel);

        Assert.assertTrue(result);

    }

    @Test
    public void registerLoginIncorrectTest(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setName("piotrek");
        registrationModel.setPass("piotrek123");
        registrationModel.setPass2("pioterk123");

        Mockito.when(this.userDAO.findAppUserByName("piotrek")).thenReturn(java.util.Optional.of(new AppUser()));

        boolean result = userService.register(registrationModel);

        Assert.assertFalse(result);
    }

    @Test
    public void incorrectPassTest(){
        AppUser user = new AppUser();
        user.setName("mateusz");
        user.setPass("mateusz333");

        Mockito.when(this.userDAO.findAppUserByName("mateusz")).thenReturn(Optional.of(generateUser()));

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    private AppUser generateUser(){
        AppUser user = new AppUser();
        user.setId((long)3);
        user.setName("admin");
        user.setPass("admin");
        user.setRole(AppUser.Role.valueOf(AppUser.Role.USER.name()));

        return user;
    }
}