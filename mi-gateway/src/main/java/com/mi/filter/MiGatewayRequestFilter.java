package com.mi.filter;

import com.alibaba.fastjson.JSONObject;
import com.mi.config.MiGatewayProperties;
import com.mi.constant.GatewayConstant;
import com.mi.entity.common.MiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

/**
 * @ClassName MiGatewayRequestFilter
 * @Author yangli
 * @Date 2021/8/9 15:47
 * @Description:
 */
@Slf4j
@Component
public class MiGatewayRequestFilter implements GlobalFilter {
    @Resource
    private MiGatewayProperties miGatewayProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();


        // 禁止客户端的访问资源逻辑
        Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }

        // 打印日志
        printLog(exchange);

        byte[] token = Base64Utils.encode((GatewayConstant.TOKEN_VALE).getBytes());
        ServerHttpRequest build = request.mutate().header(GatewayConstant.TOKEN_HEAD, new String(token)).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }


    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = miGatewayProperties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            MiResponse miResponse = new MiResponse().message("该URI不允许外部访问");
            return makeResponse(response, miResponse);
        }
        return null;
    }


    private Mono<Void> makeResponse(ServerHttpResponse response, MiResponse miResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(miResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }


    private void printLog(ServerWebExchange exchange) {
        try {
            URI url = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            LinkedHashSet<URI> uris = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
            URI originUri = null;
            if (uris != null) {
                originUri = uris.stream().findFirst().orElse(null);
            }
            if (url != null && route != null && originUri != null) {
                log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                        originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                        route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
