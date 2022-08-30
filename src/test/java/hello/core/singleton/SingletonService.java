package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    //자기 자신을 객체로 불변하게 가지려고 static final로 상수화시킴.

    public static SingletonService getInstance(){
        return instance;
    }
    //이렇게 클래스 객체에를 가져오는 함수를 만듬.

    //근데, 해당 클래스 외부에서 객체를 새롭게 생성하지 못하도록 만들어야함
    //-> private constructor
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출 완료");
    }
}
