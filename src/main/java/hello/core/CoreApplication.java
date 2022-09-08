package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	//이게 ComponentScan을 포함한 어노테이션.
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
	//개꿀팁 = cmd+o = 프로젝트에 있는 클래스, 라이브러리 등등 검색가능
}
