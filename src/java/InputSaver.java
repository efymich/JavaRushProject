package src.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

public class InputSaver {
    private Path path;
    private Integer key;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public InputSaver welcome(BufferedReader reader) throws IOException {

        System.out.println("Введите путь к файлу (абсолютный): ");
        this.setPath(Path.of(reader.readLine()));

        System.out.println("Введите ключ шифрования:");
        this.setKey(Integer.parseInt(reader.readLine()));

        return this;
    }
}
