package src.java;

import java.nio.file.Path;

public interface Decoder {

    void decode(Path path);
    void decode(Path path,Integer key);
}
