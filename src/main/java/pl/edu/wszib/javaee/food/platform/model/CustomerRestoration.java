package pl.edu.wszib.javaee.food.platform.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Data
@Entity
public class CustomerRestoration {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.EAGER)
//    private AppUser customer;

    private String name;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Dish> dishes = new HashSet<>();

    private String openTime;

    private String closeTime;

    public CustomerRestoration(String name, String openTime, String closeTime) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public CustomerRestoration() {

    }
}
