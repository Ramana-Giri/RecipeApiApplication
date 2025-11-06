package com.example.service;

import com.example.model.Recipe;
import com.example.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    /**
     * Fetah all recipes with pagination and sorting.
     */
    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    /**
     * Search recipes by keyword in title.
     */
    public Page<Recipe> searchByTitle(String keyword, Pageable pageable) {
        return recipeRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    /**
     * Search recipes by cuisine name.
     */
    public Page<Recipe> searchByCuisine(String cuisine, Pageable pageable) {
        return recipeRepository.findByCuisineContainingIgnoreCase(cuisine, pageable);
    }

    /**
     * Search recipes by country or state.
     */
    public Page<Recipe> searchByCountryState(String countryState, Pageable pageable) {
        return recipeRepository.findByCountryStateContainingIgnoreCase(countryState, pageable);
    }

    /**
     * Save all parsed recipes to the database.
     */
    public void saveAll(List<Recipe> recipes) {
        recipeRepository.saveAll(recipes);
    }
}

