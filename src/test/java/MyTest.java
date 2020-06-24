import com.jiangzhe.pojo.Books;
import com.jiangzhe.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("BookServiceImpl", BookService.class);

        for (Books books : bookServiceImpl.queryAllBooks()) {
            System.out.println(books);
        }
    }
}
