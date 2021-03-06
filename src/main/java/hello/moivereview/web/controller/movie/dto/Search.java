package hello.moivereview.web.controller.movie.dto;

import hello.moivereview.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Search {

    private String Title;

    private String Year;

    private Long movieId;

    private Type Type;

    private String Poster;

}
