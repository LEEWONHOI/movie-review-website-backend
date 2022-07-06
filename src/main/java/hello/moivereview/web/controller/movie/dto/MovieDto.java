package hello.moivereview.web.controller.movie.dto;


import hello.moivereview.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class MovieDto {

    private String Title;

    private String Year;

    private String Rated;

    private String Released;

    private String Runtime;

    private String Genre;

    private String Director;

    private String Writer;

    private String Actors;

    private String Plot;

    private String Language;

    private String Country;

    private String Awards;

    private String Poster;

    private List<hello.moivereview.web.controller.movie.dto.Ratings> Ratings;

    private List<ReviewDto> reviews;

    private Long movieId;

    private Type Type;

    private String DVD;

    private String BoxOffice;

    private String Production;

    private String Website;

    private Boolean Response;


}

