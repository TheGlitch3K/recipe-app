package org.launchcode.liftoff.recipeApp.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Ingredients {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min=3, max=15)
    private String ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @NotNull
    private int quantity;
    public Ingredients() {}
    public Ingredients(String ingredient, User user, Integer quantity) {
        this.ingredient = ingredient;
        this.user = user;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public String getIngredient() {
        return ingredient;
    }
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}