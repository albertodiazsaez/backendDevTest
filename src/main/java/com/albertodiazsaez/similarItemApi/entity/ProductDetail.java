package com.albertodiazsaez.similarItemApi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDetail(
        
        @JsonProperty("id")
        String id,
        
        @JsonProperty("name")
        String name,
        
        @JsonProperty("price") 
        Double price,
        
        @JsonProperty("availability")
        Boolean availability
) {}
