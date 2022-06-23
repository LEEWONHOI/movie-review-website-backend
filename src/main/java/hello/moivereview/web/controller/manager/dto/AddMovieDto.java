package hello.moivereview.web.controller.manager.dto;

import hello.moivereview.domain.Type;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddMovieDto {

    private String title;
    private String year;
    private Type type;
    private String genre;
    private String poster;
    private String plot;
    private String rated;
    private String released;
    private String runtime;
    private String director;
    private String writer;
    private String actors;
    private String language;
    private String country;
    private String awards;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String DVD;
    private String boxOffice;
    private String production;
    private String website;
}
