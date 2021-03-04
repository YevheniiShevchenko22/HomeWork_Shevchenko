package pl.edu.wszib.javaee.food.platform.cofiguration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.javaee.food.platform.dao.IUserDAO;
import pl.edu.wszib.javaee.food.platform.model.AppUser;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Configuration
@ComponentScan("pl.edu.wszib.javaee.food.platform")
public class AppConfiguration {

    /*@Bean
    CommandLineRunner commandLineRunner(IUserDAO createAdmin){
        return args -> {
            AppUser admin = new AppUser();
            admin.setName("admin");
            admin.setPass("admin");
            admin.setRole(AppUser.Role.ADMIN);

            createAdmin.save(admin);
        };
    }*/
}
