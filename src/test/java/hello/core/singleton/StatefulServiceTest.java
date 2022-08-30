package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);

        //스레드A에서 사용자A가 10000원 주문
        int userAPrice = bean.order("userA",10000);
        //스레드B에서 사용자B가 20000원 주문
        bean1.order("userB",20000);

        System.out.println("price = " + userAPrice);
        //사용자A가 주문한 사이에 다른 유저가 주문했다면, A의 금액이 바뀌게 됨.
        //공유필드가 있어서 생기는 문제.
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}