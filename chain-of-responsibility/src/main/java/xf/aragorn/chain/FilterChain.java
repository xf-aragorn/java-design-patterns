package xf.aragorn.chain;

/**
 * @author xufeng
 * @since 2021/7/28
 */
public interface FilterChain {
    public void doFilter(String request, Integer response);
}
