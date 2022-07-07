package hello.moivereview.service;

import hello.moivereview.annotation.Retry;
import hello.moivereview.domain.Member;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Review;
import hello.moivereview.repository.MemberRepository;
import hello.moivereview.repository.MovieRepository;
import hello.moivereview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Review save(String email, Long movieId, String comment) {

        Member findMember = memberRepository.findMemberByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        Movie findMovie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        Review review = new Review();
        review.addMember(findMember);
        review.addMovie(findMovie);
        review.addComment(comment);

        if (review.getId() == null) {
            review.addCreateTime(LocalDateTime.now());
        }
        review.addUpdateTime(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Retry
    public Page<Review> findAllReviewByMovieId(Long movieId) {
       return reviewRepository.findAllByMovie_Id(movieId, Pageable.unpaged());
    }

    @Retry
    public Optional<Review> findReviewByMyEmail(String myEmail, Long movieId) {
        return reviewRepository.findReviewByMemberEmail(myEmail, movieId);
    }




}
