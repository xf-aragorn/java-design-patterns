package xf.aragorn.chain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xufeng
 * @since 2021/7/28
 */
@Slf4j
public class BusinessServiceImpl implements  BusinessService{
    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doSomeThing(String request, Integer response) {
        log.info("do something. request: {}",request);
    }
}
