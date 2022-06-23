package hello.moivereview.web.controller;

import hello.moivereview.domain.Authority;
import hello.moivereview.domain.Member;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@Controller
public class HomeController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal Member member, Model model) {
        if (member != null) {
            model.addAttribute("name", member.getName());
            model.addAttribute("MEMBER_AUTHORITY", Authority.MEMBER_AUTHORITY);
            model.addAttribute("MANAGER_AUTHORITY", Authority.MANAGER_AUTHORITY);
            model.addAttribute("authorities", member.getAuthorities());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(
            @AuthenticationPrincipal Member member,
            @RequestParam(value = "site", required = false) String site,
            @RequestParam(value = "error", defaultValue = "false") Boolean error,
            HttpServletRequest request,
            Model model
    ) {
        if (member != null && member.isEnabled()) {
            if (member.getAuthorities().contains(Authority.MANAGER_AUTHORITY)) {
                return "redirect:/manager";
            } else if (member.getAuthorities().contains(Authority.MEMBER_AUTHORITY)) {
                return "redirect:/";
            }
        }

        if (site == null) {
            SavedRequest savedRequest = requestCache.getRequest(request, null);
            if (savedRequest != null) {
                site = estimateSite(savedRequest.getRedirectUrl());
            }
        }
        model.addAttribute("error", error);
        model.addAttribute("site", site);

        return "loginForm";
    }

    private String estimateSite(String referer) {
        if (referer == null) {
            return "/";
        }
        try {
            URL url = new URL(referer);
            String path = url.getPath();
            if (path != null) {
                if (path.startsWith("/manager")) return "manager";
                if (path.startsWith("/member")) return "member";
            }
            String query = url.getQuery();
            if (query != null) {
                if (path.startsWith("/site=manager")) return "manager";
                if (path.startsWith("/site=member")) return "member";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "study.html";
    }

    @GetMapping("/signup")
    public String singUp(
            @RequestParam String site,
            HttpServletRequest request
    ) {
        if (site == null) {
            // referer 를 통해 전 사이트 정보를 가져온다
            site = estimateSite(request.getParameter("referer"));
        }
        return "redirect:/" + site + "/signup";
    }

    // TODO 프론트쪽에서 할지 체크
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/accessDenied";
    }

}
