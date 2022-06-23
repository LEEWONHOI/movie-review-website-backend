package hello.moivereview.web.controller.movie;

import com.google.gson.Gson;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Type;
import hello.moivereview.service.MovieService;
import hello.moivereview.web.controller.movie.dto.MovieDto;
import hello.moivereview.web.controller.movie.dto.Ratings;
import hello.moivereview.web.controller.movie.dto.Search;
import hello.moivereview.web.controller.movie.dto.SearchMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search")
    public String searchMovieList(
            @RequestParam String title,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) String year,
            @RequestParam(required = true) int needPage
    ) {

        if (year.isEmpty()) {
            Page<Movie> movieList = movieService.findAllByMovieList(title, type, needPage);
            SearchMovieDto searchMovieDto = makeSearchMovieDto(movieList);
            return new Gson().toJson(searchMovieDto);

        } else {
            Page<Movie> movieList = movieService.findAllByMovieListWithYear(title, type, year, needPage);
            SearchMovieDto searchMovieDto = makeSearchMovieDto(movieList);
            return new Gson().toJson(searchMovieDto);
        }

    }

    @GetMapping("/movie/{movieId}")
    public String detailMovieInfo(@PathVariable Long movieId) {
        Movie findMovie = movieService.findByMovie(movieId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));

        Ratings metascore = new Ratings("Internet Movie Database", findMovie.getMetascore());
        Ratings imdbRating = new Ratings("Rotten Tomatoes", findMovie.getImdbRating());
        Ratings imdbVotes = new Ratings("Metacritic", findMovie.getImdbVotes());

        List<Ratings> ratingsList = makeRatingList(metascore, imdbRating, imdbVotes);
        MovieDto movieDto = makeDetailMovieDto(findMovie, ratingsList);

        return new Gson().toJson(movieDto);
    }

    private List<Ratings> makeRatingList(Ratings ...ratings) {
        List<Ratings> ratingsList = new ArrayList<>();
        Arrays.stream(ratings).forEach(ratingsList::add);
        return ratingsList;
    }

    public MovieDto makeDetailMovieDto(Movie movie, List<Ratings> ratingsList) {
        return new MovieDto(
                movie.getTitle(),
                movie.getYear(),
                movie.getRated(),
                movie.getReleased(),
                movie.getRuntime(),
                movie.getGenre(),
                movie.getDirector(),
                movie.getWriter(),
                movie.getActors(),
                movie.getPlot(),
                movie.getLanguage(),
                movie.getCountry(),
                movie.getAwards(),
                movie.getPoster(),
                ratingsList,
                movie.getId(),
                movie.getType(),
                movie.getDVD(),
                movie.getBoxOffice(),
                movie.getProduction(),
                movie.getWebsite(),
                movie.getResponse()
        );
    }

    public SearchMovieDto makeSearchMovieDto(Page<Movie> movieList) {
        SearchMovieDto searchMovieDto = new SearchMovieDto();
        for (Movie movie : movieList) {
            Search search = new Search(
                    movie.getTitle(),
                    movie.getYear(),
                    movie.getId(),
                    Type.movie,
                    movie.getPoster()
            );
            searchMovieDto.getSearch().add(search);
            searchMovieDto.setTotalResult(movieList.getTotalElements());
            searchMovieDto.setResponse(true);   // 테스트용
        }
        return searchMovieDto;
    }


}
