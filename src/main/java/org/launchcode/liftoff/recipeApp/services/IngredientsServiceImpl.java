package org.launchcode.liftoff.recipeApp.services;

import org.launchcode.liftoff.recipeApp.models.Ingredients;
import org.launchcode.liftoff.recipeApp.repositories.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IngredientsServiceImpl implements IngredientsService{

    @Autowired
    private IngredientsRepository ingredientsRepo;

    @Override
    public List<Ingredients> findAll() {
        return (List<Ingredients>) ingredientsRepo.findAll();
    }

    @Override
    public Ingredients findById(int id) {
        Optional<Ingredients> optional = ingredientsRepo.findById(id);
        Ingredients ingredient = null;
        if(optional.isPresent())
            ingredient=optional.get();
        else
            throw new RuntimeException(
                    "Ingredients not found for id :" +id);
        return ingredient;
    }

    @Override
    public void save(Ingredients ingredient) {
    ingredientsRepo.save(ingredient);
    }

    @Override
    public void delete(int id) {
    ingredientsRepo.deleteById(id);
    }
}
