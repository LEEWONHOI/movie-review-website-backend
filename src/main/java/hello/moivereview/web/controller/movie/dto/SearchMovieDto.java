package hello.moivereview.web.controller.movie.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchMovieDto {

    private List<Search> Search = new ArrayList<>();

    private long totalResult;

    private Boolean Response;

}
