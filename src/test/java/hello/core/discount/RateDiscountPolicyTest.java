package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야함")
    void vip_o(){
        //given
        //opt + cmd + v = 변수 자동 생성
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용 안됨")
    void vip_x(){
        //given
        //opt + cmd + v = 변수 자동 생성
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then -> static import = 변수 코드를 줄여준다.
        assertThat(discount).isEqualTo(0);
    }
}