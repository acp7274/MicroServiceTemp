package JavaBrains.cdcatalogservice.controller;


import JavaBrains.cdcatalogservice.domain.CD;
import JavaBrains.cdcatalogservice.domain.Catalog;
import JavaBrains.cdcatalogservice.domain.CatalogItem;
import JavaBrains.cdcatalogservice.domain.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CDCatalogController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public Catalog getCatalog(@PathVariable("userId")String userId) {

        UserRating ratings = webClientBuilder.build()
                .get()
                .uri("http://ratings-data-service/ratingsdata/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        Catalog catalog = new Catalog(ratings.getUserRating().stream().map(rating -> {
            CD cd = webClientBuilder.build()
                    .get()
                    .uri("http://cd-info-service/cds/" + rating.getCdId())
                    .retrieve()
                    .bodyToMono(CD.class)
                    .block();

        return new CatalogItem(cd.getTitle(), "Desc", rating.getRating());
        })
            .collect(Collectors.toList()));
        return catalog;
    }

}
