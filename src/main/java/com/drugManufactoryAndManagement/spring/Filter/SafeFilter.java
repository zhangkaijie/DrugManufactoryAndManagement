package com.drugManufactoryAndManagement.spring.Filter;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 */
public class SafeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        for (Map.Entry<String, String[]> entry : (Set<Map.Entry<String, String[]>>)servletRequest.getParameterMap().entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                entry.getValue()[i] = StringEscapeUtils.escapeHtml4(entry.getValue()[i]);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
