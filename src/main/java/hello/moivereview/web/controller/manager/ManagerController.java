package hello.moivereview.web.controller.manager;

import hello.moivereview.domain.Member;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Type;
import hello.moivereview.service.MemberService;
import hello.moivereview.service.MovieService;
import hello.moivereview.web.controller.manager.dto.AddMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final MemberService memberService;
    private final MovieService movieService;

    // TODO GetMapping 으로 변경?
    @RequestMapping({"", "/"})
    public String index(@AuthenticationPrincipal Member member, Model model) {

        long movieReviewCount = movieService.countMovieByMember(member.getId());

        model.addAttribute("movieReviewCount", movieReviewCount);
        // TODO 달린 댓글 리스트? 카운트
        model.addAttribute("userReviewCount", "0");

        return "manager/index";
    }

    @GetMapping("/mymovies")
    public String movieList(@AuthenticationPrincipal Member member, Model model) {
        model.addAttribute("menu", "test");
        List<Movie> movieList = movieService.findAllByMemberId(member.getId());
        model.addAttribute("movieList", movieList);
        return "/manager/mymovies";
    }

    @GetMapping("/usercomment")
    public String userCommentList(@AuthenticationPrincipal Member member, Model model) {
        // TODO 추후 리뷰 기능 추가시 작성
        return "/manager/userComments";
    }

    @GetMapping("/addMovieReview")
    public String addMovieReviewGet(Model model) {
        model.addAttribute("typeList", Type.values());

        return "manager/addMovieReview";
    }

    @Transactional
    @PostMapping("/addMovieReview")
    public String addMovieReviewPost(@AuthenticationPrincipal Member member, @ModelAttribute AddMovieDto addMovieDto) {

        // Dto 를 Movie로 변경
        Movie movie = Movie.builder()
                .title(addMovieDto.getTitle())
                .year(addMovieDto.getYear())
                .type(addMovieDto.getType())
                .genre(addMovieDto.getGenre())
                .poster(addMovieDto.getPoster())
                .plot(addMovieDto.getPlot())
                .rated(addMovieDto.getRated())
                .released(addMovieDto.getReleased())
                .runtime(addMovieDto.getRuntime())
                .director(addMovieDto.getDirector())
                .writer(addMovieDto.getWriter())
                .actors(addMovieDto.getActors())
                .language(addMovieDto.getLanguage())
                .country(addMovieDto.getCountry())
                .awards(addMovieDto.getAwards())
                .metascore(addMovieDto.getMetascore())
                .imdbRating(addMovieDto.getImdbRating())
                .imdbVotes(addMovieDto.getImdbVotes())
                .DVD(addMovieDto.getDVD())
                .boxOffice(addMovieDto.getBoxOffice())
                .production(addMovieDto.getProduction())
                .website(addMovieDto.getWebsite())
                .build();

        // movie 작성자 항목도 추가
        movie.registerMember(member);

        // 받아온 데이터를 DB에 저장
        movieService.save(movie);

        return "redirect:/manager";
    }


}
