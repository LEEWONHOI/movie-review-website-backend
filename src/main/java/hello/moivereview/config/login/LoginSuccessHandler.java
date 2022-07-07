package hello.moivereview.config.login;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException
    {
        log.info("로그인 성공 Handler 작동");
        handle(request, response, requestCache.getRequest(request, response), authentication);
        clearAuthenticationAttributes(request);
    }

    protected String determineTargetUrl(
            final HttpServletRequest request,
            SavedRequest savedRequest,
            final Authentication authentication)
    {
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            if (redirectUrl != null && !redirectUrl.startsWith("/login")) {
                return savedRequest.getRedirectUrl();
            }
        }
        if (request.getParameter("site").equals("member")) {
            return "/main";
        } else if (request.getParameter("site").equals("manager")) {
            return "/manager";
        }
        return "/";
    }

    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            SavedRequest savedRequest,
            Authentication authentication
    ) throws IOException {
        String targetUrl = determineTargetUrl(request, savedRequest, authentication);
        if (response.isCommitted()) {
            logger.debug("Response 가 이미 커밋되었습니다. redirect이 불가능함을 알림 :" + targetUrl);
            return;
        }
        // 받아온 주소값으로 리다이렉션
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }



    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication) throws IOException, ServletException
    {

    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }





}
