package hello.moivereview.service;

import hello.moivereview.domain.Member;
import hello.moivereview.domain.Movie;
import hello.moivereview.repository.MemberRepository;
import hello.moivereview.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void addData() {
        // given
        Member member = Member.builder()
                .name("test")
                .email("test123@test123.com")
                .password(passwordEncoder.encode("123"))
                .build();

        Movie movie1 = Movie.builder()
                .title("testMovie1")
                .year("2021")
                .plot("helloMovie1")
                .build();

        Movie movie2 = Movie.builder()
                .title("testMovie2")
                .year("2022")
                .plot("helloMovie2")
                .build();

        memberRepository.save(member);
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        movie1.registerMember(member);
        movie2.registerMember(member);
    }

    @AfterEach
    public void removeData() {
        Member member = memberRepository.findByEmail("test123@test123.com").get();
        memberRepository.delete(member);

        Movie movie1 = movieRepository.findByTitle("testMovie1").get();
        Movie movie2 = movieRepository.findByTitle("testMovie2").get();
        movieRepository.delete(movie1);
        movieRepository.delete(movie2);
    }

    // findByMovie
    @Test
    @DisplayName("movieId를 통해 하나의 영화의 세부 정보 가지고 오기")
    public void test2() {
        Movie result = movieService.findByMovie(1L).get();
        assertThat(result.getTitle()).isEqualTo("frozen");
    }

    @Test
    @DisplayName("내가 등록한 영화 목록의 수 가지고 오기")
    @Commit
    public void test_3() throws Exception {
        Member member = memberRepository.findByEmail("test123@test123.com").get();
        long result = movieService.countMovieByMember(member.getId());
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("내가 등록한 영화의 목록 가지고 오기")
    public void test_4() throws Exception {
        Member member = memberRepository.findByEmail("test123@test123.com").get();
        List<Movie> movieList = movieService.findAllByMemberId(member.getId());
        assertThat(movieList.get(0).getTitle()).isEqualTo("testMovie1");
        assertThat(movieList.get(1).getTitle()).isEqualTo("testMovie2");
    }

    @Test
    @DisplayName("다중 조건과 요청 페이지에 따라 원하는 영화 개수를 가지고 오기 ")
    public void test_5() {
        // 이거를 이제 메서드 명 보기 좋게 다듬어서 서비스쪽에서 맵핑해놓자
        Page<Movie> foundMovieList = movieService.findAllByMovieListWithYear("frozen", null, null, 10);
        int result = foundMovieList.getSize();
        assertThat(result).isEqualTo(10);
    }

}