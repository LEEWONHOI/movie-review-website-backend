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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SessionManager sessionManager;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal Member member, HttpServletRequest request, HttpServletResponse response) {
        sessionManager.createSession(member.getEmail(), response);

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(UrlConst.FRONT_URL);
        } catch (IOException e) {
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
        return new Gson().toJson(memberInfoDto);
    }


}
