package hello.moivereview.web.controller.member;

import hello.moivereview.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    // TODO 추후에 RESTCONTROLLER 로 변경하여 전달

//    @GetMapping("/main")
//    public String mainPage(@AuthenticationPrincipal Member member, Model model) {
//
//        model.addAttribute("id", member.getId());
//        model.addAttribute("name", member.getName());
//
//        return "main";
//    }

    @GetMapping("/about")
    public String myInfo(@AuthenticationPrincipal Member member) {

        return "";

    }


}
