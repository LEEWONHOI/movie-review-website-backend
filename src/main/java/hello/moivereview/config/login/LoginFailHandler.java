package hello.moivereview.config.login;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException
    {
        log.info("로그인 실패 Handler 작동");
        handle(request, response);
        clearAuthenticationAttributes(request);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String targetUrl = determineTargetUrl(request);
        if (response.isCommitted()) {
            logger.debug("Response 가 이미 커밋되었습니다. redirect이 불가능함을 알림 :" + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    protected String determineTargetUrl(final HttpServletRequest request) {
        if (request.getParameter("site").equals("manager")) {
            return "/login?site=manager&error=true";
        } else if (request.getParameter("site").equals("member")) {
            return "/login?site=member&error=true";
        }
        return "/";

    }


}
