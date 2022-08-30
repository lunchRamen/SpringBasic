package hello.core.singleton;

public class StatefulService {
    //cmd + shift + t = 해당 클래스 테스트케이스 만들 수 있음.
//    private int price; //상태를 유지하는 필드

    public int order(String name,int price){
//        System.out.println("name = " + name + " price = "+price);
//        this.price=price;
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}
