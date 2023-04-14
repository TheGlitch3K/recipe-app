package org.launchcode.liftoff.recipeApp.services;

import org.launchcode.liftoff.recipeApp.models.Ingredients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientsService {

    List<Ingredients> findAll();

    Ingredients findById(int id);

    void save(Ingredients ingredient);

    void delete(int id);
}