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
        Movie foundMovie = movieService.findByMovie(movieId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        List<Review> reviews = reviewService.findAllReviewByMovieId(movieId).toList();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setMemberName(review.getMember().getName());
            reviewDto.setContents(review.getContents());
            reviewDtoList.add(reviewDto);
        }

        List<Ratings> ratingsList = initRating(foundMovie, reviews);
        MovieDto movieDto = makeDetailMovieDto(foundMovie, ratingsList, reviewDtoList);

        return new Gson().toJson(movieDto);
    }

    @PostMapping("/movie/addcomment")
    public String addComment(@RequestBody AddReviewDto addReviewDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("comment 로직 접근");
        Long movieId = addReviewDto.getId();
        Movie foundMovie = movieService.findByMovie(movieId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        List<Review> reviews = reviewService.findAllReviewByMovieId(movieId).toList();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setMemberName(review.getMember().getName());
            reviewDto.setContents(review.getContents());
            reviewDtoList.add(reviewDto);
        }


        List<Ratings> ratingsList = initRating(foundMovie, reviews);
        MovieDto movieDto = makeDetailMovieDto(foundMovie, ratingsList, reviewDtoList);

        String sessionId = addReviewDto.getSession();
        System.out.println("sessionId = " + sessionId);

        if (sessionId == null) {
            // TODO 로그인이 필요합니다. 메시지
            System.out.println("세션 만료된 사용자");
            response.sendRedirect(UrlConst.BACKEND_URL);
            return "redirect:" + UrlConst.BACKEND_URL;
        }

        String memberEmail = (String) sessionManager.findBySession(sessionId);

        Review review = reviewService.save(memberEmail, movieId, addReviewDto.getComment());
        System.out.println("review = " + review.getContents());

        return new Gson().toJson(movieDto);
    }

    private List<Ratings> initRating(Movie findMovie, List<Review> reviews) {
        Ratings metascore = new Ratings("Internet Movie Database", findMovie.getMetascore());
        Ratings imdbRating = new Ratings("Rotten Tomatoes", findMovie.getImdbRating());
        Ratings imdbVotes = new Ratings("Metacritic", findMovie.getImdbVotes());

        return makeRatingList(metascore, imdbRating, imdbVotes);

    }

    private List<Ratings> makeRatingList(Ratings... ratings) {
        List<Ratings> ratingsList = new ArrayList<>();
        Arrays.stream(ratings).forEach(ratingsList::add);
        return ratingsList;
    }

    public MovieDto makeDetailMovieDto(Movie movie, List<Ratings> ratingsList, List<ReviewDto> reviews) {

        for (ReviewDto review : reviews) {
            System.out.println("review dto = " + review.getContents());
        }

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
