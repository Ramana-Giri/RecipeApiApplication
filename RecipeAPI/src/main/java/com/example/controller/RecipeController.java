package com.example.controller;

import com.example.model.Recipe;
import com.example.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;


    /**
     * ✅ GET all recipes (paginated + sorted)
     * Example: GET /api/recipes?page=0&size=5&sortBy=title
     */
    @GetMapping
    public Page<Recipe> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "rating") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return recipeService.getAllRecipes(pageable);
    }

    /**
     * ✅ Search by title
     * Example: GET /api/recipes/search/title?keyword=chicken&page=0&size=5
     */


    /**
     * ✅ Search by cuisine
     * Example: GET /api/recipes/search/cuisine?name=Italian&page=0&size=5
     */

    @GetMapping("/search")
    public Page<Recipe> searchRecipes(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String continent,
            @RequestParam(required = false, name = "countryState") String countryState,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Integer totalTime,
            @RequestParam(required = false) Integer prepTime,
            @RequestParam(required = false) Integer cookTime,
            @RequestParam(required = false) String calories,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        String sortField = sort[0];
        Sort.Direction sortDirection = sort.length > 1 && sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        return recipeService.searchRecipes(
                id, continent, countryState, rating, cuisine,
                totalTime, prepTime, cookTime, calories, pageable
        );
    }
}

