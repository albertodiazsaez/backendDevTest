package com.albertodiazsaez.similarItemApi.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.albertodiazsaez.similarItemApi.entity.ProductDetail;
/**
 * External API Client for Products in ExistingAPI
 */
@HttpExchange
public interface ProductClient {

    /**
     * Gets the ID's of the similar products of the given product ID.
     * @param productId
     * @return
     */
    @GetExchange("/product/{productId}/similarids")
    List<Long> getSimilarIdList(@PathVariable long productId);

    /**
     * Gets the details of a product given his ID.
     * @param productId
     * @return
     */
    @GetExchange("/product/{productId}")
    ProductDetail getProductDetail(@PathVariable long productId);

}
