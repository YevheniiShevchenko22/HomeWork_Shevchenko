package pl.edu.wszib.javaee.food.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wszib.javaee.food.platform.services.IBasketService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/basket")
    public String basket(Model model){
        if (!this.sessionObject.isLogged()){
            return "redirect:/main";
        }

        model.addAttribute("dishes", this.sessionObject.getBasked());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("total", this.basketService.calculateTotal());

        return "basket";
    }

    @GetMapping("/addToBasket/{id}")
        public String addToBasket(@PathVariable long id){
            if (!this.sessionObject.isLogged()){
                return "redirect:/login";
            }

            this.basketService.addDishByIdToOrder(id);

            return "redirect:/main";
        }
}
