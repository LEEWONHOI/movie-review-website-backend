package hello.moivereview.annotation;

import hello.moivereview.aop.RetryAspect;
import hello.moivereview.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({RetryAspect.class})
public class RetryAnnotationTest {

    @Autowired
    MovieService movieService;

    @Test
    void test() {
        System.out.println("실행 중입니다.");
        movieService.findByMovie(Long.MAX_VALUE);
    }

}
