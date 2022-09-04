package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean((NetworkClient.class));
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean
        NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            //생성자 호출 다음에 setUrl 호출 -> url이 null인채로 connect과 call이 호출된다.
            networkClient.setUrl("http://hello-spring.com");
            return networkClient;
        }
    }
}
