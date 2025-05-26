package io.github.trident.api.config;

import com.google.gson.JsonObject;
import io.github.trident.common.json.GsonJsonProcessor;
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
import io.github.trident.common.constant.Constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @projectName: trident
 * @package: io.github.trident.api.config
 * @className: StatelessAuthFilter
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/1 14:05
 * @version: 1.0
 */
public class StatelessAuthFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (StringUtils.equals(HttpMethod.OPTIONS.name(), httpServletRequest.getMethod())) {
            return true;
        }
        String tokenValue = getTokenValue(httpServletRequest);
        if (StringUtils.isBlank(tokenValue)) {
            LOGGER.error("token is null");
            failResponse(servletResponse);
            return false;
        }
        BearerToken token = new BearerToken(tokenValue);
        Subject subject = getSubject(servletRequest, servletResponse);
        try {
            subject.login(token);
        } catch (Exception ex) {
            LOGGER.error("token is error. token : {}.", tokenValue, ex);
            return false;
        }
        return true;
    }

    private String getTokenValue(final HttpServletRequest httpServletRequest) {
        String tokenName = Constants.AUTHORIZATION;
        String tokenValue = httpServletRequest.getHeader(tokenName);
        if (StringUtils.isNoneBlank(tokenValue)) {
            return tokenValue;
        }
        tokenValue = httpServletRequest.getParameter(tokenName);
        if (StringUtils.isNoneBlank(tokenValue)) {
            return tokenValue;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (Objects.isNull(cookies)) {
            return "";
        }
        return Arrays.stream(cookies).filter(cookie -> StringUtils.equals(tokenName, cookie.getName()))
                .findAny()
                .orElse(new Cookie(tokenName, ""))
                .getValue();
    }

    private void wrapCorsResponse(final HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
    }

    private void failResponse(final ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result_code","auth fail");
        httpResponse.getWriter().println(GsonJsonProcessor.getInstance().toJson(jsonObject));
    }
}
