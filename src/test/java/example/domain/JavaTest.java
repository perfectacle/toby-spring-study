package example.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaTest {
    @Test
    void name() {
        final Stream<Integer> integerStream = Stream.of(1, 2, 3);
        final IntStream intStream = integerStream.mapToInt(i -> i);

        List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).stream()
                .map(i -> {
                    System.out.println(i);
                    return i;
                })
                .filter(i -> {
            System.out.println("i%2: " + i);
            return i%2 == 0;
        }).filter(i -> {
            System.out.println("i%3: " + i);
            return i%3 == 0;
        }).findFirst()
                .ifPresent(i -> System.out.println("first: " + i));
    }
}

// i%2: 2
// i%3: 2
// i%2: 3
// i%2: 4
// i%3: 4
// i%2: 5
// i%2: 6
// i%3: 6