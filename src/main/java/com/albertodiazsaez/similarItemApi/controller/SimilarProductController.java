package com.albertodiazsaez.similarItemApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertodiazsaez.similarItemApi.entity.ProductDetail;
import com.albertodiazsaez.similarItemApi.service.SimilarProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Tag(name = "Products", description = "Operations related to products")
public class SimilarProductController {

    private final SimilarProductService similarProductService;
    
    @GetMapping("/{productId}/similar")
    @Operation(
            summary = "Get similar products",
            description = "Returns a list of products similar to the given productId. "
                        + "Results may be cached for performance optimization."
                        + "The list may of products can be empty if no product exists with thay ID,"
                        + "or no items are similar to that product"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of similar products, or empty list",
                    content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDetail.class)))}
                    ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Existing API unavailable"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected system error"
            )
    })
    public ResponseEntity<List<ProductDetail>> getSimilarProducts(@PathVariable Long productId) {
        List<ProductDetail> result = similarProductService.getSimilarProducts(productId);
        
        return ResponseEntity.ok(result);        
        
    }
}
