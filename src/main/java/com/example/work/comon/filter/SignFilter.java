package com.example.work.comon.filter;

import com.example.work.anotation.filter.HttpHelper;
import com.example.work.anotation.filter.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SignFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) arg0;
        if (!"POST".equals(req.getMethod())) {
            //log.info("非POST请求无需处理");
            arg2.doFilter(arg0, (HttpServletResponse) arg1);
        }
        ServletRequest requestWrapper = new ParameterRequestWrapper(req);
        String body = HttpHelper.getBodyString(req);
        //System.out.println(body);
        log.info(body);
        // 自己拦截的逻辑
        arg2.doFilter(requestWrapper, (HttpServletResponse) arg1);

    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
