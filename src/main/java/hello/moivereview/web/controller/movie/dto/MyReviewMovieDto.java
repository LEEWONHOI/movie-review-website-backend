package hello.moivereview.web.controller.movie.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MyReviewMovieDto {
    private String Title;

    private String Year;

    private Long movieId;

    private hello.moivereview.domain.Type Type;

    private String Poster;

}
