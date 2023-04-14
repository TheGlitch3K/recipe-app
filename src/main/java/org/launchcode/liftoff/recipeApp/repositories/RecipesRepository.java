package org.launchcode.liftoff.recipeApp.repositories;

import org.launchcode.liftoff.recipeApp.models.Recipes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RecipesRepository extends CrudRepository<Recipes, Integer> {
}