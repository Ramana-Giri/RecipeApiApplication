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
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return recipeService.getAllRecipes(pageable);
    }

    /**
     * ✅ Search by title
     * Example: GET /api/recipes/search/title?keyword=chicken&page=0&size=5
     */
    @GetMapping("/search/title")
    public Page<Recipe> searchByTitle(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeService.searchByTitle(keyword, pageable);
    }

    /**
     * ✅ Search by cuisine
     * Example: GET /api/recipes/search/cuisine?name=Italian&page=0&size=5
     */
    @GetMapping("/search/cuisine")
    public Page<Recipe> searchByCuisine(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeService.searchByCuisine(name, pageable);
    }

    /**
     * ✅ Search by country or state
     * Example: GET /api/recipes/search/country?name=US&page=0&size=5
     */
    @GetMapping("/search/country")
    public Page<Recipe> searchByCountryState(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeService.searchByCountryState(name, pageable);
    }
}
