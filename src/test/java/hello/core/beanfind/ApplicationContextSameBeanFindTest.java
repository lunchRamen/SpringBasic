package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("조회시 같은 타입이 2개 이상이면 오류 발생")
    void findBeanByTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("해당 타입으로 지정한 빈이 2개이상이면, 이름도 함께 씁니다")
    void findByName(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 빈 모두 조회")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
