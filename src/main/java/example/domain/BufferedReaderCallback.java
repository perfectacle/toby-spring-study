package example.domain;

import java.io.IOException;

public interface BufferedReaderCallback<T> {
    T doSomethingWithReader(T sum, final String line) throws IOException;
}
