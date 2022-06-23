package hello.moivereview.web.controller.movie.dto;

import hello.moivereview.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Search {

    private String Title;

    private String Year;

    private Long movieId;

    private Type Type;

    private String Poster;

}
