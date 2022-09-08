package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototype {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
//        PrototypeBean bean = ac.getBean(PrototypeBean.class);
//        bean.addCount();
//        assertThat(bean.getCount()).isEqualTo(1);
//
//        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
//        bean1.addCount();
//        //opt + enter = static import
//        assertThat(bean1.getCount()).isEqualTo(1);
        ClientBean bean = ac.getBean(ClientBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);

        int count1 = bean.logic();
        assertThat(count1).isEqualTo(1);

        int count2 = bean1.logic();
        assertThat(count2).isEqualTo(1);
    }


    static class ClientBean{
//        private final PrototypeBean prototypeBean;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;

        int logic(){
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count =0;

        void addCount(){
            count++;
        }
        int getCount(){
            return count;
        }

        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        void destroy(){
            System.out.println("destroy");
        }
    }
}
