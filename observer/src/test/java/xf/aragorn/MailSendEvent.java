package xf.aragorn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 继承了ApplicationContextEvent，就是个容器事件
 */
public class MailSendEvent extends ApplicationContextEvent {
    private String to;  //目的地

    public MailSendEvent(ApplicationContext source, String to) {
        super(source);
        this.to = to;
    }

    public String getTo(){
        return this.to;
    }
}
