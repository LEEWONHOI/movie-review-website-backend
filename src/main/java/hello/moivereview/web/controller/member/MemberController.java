package hello.moivereview.web.controller.member;

import hello.moivereview.constant.FrontInfoConst;
import hello.moivereview.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/main")
    public void mainPage(@AuthenticationPrincipal Member member, HttpServletResponse response) {

        // TODO Json data 로 유저 이름 보내주기 / 혹은 jwt 토큰 적용해보기

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(FrontInfoConst.FrontURL);
        } catch (IOException e) {
            // 에러를 어디서 처리할지 고민해보기
            e.printStackTrace();
        }
    }

    @GetMapping("/about")
    public String myInfo(@AuthenticationPrincipal Member member) {

        return "";

    }


}
