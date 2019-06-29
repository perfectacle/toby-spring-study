package example.domain.inaction;

import java.util.List;

public class QAImpl {
    public String asdf(Apple apple) {
        return apple.toString();
    }

    public static void prettyPrintApple(List<Apple> appleList, QAImpl qa) {
        for (final Apple apple : appleList) {
            final String output = qa.asdf(apple);
            System.out.println(output);
        }
    }
}
