package pl.edu.wszib.javaee.food.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.javaee.food.platform.services.ICustomerRestorationService;
import pl.edu.wszib.javaee.food.platform.services.IDishService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Controller
public class StartController {

    @Autowired
    ICustomerRestorationService customerRestorationService;

    @Autowired
    IDishService dishService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/")
    public String landingPage(){
        return "redirect:main";
    }

    @GetMapping("/main")
    public String main(Model model){

        model.addAttribute("rests", this.customerRestorationService.getAllRestoration());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "contact";
    }
}
