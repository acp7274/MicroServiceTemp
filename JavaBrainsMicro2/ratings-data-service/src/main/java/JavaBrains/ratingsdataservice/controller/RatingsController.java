package JavaBrains.ratingsdataservice.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JavaBrains.ratingsdataservice.domain.Rating;
import JavaBrains.ratingsdataservice.domain.UserRating;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsController {

    @RequestMapping("/{cdId}")
    public Rating getRating(@PathVariable("cdId") String cdId) { return new Rating(cdId, 4);}

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("2345", 6),
                new Rating("3456", 8),
                new Rating("4567", 9),
                new Rating("5678", 4)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;

    }

}
