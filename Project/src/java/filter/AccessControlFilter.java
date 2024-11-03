package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AccessControlFilter implements Filter {

    private Set<String> allowedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls = new HashSet<>();

        // Đọc file allowed_access.txt từ WEB-INF bằng cách sử dụng ServletContext
        try (InputStream input = filterConfig.getServletContext().getResourceAsStream("/WEB-INF/urlAllowedAccess.txt")) {
            if (input == null) {
                throw new ServletException("Da qua Pepsi oi");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    allowedUrls.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new ServletException("Could not load allowed access file", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestUri = httpRequest.getRequestURI();

        // Kiểm tra nếu URL được phép truy cập
        if (!allowedUrls.contains(requestUri)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Truy cập bị từ chối");
            return;
        }

        // Cho phép request tiếp tục nếu URL hợp lệ
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup nếu cần thiết
    }
    
}
