package hello.moivereview.web.controller.member;

import hello.moivereview.domain.Authority;
import hello.moivereview.domain.City;
import hello.moivereview.domain.Gender;
import hello.moivereview.domain.Member;
import hello.moivereview.service.MemberService;
import hello.moivereview.web.controller.member.dto.MemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberSignupController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup/member")
    public String signupGet(Model model) {
        // TODO 이건 왜 전달하는지 참고..
        model.addAttribute("site", "member");
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("cityList", City.values());
        return "member/signup";
    }

    @PostMapping(value = "/signup/member", consumes = {"application/x-www-form-urlencoded;charset=UTF-8", MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String signupPost(MemberSignDto form, Model model) {
        final Member member = Member.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .age(form.getAge())
                .gender(form.getGender())
                .city(form.getCity())
                .enabled(true)
                .build();

        Member savedMember = memberService.save(member);
        memberService.addAuthority(savedMember.getId(), Authority.ROLE_MEMBER);
        model.addAttribute("site", "member");
        return "loginForm.html";

    }
}
