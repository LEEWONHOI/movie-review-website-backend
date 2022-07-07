package hello.moivereview.service;

import hello.moivereview.annotation.Retry;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Type;
import hello.moivereview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.addCreateTime(LocalDateTime.now());
        }
        movie.addUpdateTime(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    @Retry
    public Movie findByMovie(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 영화 id 입니다. = " + movieId));
    }

    @Retry
    public Movie findByTitle(String movieTitle) {
        return movieRepository.findByTitle(movieTitle).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 영화 title 입니다. = " + movieTitle));
    }

    @Retry
    public List<Movie> findAllByMemberId(Long memberId) {
        return movieRepository.findAllByMemberId(memberId);
    }

    public Page<Movie> findAllByMemberId(Long memberId, Pageable pageable) {
        return movieRepository.findAllByMemberId(memberId, pageable);
    }

    @Retry
    public long countMovieByMember(Long memberId) {
        return movieRepository.countMovieByMember(memberId);
    }

    @Retry
    public Page<Movie> findAllByMovieListWithYear(String title, Type type, String year, int needNumber) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndTypeEqualsAndYearEquals(title, type, year, PageRequest.of(0, needNumber));
    }

    @Retry
    public Page<Movie> findAllByMovieList(String title, Type type, int needNumber) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndTypeEquals(title, type, PageRequest.of(0, needNumber));
    }

}
