package src.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class InputSaver {
    private Path path;
    private int key;


    public InputSaver(Scanner console) {
        try {
            System.out.println("Введите путь к файлу: ");
            this.setPath(Path.of(console.nextLine()));
            System.out.println("Введите ключ шифрования: ");
            this.setKey(Integer.parseInt(console.nextLine()));

            if (!Files.exists(this.path) || !Files.isRegularFile(this.path)) {
                throw new IOException();
            }
        } catch (IOException ioException) {
            System.out.println("Файл не существует");
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            System.out.println("Ключ в шифре Цезаря должен быть Integer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void saveFile(String text,int mode) {
        try {
            Path newPath;
            String def = this.path.toString();
            if (mode == 1) {
                newPath = Path.of(def.replace(".txt","_encrypted.txt"));
            } else if (mode == 2) {
                newPath = Path.of(def.replace(".txt","_decrypted.txt"));
            } else throw new IllegalArgumentException();

            Files.write(newPath,text.getBytes(StandardCharsets.UTF_8));

            System.out.println("Новый файл расположен по следующему пути " + newPath.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный агрумент для метода " + Object.class.getEnclosingMethod().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
