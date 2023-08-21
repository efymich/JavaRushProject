package src.java;

import java.nio.file.Path;

public class CeasarCipher implements Cipher{
    @Override
    public void encrypt(Path path, Integer key) {
        System.out.println("Cipher");
    }
}
