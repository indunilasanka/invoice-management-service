package com.demo.ims.interceptor;

import com.demo.ims.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationIdInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String correlationId = Optional.ofNullable(request.getHeader(Constant.RequestHeader.CORRELATION_ID))
                .orElse(UUID.randomUUID().toString());
        final String clientIp = request.getHeader(Constant.RequestHeader.CLIENT_IP);
        String requestPath = request.getServletPath();

        response.setHeader(Constant.RequestHeader.CORRELATION_ID, correlationId);

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Syy-Correlation-Id");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        //credentials: true, origin: true,

        MDC.put(Constant.CORRELATION_ID_LOG, correlationId); // save in MDC for logging purposes(logback is configured to use correlation id)

        LOGGER.info("New request - correlationId: [{}], clientIP: [{}], path: [{}]", correlationId, clientIp, requestPath);
        return true;
    }


}
