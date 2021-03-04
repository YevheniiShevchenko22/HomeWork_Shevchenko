package pl.edu.wszib.javaee.food.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;
import pl.edu.wszib.javaee.food.platform.model.Dish;
import pl.edu.wszib.javaee.food.platform.services.ICustomerRestorationService;
import pl.edu.wszib.javaee.food.platform.services.IDishService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Controller
public class CustomerController {

    @Autowired
    IDishService dishService;

    @Autowired
    ICustomerRestorationService customerRestorationService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/newDish")
    public String newDish(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        model.addAttribute("newDish", new Dish());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "newDish";
    }

    @PostMapping("newDish")
    public String saveNewDish(@ModelAttribute(name = "newDish") Dish newDish,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        this.dishService.saveDish(newDish, multipartFile);

        return "redirect:/main";
    }

    @GetMapping("/editDish/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        Dish dish = this.dishService.getDishByID(id);

        model.addAttribute("dish", dish);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "editDish";
    }

    @PostMapping("/editDish/{id}")
    public String editDish(@ModelAttribute Dish dish){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        this.dishService.updateDish(dish);

        return "redirect:/main";
    }

    @DeleteMapping("/deleteDish/{id}")
    public String deleteDishById(@PathVariable Long id){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        this.dishService.deleteDishById(id);

        return  "redirect:/main";
    }

    //Customer controller for operation with Restoration

    @GetMapping("/newRestoration")
    public String newRestoration(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        model.addAttribute("newRestoration", new CustomerRestoration());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "newRestoration";
    }

    @PostMapping("newRestoration")
    public String saveNewRestoration(@ModelAttribute(name = "newRestoration") CustomerRestoration restoration) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        this.customerRestorationService.saveRestoration(restoration);

        return "redirect:/main";
    }

    @GetMapping("/editRestoration/{id}")
    public String editRestorationForm(@PathVariable Long id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        CustomerRestoration restoration = this.customerRestorationService.getRestorationById(id);

        model.addAttribute("restoration", restoration);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "editRestoration";
    }

    @PostMapping("/editRestoration/{id}")
    public String editCustomerRestoration(@ModelAttribute CustomerRestoration restoration){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.CUSTOMER){
            return "redirect:/main";
        }

        this.customerRestorationService.updateRestorante(restoration);

        return "redirect:/main";
    }

}
