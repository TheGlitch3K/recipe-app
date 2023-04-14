package org.launchcode.liftoff.recipeApp.models;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Entity
public class User extends AbstractEntity {
    @NotNull
    @Size(min=3, max=15)
    private String username;
    @NotNull
    @Size(min=6)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Ingredients> ingredients = new ArrayList<>();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = encoder.encode(password);
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Ingredients> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.password);
    }
}
