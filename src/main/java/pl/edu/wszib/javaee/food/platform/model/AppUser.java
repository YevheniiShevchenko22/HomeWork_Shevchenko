package pl.edu.wszib.javaee.food.platform.model;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String pass;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUser(String name,
                   String pass,
                   String email,
                   String phone,
                   Role role,
                   CustomerRestoration customerRestoration) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum Role {
        ADMIN,
        CUSTOMER,
        USER
    }
}
