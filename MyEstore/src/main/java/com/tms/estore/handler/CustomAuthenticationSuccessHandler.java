package com.tms.estore.handler;

import com.tms.estore.security.CustomUserDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import static com.tms.estore.utils.Constants.CONVERSATION;
import static com.tms.estore.utils.Constants.ControllerMappingPath.ESTORE;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String userUuid = UUID.randomUUID().toString();
        MDC.put(CONVERSATION, userUuid);
        CustomUserDetail principal = (CustomUserDetail) authentication.getPrincipal();
        log.info("The user with a login " + principal.getUser().getLogin() + " is logged in, has been assigned a UUID [" + userUuid + "]");
        response.sendRedirect(ESTORE);
    }
}
