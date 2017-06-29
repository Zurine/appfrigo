package es.smt.appfrigo.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Created by Galder on 23/06/2017.
 */
public class GZIPFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.indexOf("gzip") != -1) {
                GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                chain.doFilter(req, wrappedResponse);
                wrappedResponse.finishResponse();
                return;
            }
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {
        // noop
    }

    public void destroy() {
        // noop
    }
}
