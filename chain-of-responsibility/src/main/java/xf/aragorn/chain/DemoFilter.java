package xf.aragorn.chain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author xufeng
 * @since 2021/7/28
 */
@Slf4j
public class DemoFilter implements Filter {
    org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) {
//        filterConfig
        log.info("{} 初始化了", getClass());
    }

    @Override
    public void doFilter(String request, Integer response, FilterChain chain) {
        if(request.contains(FilterConditionEnum.condition3.toString()) || request.contains(FilterConditionEnum.condition2.toString())) {
            log.info("request: 【{}】 now do demo filter: {}", request,getClass());
        }
        else{
            log.info("request: 【{}】 now do 【NOT】exec demo filter: {}", request,getClass());
        }
        chain.doFilter(request, response);
        log.info("now end demo filter");
    }
}
