package pl.edu.wszib.javaee.food.platform.services.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.javaee.food.platform.cofiguration.AppConfiguration;
import pl.edu.wszib.javaee.food.platform.cofiguration.WebConfig;
import pl.edu.wszib.javaee.food.platform.dao.IDishDAO;
import pl.edu.wszib.javaee.food.platform.dao.IUserDAO;
import pl.edu.wszib.javaee.food.platform.model.Dish;
import pl.edu.wszib.javaee.food.platform.services.IBasketService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yevhenii Shevchenko at 3/4/21
 * Project name: food.platform
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, WebConfig.class})
@WebAppConfiguration
class BasketServiceTest {

    @MockBean
    IDishDAO dishDAO;

    @MockBean
    IUserDAO userDAO;

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @Test
    void calculateTotalTest() {

        this.sessionObject.getBasked().add(new Dish(
                "bludo",
                "bludo_.jpg",
                304.,
                1200,
                43.99,
                "AAAAAAAA"
        ));

        sessionObject.getBasked().add(new Dish("bludo1",
                "bludo_1.jpg",
                350.,
                1300,
                44.99,
                "GrandeGrill"));
        sessionObject.getBasked().add(new Dish("bludo2",
                "bludo_2.jpg",
                330.,
                2400,
                52.99,
                "GrandeGrill"));
        sessionObject.getBasked().add(new Dish("bludo3",
                "bludo_3.jpg",
                420.,
                2800,
                100.,
                "Hawelka"));

        double expectedRes = 197.98;
        double res = this.basketService.calculateTotal();

        Assert.assertEquals(expectedRes, res, .01);
    }

}