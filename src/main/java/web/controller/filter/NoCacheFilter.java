package web.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(NoCacheFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("Filter init starts");
        LOG.info("Filter init finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("No-cache filter starts working");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        httpServletResponse.setHeader("Pragma", "no-cache");
        LOG.info("No-cache filter finished work");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOG.info("Filter destruction starts");
        LOG.info("Filter destruction finished");
    }
}
