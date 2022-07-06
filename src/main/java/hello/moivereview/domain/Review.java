package hello.moivereview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String contents;

    private Boolean isLike;


    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    public void addCreateTime(LocalDateTime createTime) {
        this.created = createTime;
    }

    public void addUpdateTime(LocalDateTime updateTime) {
        this.updated = updateTime;
    }

    public void addComment(String comment) {
        this.contents = comment;
    }

    // 연관관계 편의 메서드
    public void addMember(Member member) {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getReviewList().add(this);
    }



}
