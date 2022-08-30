package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순사 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //위의 두 멤버서비스는 다른 객체가 생성된다.

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        //하나의 멤버서비스로 모든 요청을 핸들링해야 효율적으로 동작.
        //객체를 1개만 생성하고, 생성되었으면 해당 객체를 요청에 공유하면 된다.
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonServiceTest(){
        SingletonService.getInstance();
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        //같은 객체를 호출한다.

        Assertions.assertThat(instance1).isSameAs(instance2);
        //same은 == 참조값이 같은건지
        //equal은 = 값이 값은건지
    }

    @Test
    @DisplayName("스프링 컨테이너로 싱글톤 테스트")
    void springContainer(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //따로 싱글톤 코드 없이, 스프링이 알아서 객체를 싱글톤 패턴으로 다룸을 알 수 있음.
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }


}
