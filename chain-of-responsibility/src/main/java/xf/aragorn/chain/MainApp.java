package xf.aragorn.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xufeng
 * @since 2021/7/28
 */
public class MainApp {
    private static Logger log = LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {
        CharSetFilter cf = new CharSetFilter();
        DemoFilter df = new DemoFilter();
        ApplicationFilterChain chain = new ApplicationFilterChain(new BusinessServiceImpl());
        chain.addFilter(new ApplicationFilterConfig(cf));
        chain.addFilter(new ApplicationFilterConfig(df));

        log.info(">>_start   [request]---------------------------");
        chain.doFilter("request",2);
        log.info("request>>_---------------------------");
        chain.reuse();
        chain.doFilter("condition1",2);
        log.info("condition1>>_---------------------------");
        chain.reuse();
        chain.doFilter("condition2",2);
        log.info("condition2>>_---------------------------");
        chain.reuse();
        chain.doFilter("condition3",2);
        log.info("condition3>>_---------------------------");
        chain.release();
    }
}
