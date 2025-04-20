package kr.hhplus.be.server.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter가 생성 됩니다.");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("==========First 필터 시작!==========");
        // ServletRequest 보다 HttpServletRequest가 더 많은 기능을 제공.
        // HTTP 관련 요청이 주를 이루기 때문에 HttpServletRequest로 변환
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        String ip = servletRequest.getRemoteAddr();
        String method = req.getMethod();
        LocalDateTime now = LocalDateTime.now();

        log.info("[FILTER LOG] {} {} - {} 요청 from IP: {}", now, method, uri, ip);

        filterChain.doFilter(servletRequest, servletResponse);
        log.info("==========First 필터 종료!==========");
    }

    @Override
    public void destroy() {
        log.info("FirstFilter가 사라집니다.");
        Filter.super.destroy();
    }
}
