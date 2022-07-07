package hello.moivereview.web.controller.movie;

import com.google.gson.Gson;
import hello.moivereview.config.SessionManager;
import hello.moivereview.constant.UrlConst;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Review;
import hello.moivereview.domain.Type;
import hello.moivereview.service.MovieService;
import hello.moivereview.service.ReviewService;
import hello.moivereview.web.controller.movie.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;
    private final SessionManager sessionManager;

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
        MovieDto movieDto = makeMovieDto(movieId);
        return new Gson().toJson(movieDto);
    }

    @PostMapping("/movie/addcomment")
    public String addComment(@RequestBody AddReviewDto addReviewDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Long movieId = addReviewDto.getId();
        MovieDto movieDto = makeMovieDto(movieId);

        String sessionId = addReviewDto.getSession();
        log.info("sessionId = {}", sessionId);

        if (sessionId == null) {
            log.info("세션 만료된 사용자");
            response.sendRedirect(UrlConst.BACKEND_URL);
            return "redirect:" + UrlConst.BACKEND_URL;
        }

        String memberEmail = (String) sessionManager.findBySession(sessionId);
        reviewService.save(memberEmail, movieId, addReviewDto.getComment());

        return new Gson().toJson(movieDto);
    }

    private MovieDto makeMovieDto(Long movieId) {
        Movie foundMovie = movieService.findByMovie(movieId);
        List<Review> reviews = reviewService.findAllReviewByMovieId(movieId).toList();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setMemberName(review.getMember().getName());
            reviewDto.setContents(review.getContents());
            reviewDtoList.add(reviewDto);
        }


        List<Ratings> ratingsList = initRating(foundMovie);
        return makeDetailMovieDto(foundMovie, ratingsList, reviewDtoList);
    }

    private List<Ratings> initRating(Movie findMovie) {
        Ratings metascore = new Ratings("Internet Movie Database", findMovie.getMetascore());
        Ratings imdbRating = new Ratings("Rotten Tomatoes", findMovie.getImdbRating());
        Ratings imdbVotes = new Ratings("Metacritic", findMovie.getImdbVotes());

        return makeRatingList(metascore, imdbRating, imdbVotes);

    }

    private List<Ratings> makeRatingList(Ratings... ratings) {
        return new ArrayList<>(Arrays.asList(ratings));
    }

    public MovieDto makeDetailMovieDto(Movie movie, List<Ratings> ratingsList, List<ReviewDto> reviews) {
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
                reviews,
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
