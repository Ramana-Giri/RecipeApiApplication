package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutrients {

    private String calories;
    private String carbohydrateContent;
    private String fatContent;
    private String proteinContent;
    private String sugarContent;
    private String fiberContent;
    private String sodiumContent;
}

