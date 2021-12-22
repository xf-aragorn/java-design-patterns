package xf.aragorn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObserverApplication.class  )
public class SpringEventTest {

    @Autowired
    MailSender mailSender;
//    public static void main(String[] args) {
    @Test
    public void testObserver(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.scan("xf/aragorn");

//        MailSender sender  = (MailSender)context.getBean("mailSender");
        mailSender.sendMail("北京");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
