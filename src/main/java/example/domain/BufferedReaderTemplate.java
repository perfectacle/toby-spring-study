package example.domain;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class BufferedReaderTemplate<T> {
    public T calcSum(final String path, final BufferedReaderCallback<T> callback, final T init) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            T sum = init;
            String line;
            while((line = br.readLine()) != null) {
                sum = callback.doSomethingWithReader(sum, line);
            }

            return sum;
        }
    }
}
