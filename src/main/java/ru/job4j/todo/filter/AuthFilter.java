package ru.job4j.todo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class AuthFilter implements Filter {

    private static final Set<String> PAGES = Set.of(
            "loginPage",
            "login",
            "logout",
            "formAddUser"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (checkUriEnd(uri, PAGES)) {
            filterChain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        filterChain.doFilter(req, res);
    }

    private boolean checkUriEnd(String uri, Set<String> set) {
        return set.stream()
                .anyMatch(uri::endsWith);
    }
}
