package kr.hhplus.be.server.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    // 요청 시작 시간 기록
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime); // 요청 속성으로 저장
        return true; // 계속 진행
    }

    // 요청 처리 완료 후 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
//        long endTime = System.currentTimeMillis();
        long duration = System.currentTimeMillis() - startTime;

        String method = request.getMethod();
        String uri = request.getRequestURI();
//        long duration = endTime - startTime;
        // 느린 요청이면 경고 로그로 출력
        if (duration >= 1000) {
            log.warn("[SLOW REQUEST] {} {} took {}ms", method, uri, duration);
        } else {
            log.info("[INTERCEPTOR LOG] {} {} took {}ms", method, uri, duration);
        }
    }
}
