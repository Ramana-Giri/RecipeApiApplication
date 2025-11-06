package com.example.config;


import com.example.model.Recipe;
import com.example.service.RecipeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Loads recipe data from recipes.json into the database on application startup.
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RecipeService recipeService;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void run(String... args) throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream("/recipes.json")) {
            if (inputStream == null) {
                System.out.println("❌ recipes.json not found in resources!");
                return;
            }

            // Parse JSON file (root object is a map of index -> recipe)
            TypeReference<Map<String, Recipe>> typeRef = new TypeReference<>() {};
            Map<String, Recipe> recipeMap = objectMapper.readValue(inputStream, typeRef);

            List<Recipe> recipes = new ArrayList<>(recipeMap.values());
            recipeService.saveAll(recipes);

            System.out.println("✅ Successfully loaded " + recipes.size() + " recipes into the database.");
        } catch (Exception e) {
            System.out.println("❌ Failed to load recipes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

