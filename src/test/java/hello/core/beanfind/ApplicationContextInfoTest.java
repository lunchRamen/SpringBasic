package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    /*
    * getBeanDefinitionNames = 설정클래스에 등록된 빈들의 이름들을 가져오는 메서드
    * bean은 객체형태라서 타입이 object임.
    * 근데 이러면 컨테이너를 만들면서 생기는 빈들까지 출력이 된다.
    * 만약 내가 만든 빈만 확인하고 싶다면
    * Names를 통해서 이름들을 받아온 다음,
    * getBeanDefinition으로 한번 더 호출하고
    * 해당 빈의 역할이 ROLE_APPLICATION(앱의 역할을 하는지)인지 확인해서 출력해주면 된다.
    * 거꾸로 컨테이너에서 쓰는 빈은 INFRASTRUCTURE로 확인하면 된다.
    * */
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "  object"+ bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + "  object"+ bean);
            }
        }
    }
}
