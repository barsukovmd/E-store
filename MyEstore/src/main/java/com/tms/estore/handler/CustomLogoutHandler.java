package com.tms.estore.handler;

import com.tms.estore.security.CustomUserDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.tms.estore.utils.Constants.ControllerMappingPath.ESTORE;

@Component
@Slf4j
public class CustomLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        CustomUserDetail principal = (CustomUserDetail) authentication.getPrincipal();
        log.info("The user with a login " + principal.getUser().getLogin() + " logged out.");
        response.sendRedirect(ESTORE);
    }
}
