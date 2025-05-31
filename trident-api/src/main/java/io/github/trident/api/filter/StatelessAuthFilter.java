package io.github.trident.api.filter;

import com.google.gson.JsonObject;
import io.github.trident.api.Constants;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class StatelessAuthFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    private void wrapCorsResponse(final HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
    }

    private void unionFailResponse(final ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        wrapCorsResponse(httpServletResponse);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JsonObject response = new JsonObject();
        response.addProperty(Constants.RESULT_CODE, Constants.TOKEN_IS_ERROR);
        httpServletResponse.getWriter().println(response);
    }

    private String getTokenValue(final HttpServletRequest request) {
        String tokenName = Constants.X_ACCESS_TOKEN;
        String tokenValue = request.getHeader(tokenName);
        if (StringUtils.isNoneBlank(tokenValue)) {
            return tokenValue;
        }
        tokenValue = request.getParameter(tokenName);
        if (StringUtils.isNoneBlank(tokenValue)) {
            return tokenValue;
        }
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return null;
        }
        return Arrays.stream(cookies)
                .filter(cookie -> StringUtils.equals(tokenName, cookie.getName()))
                .findAny().orElse(new Cookie(tokenName, "")).getValue();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (StringUtils.equals(HttpMethod.OPTIONS.name(), httpServletRequest.getMethod())) {
            return true;
        }
        String tokenValue = getTokenValue(httpServletRequest);
        if (StringUtils.isBlank(tokenValue)) {
            unionFailResponse(servletResponse);
            return false;
        }
        BearerToken bearerToken = new BearerToken(tokenValue);
        Subject subject = getSubject(servletRequest, servletResponse);
        try {
            subject.login(bearerToken);
        } catch (Exception e) {
            LOGGER.error("token is warning. token : {}.", tokenValue, e);
            unionFailResponse(servletResponse);
            return false;
        }
        return true;
    }
}
