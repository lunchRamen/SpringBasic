package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 조회 안되는 경우")
    void findBeanByNameX(){
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxxxx", MemberService.class));
    }
}
