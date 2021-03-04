package pl.edu.wszib.javaee.food.platform.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.javaee.food.platform.model.AppUser;
import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Component
@NoArgsConstructor
@Getter
@Setter
public class RegistrationModel {
    private String name;
    private String pass;
    private String pass2;
    private String email;
    private String phone;
    private CustomerRestoration customerRestoration;

    public RegistrationModel(String name, String pass, String pass2, String email, String phone) {
        this.name = name;
        this.pass = pass;
        this.pass2 = pass2;
        this.email = email;
        this.phone = phone;
    }

    public RegistrationModel(String name, String pass, String pass2, String email, String phone, CustomerRestoration customerRestoration) {
        this.name = name;
        this.pass = pass;
        this.pass2 = pass2;
        this.email = email;
        this.phone = phone;
        this.customerRestoration = customerRestoration;
    }

    public AppUser toUser(RegistrationModel registrationModel){
        return new AppUser(registrationModel.getName(), registrationModel.getPass(), registrationModel.getEmail(), registrationModel.getPhone(), AppUser.Role.USER, null);
    }

    public AppUser toCustomer(RegistrationModel registrationModel){
        return new AppUser(registrationModel.getName(), registrationModel.getPass(), registrationModel.getEmail(), registrationModel.getPhone(), AppUser.Role.CUSTOMER, registrationModel.getCustomerRestoration());
    }
}
