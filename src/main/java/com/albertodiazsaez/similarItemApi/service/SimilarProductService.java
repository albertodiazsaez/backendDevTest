package com.albertodiazsaez.similarItemApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.albertodiazsaez.similarItemApi.client.ProductClient;
import com.albertodiazsaez.similarItemApi.entity.ProductDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimilarProductService {

    private final ProductClient productClient;
    /**
     * Returns a list of similar products as the product matching the given ID.
     * 
     * Note: If the product ID returns no similar products, it returns an empty list, as the ExistingAPI doesn't specify
     * if the 404 given is because the product does not exist, or there are not similar products. Other errors are thrown.
     * 
     * @param productId
     * @return
     */
    @Cacheable(value = "similarProducts", key = "#productId")
    public List<ProductDetail> getSimilarProducts(Long productId) {
        List<ProductDetail> result = new ArrayList<>();
        
        try {
            productClient.getSimilarIdList(productId).stream()
        .forEach(id -> result.add(productClient.getProductDetail(id)));
        } catch (HttpClientErrorException error) {
            if (error.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw error;
            }
        }
        
        return result;
    }
    
}
