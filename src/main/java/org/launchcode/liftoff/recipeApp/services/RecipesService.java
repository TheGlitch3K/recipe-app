package org.launchcode.liftoff.recipeApp.services;

import org.launchcode.liftoff.recipeApp.models.Recipes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipesService {

    List<Recipes> findAll();

    Recipes findById(int id);

    void save(Recipes recipe);

    void delete(int id);
}