package com.cryptoRates.currency.controller;

import com.cryptoRates.currency.domain.Category;
import com.cryptoRates.currency.domain.CurrencyRate;
import com.cryptoRates.currency.domain.Index;
import com.cryptoRates.currency.domain.ObjectCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/currency")
@RestController
public class CurrencyController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/rates")
    public Flux<CurrencyRate> getRAll(){
        Flux<CurrencyRate> currencyRateList = webClientBuilder.build()
                .get()
                .uri("https://api.coingecko.com/api/v3/coins/categories/list")
                .retrieve()
                .bodyToFlux(CurrencyRate.class);
        return currencyRateList;
    }
    @GetMapping("/test")
    public Flux<Category> getAll() {
        Flux<Category> catFlux = webClientBuilder.build()
                .get()
                .uri("https://api.coingecko.com/api/v3/asset_platforms")
                .retrieve()
                .bodyToFlux(Category.class);
        return catFlux;
    }
    @GetMapping("/indexes")
    public Flux<Index> getAllIndexes(){
        Flux<Index> flux = webClientBuilder.build()
                .get()
                .uri("https://api.coingecko.com/api/v3/indexes/list")
                .retrieve()
                .bodyToFlux(Index.class);
        return flux;
    }
    @GetMapping("/categories")
    public Flux<ObjectCategory> getAllCategories() {
        Flux<ObjectCategory> flux = webClientBuilder.build()
                .get()
                .uri("https://api.coingecko.com/api/v3/coins/categories")
                .retrieve()
                .bodyToFlux(ObjectCategory.class);
        return flux;
    }

}
