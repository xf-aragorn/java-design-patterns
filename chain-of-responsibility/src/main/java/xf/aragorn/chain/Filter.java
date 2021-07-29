package xf.aragorn.chain;

/**
 * @author xufeng
 * @since 2021/7/28
 */

public interface Filter {


    public default void init(FilterConfig filterConfig) {
    }


    public void doFilter(String request, Integer response,
                         FilterChain chain);


    public default void destroy() {
    }
}

