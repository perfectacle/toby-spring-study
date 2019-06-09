package example.domain;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class Calculator {
    private BufferedReaderTemplate<Integer> bufferedReaderTemplate;

    public Calculator(final BufferedReaderTemplate<Integer> bufferedReaderTemplate) {
        this.bufferedReaderTemplate = bufferedReaderTemplate;
    }

    public int calcSumOfPlus(final String path) throws IOException {
        return bufferedReaderTemplate.calcSum(path, (sum, line) -> sum + Integer.valueOf(line), 0);
    }

    public int calcSumOfMultiply(final String path) throws IOException {
        return bufferedReaderTemplate.calcSum(path, (sum, line) -> sum * Integer.valueOf(line), 1);
    }
}
