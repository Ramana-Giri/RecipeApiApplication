package com.example.repository;

import com.example.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    // 🔍 Search recipes by title (case-insensitive)
    Page<Recipe> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // 🔍 Search recipes by cuisine (case-insensitive)
    Page<Recipe> findByCuisineContainingIgnoreCase(String cuisine, Pageable pageable);

    // 🔍 Search recipes by country or state (case-insensitive)
    Page<Recipe> findByCountryStateContainingIgnoreCase(String countryState, Pageable pageable);
}

