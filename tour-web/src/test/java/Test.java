import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Sole on 2017/3/31.
 */
public class Test {

    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-application.xml");
        // 从spring容器中获取mapper代理对象
//        SpotCommentService s = context.getBean(SpotCommentService.class);
//        List<SpotComment> list = s.getAll();
//        for (SpotComment sw : list) {
//            System.out.println(sw.getId());
//        }
//        PageInfo<SpotComment> pageInfo = new PageInfo<>(list);
//        System.out.println("total: " + pageInfo.getTotal());
//        System.out.println("pageNum: " + pageInfo.getPageNum());
//        System.out.println("size: " + pageInfo.getSize());
//        System.out.println("lastpage: " + pageInfo.getLastPage());
//        System.out.println("firstpage: " + pageInfo.getFirstPage());
//        System.out.println("prePage: " + pageInfo.getPrePage());
//        System.out.println("nextPage: " + pageInfo.getNextPage());
    }

}
