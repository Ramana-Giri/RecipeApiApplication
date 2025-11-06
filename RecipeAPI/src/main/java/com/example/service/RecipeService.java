package com.example.service;

import com.example.model.Recipe;
import com.example.repository.RecipeRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Recipe> searchRecipes(
            Long id, String continent, String countryState, Double rating, String cuisine,
            Integer totalTime, Integer prepTime, Integer cookTime, String calories, Pageable pageable
    ) {
        Specification<Recipe> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (continent != null) predicates.add(cb.like(cb.lower(root.get("contient")), "%" + continent.toLowerCase() + "%"));
            if (countryState != null) predicates.add(cb.like(cb.lower(root.get("countryState")), "%" + countryState.toLowerCase() + "%"));
            if (rating != null) predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), rating));
            if (cuisine != null) predicates.add(cb.like(cb.lower(root.get("cuisine")), "%" + cuisine.toLowerCase() + "%"));
            if (totalTime != null) predicates.add(cb.lessThanOrEqualTo(root.get("totalTime"), totalTime));
            if (prepTime != null) predicates.add(cb.lessThanOrEqualTo(root.get("prepTime"), prepTime));
            if (cookTime != null) predicates.add(cb.lessThanOrEqualTo(root.get("cookTime"), cookTime));
            if (calories != null) predicates.add(cb.like(cb.lower(root.get("calories")), "%" + calories.toLowerCase() + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return recipeRepository.findAll(spec, pageable);
    }

}




