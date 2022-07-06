package hello.moivereview.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO mySql 방식이라 나중에 체크해볼것
    @Column(name = "movie_id")
    private Long id;

    private String title;

    private String year;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String poster;

    private int selectedCount;

    // === 디테일 정보 시작 ===
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
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


    private Boolean response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    public void addCreateTime(LocalDateTime createTime) {
        this.created = createTime;
    }

    public void addUpdateTime(LocalDateTime updateTime) {
        this.updated = updateTime;
    }

    public void registerMember(Member member) {
        this.member = member;
    }


    // TODO 양방향 연결이 필요한지 프로젝트 진행하며 체크할것
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    public Movie(String title, String year, Type type, String poster, int selectedCount, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String metascore, String imdbRating, String imdbVotes, String DVD, String boxOffice, String production, String website, Boolean response) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
        this.selectedCount = selectedCount;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.DVD = DVD;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
        this.response = response;
    }
}
