package src.java;

import java.nio.file.Path;

public interface Cipher {
    void encrypt(Path path,Integer key);
}
