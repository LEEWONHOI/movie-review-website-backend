package hello.moivereview.web.controller.manager;

import hello.moivereview.domain.Authority;
import hello.moivereview.domain.City;
import hello.moivereview.domain.Gender;
import hello.moivereview.domain.Member;
import hello.moivereview.service.MemberService;
import hello.moivereview.web.controller.manager.dto.ManagerSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ManagerSignupController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup/manager")
    public String signUpGet(Model model) {
        model.addAttribute("site", "manager");
        model.addAttribute("cityList", City.values());
        model.addAttribute("genderList", Gender.values());
        return "manager/signup";
    }

    @PostMapping(value = "/signup/manager", consumes = {"application/x-www-form-urlencoded;charset=UTF-8", MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String signUpPost(ManagerSignUpDto form, Model model) {
        final Member manager = Member.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .age(form.getAge())
                .gender(form.getGender())
                .city(form.getCity())
                .enabled(true)
                .build();

        Member savedManager = memberService.save(manager);
        memberService.addAuthority(savedManager.getId(), Authority.ROLE_MANAGER);
        model.addAttribute("site", "manager");
        return "loginForm.html";


    }

}
