package com.albertodiazsaez.similarItemApi.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.albertodiazsaez.similarItemApi.client.ProductClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ExistingApiConfiguration {

    @Value("${env.existing-api-url}")
    private String existingApiUrl;
    

    @Bean
    RestClient existingApiRestClient() {
        return RestClient.builder()
                .baseUrl(existingApiUrl)
                .build();
    }
    
    @Bean
    HttpServiceProxyFactory existingApiProxyFactory(RestClient existingApiRestClient) {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(existingApiRestClient))
                .build();
    }
    
    @Bean
    ProductClient productClient(HttpServiceProxyFactory existingApiProxyFactory) {
        return existingApiProxyFactory.createClient(ProductClient.class);
    }
    

}
