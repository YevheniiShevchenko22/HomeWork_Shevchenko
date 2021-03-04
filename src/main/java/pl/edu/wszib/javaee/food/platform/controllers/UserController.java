package pl.edu.wszib.javaee.food.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;
import pl.edu.wszib.javaee.food.platform.services.IDishService;
import pl.edu.wszib.javaee.food.platform.services.IUserService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IDishService dishService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/login")
    public String loginform(Model model){
        if (this.sessionObject.isLogged()){
            return "redirect:/main";
        }

        model.addAttribute("userModel", new AppUser());

        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "userModel") AppUser user){

        this.userService.authenticate(user);

        if (this.sessionObject.isLogged()){
            return "redirect:/main";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(){

        this.userService.logout();

        return "redirect:/login";
    }

    @GetMapping(value = "/register")
    public String registerForm( Model model)
    {

        model.addAttribute("registerModel", new RegistrationModel());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute RegistrationModel registrationModel ){

        Pattern regex = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher validateLogin = regex.matcher(registrationModel.getName());
        Matcher validatePass = regex.matcher(registrationModel.getPass());
        Matcher validatePass2 = regex.matcher(registrationModel.getPass2());

        if (!validateLogin.matches() || !validatePass.matches() || !validatePass2.matches()
                || !registrationModel.getPass().equals(registrationModel.getPass2())){
            this.sessionObject.setInfo("Validation ERROR!");
            return "redirect:/register";
        }

        if (this.userService.register(registrationModel)){
            return "redirect:/login";
        }else{
            this.sessionObject.setInfo("Login is occupied!");
            return "redirect:/register";
        }
    }

    @GetMapping("/showDishes")
    public String showDish(Model model){

        model.addAttribute("dishes", this.dishService.getAllDishes());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "showDishes";
    }

}
