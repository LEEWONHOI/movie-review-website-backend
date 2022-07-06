package hello.moivereview.repository;

import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByMovie_Id(Long movieId, Pageable pageable);

    @Query("select r from Review r where r.member.email =?1 and r.movie.id =?2")
    Optional<Review> findReviewByMemberEmail(String memberEmail, Long movieId);

    @Query("select r.movie from Review r where r.member.email=?1")
    Page<Movie> findMovieByMovie(String memberEmail, Pageable pageable);

}
