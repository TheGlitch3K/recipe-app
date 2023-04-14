package org.launchcode.liftoff.recipeApp.controllers;
import org.launchcode.liftoff.recipeApp.models.Ingredients;
import org.launchcode.liftoff.recipeApp.models.User;
import org.launchcode.liftoff.recipeApp.repositories.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
@Controller
@RequestMapping("ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    AuthenticationController authenticationController;
    @GetMapping("")
    public String index(Model model, HttpServletRequest request, String id) {
        User user = authenticationController.getUserFromSession(request.getSession());
       model.addAttribute("ingredients",user.getIngredients());
        model.addAttribute("username", user.getUsername());
        return "ingredients/index";
    }
    @GetMapping("add")
    public String displayAddIngredientsForm(Model model) {
        model.addAttribute(new Ingredients());
        return "ingredients/add";
    }

    @PostMapping("add")
    public String processAddIngredientsForm(@ModelAttribute @Valid Ingredients newIngredients,
                                            Errors errors, Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredient");
            model.addAttribute("ingredients", ingredientsRepository.findAll());
            return "ingredients/add";
        }

        User user = authenticationController.getUserFromSession(request.getSession());
        newIngredients.setUser(user);
        ingredientsRepository.save(newIngredients);
        return "redirect:";
    }

    @GetMapping("view/{addIngredientsId}")
    public String displayViewIngredient(Model model, @PathVariable int addIngredientsId, HttpServletRequest request) {
        Optional<Ingredients> optionalIngredients = ingredientsRepository.findById(addIngredientsId);
        if (optionalIngredients.isPresent()) {
            Ingredients ingredients = optionalIngredients.get();

            User user = authenticationController.getUserFromSession(request.getSession());

            if (user != ingredients.getUser()) {
                return "redirect:../";
            }
            model.addAttribute("ingredients", ingredients);
            return "ingredients/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("edit/{addIngredientsId}")
    public String displayEditIngredientForm(Model model, @PathVariable int addIngredientsId, HttpServletRequest request) {
        Optional<Ingredients> optionalIngredients = ingredientsRepository.findById(addIngredientsId);
        if (optionalIngredients.isPresent()) {
            Ingredients ingredients = optionalIngredients.get();

            User user = authenticationController.getUserFromSession(request.getSession());

            if (user != ingredients.getUser()) {
                return "redirect:../";
            }
            model.addAttribute("ingredients", ingredients);
            return "ingredients/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit")
    public String processEditIngredientForm(@RequestParam int addIngredientsId, String ingredient, int quantity, Model model, HttpServletRequest request) {
        Optional<Ingredients> optionalIngredients = ingredientsRepository.findById(addIngredientsId);
        if (optionalIngredients.isPresent()) {
            Ingredients ingredients = optionalIngredients.get();

            User user = authenticationController.getUserFromSession(request.getSession());

            if (user != ingredients.getUser()) {
                return "redirect:../";
            }
            ingredients.setIngredient(ingredient);
            ingredients.setQuantity(quantity);
            ingredientsRepository.save(ingredients);
            return "redirect:";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete/{addIngredientsId}")
    public String displayDeleteIngredientForm(Model model, @PathVariable int addIngredientsId, HttpServletRequest request) {
        Optional<Ingredients> optionalIngredients = ingredientsRepository.findById(addIngredientsId);
        if (optionalIngredients.isPresent()) {
            Ingredients ingredients = optionalIngredients.get();

            User user = authenticationController.getUserFromSession(request.getSession());

            if (user != ingredients.getUser()) {
                return "redirect:../";
            }
            model.addAttribute("ingredients", ingredients);
            return "ingredients/delete";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("delete")
    public String processDeleteIngredientForm(int addIngredientsId, HttpServletRequest request) {
        Optional<Ingredients> optionalIngredients = ingredientsRepository.findById(addIngredientsId);
        if (optionalIngredients.isPresent()) {
            Ingredients ingredients = optionalIngredients.get();

            User user = authenticationController.getUserFromSession(request.getSession());

            if (user != ingredients.getUser()) {
                return "redirect:../";
            }
            ingredientsRepository.delete(ingredients);
            return "redirect:";
        } else {
            return "redirect:../";
        }
    }
}