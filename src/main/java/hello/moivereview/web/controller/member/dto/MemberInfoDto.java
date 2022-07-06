package hello.moivereview.web.controller.member.dto;

import hello.moivereview.web.controller.movie.dto.MyReviewMovieDto;
import hello.moivereview.web.controller.movie.dto.Search;
import hello.moivereview.web.controller.movie.dto.SearchMovieDto;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberInfoDto {

    private List<Search> Search = new ArrayList<>();
    private String name;
    private String email;

}
