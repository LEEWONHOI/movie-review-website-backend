package hello.moivereview.service;

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
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.addCreateTime(LocalDateTime.now());
        }
        movie.addUpdateTime(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public Optional<Movie> findByMovie(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Optional<Movie> findByTitle(String movieTitle) {
        return movieRepository.findByTitle(movieTitle);
    }

    public List<Movie> findAllByMemberId(Long memberId) {
        return movieRepository.findAllByMemberId(memberId);
    }

    // TODO 검증 필요
    public Page<Movie> findAllByMemberId(Long memberId, Pageable pageable) {
        return movieRepository.findAllByMemberId(memberId, pageable);
    }

    public long countMovieByMember(Long memberId) {
        return movieRepository.countMovieByMember(memberId);
    }

    // TODO 삭제 예정 메서드
    @Transactional(readOnly = true)
    public List<Movie> findByMovieListTop10(String title) {
        return movieRepository.findTop10ByTitleContainingIgnoreCase(title);
    }

    @Transactional(readOnly = true)
    public Page<Movie> findAllByMovieListWithYear(String title, Type type, String year, int needNumber) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndTypeEqualsAndYearEquals(title, type, year, PageRequest.of(0, needNumber));
    }

    @Transactional(readOnly = true)
    public Page<Movie> findAllByMovieList(String title, Type type, int needNumber) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndTypeEquals(title, type, PageRequest.of(0, needNumber));
    }

}
