package hello.moivereview.web.controller.about;

import com.google.gson.Gson;
import hello.moivereview.config.SessionManager;
import hello.moivereview.domain.Member;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Review;
import hello.moivereview.service.MemberService;
import hello.moivereview.web.controller.member.dto.MemberInfoDto;
import hello.moivereview.web.controller.movie.dto.MyReviewMovieDto;
import hello.moivereview.web.controller.movie.dto.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AboutController {

    private final SessionManager sessionManager;
    private final MemberService memberService;

    @PostMapping("/about")
    public String aboutPage(@RequestBody String inputData) {

        String sessionId = inputData;

        char lastChar = sessionId.charAt(sessionId.length() - 1);
        if (lastChar == '=') {
            sessionId = sessionId.substring(0, sessionId.length() - 1);
        }

        String memberEmail = (String) sessionManager.findBySession(sessionId);
        Member findMember = memberService.findMemberByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        List<Search> myReviewMovieDtoList = new ArrayList<>();

        HashMap<String, Review> hashMap = new HashMap<>();

        for (int i = 0; i < findMember.getReviewList().size(); i++) {
            hashMap.put(findMember.getReviewList().get(i).getMovie().getTitle(), findMember.getReviewList().get(i));
        }

        ArrayList<Review> searchList = new ArrayList<>(hashMap.values());

        for (Review review : searchList) {
            Movie reviewedMovie = review.getMovie();

            Search dto = Search.builder()
                    .movieId(reviewedMovie.getId())
                    .Title(reviewedMovie.getTitle())
                    .Year(reviewedMovie.getYear())
                    .Type(reviewedMovie.getType())
                    .Poster(reviewedMovie.getPoster())
                    .build();
            myReviewMovieDtoList.add(dto);
        }

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .name(findMember.getName())
                .email(findMember.getEmail())
                .Search(myReviewMovieDtoList)
                .build();

        return new Gson().toJson(memberInfoDto);
    }

}
