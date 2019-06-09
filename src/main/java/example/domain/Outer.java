package example.domain;

public class Outer {
    public void test() {
        Outer outer = new Outer() {
            @Override
            public void test2() {
                System.out.println("bbb");
            }
        };

        // 여기서의 Outer는 public class Outer와는 전혀 다른 클래스이다.
        outer.test2();
    }

    public void test2() {
        System.out.println("zzz");


    }

    Outer outer2 = new Outer() {

    };
}

class Test {
    public static void main(String args[]) {
        Outer outer = new Outer();
        outer.test(); // "bbb"
        outer.test2(); // "zzz"
    }
}