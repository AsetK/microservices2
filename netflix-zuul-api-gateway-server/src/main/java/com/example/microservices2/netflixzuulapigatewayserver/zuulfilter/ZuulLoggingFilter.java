package com.example.microservices2.netflixzuulapigatewayserver.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; //"pre", "post", "error"
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        System.out.println("ZUUL-LOGGING-FILTER:");
        System.out.println(request.toString() + " : " + request.getRequestURL());
        return null;
    }
}
