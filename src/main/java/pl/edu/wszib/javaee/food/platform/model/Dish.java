package pl.edu.wszib.javaee.food.platform.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@NoArgsConstructor
@Entity
@ToString
public class Dish {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
/*    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerRestoration customerRestoration;*/
    private String name;
    private String picture;
    private Double weight;
    private Integer calories;
    private Integer amount;
    private Double price;
    private String restName;

    public Dish(String name,
                String picture,
                Double weight,
                Integer calories,
                Double price,
                String restName) {
        this.name = name;
        this.picture = picture;
        this.weight = weight;
        this.calories = calories;
        this.amount = 7;
        this.price = price;
        this.restName = restName;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getPhotosImagePath() {
        if (picture == null || id == null) return null;

        return "/images/dishes/" + picture;
    }

}
