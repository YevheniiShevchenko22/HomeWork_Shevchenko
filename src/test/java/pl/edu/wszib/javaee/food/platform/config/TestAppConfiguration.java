package pl.edu.wszib.javaee.food.platform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yevhenii Shevchenko at 3/4/21
 * Project name: food.platform
 **/

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.javaee.food.platform.controllers",
        "pl.edu.wszib.javaee.food.platform.session",
        "pl.edu.wszib.javaee.food.platform.services.impl"

})
public class TestAppConfiguration {
}
