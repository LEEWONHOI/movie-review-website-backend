package hello.moivereview.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ReviewAndLike {

    @Id @GeneratedValue
    @Column(name = "review_and_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String contents;

    private Boolean isLike;

    // 연관관계 편의 메서드
    public void addMember(Member member) {
        this.member = member;
        member.getReviewAndLikeList().add(this);
    }

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getReviewAndLikeList().add(this);
    }


}
