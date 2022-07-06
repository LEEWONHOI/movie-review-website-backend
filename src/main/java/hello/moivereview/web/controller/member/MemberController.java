package hello.moivereview.web.controller.member;

import com.google.gson.Gson;
import hello.moivereview.config.SessionManager;
import hello.moivereview.constant.UrlConst;
import hello.moivereview.domain.Member;
import hello.moivereview.web.controller.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SessionManager sessionManager;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal Member member, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("member 페이지 접근");

        sessionManager.createSession(member.getEmail(), response);

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("http://localhost:1234/#/");
        } catch (IOException e) {
            // 에러를 어디서 처리할지 고민해보기
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(value = UrlConst.FRONT_URL)
    public String myInfo(@AuthenticationPrincipal Member member) {
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();

        System.out.println("about 페이지 접근");
        System.out.println("memberInfoDto.getName() = " + memberInfoDto.getName());
        System.out.println("memberInfoDto.getEmail() = " + memberInfoDto.getEmail());

        return new Gson().toJson(memberInfoDto);
    }


}
