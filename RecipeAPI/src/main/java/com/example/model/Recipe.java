package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Contient")
    private String contient;
    @JsonProperty("Country_State") // e.g. "North America"
    private String countryState;      // e.g. "US"
    private String cuisine;           // e.g. "Southern Recipes"
    private String title;             // Recipe name
    @JsonProperty("URL")
    private String url;               // Link to recipe source
    private Double rating;            // e.g. 4.8
    @JsonProperty("total_time")
    private Integer totalTime;        // Total cooking time in minutes
    @JsonProperty("prep_time")
    private Integer prepTime;         // Preparation time in minutes
    @JsonProperty("cook_time")
    private Integer cookTime;         // Cooking time in minutes (nullable)

    @Column(length = 2000)
    private String description;       // Recipe description or overview

    @ElementCollection
    @CollectionTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id")
    )
    @Column(name = "ingredient", length = 500)
    private List<String> ingredients; // List of ingredients

    @ElementCollection
    @CollectionTable(
            name = "recipe_instructions",
            joinColumns = @JoinColumn(name = "recipe_id")
    )
    @Column(name = "instruction", length = 2000)
    private List<String> instructions; // Step-by-step instructions

    private String calories;              // e.g. "389 kcal"
    private String carbohydrateContent;   // e.g. "42g"
    private String serves;                // e.g. "8 servings"
}

