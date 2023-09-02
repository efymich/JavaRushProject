package src.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class InputSaver {
    //TODO: Убрать лишние строки и сделать логические пробелы
    private Path path;
    private int key;

    private int mode;

    private Path bible;


    public InputSaver(Scanner console, int mode) {
        this.mode = mode;
        try {
            System.out.println("Введите путь к файлу: ");
            this.setPath(Path.of(console.nextLine()));
            if (this.mode == 1 || this.mode == 2) {
                System.out.println("Введите ключ шифрования: ");
                this.setKey(Integer.parseInt(console.nextLine()));
            } else if (this.mode == 4) {
                System.out.println("Введите путь к файлу для анализа: ");
                this.setBible(Path.of(console.nextLine()));
            }

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

    public void setBible(Path bible) {
        this.bible = bible;
    }

    public Path getBible() {
        return bible;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
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

    public void saveFile(String text) {
        //TODO: Вынести бизнес логику из конструктора. Сделать отдельный метод и применять его
        //Возможно нужно сделать static
        try {
            Path newPath;
            String def = this.path.toString();
            if (mode == 1) {
                newPath = Path.of(def.replace(".txt", "_encrypted.txt"));
            } else if (mode == 2) {
                newPath = Path.of(def.replace(".txt", "_decrypted.txt"));
            } else if (mode == 3) {
                newPath = Path.of(def.replace(".txt", "_bruteforce_decrypted.txt"));
            } else if (mode == 4) {
                newPath = Path.of(def.replace(".txt", "_static_analysis_decrypted.txt"));
            } else throw new IllegalArgumentException();

            Files.write(newPath, text.getBytes(StandardCharsets.UTF_8));

            System.out.println("Новый файл расположен по следующему пути " + newPath.toString());
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный агрумент для метода " + Object.class.getEnclosingMethod().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
