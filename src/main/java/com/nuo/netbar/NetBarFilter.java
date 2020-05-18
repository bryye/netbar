package com.nuo.netbar;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//过滤器，处理跨域请求
@Configuration
@Component
@WebFilter(urlPatterns = "/*",filterName = "NetBarFilter")
public class NetBarFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse rep = (HttpServletResponse) response;
        rep.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域访问报错
        rep.setHeader("Access-Control-Allow-Credentials","true");
        rep.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        rep.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
        rep.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
        rep.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        rep.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");

        response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}