package com.javabrains.moviecatalogservice.controller;

import com.javabrains.moviecatalogservice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

// API available at catalog/userId URL
@RequestMapping("/catalog")
@RestController
public class MovieCatalogController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    //item by userId  brackets take a variable not literal
    @RequestMapping("/{userId}") // path variable makes sure to pass variable into the path
    public Catalog getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = webClientBuilder.build()
                .get()
                .uri("http://ratings-data-service/ratingsdata/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();


        Catalog catalog = new Catalog(ratings.getUserRating().stream().map(rating -> {
                    Movie movie = webClientBuilder.build()
                            .get() //going to do a get
                            .uri("http://movie-info-service/movies/" + rating.getMovieId())// where to get the call
                            .retrieve()//do the fetch
                            .bodyToMono(Movie.class)// promise to get you what you want
                            .block();
                    return new CatalogItem(movie.getName(), "Desc",  rating.getRating());//API call to getname, and getRating
                })
                .collect(Collectors.toList()));

        return catalog;
    }
}
