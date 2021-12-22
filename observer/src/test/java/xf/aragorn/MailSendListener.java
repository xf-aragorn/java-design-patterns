package xf.aragorn;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xufeng
 * @since 2021/12/20
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent mailSendEvent) {
        MailSendEvent event = mailSendEvent;
        System.out.println("Listener： MailSender向"+ event.getTo()+ "发送了邮件");
    }
}
