//package com.example.work.comon.interceptor;
//
//import com.example.work.comon.aop.Sign;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
///*
// *
// * 拦截器通常通过动态代理的方式来执行 拦截器则可以通过IoC容器来管理，因此可以通过注入等方式来获取其他Bean的实例，因此使用会更方便。
// * 因为preHandle和postHandle是两个方法，所以我们这里不得不设置一个共享变量start来存储开始值，
// * 但是这样就会存在线程安全问题。当然，我们可以通过其他方法来解决，比如通过ThreadLocal就可以很好的解决这个问题，有兴趣的同学可以自己实现。
// *
// *
// * 拦截器中用于在某个方法或字段被访问之前，进行拦截然后，在之前或之后加入某些操作。
// * 比如日志，安全等。一般拦截器方法都是通过动态代理的方式实现。可以通过它来进行权限验证，或者判断用户是否登陆，或者是像12306 判断当前时间是否是购票时间。
// * */
//@Component
//@Slf4j
//public class TestInterceptor extends HandlerInterceptorAdapter {
//    private static String getRequestUri(HttpServletRequest request) {
//        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
//        if (uri == null) {
//            uri = request.getRequestURI();
//        }
//
//        return uri;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String params = (String) request.getParameterMap().entrySet().stream().map((entry) -> {
//            return (String) entry.getKey() + ":" + Arrays.toString((Object[]) entry.getValue());
//        }).collect(Collectors.joining(", "));
//        List<String> values = Collections.list(request.getHeaderNames());
//
//        String headers = values.size() > 0 ? "masked" : "";
//        headers = (String) values.stream().map((name) -> {
//            return name + ":" + Collections.list(request.getHeaders(name));
//        }).collect(Collectors.joining(", "));
//        //BufferedServletRequestWrapper bufferedServletRequestWrapper = new BufferedServletRequestWrapper(request);
//
//        String query = StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString();
//        String dispatchType = !request.getDispatcherType().equals(DispatcherType.REQUEST) ? "\"" + request.getDispatcherType().name() + "\" dispatch for " : "";
//        String message = dispatchType + request.getMethod() + " \"" + getRequestUri(request) + query + "\",,parameters={" + params + "}";//headers={" + bufferedServletRequestWrapper.getBody() + "}
//        //log.info("{}" , message);
//        /*log.info("进入拦截器");
//        log.info(request.toString());
//        log.info(handler.toString());*/
//        if (!(handler instanceof HandlerMethod)) {//|| request.getRequestURI().equals("/" + jwtProperties.getAuthPath())
//            return true;
//        }
//        final HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Object controller = handlerMethod.getBean();
//        Class clazz = controller.getClass();
//        if(!handlerMethod.hasMethodAnnotation(Sign.class)){
//            return true;
//        }
//        /*log.info("该方法请求参数已加密");
//        ServletRequest requestWrapper = new ParameterRequestWrapper(request);
//        String body = HttpHelper.getBodyString(requestWrapper);
//        JSON json = JSON.parseObject(body);*/
//        /**
//         * 不存在需要认证的注解，直接返回
//         */
//       /* if(clazz.getAnnotation(Authroization.class) == null && !handlerMethod.hasMethodAnnotation(Authroization.class)){
//            log.debug("不存在需要认证的请求--" + request.getRequestURI());
//            return true;
//        }*/
//
//        /**
//         * 请求头token格式：
//         * Authorization
//         * Bearer_eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJsZWlzM3UiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUxMTk1NjM3MywiaWF0IjoxNTExMzUxNTczfQ.smb180s3y7KxSCPF6Yw3Qg8cEpMSlrh4tvob6cMgiwYqgcVgzNAiFP4-N1gCYNpPI6RUWncO57RIWaU3IcY_Wg
//         */
//        //final String requestHeader = request.getHeader(jwtProperties.getHeader());
//        String authToken = null;
//        /*if (requestHeader != null && requestHeader.startsWith("Bearer_")) {
//            //去掉 "Bearer_"
//            authToken = requestHeader.substring(7);
//
//            //验证token是否过期,包含了验证jwt是否正确
//            try {
//                //判断是否是失效token
//                if(redisUtils.exists(INVALID_TOKEN.concat(authToken))){
//                    renderJson(response, new ResultVO(ErrorResultEnums.LOGIN_EXPIRE.getCode(), ErrorResultEnums.LOGIN_EXPIRE.getMsg()));
//                    return false;
//                }
//                jwtTokenUtil.isTokenExpired(authToken);
//            } catch (JwtException e) {
//                //有异常就是token解析失败
//                renderJson(response, new ResultVO(ErrorResultEnums.LOGIN_EXPIRE.getCode(), ErrorResultEnums.LOGIN_EXPIRE.getMsg()));
//                return false;
//            }
//        } else {
//            //header没有带Bearer字段
//            renderJson(response, new ResultVO(ErrorResultEnums.PARAMS_ERROR.getCode(), ErrorResultEnums.PARAMS_ERROR.getMsg()));
//            return false;
//        }*/
//        return true;
//    }
//
//    private void renderJson(HttpServletResponse response, Object jsonObject) {
//        try {
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter writer = response.getWriter();
//            // writer.write(JSON.toJSONString(jsonObject));
//        } catch (IOException e) {
////            throw new GunsException(GunsExceptionEnum.WRITE_ERROR);
//        }
//    }
//}
