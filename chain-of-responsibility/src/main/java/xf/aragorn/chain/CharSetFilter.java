package xf.aragorn.chain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author xufeng
 * @since 2021/7/28
 */
@Slf4j
public class CharSetFilter implements Filter {
    org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) {
//        filterConfig
        log.info("{} 初始化了", getClass());
    }

    @Override
    public void doFilter(String request, Integer response, FilterChain chain) {
        if(request.indexOf(FilterConditionEnum.condition1.toString())>=0 || request.indexOf(FilterConditionEnum.condition2.toString())>=0) {
            log.info("request: {} now do exec char filter: {}", request,getClass());
        }
        else{
            log.info("request: {}  now do 【NOT】exec char filter: {}",request, getClass());
        }
        chain.doFilter(request, response);
        log.info("now end char filter");
    }
}
