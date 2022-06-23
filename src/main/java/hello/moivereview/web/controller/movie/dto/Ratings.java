package hello.moivereview.web.controller.movie.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ratings {

    private String site;
    private String value;

}
