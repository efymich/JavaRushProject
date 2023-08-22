package src.java;

import java.nio.file.Path;

public class BruteDecoder implements Decoder{

    @Override
    public void decode(Path path, Integer key) {
        System.out.println("BruteDecoder");
    }

}
