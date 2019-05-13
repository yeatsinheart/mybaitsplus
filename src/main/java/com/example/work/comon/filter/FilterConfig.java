package com.example.work.comon.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
请求进入tomcat容器后，但请求进入servlet之前进行预处理的。请求结束返回也是，是在servlet处理完后，返回给前端之前
ServletRequest得到HttpServletRequest,此时你就可以对请求或响应(Request、Response)那就可以对对web服务器管理的所有web资源：
例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。
例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息、字符集统一等一些高级功能。
它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理。
使用Filter的完整流程：
    Filter对用户请求进行预处理，
       接着将请求交给Servlet进行处理并生成响应，
       最后Filter再对服务器响应进行后处理。。
它是随你的web应用启动而启动的，只初始化一次，以后就可以拦截相关请求，只有当你的web应用停止或重新部署的时候才销毁。（每次热部署后，都会销毁）。


* 除了通过 FilterRegistrationBean 来配置以外，还有一种更直接的办法，直接通过注解就可以完成了：
*
这里直接用@WebFilter就可以进行配置，同样，可以设置url匹配模式，过滤器名称等。
* 这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。
* 除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。
* @ServletComponentScan("com.pandy.blog.filters")
* 第二个注解Filter我们并没有指定执行的顺序，但是却在第一个Filter之前执行。
* 这里需要解释一下，
* @WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，是根据Filter类名（注意不是配置的filter的名字）的字母顺序倒序排列，并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。
@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class LogCostFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LogFilter2 Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
* */
//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //registration.setFilter(new SignFilter());
        registration.addUrlPatterns("/*");
        registration.setName("SignFilter check……");//        registration.setOrder(1);
        return registration;
    }

}