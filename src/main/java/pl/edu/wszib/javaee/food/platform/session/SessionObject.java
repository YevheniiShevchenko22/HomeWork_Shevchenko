package pl.edu.wszib.javaee.food.platform.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Component
@SessionScope
public class SessionObject {
    private AppUser loggedUser = null;
    private String info = null;
    private final List<Dish> basket = new ArrayList<>();

    public AppUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(AppUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged(){
        return this.loggedUser != null;
    }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Dish> getBasked() {
        return basket;
    }

    public void clearBasket(){
        basket.clear();
    }
}
