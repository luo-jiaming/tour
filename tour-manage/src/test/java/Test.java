import cn.edu.hlju.tour.core.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenweijie on 2017/5/6.
 */
public class Test {

    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-application.xml");
        UserService userService = context.getBean(UserService.class);
        Long[] ids = {new Long(100), new Long(101)};
        userService.delUser(ids);
    }

}
