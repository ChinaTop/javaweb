package com.javaweb.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterFilter implements Filter{
    private FilterConfig filterConfig;//过滤器的配置信息
    private String encoding;


    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
        //根据配置器的过滤信息获取指定参数的值
        this.encoding=this.filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getCharacterEncoding()==null){
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        this.filterConfig=null;
    }
}
