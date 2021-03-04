package pl.edu.wszib.javaee.food.platform.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.view.RegistrationModel;
import pl.edu.wszib.javaee.food.platform.services.IAdminService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/

@Controller
@Log
public class AdminController {

    @Autowired
    IAdminService adminService;

    @Resource
    SessionObject sessionObject;

    @GetMapping("/admin")
    public String main(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.ADMIN){
            return "redirect:/login";
        }

        model.addAttribute("customers", this.adminService.getAllCustomers());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "admin";
    }

    @GetMapping("/addCustomer")
    public String registerNewCustomer(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.ADMIN){
            return "redirect:/login";
        }

        model.addAttribute("registerModelCustomer", new RegistrationModel());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String saveCustomer(@ModelAttribute (name = "registerModelCustomer") RegistrationModel registrationModel){

        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.ADMIN){
            return "redirect:/login";
        }

        if (this.adminService.registerCustomer(registrationModel)) {
            return "redirect:/login";
        }else{
            this.sessionObject.setInfo("Login is occupied!");
            return "redirect:/admin";
        }
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != AppUser.Role.ADMIN){
            return "redirect:/login";
        }

        this.adminService.deleteCustomerById(id);

        return "redirect:/main";
    }

/*

    @PostMapping("/save")
    public AppUser saveUsers(@RequestBody RegistrationModel customer) throws ValidationException {
        log.info("Handling save users: " + customer);
        return adminService.saveCustomer(customer);
    }

    @GetMapping("/findAll")
    public List<AppUser> findAllUsers() {
        log.info("Handling find all users request");
        return adminService.getAllCustomers();
    }

    @GetMapping("/findByLogin")
    public Optional<AppUser> findByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return adminService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        log.info("Handling delete user request: " + id);
        adminService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }
*/
}
