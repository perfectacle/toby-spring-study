package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Calculator.class, BufferedReaderTemplate.class})
class CalcSumTest {
    @Autowired
    private Calculator calculator;
    private String path;

    @BeforeEach
    void setup() {
        path = getClass().getClassLoader().getResource("numbers.txt").getPath();
    }

    @Test
    void sumOfNumbers() throws IOException {
        int sum = calculator.calcSumOfPlus(path);

        assertEquals(10, sum);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        int sum = calculator.calcSumOfMultiply(path);

        assertEquals(24, sum);
    }
}
