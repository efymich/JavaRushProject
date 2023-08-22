package src.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CeasarDecoder implements Decoder{

    @Override
    public void decode(Path path,Integer key) {
        File file = new File(path.toString());

        try (FileReader reader = new FileReader(file);
             FileWriter writer = new FileWriter("D:\\test_decrypt.txt")) {
            char[] encChar = new char[(int)file.length()];
            reader.read(encChar);

            for (char i : encChar) {
                i -= key;
                writer.write(i);
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
