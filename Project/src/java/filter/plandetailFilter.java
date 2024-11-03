package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class plandetailFilter implements Filter {

    @Override
    public void destroy() {
        // Cleanup if necessary
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getSession().getAttribute("account") == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bạn cần đăng nhập để truy cập");
            return;
        }


        chain.doFilter(request, response);
    }
}
